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

package com.aliyun.polardbx.rpl.pipeline;

import com.aliyun.polardbx.binlog.canal.binlog.dbms.DBMSEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shicai.xsc 2020/11/30 14:59
 * @since 5.0.0.0
 */
@Slf4j
public class SerialPipeline extends BasePipeline {

    private List<DBMSEvent> events;

    public SerialPipeline(int bufferSize) {
        events = new ArrayList<>(bufferSize);
    }

    @Override
    public void accept(DBMSEvent event, boolean endOfBatch) {
        events.add(event);

        if (!endOfBatch) {
            return;
        }

        consume(events);
    }

    public void consume(List<DBMSEvent> events) {
        applier.apply(events);
        events.clear();
    }
}
