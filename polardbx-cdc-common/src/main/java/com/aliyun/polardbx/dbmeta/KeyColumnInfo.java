package com.aliyun.polardbx.dbmeta;

import lombok.Data;

/**
 * @author shicai.xsc 2022/10/8 15:14
 * @since 5.0.0.0
 */
@Data
public class KeyColumnInfo {

    private String table;
    private String keyName;
    private String columnName;
    private int nonUnique;
    private int seqInIndex;

    public KeyColumnInfo(String table, String keyName, String columnName, int nonUnique, int seqInIndex) {
        this.table = table;
        this.keyName = keyName;
        this.columnName = columnName;
        this.nonUnique = nonUnique;
        this.seqInIndex = seqInIndex;
    }
}
