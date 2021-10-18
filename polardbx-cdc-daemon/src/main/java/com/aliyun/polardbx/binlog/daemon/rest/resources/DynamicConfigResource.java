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

package com.aliyun.polardbx.binlog.daemon.rest.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aliyun.polardbx.binlog.DynamicApplicationConfig;
import com.aliyun.polardbx.binlog.daemon.rest.entities.ConfigInfo;

@Path("/config/v1")
@Singleton
public class DynamicConfigResource {

    @POST
    @Path("/set")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean set(ConfigInfo configInfo) {
        DynamicApplicationConfig.setValue(configInfo.getKey(), configInfo.getValue());
        return true;
    }

    @GET
    @Path("/get/{key}")
    @Produces("text/plain;charset=utf-8")
    public String get(@PathParam("key") String key) {
        return DynamicApplicationConfig.getValue(key);
    }
}
