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
package org.dblue.application.module.logs.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dblue.application.module.logs.application.dto.OperationLogQueryDto;
import org.dblue.application.module.logs.application.vo.OperationLogPageListVo;

/**
 * 操作日志
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface OperationLogApplicationService {

    /**
     * 分页查询操作日志
     *
     * @param queryDto 查询条件
     * @return 操作日志列表
     */
    IPage<OperationLogPageListVo> findByPage(OperationLogQueryDto queryDto);

    /**
     * 获取操作日志中的错误详情
     *
     * @param operationLogId 操作日志ID
     * @return 错误详情
     */
    String getErrorDetails(String operationLogId);
}
