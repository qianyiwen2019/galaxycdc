package com.aliyun.polardbx.rpl;

import com.aliyun.polardbx.binlog.canal.core.model.BinlogPosition;
import com.aliyun.polardbx.rpl.extractor.BaseExtractor;
import com.aliyun.polardbx.rpl.extractor.CanalBinlogExtractor;
import com.aliyun.polardbx.rpl.filter.BaseFilter;
import com.aliyun.polardbx.rpl.pipeline.BasePipeline;
import com.aliyun.polardbx.rpl.pipeline.SerialPipeline;
import com.aliyun.polardbx.rpl.taskmeta.ExtractorConfig;
import com.aliyun.polardbx.rpl.taskmeta.HostInfo;
import com.aliyun.polardbx.rpl.taskmeta.PipelineConfig;
import org.junit.Test;

/**
 * @author shicai.xsc 2022/9/15 16:16
 * @since 5.0.0.0
 */
public class ExtractorTest {
    @Test
    public void test() throws Throwable {
        PipelineConfig pipeLineConfig = new PipelineConfig();

        ExtractorConfig extractorConfig = new ExtractorConfig();
        HostInfo srcHostInfo = new HostInfo();
        srcHostInfo.setHost("127.0.0.1");
        srcHostInfo.setPort(3306);
        srcHostInfo.setUserName("root");
        srcHostInfo.setPassword("123456");
        HostInfo metaHostInfo = srcHostInfo;
        BinlogPosition position = new BinlogPosition("binlog.000047", 304, 1, 0);
        BaseFilter filter = new BaseFilter();

        BaseExtractor extractor =
            new CanalBinlogExtractor(extractorConfig, srcHostInfo, metaHostInfo, position, filter);

        BasePipeline pipeline = new SerialPipeline(pipeLineConfig, extractor, null);
        extractor.setPipeline(pipeline);

        pipeline.init();
        pipeline.start();

        Thread.sleep(10000000);
    }
}
