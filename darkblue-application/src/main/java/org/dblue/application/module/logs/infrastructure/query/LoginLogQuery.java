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
package org.dblue.application.module.logs.infrastructure.query;

import org.dblue.application.module.logs.infrastructure.entity.LoginLog;
import org.dblue.core.mybatis.query.BaseQuery;
import org.dblue.security.enums.LoginPlatform;
import org.dblue.security.enums.LoginType;

import java.time.LocalDateTime;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface LoginLogQuery extends BaseQuery<LoginLog> {

    LoginLogQuery userId(String userId);

    LoginLogQuery loginPlatform(LoginPlatform loginPlatform);

    LoginLogQuery loginPlatform(Integer loginPlatform);

    LoginLogQuery loginType(LoginType loginType);

    LoginLogQuery loginType(Integer loginType);

    LoginLogQuery loginTime(LocalDateTime loginTimeStart, LocalDateTime loginTimeEnd);

    LoginLogQuery loginIp(String loginIp);

    LoginLogQuery userAgentLike(String userAgent);

    LoginLogQuery orderByCreateTime();

    LoginLogQuery orderByCreateTimeDesc();

}
