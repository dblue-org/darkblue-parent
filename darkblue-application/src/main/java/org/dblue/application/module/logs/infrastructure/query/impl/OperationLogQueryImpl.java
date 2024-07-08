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
package org.dblue.application.module.logs.infrastructure.query.impl;

import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.logs.infrastructure.entity.OperationLog;
import org.dblue.application.module.logs.infrastructure.mapper.OperationLogMapper;
import org.dblue.application.module.logs.infrastructure.query.OperationLogQuery;
import org.dblue.core.mybatis.query.AbstractBaseQuery;

import java.time.LocalDateTime;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class OperationLogQueryImpl extends AbstractBaseQuery<OperationLog> implements OperationLogQuery {

    public OperationLogQueryImpl(OperationLogMapper operationLogMapper) {
        super(operationLogMapper);
    }


    @Override
    public OperationLogQuery userId(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            this.queryWrapper.eq(OperationLog::getUserId, userId);
        }
        return this;
    }

    @Override
    public OperationLogQuery operationNameLike(String operationName) {
        if (StringUtils.isNotBlank(operationName)) {
            this.queryWrapper.like(OperationLog::getOperationName, operationName);
        }
        return this;
    }

    @Override
    public OperationLogQuery operationTimeBetween(LocalDateTime operationTimeStart, LocalDateTime operationTimeEnd) {
        if (operationTimeStart != null) {
            this.queryWrapper.ge(OperationLog::getOperationTime, operationTimeStart);
        }

        if (operationTimeEnd != null) {
            this.queryWrapper.ge(OperationLog::getOperationTime, operationTimeEnd);
        }
        return this;
    }

    @Override
    public OperationLogQuery serviceClassLike(String serviceClass) {
        if (StringUtils.isNotBlank(serviceClass)) {
            this.queryWrapper.like(OperationLog::getServiceClass, serviceClass);
        }
        return this;
    }

    @Override
    public OperationLogQuery serviceMethod(String serviceMethod) {
        if (StringUtils.isNotBlank(serviceMethod)) {
            this.queryWrapper.eq(OperationLog::getServiceMethod, serviceMethod);
        }
        return this;
    }

    @Override
    public OperationLogQuery isError(Boolean isError) {
        if (isError != null) {
            this.queryWrapper.eq(OperationLog::getIsError, isError);
        }
        return this;
    }
}