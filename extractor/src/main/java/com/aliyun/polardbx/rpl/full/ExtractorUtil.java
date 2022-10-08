package com.aliyun.polardbx.rpl.full;

import com.aliyun.polardbx.binlog.canal.binlog.dbms.DBMSAction;
import com.aliyun.polardbx.binlog.canal.binlog.dbms.DBMSRowChange;
import com.aliyun.polardbx.dbmeta.ColumnInfo;
import com.aliyun.polardbx.dbmeta.TableInfo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shicai.xsc 2022/10/8 16:03
 * @since 5.0.0.0
 */
public class ExtractorUtil {

    private final static boolean DEFAULT_NULLABLE = true;
    private final static boolean DEFAULT_SIGNED = true;

    /**
     * @return Object
     */
    public static Object getColumnValue(ResultSet resultSet, String columnName, int type) throws SQLException {
        Object value;
        if (type == Types.TIME || type == Types.DATE || type == Types.TIMESTAMP || isCharType(type)
            || isClobType(type)) {
            value = resultSet.getString(columnName);
        } else if (isBlobType(type)) {
            value = resultSet.getBytes(columnName);
        } else if (Types.BIT == type) {
            // 需要特殊处理tinyint(1)
            value = resultSet.getBytes(columnName);
        } else {
            value = resultSet.getString(columnName);
        }
        // 使用clone对象，避免translator修改了引用
        return value;
    }

    public static boolean isCharType(int sqlType) {
        return (sqlType == Types.CHAR || sqlType == Types.VARCHAR || sqlType == Types.NCHAR
            || sqlType == Types.NVARCHAR);
    }

    public static boolean isClobType(int sqlType) {
        return (sqlType == Types.CLOB || sqlType == Types.LONGVARCHAR || sqlType == Types.NCLOB
            || sqlType == Types.LONGNVARCHAR);
    }

    public static boolean isBlobType(int sqlType) {
        return (sqlType == Types.BLOB || sqlType == Types.BINARY || sqlType == Types.VARBINARY
            || sqlType == Types.LONGVARBINARY);
    }

    public static boolean isNumber(int sqlType) {
        return (sqlType == Types.TINYINT || sqlType == Types.SMALLINT || sqlType == Types.INTEGER
            || sqlType == Types.BIGINT || sqlType == Types.NUMERIC || sqlType == Types.DECIMAL);
    }

    public static RowChangeBuilder buildRowChangeMeta(TableInfo tableInfo, String schema, String tbName,
                                                      DBMSAction action) {
        RowChangeBuilder builder = RowChangeBuilder.createBuilder(schema, tbName, action);
        for (ColumnInfo column : tableInfo.getColumns()) {
            builder.addMetaColumn(column.getName(),
                column.getType(),
                DEFAULT_SIGNED,
                DEFAULT_NULLABLE,
                tableInfo.getPks().contains(column.getName()));
        }
        return builder;
    }

    public static DBMSRowChange buildMessageEvent(RowChangeBuilder builder, TableInfo tableInfo,
                                                  ResultSet resultSet) throws Exception {
        Map<String, Serializable> fieldValueMap = new HashMap<>(tableInfo.getColumns().size());
        for (ColumnInfo column : tableInfo.getColumns()) {
            Object value = ExtractorUtil.getColumnValue(resultSet, column.getName(), column.getType());
            fieldValueMap.put(column.getName(), (Serializable) value);
        }

        builder.getRowDatas().clear();
        builder.addRowData(fieldValueMap);
        return builder.build();
    }

}
