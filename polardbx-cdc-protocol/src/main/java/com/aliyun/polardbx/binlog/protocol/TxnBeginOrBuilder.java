// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TxnStream.proto

package com.aliyun.polardbx.binlog.protocol;

public interface TxnBeginOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.aliyun.polardbx.binlog.protocol.TxnBegin)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.aliyun.polardbx.binlog.protocol.TxnToken txnToken = 1;</code>
   * @return Whether the txnToken field is set.
   */
  boolean hasTxnToken();
  /**
   * <code>.com.aliyun.polardbx.binlog.protocol.TxnToken txnToken = 1;</code>
   * @return The txnToken.
   */
  com.aliyun.polardbx.binlog.protocol.TxnToken getTxnToken();
  /**
   * <code>.com.aliyun.polardbx.binlog.protocol.TxnToken txnToken = 1;</code>
   */
  com.aliyun.polardbx.binlog.protocol.TxnTokenOrBuilder getTxnTokenOrBuilder();

  /**
   * <code>.com.aliyun.polardbx.binlog.protocol.TxnMergedToken txnMergedToken = 2;</code>
   * @return Whether the txnMergedToken field is set.
   */
  boolean hasTxnMergedToken();
  /**
   * <code>.com.aliyun.polardbx.binlog.protocol.TxnMergedToken txnMergedToken = 2;</code>
   * @return The txnMergedToken.
   */
  com.aliyun.polardbx.binlog.protocol.TxnMergedToken getTxnMergedToken();
  /**
   * <code>.com.aliyun.polardbx.binlog.protocol.TxnMergedToken txnMergedToken = 2;</code>
   */
  com.aliyun.polardbx.binlog.protocol.TxnMergedTokenOrBuilder getTxnMergedTokenOrBuilder();

  public com.aliyun.polardbx.binlog.protocol.TxnBegin.TokenOneofCase getTokenOneofCase();
}
