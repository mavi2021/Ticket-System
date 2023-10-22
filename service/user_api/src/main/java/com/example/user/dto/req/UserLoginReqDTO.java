/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.user.dto.req;

import lombok.Builder;
import lombok.Data;

/**
 * 用户登录请求参数
 *
 */
@Data
@Builder
public class UserLoginReqDTO {

    /**
     * 用户登录名（用户名、手机号、邮箱等）
     */
    private String loginName;

    /**
     * 用户名登录类型（用户名 1、手机号 2、邮箱 3等）
     */
    private Integer LoginType;

    /**
     * 密码
     */
    private String password;
}
