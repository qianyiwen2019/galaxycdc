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
import com.aliyun.polardbx.rpl.applier.BaseApplier;
import com.aliyun.polardbx.rpl.common.ThreadPoolUtil;
import com.aliyun.polardbx.rpl.extractor.BaseExtractor;
import com.aliyun.polardbx.rpl.taskmeta.PipelineConfig;
import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author shicai.xsc 2020/11/30 14:59
 * @since 5.0.0.0
 */
@Slf4j
public class SerialPipeline extends BasePipeline {

    private RingBuffer<MessageEvent> msgRingBuffer;
    private ExecutorService offerExecutor;
    private BatchEventProcessor<MessageEvent> offerProcessor;
    private MessageEventFactory messageEventFactory;
    private String position;
    private AtomicLong lastStatisticsTime = new AtomicLong(0);
    private AtomicLong periodBatchSize = new AtomicLong(0);
    private AtomicLong periodBatchCount = new AtomicLong(0);
    private AtomicLong periodBatchCost = new AtomicLong(0);
    private final long LOG_STAT_PERIOD_MILLS = 10000;

    public SerialPipeline(PipelineConfig pipelineConfig, BaseExtractor extractor, BaseApplier applier) {
        this.pipeLineConfig = pipelineConfig;
        this.extractor = extractor;
        this.applier = applier;
    }

    @Override
    public boolean init() {
        try {
            messageEventFactory = new MessageEventFactory();
            offerExecutor = ThreadPoolUtil.createExecutorWithFixedNum(1, "applier");
            // create ringBuffer and set ringBuffer eventFactory
            msgRingBuffer = RingBuffer
                .createSingleProducer(messageEventFactory, pipeLineConfig.getBufferSize(),
                    new BlockingWaitStrategy());
            SequenceBarrier sequenceBarrier = msgRingBuffer.newBarrier();
            EventHandler eventHandler = new RingBufferEventHandler(pipeLineConfig.getBufferSize());
            offerProcessor = new BatchEventProcessor<>(msgRingBuffer, sequenceBarrier, eventHandler);
            msgRingBuffer.addGatingSequences(offerProcessor.getSequence());
            return true;
        } catch (Throwable e) {
            log.error("SerialPipeline init failed", e);
            return false;
        }
    }

    @Override
    public void start() {
        try {
            // start extractor thread which will call EXTRACTOR to extract events from
            // binlog and write it to ringBuffer
            log.info("extractor starting");
            extractor.start();
            log.info("extractor started");

            // start offerProcessor thread which will call APPLIER to consume events from
            // ringBuffer
            offerExecutor.submit(offerProcessor);
        } catch (Throwable e) {
            log.error("start extractor occur error", e);
            Runtime.getRuntime().halt(1);
        }
    }

    @Override
    public void stop() {
        extractor.stop();
    }

    @Override
    public boolean checkDone() {
        return extractor.isDone();
    }

    /**
     * This will be called by EXTRACTOR to write messages to ringBuffer
     */
    @Override
    public void writeRingbuffer(List<MessageEvent> events) {
        long lo = -1, hi = -1;
        boolean isLowSet = false;
        try {
            for (MessageEvent event : events) {
                while (msgRingBuffer.remainingCapacity() <= 0) {
                    if (lo != -1 && hi != -1) {
                        msgRingBuffer.publish(lo, hi);
                        lo = -1;
                        hi = -1;
                        isLowSet = false;
                    } else {
                        Thread.sleep(5);
                    }
                }
                long next = msgRingBuffer.next();
                if (!isLowSet) {
                    lo = next;
                    isLowSet = true;
                }
                hi = next;
                MessageEvent e = msgRingBuffer.get(next);
                e.setDbmsEvent(event.getDbmsEvent());
                e.setPosition(event.getPosition());
                e.setXaTransaction(event.getXaTransaction());
                e.setSourceTimestamp(event.getSourceTimestamp());
                e.setExtractTimestamp(event.getExtractTimestamp());
            }
        } catch (Throwable e) {
            log.error("writeRingBuffer exception ", e);
            System.exit(1);
        } finally {
            if (lo != -1 && hi != -1) {
                msgRingBuffer.publish(lo, hi);
            }
        }
    }

    /**
     * RingBufferEventHandler, this will call APPLIER to consume ringBuffer messages
     */
    private class RingBufferEventHandler implements EventHandler<MessageEvent>, LifecycleAware {

        private List<DBMSEvent> eventBatch;

        public RingBufferEventHandler(int batchSize) {
            eventBatch = new ArrayList<>(batchSize / 2);
        }

        @Override
        public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println(event.toString());
        }

        @Override
        public void onStart() {
        }

        @Override
        public void onShutdown() {
        }
    }
}
