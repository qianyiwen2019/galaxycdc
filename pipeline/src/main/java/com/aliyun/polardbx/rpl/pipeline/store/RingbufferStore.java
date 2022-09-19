package com.aliyun.polardbx.rpl.pipeline.store;

import com.aliyun.polardbx.ThreadPoolUtil;
import com.aliyun.polardbx.rpl.pipeline.BasePipeline;
import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author shicai.xsc 2022/9/16 14:46
 * @since 5.0.0.0
 */
public class RingbufferStore {
    private RingBuffer<MessageEvent> ringBuffer;
    private ExecutorService offerExecutor;
    private BatchEventProcessor<MessageEvent> offerProcessor;
    private MessageEventFactory messageEventFactory;

    private BasePipeline pipeline;

    public RingbufferStore(int bufferSize) {
        messageEventFactory = new MessageEventFactory();
        // create ringBuffer and set ringBuffer eventFactory
        ringBuffer = RingBuffer
            .createSingleProducer(messageEventFactory, bufferSize, new BlockingWaitStrategy());

        RingBufferEventHandler eventHandler = new RingBufferEventHandler();
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        offerProcessor = new BatchEventProcessor<>(ringBuffer, sequenceBarrier, eventHandler);
        ringBuffer.addGatingSequences(offerProcessor.getSequence());
        offerExecutor = ThreadPoolUtil.createExecutorWithFixedNum(1, "RingBufferEventHandler");
        offerExecutor.submit(offerProcessor);
    }

    public void setPipeline(BasePipeline pipeline) {
        this.pipeline = pipeline;
    }

    /**
     * This will be called by EXTRACTOR to write messages to ringBuffer
     */
    public void accept(List<MessageEvent> events) {
        long lo = -1, hi = -1;
        boolean isLowSet = false;
        try {
            for (MessageEvent event : events) {
                while (ringBuffer.remainingCapacity() <= 0) {
                    if (lo != -1 && hi != -1) {
                        ringBuffer.publish(lo, hi);
                        lo = -1;
                        hi = -1;
                        isLowSet = false;
                    } else {
                        Thread.sleep(5);
                    }
                }
                long next = ringBuffer.next();
                if (!isLowSet) {
                    lo = next;
                    isLowSet = true;
                }
                hi = next;
                MessageEvent e = ringBuffer.get(next);
                e.setDbmsEvent(event.getDbmsEvent());
                e.setPosition(event.getPosition());
                e.setXaTransaction(event.getXaTransaction());
                e.setSourceTimestamp(event.getSourceTimestamp());
                e.setExtractTimestamp(event.getExtractTimestamp());
            }
        } catch (Throwable e) {
            System.exit(1);
        } finally {
            if (lo != -1 && hi != -1) {
                ringBuffer.publish(lo, hi);
            }
        }
    }

    private class RingBufferEventHandler implements EventHandler<MessageEvent>, LifecycleAware {

        public RingBufferEventHandler() {
        }

        @Override
        public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception {
            pipeline.accept(event.getDbmsEvent(), endOfBatch);
        }

        @Override
        public void onStart() {
        }

        @Override
        public void onShutdown() {
        }
    }
}
