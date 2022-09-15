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

import java.util.List;

import com.aliyun.polardbx.rpl.applier.BaseApplier;
import com.aliyun.polardbx.rpl.extractor.BaseExtractor;
import com.aliyun.polardbx.rpl.taskmeta.PipelineConfig;

import lombok.Data;

/**
 * @author shicai.xsc 2020/11/30 15:00
 * @since 5.0.0.0
 */
@Data
public abstract class BasePipeline {
    protected BaseExtractor extractor;
    protected BaseApplier applier;
    protected PipelineConfig pipeLineConfig;

    public abstract boolean init();

    public abstract void start();

    public abstract void stop();

    public abstract boolean checkDone();

    public abstract void writeRingbuffer(List<MessageEvent> events);



//    public abstract void apply(List<DBMSEvent> messages);
//
//    public abstract void tranApply(List<Transaction> transactions);
}
