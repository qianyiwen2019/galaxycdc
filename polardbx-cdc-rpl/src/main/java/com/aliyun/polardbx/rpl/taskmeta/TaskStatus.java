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

package com.aliyun.polardbx.rpl.taskmeta;

/**
 * @author shicai.xsc 2021/1/12 15:24
 * @since 5.0.0.0
 */
public enum TaskStatus {
    NULL(0),

    READY(10),

    RUNNING(20),

    STOPPED(30),

    FINISHED(40),

    RESTART(50);

    private int value;

    public int getValue() {
        return value;
    }

    TaskStatus(int value) {
        this.value = value;
    }

    public static TaskStatus from(int value) {
        for (TaskStatus i : TaskStatus.values()) {
            if (i.value == value) {
                return i;
            }
        }
        return NULL;
    }
}