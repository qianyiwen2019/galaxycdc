package com.aliyun.polardbx.dbmeta;

import lombok.Data;

/**
 * @author shicai.xsc 2022/10/8 15:01
 * @since 5.0.0.0
 */
@Data
public class ColumnInfo {

    private String name;
    // type value refers to
    // "Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/src.zip!/java/sql/Types.java"
    private int type;
    private String javaCharset;
    private boolean nullable;

    public ColumnInfo(String name, int type, String javaCharset, boolean nullable) {
        this.name = name;
        this.type = type;
        this.javaCharset = javaCharset;
        this.nullable = nullable;
    }
}