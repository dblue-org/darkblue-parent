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

package org.dblue.application.module.logs.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 操作日志
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_operation_log")
@TableName("tb_sys_operation_log")
public class OperationLog {
    /**
     * 操作日志ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "operation_log_id", nullable = false, length = 64)
    @TableId(value = "operation_log_id", type = IdType.INPUT)
    private String operationLogId;

    /**
     * 用户ID
     */
    @Size(max = 64)
    @Column(name = "user_id", length = 64)
    private String userId;

    /**
     * 操作名称
     */
    @Size(max = 256)
    @Column(name = "operation_name", length = 256)
    private String operationName;

    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private LocalDateTime operationTime;

    /**
     * 操作类
     */
    @Size(max = 256)
    @Column(name = "service_class", length = 256)
    private String serviceClass;

    /**
     * 操作方法
     */
    @Size(max = 128)
    @Column(name = "service_method", length = 128)
    private String serviceMethod;

    /**
     * 入参参数
     */
    @Column(name = "method_params")
    private String methodParams;

    /**
     * 方法返回结果
     */
    @Column(name = "result")
    private String result;

    /**
     * 是否发生错误
     */
    @Column(name = "is_error")
    private Boolean isError;

    /**
     * 方法执行耗时
     */
    @Column(name = "time_consuming")
    private Integer timeConsuming;

    /**
     * 错误详情
     */
    @Lob
    @Column(name = "error_details")
    private String errorDetails;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Size(max = 64)
    @Column(name = "create_user", length = 64)
    private String createUser;

}