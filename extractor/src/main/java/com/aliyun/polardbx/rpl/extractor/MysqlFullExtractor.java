package com.aliyun.polardbx.rpl.extractor;

import com.aliyun.polardbx.DataSourceUtil;
import com.aliyun.polardbx.ThreadPoolUtil;
import com.aliyun.polardbx.rpl.pipeline.store.RingbufferStore;
import com.aliyun.polardbx.taskmeta.FullExtractorConfig;
import com.aliyun.polardbx.taskmeta.HostInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author shicai.xsc 2022/10/8 14:50
 * @since 5.0.0.0
 */
@Slf4j
@Data
public class MysqlFullExtractor extends BaseExtractor {
    private RingbufferStore store;
    private List<String> doTbs;
    protected HostInfo hostInfo;
    protected Map<String, DataSource> dataSourceMap;
    protected List<MysqlFullProcessor> runningProcessors;
    protected List<Future> runningCountTasks;
    protected List<Future> runningTasks;
    protected FullExtractorConfig extractorConfig;
    protected ExecutorService executorService;

    public MysqlFullExtractor(FullExtractorConfig extractorConfig, HostInfo hostInfo) {
        super(extractorConfig);
        this.extractorName = "MysqlFullExtractor";
        this.extractorConfig = extractorConfig;
        this.hostInfo = hostInfo;
    }

    @Override
    public boolean init() throws Exception {
        try {
            log.info("initializing {}", extractorName);
            super.init();
            dataSourceMap = new HashMap<>();

            // TODO：add filters for doDbs, support multiple dbs

            DataSource dataSource =
                DataSourceUtil.createDruidMySqlDataSource(hostInfo.isUsePolarxPoolCN(), hostInfo.getHost(),
                    hostInfo.getPort(),
                    hostInfo.getSchema(),
                    hostInfo.getUserName(),
                    hostInfo.getPassword(),
                    "",
                    1,
                    extractorConfig.getParallelCount(),
                    null,
                    null);
            dataSourceMap.put(hostInfo.getSchema(), dataSource);

            executorService = ThreadPoolUtil.createExecutorWithFixedNum(extractorConfig.getParallelCount(),
                extractorName);

            runningProcessors = new ArrayList<>();
            runningTasks = new ArrayList<>();
        } catch (Throwable e) {
            log.error("failed to init {}", extractorName, e);
            throw e;
        }
        return true;
    }

    @Override
    public void start() throws Exception {
        log.info("starting {}", extractorName);

        for (String tbName : doTbs) {
            MysqlFullProcessor processor = new MysqlFullProcessor();
            processor.setExtractorConfig(extractorConfig);
            processor.setDataSource(dataSourceMap.get(hostInfo.getSchema()));
            processor.setSchema(hostInfo.getSchema());
            processor.setTbName(tbName);
            processor.setHostInfo(hostInfo);
            processor.setStore(store);
            Future future = executorService.submit(() -> processor.start());
            runningTasks.add(future);
            runningProcessors.add(processor);
            log.info("{} submit for schema:{}, tbName:{}", extractorName, hostInfo.getSchema(), tbName);
        }

        for (Future future : runningTasks) {
            future.get();
        }

        for (MysqlFullProcessor processor : runningProcessors) {
            Future future = executorService.submit(() -> processor.start());
            runningTasks.add(future);
        }
    }

    @Override
    public boolean isDone() {
        boolean allDone = true;
        for (Future future : runningTasks) {
            allDone &= future.isDone();
        }
        return allDone;
    }
}
