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

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DumperServer.proto

package com.aliyun.polardbx.rpc.cdc;

public final class DumperServer {
    private DumperServer() {
    }

    public static void registerAllExtensions(
        com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
        com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
            (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor
        internal_static_dumper_Request_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internal_static_dumper_Request_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
        internal_static_dumper_ShowBinlogEventsRequest_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internal_static_dumper_ShowBinlogEventsRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
        internal_static_dumper_DumpRequest_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internal_static_dumper_DumpRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
        internal_static_dumper_BinaryLog_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internal_static_dumper_BinaryLog_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
        internal_static_dumper_MasterStatus_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internal_static_dumper_MasterStatus_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
        internal_static_dumper_BinlogEvent_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internal_static_dumper_BinlogEvent_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
        internal_static_dumper_DumpStream_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internal_static_dumper_DumpStream_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
        descriptor;

    static {
        java.lang.String[] descriptorData = {
            "\n\022DumperServer.proto\022\006dumper\"\026\n\007Request\022" +
                "\013\n\003req\030\001 \001(\t\"Y\n\027ShowBinlogEventsRequest\022" +
                "\017\n\007logName\030\001 \001(\t\022\013\n\003pos\030\002 \001(\003\022\016\n\006offset\030" +
                "\003 \001(\003\022\020\n\010rowCount\030\004 \001(\003\"1\n\013DumpRequest\022\020" +
                "\n\010fileName\030\001 \001(\t\022\020\n\010position\030\002 \001(\003\".\n\tBi" +
                "naryLog\022\017\n\007logName\030\001 \001(\t\022\020\n\010fileSize\030\002 \001" +
                "(\003\"s\n\014MasterStatus\022\014\n\004file\030\001 \001(\t\022\020\n\010posi" +
                "tion\030\002 \001(\003\022\022\n\nbinlogDoDB\030\003 \001(\t\022\026\n\016binlog" +
                "IgnoreDB\030\004 \001(\t\022\027\n\017executedGtidSet\030\005 \001(\t\"" +
                "q\n\013BinlogEvent\022\017\n\007logName\030\001 \001(\t\022\013\n\003pos\030\002" +
                " \001(\003\022\021\n\teventType\030\003 \001(\t\022\020\n\010serverId\030\004 \001(" +
                "\003\022\021\n\tendLogPos\030\005 \001(\003\022\014\n\004info\030\006 \001(\t\"\035\n\nDu" +
                "mpStream\022\017\n\007payload\030\001 \001(\0142\273\002\n\nCdcService" +
                "\0228\n\016ShowBinaryLogs\022\017.dumper.Request\032\021.du" +
                "mper.BinaryLog\"\0000\001\022;\n\020ShowMasterStatus\022\017" +
                ".dumper.Request\032\024.dumper.MasterStatus\"\000\022" +
                "L\n\020ShowBinlogEvents\022\037.dumper.ShowBinlogE" +
                "ventsRequest\032\023.dumper.BinlogEvent\"\0000\001\0223\n" +
                "\004Dump\022\023.dumper.DumpRequest\032\022.dumper.Dump" +
                "Stream\"\0000\001\0223\n\004Sync\022\023.dumper.DumpRequest\032" +
                "\022.dumper.DumpStream\"\0000\001B\036\n\030com.alibaba.t" +
                "ddl.rpc.cdcH\001P\001b\006proto3"
        };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor
            .internalBuildGeneratedFileFrom(descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[] {
                });
        internal_static_dumper_Request_descriptor =
            getDescriptor().getMessageTypes().get(0);
        internal_static_dumper_Request_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_dumper_Request_descriptor,
            new java.lang.String[] {"Req",});
        internal_static_dumper_ShowBinlogEventsRequest_descriptor =
            getDescriptor().getMessageTypes().get(1);
        internal_static_dumper_ShowBinlogEventsRequest_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_dumper_ShowBinlogEventsRequest_descriptor,
            new java.lang.String[] {"LogName", "Pos", "Offset", "RowCount",});
        internal_static_dumper_DumpRequest_descriptor =
            getDescriptor().getMessageTypes().get(2);
        internal_static_dumper_DumpRequest_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_dumper_DumpRequest_descriptor,
            new java.lang.String[] {"FileName", "Position",});
        internal_static_dumper_BinaryLog_descriptor =
            getDescriptor().getMessageTypes().get(3);
        internal_static_dumper_BinaryLog_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_dumper_BinaryLog_descriptor,
            new java.lang.String[] {"LogName", "FileSize",});
        internal_static_dumper_MasterStatus_descriptor =
            getDescriptor().getMessageTypes().get(4);
        internal_static_dumper_MasterStatus_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_dumper_MasterStatus_descriptor,
            new java.lang.String[] {"File", "Position", "BinlogDoDB", "BinlogIgnoreDB", "ExecutedGtidSet",});
        internal_static_dumper_BinlogEvent_descriptor =
            getDescriptor().getMessageTypes().get(5);
        internal_static_dumper_BinlogEvent_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_dumper_BinlogEvent_descriptor,
            new java.lang.String[] {"LogName", "Pos", "EventType", "ServerId", "EndLogPos", "Info",});
        internal_static_dumper_DumpStream_descriptor =
            getDescriptor().getMessageTypes().get(6);
        internal_static_dumper_DumpStream_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_dumper_DumpStream_descriptor,
            new java.lang.String[] {"Payload",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}
