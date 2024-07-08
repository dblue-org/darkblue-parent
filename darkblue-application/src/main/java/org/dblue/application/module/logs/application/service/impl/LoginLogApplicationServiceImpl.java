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
package org.dblue.application.module.logs.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dblue.application.module.caching.UserCacheObject;
import org.dblue.application.module.caching.UserCacheService;
import org.dblue.application.module.logs.application.dto.LoginLogQueryDto;
import org.dblue.application.module.logs.application.service.LoginLogApplicationService;
import org.dblue.application.module.logs.application.vo.LoginLogPageListVo;
import org.dblue.application.module.logs.infrastructure.entity.LoginLog;
import org.dblue.application.module.logs.infrastructure.query.LoginLogQuery;
import org.dblue.application.module.logs.infrastructure.repository.LoginLogRepository;
import org.dblue.core.aspect.ServiceOperation;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登录日志查询
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Service
public class LoginLogApplicationServiceImpl implements LoginLogApplicationService {

    private final LoginLogRepository loginLogRepository;

    private final UserCacheService userCacheService;

    public LoginLogApplicationServiceImpl(LoginLogRepository loginLogRepository, UserCacheService userCacheService) {
        this.loginLogRepository = loginLogRepository;
        this.userCacheService = userCacheService;
    }

    @ServiceOperation("登录日志查询")
    @Override
    public IPage<LoginLogPageListVo> findByPage(LoginLogQueryDto queryDto) {
        LoginLogQuery loginLogQuery = this.loginLogRepository.createQuery();
        IPage<LoginLog> loginLogPage = loginLogQuery.userId(queryDto.getUserId())
                .loginIp(queryDto.getLoginIp())
                .loginPlatform(queryDto.getLoginPlatform())
                .loginType(queryDto.getLoginType())
                .loginTime(queryDto.getLoginTimeStart(), queryDto.getLoginTimeEnd())
                .page(queryDto.getPage(), queryDto.getPageSize());

        Set<String> userIdSet = loginLogPage.getRecords().stream().map(LoginLog::getUserId).collect(Collectors.toSet());

        Map<String, String> userMap = this.userCacheService.getAllById(userIdSet).stream().collect(Collectors.toMap(
                UserCacheObject::getUserId, UserCacheObject::getName
        ));

        return loginLogPage.convert(entity -> {
            LoginLogPageListVo vo = LoginLogPageListVo.of(entity);
            vo.setName(userMap.get(vo.getUserId()));
            return vo;
        });
    }
}