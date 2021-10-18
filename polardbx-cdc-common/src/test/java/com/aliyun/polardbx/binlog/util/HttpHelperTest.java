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

package com.aliyun.polardbx.binlog.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;

import com.aliyun.polardbx.binlog.CommonMetrics;
import com.google.common.collect.Lists;
import org.apache.http.entity.ContentType;
import org.junit.Test;

public class HttpHelperTest {

    @Test
    public void post() throws IOException, URISyntaxException, InterruptedException {
        List<CommonMetrics> commonMetrics = Lists.newArrayList(CommonMetrics.builder().key("a").type(1).value(100)
                .build(),
            CommonMetrics.builder().key("b").type(2).value(200).build(),
            CommonMetrics.builder().key("c").type(1).value(300).build()
        );

        for (int i = 0; i < 10; i++) {
            PooledHttpHelper.doPost("http://127.0.0.1:3007/cdc/reports", ContentType.APPLICATION_JSON,
                JSON.toJSONString(commonMetrics), 1000);
            TimeUnit.SECONDS.sleep(3);
        }

    }
}