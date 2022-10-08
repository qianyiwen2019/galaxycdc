package com.aliyun.polardbx.dbmeta;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shicai.xsc 2022/10/8 15:07
 * @since 5.0.0.0
 */
@Data
@Slf4j
public class TableInfo {

    private String schema;
    private String name;
    private String createTable;
    private List<String> pks = new ArrayList<>();
    private List<String> uks = new ArrayList<>();
    private List<ColumnInfo> columns = new ArrayList<>();
    private List<String> keyList;
    private List<String> identifyKeyList;
    private String dbShardKey;
    private String tbShardKey;

    public TableInfo(String schema, String name) {
        this.schema = schema;
        this.name = name;
        this.keyList = new ArrayList<>();
    }

    public List<String> getKeyList() {
        if (CollectionUtils.isEmpty(keyList)) {
            // 无主键表
            if (CollectionUtils.isEmpty(pks)) {
                for (ColumnInfo column : columns) {
                    keyList.add(column.getName());
                }
                return keyList;
            } else {
                keyList.addAll(pks);
            }

            if (StringUtils.isNotBlank(dbShardKey)) {
                // use first key in range hash. e.g. CINEMA_UID,TENANT_ID
                String[] s = dbShardKey.split(",");
                if (!keyList.contains(s[0])) {
                    keyList.add(s[0]);
                }
                if (s.length >= 2) {
                    if (!keyList.contains(s[1])) {
                        keyList.add(s[1]);
                    }
                }
            }

            if (StringUtils.isNotBlank(tbShardKey)) {
                // use first key in range hash. e.g. CINEMA_UID,TENANT_ID
                String[] s = tbShardKey.split(",");
                if (!keyList.contains(s[0])) {
                    keyList.add(s[0]);
                }
                if (s.length >= 2) {
                    if (!keyList.contains(s[1])) {
                        keyList.add(s[1]);
                    }
                }
            }
        }
        return keyList;
    }

    public List<String> getIdentifyKeyList() {
        if (CollectionUtils.isEmpty(identifyKeyList)) {
            identifyKeyList = new ArrayList<>(getKeyList());
            for (String uk : uks) {
                if (!identifyKeyList.contains(uk)) {
                    identifyKeyList.add(uk);
                }
            }
        }
        return identifyKeyList;
    }
}