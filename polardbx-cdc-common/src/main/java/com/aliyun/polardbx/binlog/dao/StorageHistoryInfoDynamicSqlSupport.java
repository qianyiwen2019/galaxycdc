/*
 *
 * Copyright (c) 2013-2021, Alibaba Group Holding Limited;
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.aliyun.polardbx.binlog.dao;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class StorageHistoryInfoDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-26T21:06:05.223+08:00",
        comments = "Source Table: binlog_storage_history")
    public static final StorageHistoryInfo storageHistoryInfo = new StorageHistoryInfo();

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-26T21:06:05.224+08:00",
        comments = "Source field: binlog_storage_history.id")
    public static final SqlColumn<Long> id = storageHistoryInfo.id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-26T21:06:05.224+08:00",
        comments = "Source field: binlog_storage_history.gmt_created")
    public static final SqlColumn<Date> gmtCreated = storageHistoryInfo.gmtCreated;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-26T21:06:05.224+08:00",
        comments = "Source field: binlog_storage_history.gmt_modified")
    public static final SqlColumn<Date> gmtModified = storageHistoryInfo.gmtModified;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-26T21:06:05.224+08:00",
        comments = "Source field: binlog_storage_history.tso")
    public static final SqlColumn<String> tso = storageHistoryInfo.tso;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-26T21:06:05.224+08:00",
        comments = "Source field: binlog_storage_history.status")
    public static final SqlColumn<Integer> status = storageHistoryInfo.status;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-26T21:06:05.225+08:00",
        comments = "Source field: binlog_storage_history.instruction_id")
    public static final SqlColumn<String> instructionId = storageHistoryInfo.instructionId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-26T21:06:05.225+08:00",
        comments = "Source field: binlog_storage_history.storage_content")
    public static final SqlColumn<String> storageContent = storageHistoryInfo.storageContent;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-26T21:06:05.223+08:00",
        comments = "Source Table: binlog_storage_history")
    public static final class StorageHistoryInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Date> gmtCreated = column("gmt_created", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> gmtModified = column("gmt_modified", JDBCType.TIMESTAMP);

        public final SqlColumn<String> tso = column("tso", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<String> instructionId = column("instruction_id", JDBCType.VARCHAR);

        public final SqlColumn<String> storageContent = column("storage_content", JDBCType.LONGVARCHAR);

        public StorageHistoryInfo() {
            super("binlog_storage_history");
        }
    }
}