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

import com.aliyun.polardbx.binlog.domain.po.BinlogLogicMetaHistory;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.binlogLogicMetaHistory;
import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.dbName;
import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.ddl;
import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.gmtCreated;
import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.gmtModified;
import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.id;
import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.tableName;
import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.topology;
import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.tso;
import static com.aliyun.polardbx.binlog.dao.BinlogLogicMetaHistoryDynamicSqlSupport.type;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface BinlogLogicMetaHistoryMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.222+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    BasicColumn[] selectList =
        BasicColumn.columnList(id, gmtCreated, gmtModified, tso, dbName, tableName, type, ddl, topology);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.205+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.207+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.207+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<BinlogLogicMetaHistory> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.209+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<BinlogLogicMetaHistory> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.211+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ConstructorArgs({
        @Arg(column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
        @Arg(column = "gmt_created", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Arg(column = "gmt_modified", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Arg(column = "tso", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Arg(column = "db_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Arg(column = "table_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Arg(column = "type", javaType = Byte.class, jdbcType = JdbcType.TINYINT),
        @Arg(column = "ddl", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR),
        @Arg(column = "topology", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR)
    })
    Optional<BinlogLogicMetaHistory> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.214+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ConstructorArgs({
        @Arg(column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
        @Arg(column = "gmt_created", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Arg(column = "gmt_modified", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Arg(column = "tso", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Arg(column = "db_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Arg(column = "table_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Arg(column = "type", javaType = Byte.class, jdbcType = JdbcType.TINYINT),
        @Arg(column = "ddl", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR),
        @Arg(column = "topology", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR)
    })
    List<BinlogLogicMetaHistory> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.215+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.216+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, binlogLogicMetaHistory, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.216+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, binlogLogicMetaHistory, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.217+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.217+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default int insert(BinlogLogicMetaHistory record) {
        return MyBatis3Utils.insert(this::insert, record, binlogLogicMetaHistory, c ->
            c.map(id).toProperty("id")
                .map(gmtCreated).toProperty("gmtCreated")
                .map(gmtModified).toProperty("gmtModified")
                .map(tso).toProperty("tso")
                .map(dbName).toProperty("dbName")
                .map(tableName).toProperty("tableName")
                .map(type).toProperty("type")
                .map(ddl).toProperty("ddl")
                .map(topology).toProperty("topology")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.219+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default int insertMultiple(Collection<BinlogLogicMetaHistory> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, binlogLogicMetaHistory, c ->
            c.map(id).toProperty("id")
                .map(gmtCreated).toProperty("gmtCreated")
                .map(gmtModified).toProperty("gmtModified")
                .map(tso).toProperty("tso")
                .map(dbName).toProperty("dbName")
                .map(tableName).toProperty("tableName")
                .map(type).toProperty("type")
                .map(ddl).toProperty("ddl")
                .map(topology).toProperty("topology")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.22+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default int insertSelective(BinlogLogicMetaHistory record) {
        return MyBatis3Utils.insert(this::insert, record, binlogLogicMetaHistory, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
                .map(gmtCreated).toPropertyWhenPresent("gmtCreated", record::getGmtCreated)
                .map(gmtModified).toPropertyWhenPresent("gmtModified", record::getGmtModified)
                .map(tso).toPropertyWhenPresent("tso", record::getTso)
                .map(dbName).toPropertyWhenPresent("dbName", record::getDbName)
                .map(tableName).toPropertyWhenPresent("tableName", record::getTableName)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .map(ddl).toPropertyWhenPresent("ddl", record::getDdl)
                .map(topology).toPropertyWhenPresent("topology", record::getTopology)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.223+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default Optional<BinlogLogicMetaHistory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, binlogLogicMetaHistory, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.224+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default List<BinlogLogicMetaHistory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, binlogLogicMetaHistory, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.224+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default List<BinlogLogicMetaHistory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, binlogLogicMetaHistory, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.225+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default Optional<BinlogLogicMetaHistory> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.226+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, binlogLogicMetaHistory, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.227+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    static UpdateDSL<UpdateModel> updateAllColumns(BinlogLogicMetaHistory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
            .set(gmtCreated).equalTo(record::getGmtCreated)
            .set(gmtModified).equalTo(record::getGmtModified)
            .set(tso).equalTo(record::getTso)
            .set(dbName).equalTo(record::getDbName)
            .set(tableName).equalTo(record::getTableName)
            .set(type).equalTo(record::getType)
            .set(ddl).equalTo(record::getDdl)
            .set(topology).equalTo(record::getTopology);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.228+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(BinlogLogicMetaHistory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
            .set(gmtCreated).equalToWhenPresent(record::getGmtCreated)
            .set(gmtModified).equalToWhenPresent(record::getGmtModified)
            .set(tso).equalToWhenPresent(record::getTso)
            .set(dbName).equalToWhenPresent(record::getDbName)
            .set(tableName).equalToWhenPresent(record::getTableName)
            .set(type).equalToWhenPresent(record::getType)
            .set(ddl).equalToWhenPresent(record::getDdl)
            .set(topology).equalToWhenPresent(record::getTopology);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.23+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default int updateByPrimaryKey(BinlogLogicMetaHistory record) {
        return update(c ->
            c.set(gmtCreated).equalTo(record::getGmtCreated)
                .set(gmtModified).equalTo(record::getGmtModified)
                .set(tso).equalTo(record::getTso)
                .set(dbName).equalTo(record::getDbName)
                .set(tableName).equalTo(record::getTableName)
                .set(type).equalTo(record::getType)
                .set(ddl).equalTo(record::getDdl)
                .set(topology).equalTo(record::getTopology)
                .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-03-30T17:33:06.231+08:00",
        comments = "Source Table: binlog_logic_meta_history")
    default int updateByPrimaryKeySelective(BinlogLogicMetaHistory record) {
        return update(c ->
            c.set(gmtCreated).equalToWhenPresent(record::getGmtCreated)
                .set(gmtModified).equalToWhenPresent(record::getGmtModified)
                .set(tso).equalToWhenPresent(record::getTso)
                .set(dbName).equalToWhenPresent(record::getDbName)
                .set(tableName).equalToWhenPresent(record::getTableName)
                .set(type).equalToWhenPresent(record::getType)
                .set(ddl).equalToWhenPresent(record::getDdl)
                .set(topology).equalToWhenPresent(record::getTopology)
                .where(id, isEqualTo(record::getId))
        );
    }
}