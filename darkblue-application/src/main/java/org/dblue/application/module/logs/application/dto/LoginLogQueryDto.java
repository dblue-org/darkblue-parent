/*
 * Copyright (c) 2023-2024. the original authors and DBLUE.ORG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dblue.application.module.logs.application.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.logs.infrastructure.entity.LoginLog;
import org.dblue.core.web.param.PageParamImpl;
import org.springdoc.core.annotations.ParameterObject;

import java.time.LocalDateTime;

/**
 * 登录信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@ParameterObject
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginLogQueryDto extends PageParamImpl<LoginLog> {

    /**
     * 登录用户
     */
    @Parameter(description = "登录用户")
    private String userId;

    /**
     * 登录平台
     */
    @Parameter(description = "登录平台")
    private Integer loginPlatform;

    /**
     * 登录类型
     */
    @Parameter(description = "登录类型")
    private Integer loginType;

    /**
     * 登录开始时间
     */
    @Parameter(description = "登录开始时间")
    private LocalDateTime loginTimeStart;

    /**
     * 登录结束时间
     */
    @Parameter(description = "登录结束时间")
    private LocalDateTime loginTimeEnd;

    /**
     * 登录IP
     */
    @Parameter(description = "登录IP")
    private String loginIp;
}