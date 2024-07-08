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

import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.logs.domain.service.OperationLogDomainService;
import org.dblue.application.module.logs.infrastructure.entity.OperationLog;
import org.dblue.application.module.logs.infrastructure.repository.OperationLogRepository;
import org.dblue.common.id.Snowflake;
import org.dblue.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Service
public class OperationLogDomainServiceImpl implements OperationLogDomainService {

    private final OperationLogRepository operationLogRepository;

    public OperationLogDomainServiceImpl(OperationLogRepository operationLogRepository) {
        this.operationLogRepository = operationLogRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(OperationLog operationLog) {
        if (StringUtils.isBlank(operationLog.getOperationLogId())) {
            operationLog.setOperationLogId(Snowflake.stringId());
        }
        if (SecurityUtils.isAuthenticated()) {
            operationLog.setCreateUser(SecurityUtils.getUserId());
            operationLog.setUserId(SecurityUtils.getUserId());
        }
        operationLog.setCreateTime(LocalDateTime.now());
        operationLog.setOperationTime(LocalDateTime.now());
        this.operationLogRepository.save(operationLog);
    }
}