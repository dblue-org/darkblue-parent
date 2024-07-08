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

package org.dblue.application.module.logs.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dblue.application.module.logs.infrastructure.entity.OperationLog;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * 操作日志(tb_sys_operation_log)表实体类
 *
 * @author Wang Chengwei
 * @since 1.0.0 2024-07-08 13:28:37
 */
@Schema(description = "操作日志")
@Data
public class OperationLogPageListVo {

    /**
     * 操作日志ID
     */
    @Schema(description = "操作日志ID")
    private String operationLogId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;
    /**
     * 用户姓名
     */
    @Schema(description = "用户姓名")
    private String name;

    /**
     * 操作名称
     */
    @Schema(description = "操作名称")
    private String operationName;

    /**
     * 操作时间
     */
    @Schema(description = "操作时间")
    private LocalDateTime operationTime;

    /**
     * 操作类
     */
    @Schema(description = "操作类")
    private String serviceClass;

    /**
     * 操作方法
     */
    @Schema(description = "操作方法")
    private String serviceMethod;

    /**
     * 入参参数
     */
    @Schema(description = "入参参数")
    private String methodParams;

    /**
     * 方法返回结果
     */
    @Schema(description = "方法返回结果")
    private String result;

    /**
     * 是否发生错误
     */
    @Schema(description = "是否发生错误")
    private Boolean isError;

    /**
     * 方法执行耗时
     */
    @Schema(description = "方法执行耗时")
    private Integer timeConsuming;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    public static OperationLogPageListVo of(OperationLog operationLog) {
        OperationLogPageListVo vo = new OperationLogPageListVo();
        BeanUtils.copyProperties(operationLog, vo);
        return vo;
    }

}


