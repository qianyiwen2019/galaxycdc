// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TxnStream.proto

package com.aliyun.polardbx.binlog.protocol;

/**
 * Protobuf type {@code com.aliyun.polardbx.binlog.protocol.DumpRequest}
 */
public final class DumpRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.aliyun.polardbx.binlog.protocol.DumpRequest)
    DumpRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DumpRequest.newBuilder() to construct.
  private DumpRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DumpRequest() {
    tso_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DumpRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DumpRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            tso_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.aliyun.polardbx.binlog.protocol.TxnStream.internal_static_com_aliyun_polardbx_binlog_protocol_DumpRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.aliyun.polardbx.binlog.protocol.TxnStream.internal_static_com_aliyun_polardbx_binlog_protocol_DumpRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.aliyun.polardbx.binlog.protocol.DumpRequest.class, com.aliyun.polardbx.binlog.protocol.DumpRequest.Builder.class);
  }

  public static final int TSO_FIELD_NUMBER = 1;
  private volatile java.lang.Object tso_;
  /**
   * <code>string tso = 1;</code>
   * @return The tso.
   */
  @java.lang.Override
  public java.lang.String getTso() {
    java.lang.Object ref = tso_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      tso_ = s;
      return s;
    }
  }
  /**
   * <code>string tso = 1;</code>
   * @return The bytes for tso.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTsoBytes() {
    java.lang.Object ref = tso_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      tso_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getTsoBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, tso_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getTsoBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, tso_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.aliyun.polardbx.binlog.protocol.DumpRequest)) {
      return super.equals(obj);
    }
    com.aliyun.polardbx.binlog.protocol.DumpRequest other = (com.aliyun.polardbx.binlog.protocol.DumpRequest) obj;

    if (!getTso()
        .equals(other.getTso())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TSO_FIELD_NUMBER;
    hash = (53 * hash) + getTso().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aliyun.polardbx.binlog.protocol.DumpRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.aliyun.polardbx.binlog.protocol.DumpRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.aliyun.polardbx.binlog.protocol.DumpRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.aliyun.polardbx.binlog.protocol.DumpRequest)
      com.aliyun.polardbx.binlog.protocol.DumpRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.aliyun.polardbx.binlog.protocol.TxnStream.internal_static_com_aliyun_polardbx_binlog_protocol_DumpRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.aliyun.polardbx.binlog.protocol.TxnStream.internal_static_com_aliyun_polardbx_binlog_protocol_DumpRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.aliyun.polardbx.binlog.protocol.DumpRequest.class, com.aliyun.polardbx.binlog.protocol.DumpRequest.Builder.class);
    }

    // Construct using com.aliyun.polardbx.binlog.protocol.DumpRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      tso_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.aliyun.polardbx.binlog.protocol.TxnStream.internal_static_com_aliyun_polardbx_binlog_protocol_DumpRequest_descriptor;
    }

    @java.lang.Override
    public com.aliyun.polardbx.binlog.protocol.DumpRequest getDefaultInstanceForType() {
      return com.aliyun.polardbx.binlog.protocol.DumpRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.aliyun.polardbx.binlog.protocol.DumpRequest build() {
      com.aliyun.polardbx.binlog.protocol.DumpRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.aliyun.polardbx.binlog.protocol.DumpRequest buildPartial() {
      com.aliyun.polardbx.binlog.protocol.DumpRequest result = new com.aliyun.polardbx.binlog.protocol.DumpRequest(this);
      result.tso_ = tso_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.aliyun.polardbx.binlog.protocol.DumpRequest) {
        return mergeFrom((com.aliyun.polardbx.binlog.protocol.DumpRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.aliyun.polardbx.binlog.protocol.DumpRequest other) {
      if (other == com.aliyun.polardbx.binlog.protocol.DumpRequest.getDefaultInstance()) return this;
      if (!other.getTso().isEmpty()) {
        tso_ = other.tso_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.aliyun.polardbx.binlog.protocol.DumpRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.aliyun.polardbx.binlog.protocol.DumpRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object tso_ = "";
    /**
     * <code>string tso = 1;</code>
     * @return The tso.
     */
    public java.lang.String getTso() {
      java.lang.Object ref = tso_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        tso_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string tso = 1;</code>
     * @return The bytes for tso.
     */
    public com.google.protobuf.ByteString
        getTsoBytes() {
      java.lang.Object ref = tso_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        tso_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string tso = 1;</code>
     * @param value The tso to set.
     * @return This builder for chaining.
     */
    public Builder setTso(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      tso_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string tso = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearTso() {
      
      tso_ = getDefaultInstance().getTso();
      onChanged();
      return this;
    }
    /**
     * <code>string tso = 1;</code>
     * @param value The bytes for tso to set.
     * @return This builder for chaining.
     */
    public Builder setTsoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      tso_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.aliyun.polardbx.binlog.protocol.DumpRequest)
  }

  // @@protoc_insertion_point(class_scope:com.aliyun.polardbx.binlog.protocol.DumpRequest)
  private static final com.aliyun.polardbx.binlog.protocol.DumpRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.aliyun.polardbx.binlog.protocol.DumpRequest();
  }

  public static com.aliyun.polardbx.binlog.protocol.DumpRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DumpRequest>
      PARSER = new com.google.protobuf.AbstractParser<DumpRequest>() {
    @java.lang.Override
    public DumpRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DumpRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DumpRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DumpRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.aliyun.polardbx.binlog.protocol.DumpRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

