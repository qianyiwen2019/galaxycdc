package com.aliyun.polardbx.rpl.extractor;

import com.aliyun.polardbx.DataSourceUtil;
import com.aliyun.polardbx.binlog.canal.binlog.dbms.DBMSAction;
import com.aliyun.polardbx.binlog.canal.binlog.dbms.DBMSEvent;
import com.aliyun.polardbx.binlog.canal.binlog.dbms.DBMSRowChange;
import com.aliyun.polardbx.dbmeta.ColumnInfo;
import com.aliyun.polardbx.dbmeta.DbMetaManager;
import com.aliyun.polardbx.dbmeta.TableInfo;
import com.aliyun.polardbx.rpl.full.ExtractorUtil;
import com.aliyun.polardbx.rpl.full.RowChangeBuilder;
import com.aliyun.polardbx.rpl.pipeline.store.MessageEvent;
import com.aliyun.polardbx.rpl.pipeline.store.RingbufferStore;
import com.aliyun.polardbx.taskmeta.FullExtractorConfig;
import com.aliyun.polardbx.taskmeta.HostInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author shicai.xsc 2022/10/8 14:50
 * @since 5.0.0.0
 */
@Slf4j
@Data
public class MysqlFullProcessor {
    private DataSource dataSource;
    private String schema;
    private String tbName;
    private String fullTableName;
    private TableInfo tableInfo;
    private HostInfo hostInfo;
    private String orderKey;
    private Object orderKeyStart;
    private FullExtractorConfig extractorConfig;
    private RingbufferStore store;

    public void start() {
        try {
            tableInfo = DbMetaManager.getTableInfo(dataSource, schema, tbName, hostInfo.getType());
            orderKey = null;
            if (tableInfo.getPks() != null && tableInfo.getPks().size() != 0) {
                orderKey = tableInfo.getPks().get(0);
            } else if (tableInfo.getUks() != null && tableInfo.getUks().size() != 0) {
                for (ColumnInfo column : tableInfo.getColumns()) {
                    if (column.getName().equals(tableInfo.getUks().get(0))) {
                        if (!column.isNullable()) {
                            orderKey = tableInfo.getUks().get(0);
                        }
                        break;
                    }
                }
            }
            if (orderKey == null) {
                // 暂不支持不含主键或非空uk的表
                log.error("can't find orderKey for schema:{}, tbName:{}", schema, tbName);
                return;
            }

//            RplDbFullPosition fullPosition =
//                DbTaskMetaManager.getDbFullPosition(TaskContext.getInstance().getTaskId(), fullTableName);
//            if (fullPosition.getFinished() == RplConstants.FINISH) {
//                log.info("full copy done, position is finished. schema:{}, tbName:{}", schema, tbName);
//                return;
//            }
//            orderKeyStart = null;
//            if (StringUtils.isBlank(fullPosition.getPosition())) {
//                orderKeyStart = getMinOrderKey();
//            } else {
//                orderKeyStart = fullPosition.getPosition();
//            }

            orderKeyStart = getMinOrderKey();

            if (orderKeyStart == null) {
                if (getTotalCount() == 0) {
//                    updateDbFullPosition(fullTableName, 0, null, RplConstants.FINISH);
                    log.info("full transfer done, 0 record in table, schema:{}, tbName:{}", schema, tbName);
                } else {
                    log.error("no orderKeyStart found, schema:{}, tbName:{}, processor exits", schema, tbName);
                }
                return;
            }
            fetchData();
        } catch (Throwable e) {
            log.error("failed to start com.aliyun.polardbx.extractor", e);
//            MonitorManager.getInstance().triggerAlarmSync(MonitorType.IMPORT_FULL_ERROR,
//                TaskContext.getInstance().getTaskId(), e.getMessage());
        }
    }

