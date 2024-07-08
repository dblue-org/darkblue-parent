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
package org.dblue.application.module.logs.domain.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.logs.infrastructure.entity.LoginLog;
import org.dblue.application.module.logs.infrastructure.mapper.LoginLogMapper;
import org.dblue.security.enums.LoginPlatform;
import org.dblue.security.enums.LoginType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class LoginLogQueryImpl implements LoginLogQuery {


    private final LoginLogMapper loginLogMapper;
    private final LambdaQueryWrapper<LoginLog> queryWrapper = new LambdaQueryWrapper<>();


    public LoginLogQueryImpl(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;

    }


    @Override
    public LoginLogQuery userId(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq(LoginLog::getUserId, userId);
        }
        return this;
    }

    @Override
    public LoginLogQuery loginPlatform(LoginPlatform loginPlatform) {
        if (loginPlatform != null) {
            queryWrapper.eq(LoginLog::getLoginPlatform, loginPlatform.getValue());
        }
        return this;
    }

    @Override
    public LoginLogQuery loginPlatform(Integer loginPlatform) {
        if (loginPlatform != null) {
            queryWrapper.eq(LoginLog::getLoginPlatform, loginPlatform);
        }
        return this;
    }

    @Override
    public LoginLogQuery loginType(LoginType loginType) {
        if (loginType != null) {
            queryWrapper.eq(LoginLog::getLoginType, loginType.getValue());
        }
        return this;
    }

    @Override
    public LoginLogQuery loginType(Integer loginType) {
        if (loginType != null) {
            queryWrapper.eq(LoginLog::getLoginType, loginType);
        }
        return this;
    }

    @Override
    public LoginLogQuery loginTime(LocalDateTime loginTimeStart, LocalDateTime loginTimeEnd) {
        if (loginTimeStart != null) {
            this.queryWrapper.ge(LoginLog::getLoginTime, loginTimeStart);
        }
        if (loginTimeEnd != null) {
            this.queryWrapper.le(LoginLog::getLoginTime, loginTimeEnd);
        }
        return this;
    }

    @Override
    public LoginLogQuery loginIp(String loginIp) {
        if (StringUtils.isNotBlank(loginIp)) {
            queryWrapper.eq(LoginLog::getLoginIp, loginIp);
        }
        return this;
    }

    @Override
    public LoginLogQuery userAgentLike(String userAgent) {
        if (StringUtils.isNotBlank(userAgent)) {
            queryWrapper.like(LoginLog::getUserAgent, userAgent);
        }
        return this;
    }

    @Override
    public List<LoginLog> list() {
        return this.loginLogMapper.selectList(this.queryWrapper);
    }

    @Override
    public IPage<LoginLog> page(long page, long pageSize) {
        IPage<LoginLog> pageInfo = new Page<>(page, pageSize);
        return this.loginLogMapper.selectPage(pageInfo, this.queryWrapper);
    }
}