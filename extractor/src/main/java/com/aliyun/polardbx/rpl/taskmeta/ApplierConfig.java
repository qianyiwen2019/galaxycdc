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

package com.aliyun.polardbx.rpl.taskmeta;

import com.aliyun.polardbx.rpl.common.RplConstants;
import lombok.Data;

/**
 * @author shicai.xsc 2020/12/1 11:06
 * @since 5.0.0.0
 */
@Data
public class ApplierConfig {
    protected int mergeBatchSize = 1000;
    protected int sendBatchSize = 100;
    protected int logCommitLevel = RplConstants.LOG_NO_COMMIT;
    protected boolean enableDdl = true;
    protected int maxPoolSize = 256;
    protected int minPoolSize = 64;
    protected int statisticIntervalSec = 5;
    protected int applierType = ApplierType.MERGE.getValue();
    protected HostInfo hostInfo;
}
