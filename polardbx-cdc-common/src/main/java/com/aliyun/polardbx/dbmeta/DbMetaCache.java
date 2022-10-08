package com.aliyun.polardbx.dbmeta;

import com.aliyun.polardbx.DataSourceUtil;
import com.aliyun.polardbx.taskmeta.HostInfo;
import com.aliyun.polardbx.taskmeta.HostType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shicai.xsc 2022/10/8 15:09
 * @since 5.0.0.0
 */
@Slf4j
public class DbMetaCache {
    private HostInfo hostInfo;
    private Map<String, DataSource> dataSources = new HashMap<>();
    private Map<String, TableInfo> tableInfos = new HashMap<>();

    private int minPoolSize = 1;
    private int maxPoolSize = 60;
    private final static String POLARX_DEFAULT_SCHEMA = "polardbx";
    private final static String SET_POLARX_SERVER_ID = "set polardbx_server_id=%d";

    public DbMetaCache(HostInfo hostInfo, int maxPoolSize) {
        this.hostInfo = hostInfo;
        this.maxPoolSize = maxPoolSize;
    }

    public DataSource getDataSource(String schema) throws Exception {
        try {
            if (!dataSources.containsKey(schema)) {
                List<String> connectionInitSqls = new ArrayList<>();
                String setServerIdSql =
                    String.format(SET_POLARX_SERVER_ID, hostInfo.getServerId());
                connectionInitSqls.add(setServerIdSql);
                DataSource dataSource = DataSourceUtil.createDruidMySqlDataSource(hostInfo.isUsePolarxPoolCN(),
                    hostInfo.getHost(),
                    hostInfo.getPort(),
                    schema,
                    hostInfo.getUserName(),
                    hostInfo.getPassword(),
                    "",
                    minPoolSize,
                    maxPoolSize,
                    null,
                    connectionInitSqls);
                dataSources.put(schema, dataSource);
            }
            return dataSources.get(schema);
        } catch (Exception e) {
            log.error("failed in getDataSource, host: {}, port: {}, schema: {}",
                hostInfo.getHost(),
                hostInfo.getPort(),
                schema);
            throw e;
        }
    }

    public DataSource getDataSource() throws Exception {
        if (StringUtils.isNotBlank(hostInfo.getSchema())) {
            return getDataSource(hostInfo.getSchema());
        }
        return getDefaultDataSource();
    }

    public DataSource getDefaultDataSource() throws Exception {
        String defaultSchema = hostInfo.getType() == HostType.POLARX2 ? POLARX_DEFAULT_SCHEMA : "";
        return getDataSource(defaultSchema);
    }

    public List<String> getDatabases() throws Throwable {
        try {
            DataSource dataSource = getDefaultDataSource();
            return DbMetaManager.getDatabases(dataSource);
        } catch (Throwable e) {
            log.error("failed in getDatabases, host: {}, port: {}", hostInfo.getHost(), hostInfo.getPort());
            throw e;
        }
    }

    public void refreshTableInfo(String schema, String tbName) {
        String key = schema + "." + tbName;
        tableInfos.remove(key);
    }

    public TableInfo getTableInfo(String schema, String tbName) throws Throwable {
        String key = schema + "." + tbName;
        try {
            TableInfo dstTableInfo = tableInfos.get(key);
            if (dstTableInfo == null) {
                DataSource dataSource = getDataSource(schema);
                dstTableInfo = DbMetaManager.getTableInfo(dataSource, schema, tbName, hostInfo.getType());
                tableInfos.put(key, dstTableInfo);
            }
        } catch (Throwable e) {
            log.error("failed in getTableInfo, host: {}, port: {}, schema: {}, tbName: {}",
                hostInfo.getHost(), hostInfo.getPort(), schema, tbName);
            throw e;
        }
        return tableInfos.get(key);
    }

    public List<String> getTables(String schema) throws Throwable {
        try {
            DataSource dataSource = getDataSource(schema);
            return DbMetaManager.getTables(dataSource);
        } catch (Throwable e) {
            log.error("failed in getTables, host: {}, port: {}, schema: {}",
                hostInfo.getHost(),
                hostInfo.getPort(),
                schema);
            throw e;
        }
    }
}
