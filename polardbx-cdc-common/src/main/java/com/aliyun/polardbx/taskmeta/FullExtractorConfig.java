package com.aliyun.polardbx.taskmeta;

import lombok.Data;

/**
 * @author shicai.xsc 2022/10/8 15:42
 * @since 5.0.0.0
 */
@Data
public class FullExtractorConfig extends ExtractorConfig {
    private int fetchBatchSize;
    private int parallelCount;
}
