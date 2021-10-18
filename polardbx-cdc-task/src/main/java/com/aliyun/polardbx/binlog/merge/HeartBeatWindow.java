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

package com.aliyun.polardbx.binlog.merge;

import com.aliyun.polardbx.binlog.CommonUtils;
import com.aliyun.polardbx.binlog.error.PolardbxException;
import com.aliyun.polardbx.binlog.protocol.TxnToken;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ziyang.lb
 **/
public class HeartBeatWindow {

    private static final AtomicLong SEQ = new AtomicLong(0);

    private final String txnId;
    private final String actualTso;
    private final long snapshotSeq;
    private final long seq;
    private final int expectTokenSize;
    private final HashMap<String, TxnToken> heartbeatTokens;
    private boolean forceComplete;

    public HeartBeatWindow(String txnId, String actualTso, long snapshotSeq, int expectTokenSize) {
        if (snapshotSeq <= 0) {
            throw new PolardbxException("invalid snapshot seq :" + snapshotSeq);
        }
        this.txnId = txnId;
        this.actualTso = actualTso;
        this.snapshotSeq = snapshotSeq;
        this.expectTokenSize = expectTokenSize;
        this.seq = SEQ.incrementAndGet();
        this.heartbeatTokens = new HashMap<>();
    }

    public void addHeartbeatToken(String sourceId, TxnToken token) {
        if (heartbeatTokens.containsKey(sourceId)) {
            throw new PolardbxException("Duplicate heartbeat token for merge source " + sourceId);
        }

        heartbeatTokens.put(sourceId, token);
    }

    public boolean isSameWindow(TxnToken token) {
        // 对tso和txnId进行双重验证
        String otherActualTso = CommonUtils.getActualTso(token.getTso());
        return StringUtils.equals(actualTso, otherActualTso) && txnId.equals(token.getTxnId());
    }

    public void forceComplete() {
        forceComplete = true;
    }

    public boolean isComplete() {
        return forceComplete || heartbeatTokens.size() == expectTokenSize;
    }

    public String getActualTso() {
        return actualTso;
    }

    public Collection<TxnToken> getAllTokens() {
        return heartbeatTokens.values();
    }

    public long getSeq() {
        return seq;
    }

    public long getSnapshotSeq() {
        return snapshotSeq;
    }

    public boolean isForceComplete() {
        return forceComplete;
    }

    @Override
    public String toString() {
        return "HeartBeatWindow{" + "txnId='" + txnId + '\'' + ", actualTso=" + actualTso + ", seq=" + seq
            + ", expectTokenSize=" + expectTokenSize + ", heartbeatTokens=" + heartbeatTokens + '}';
    }
}
