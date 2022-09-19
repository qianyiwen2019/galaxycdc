package com.aliyun.polardbx.rpl;

import com.aliyun.polardbx.binlog.canal.core.model.BinlogPosition;
import com.aliyun.polardbx.rpl.applier.BaseApplier;
import com.aliyun.polardbx.rpl.extractor.BaseExtractor;
import com.aliyun.polardbx.rpl.extractor.CanalBinlogExtractor;
import com.aliyun.polardbx.rpl.filter.BaseFilter;
import com.aliyun.polardbx.rpl.pipeline.BasePipeline;
import com.aliyun.polardbx.rpl.pipeline.SerialPipeline;
import com.aliyun.polardbx.rpl.pipeline.store.RingbufferStore;
import com.aliyun.polardbx.taskmeta.ExtractorConfig;
import com.aliyun.polardbx.taskmeta.HostInfo;

/**
 * @author shicai.xsc 2022/9/19 11:16
 * @since 5.0.0.0
 */
public class TaskRunner {

    public static void main(String[] args) throws Exception {
        TaskRunner me = new TaskRunner();

        // extractor
        BaseExtractor extractor = me.getExtractor();

        // store
        RingbufferStore store = new RingbufferStore(4096);

        // pipeline
        BasePipeline pipeline = new SerialPipeline(2048);

        // applier
        BaseApplier applier = new BaseApplier();

        extractor.setStore(store);
        store.setPipeline(pipeline);
        pipeline.setApplier(applier);

        // start
        extractor.start();

        while (extractor.isRunning()) {
            Thread.sleep(1000);
        }
    }

    private BaseExtractor getExtractor() {
        ExtractorConfig extractorConfig = new ExtractorConfig();
        HostInfo srcHostInfo = new HostInfo();
        srcHostInfo.setHost("127.0.0.1");
        srcHostInfo.setPort(3306);
        srcHostInfo.setUserName("root");
        srcHostInfo.setPassword("123456");
        HostInfo metaHostInfo = srcHostInfo;
        BinlogPosition position = new BinlogPosition("binlog.000047", 304, 1, 0);
        BaseFilter filter = new BaseFilter();

        return new CanalBinlogExtractor(extractorConfig, srcHostInfo, metaHostInfo, position, filter);
    }
}