    private void fetchData() throws Throwable {
        log.info("starting fetching Data, tbName:{}", tbName);

        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;

        String fetchSql = getFetchSql();

        try {
            conn = dataSource.getConnection();
            // 为了设置fetchSize,必须设置为false
            conn.setAutoCommit(false);
            RowChangeBuilder builder = ExtractorUtil.buildRowChangeMeta(tableInfo, schema, tbName, DBMSAction.INSERT);
            List<DBMSEvent> events = new ArrayList<>(extractorConfig.getFetchBatchSize());
            // prepared statement
            stmt = conn.prepareStatement(fetchSql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt.setFetchSize(Integer.MIN_VALUE);
            stmt.setObject(1, orderKeyStart);

            // execute fetch sql
            rs = stmt.executeQuery();
            log.info("fetching data, tbName:{}, orderKeyStart:{}", tbName, orderKeyStart);

            // create DBMSRowChange message
            while (rs.next()) {
                events.add(ExtractorUtil.buildMessageEvent(builder, tableInfo, rs));
                if (extractorConfig.getFetchBatchSize() == events.size()) {
                    transfer(events);
                    DBMSRowChange rowChange = (DBMSRowChange) events.get(events.size() - 1);
                    orderKeyStart = rowChange.getRowValue(1, orderKey);
//                    String position = StringUtils2.safeToString(orderKeyStart);
//                    updateDbFullPosition(fullTableName, extractorConfig.getFetchBatchSize(), position,
//                        RplConstants.NOT_FINISH);
//                    StatisticalProxy.getInstance().heartbeat();
                    events.clear();
                }
            }
            int resiSize = events.size();
            if (resiSize > 0) {
                transfer(events);
            }
//            updateDbFullPosition(fullTableName, resiSize, null, RplConstants.FINISH);
            log.info("fetching data done, dbName:{} tbName:{}, last orderKeyValue:{}", schema, tbName, orderKeyStart);
        } catch (Throwable e) {
            log.error("fetching data failed, schema:{}, tbName:{}, sql:{}", schema, tbName,
                fetchSql, e);
            throw e;
        } finally {
            DataSourceUtil.closeQuery(rs, stmt, conn);
        }
    }

    private void transfer(List<DBMSEvent> events) {
        // TODO：move the transfer logic to RingbufferStore
        // TODO: update the position in applier instead of pipeline
        List<MessageEvent> datas = new ArrayList<>(events.size());
        for (DBMSEvent event : events) {
            MessageEvent e = new MessageEvent();
            e.setDbmsEvent(event);
            datas.add(e);
        }

        store.accept(datas);

        // update orderKeyStart
        if (events.size() > 0) {
            DBMSRowChange rowChange = (DBMSRowChange) events.get(events.size() - 1);
            orderKeyStart = rowChange.getRowValue(1, orderKey);
        }
    }

    private String getFetchSql() {
        StringBuilder nameSqlSb = new StringBuilder();
        Iterator<ColumnInfo> it = tableInfo.getColumns().iterator();
        while (it.hasNext()) {
            ColumnInfo column = it.next();
            nameSqlSb.append("`");
            nameSqlSb.append(column.getName());
            nameSqlSb.append("`");
            if (it.hasNext()) {
                nameSqlSb.append(",");
            }
        }
        return String
            .format("select %s from `%s` where `%s` >= ? order by `%s`", nameSqlSb, tbName, orderKey, orderKey);
    }

    private Object getMinOrderKey() throws SQLException {
        String sql = String.format("select min(`%s`) from `%s`", orderKey, tbName);
        return getMetaInfo(sql);
    }

    private long getTotalCount() throws SQLException {
        String sql = String.format("select count(1) from `%s`", tbName);
        Object res = getMetaInfo(sql);
        if (res == null) {
            return -1;
        }
        return Long.valueOf(String.valueOf(res));
    }

    private Object getMetaInfo(String sql) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getObject(1);
            }
        } catch (SQLException e) {
            log.error("failed in getMetaInfo: {}", sql, e);
            throw e;
        } finally {
            DataSourceUtil.closeQuery(rs, stmt, conn);
        }

        return null;
    }
}
