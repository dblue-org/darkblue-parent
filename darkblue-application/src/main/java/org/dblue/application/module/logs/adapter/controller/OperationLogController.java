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
package org.dblue.application.module.logs.adapter.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dblue.application.module.logs.application.dto.OperationLogQueryDto;
import org.dblue.application.module.logs.application.service.OperationLogApplicationService;
import org.dblue.application.module.logs.application.vo.OperationLogPageListVo;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Tag(name = "操作日志")
@RestController
@RequestMapping("/api/operation-log")
public class OperationLogController {

    private final OperationLogApplicationService operationLogApplicationService;

    public OperationLogController(OperationLogApplicationService operationLogApplicationService) {
        this.operationLogApplicationService = operationLogApplicationService;
    }

    /**
     * 分页查询操作日志
     *
     * @param queryDto 查询条件
     * @return 操作日志列表
     */
    @Operation(summary = "分页查询操作日志")
    @GetMapping("/findByPage")
    public PageResponseBean<OperationLogPageListVo> findByPage(OperationLogQueryDto queryDto) {
        IPage<OperationLogPageListVo> voPage = this.operationLogApplicationService.findByPage(queryDto);
        return PageResponseBean.success(voPage);
    }

    /**
     * 获取操作日志中的错误详情
     *
     * @param operationLogId 操作日志ID
     * @return 错误详情
     */
    @Operation(summary = "获取操作日志中的错误详情")
    @GetMapping("/getErrorDetails/{operationLogId}")
    public ResponseBean<String> getErrorDetails(@PathVariable String operationLogId) {
        String errorDetails = this.operationLogApplicationService.getErrorDetails(operationLogId);
        return ResponseBean.success(errorDetails);
    }
}