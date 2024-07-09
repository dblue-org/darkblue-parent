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
import org.dblue.application.module.logs.application.dto.OperationLogQueryDto;
import org.dblue.application.module.logs.application.service.OperationLogApplicationService;
import org.dblue.application.module.logs.application.vo.OperationLogPageListVo;
import org.dblue.application.module.logs.infrastructure.entity.OperationLog;
import org.dblue.application.module.logs.infrastructure.query.OperationLogQuery;
import org.dblue.application.module.logs.infrastructure.repository.OperationLogRepository;
import org.dblue.application.module.user.domain.cache.UserCacheObject;
import org.dblue.application.module.user.domain.cache.UserCacheService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Service
public class OperationLogApplicationServiceImpl implements OperationLogApplicationService {

    private final OperationLogRepository operationLogRepository;

    private final UserCacheService userCacheService;

    public OperationLogApplicationServiceImpl(OperationLogRepository operationLogRepository, UserCacheService userCacheService) {
        this.operationLogRepository = operationLogRepository;
        this.userCacheService = userCacheService;
    }

    @Override
    public IPage<OperationLogPageListVo> findByPage(OperationLogQueryDto queryDto) {
        OperationLogQuery operationLogQuery = this.operationLogRepository.createQuery()
                .userId(queryDto.getUserId())
                .operationNameLike(queryDto.getOperationName())
                .operationTimeBetween(queryDto.getOperationTimeStart(), queryDto.getOperationTimeEnd())
                .serviceClassLike(queryDto.getServiceClass())
                .serviceMethod(queryDto.getServiceMethod())
                .isError(queryDto.getIsError());

        IPage<OperationLog> operationLogPage = operationLogQuery.page(queryDto.getPage(), queryDto.getPageSize());
        Set<String> userIdSet = operationLogPage.getRecords().stream().map(OperationLog::getUserId).collect(Collectors.toSet());

        Map<String, String> userMap = this.userCacheService.getAllById(userIdSet).stream().collect(Collectors.toMap(
                UserCacheObject::getUserId, UserCacheObject::getName
        ));

        return operationLogPage.convert(log -> {
            OperationLogPageListVo vo = OperationLogPageListVo.of(log);
            vo.setName(userMap.get(log.getUserId()));
            return vo;
        });
    }

    @Override
    public String getErrorDetails(String operationLogId) {
        Optional<OperationLog> operationLogOptional = this.operationLogRepository.findById(operationLogId);
        if (operationLogOptional.isPresent()) {
            return operationLogOptional.get().getErrorDetails();
        }
        return "";
    }


}