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
package org.dblue.application.module.logs.domain.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.dblue.application.module.logs.domain.service.LoginLogDomainService;
import org.dblue.application.module.logs.infrastructure.entity.LoginLog;
import org.dblue.application.module.logs.infrastructure.repository.LoginLogRepository;
import org.dblue.common.id.Snowflake;
import org.dblue.security.enums.LoginPlatform;
import org.dblue.security.enums.LoginType;
import org.dblue.utils.ip.IpUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Service
public class LoginLogDomainServiceImpl implements LoginLogDomainService {

    private final LoginLogRepository loginLogRepository;

    public LoginLogDomainServiceImpl(LoginLogRepository loginLogRepository) {
        this.loginLogRepository = loginLogRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginLog add(String userId, LoginPlatform loginPlatform, LoginType loginType, HttpServletRequest request) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginLogId(Snowflake.stringId());
        loginLog.setLoginPlatform(loginPlatform.getValue());
        loginLog.setLoginType(loginType.getValue());
        loginLog.setUserAgent(request.getHeader("user-agent"));
        loginLog.setUserId(userId);
        loginLog.setLoginTime(LocalDateTime.now());
        loginLog.setCreateTime(LocalDateTime.now());
        loginLog.setCreateUser(userId);

        String ip = IpUtils.getRequestIp(request);
        loginLog.setLoginIp(ip);

        this.loginLogRepository.save(loginLog);

        return loginLog;
    }
}