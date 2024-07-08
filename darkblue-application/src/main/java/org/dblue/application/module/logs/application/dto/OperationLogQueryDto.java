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
import org.dblue.application.module.logs.application.vo.OperationLogPageListVo;
import org.dblue.core.web.param.PageParamImpl;
import org.springdoc.core.annotations.ParameterObject;

import java.time.LocalDateTime;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ParameterObject
public class OperationLogQueryDto extends PageParamImpl<OperationLogPageListVo> {

    /**
     * 用户ID
     */
    @Parameter(description = "用户ID")
    private String userId;

    /**
     * 操作名称
     */
    @Parameter(description = "操作名称")
    private String operationName;

    /**
     * 操作时间开始
     */
    @Parameter(description = "操作时间开始")
    private LocalDateTime operationTimeStart;

    /**
     * 操作时间结束
     */
    @Parameter(description = "操作时间结束")
    private LocalDateTime operationTimeEnd;

    /**
     * 操作类
     */
    @Parameter(description = "操作类")
    private String serviceClass;

    /**
     * 操作方法
     */
    @Parameter(description = "操作方法")
    private String serviceMethod;

    /**
     * 是否发生错误
     */
    @Parameter(description = "是否发生错误")
    private Boolean isError;
}