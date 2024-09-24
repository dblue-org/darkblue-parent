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

package org.dblue.application.module.todo.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

/**
 * 待办列表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_todo")
public class Todo {
    /**
     * 待办ID
     */
    @Id
    @Size(max = 32)
    @Column(name = "todo_id", nullable = false, length = 32)
    private String todoId;

    /**
     * 待办类型（1-待办；2-我的；3-抄送）
     */
    @NotNull
    @Column(name = "todo_type", nullable = false)
    private Integer todoType;

    /**
     * 消息分类（1-审批类；2-业务类）
     */
    @NotNull
    @Column(name = "message_type", nullable = false)
    private Integer messageType;

    /**
     * 消息模板组ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "message_template_group_id", nullable = false, length = 32)
    private String messageTemplateGroupId;

    /**
     * 模板ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "message_template_id", nullable = false, length = 32)
    private String messageTemplateId;

    /**
     * 业务类型标识
     */
    @Size(max = 100)
    @Column(name = "service_mark", length = 100)
    private String serviceMark;

    /**
     * 步骤编码
     */
    @Size(max = 32)
    @Column(name = "stage", length = 32)
    private String stage;

    /**
     * 业务记录编码
     */
    @Size(max = 32)
    @Column(name = "service_code", length = 32)
    private String serviceCode;

    /**
     * 业务ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "service_id", nullable = false, length = 32)
    private String serviceId;

    /**
     * 消息标题
     */
    @Size(max = 512)
    @NotNull
    @Column(name = "message_title", nullable = false, length = 512)
    private String messageTitle;

    /**
     * 消息内容
     */
    @NotNull
    @Lob
    @Column(name = "message_content", nullable = false)
    private String messageContent;

    /**
     * 标签
     */
    @Size(max = 256)
    @Column(name = "tags", length = 256)
    private String tags;

    /**
     * 状态（1-未处理；2-已处理）
     */
    @NotNull
    @ColumnDefault("1")
    @Column(name = "state", nullable = false)
    private Integer state;

    /**
     * 待办完成时间
     */
    @Column(name = "complete_time")
    private Instant completeTime;

    /**
     * 发起人
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "starter", nullable = false, length = 32)
    private String starter;

    /**
     * 接收人
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "to_user", nullable = false, length = 32)
    private String toUser;

    /**
     * 处理人
     */
    @Size(max = 32)
    @Column(name = "handle_user", length = 32)
    private String handleUser;

    /**
     * 发起时间
     */
    @Column(name = "start_time")
    private Instant startTime;

    /**
     * 任务接受时间
     */
    @Column(name = "receive_time")
    private Instant receiveTime;

    /**
     * 流程ID
     */
    @Size(max = 64)
    @Column(name = "process_instance_id", length = 64)
    private String processInstanceId;

    /**
     * 任务ID
     */
    @Size(max = 64)
    @Column(name = "task_id", length = 64)
    private String taskId;

    /**
     * 业务状态
     */
    @Size(max = 64)
    @Column(name = "service_state", length = 64)
    private String serviceState;

    /**
     * 业务状态名称
     */
    @Size(max = 64)
    @Column(name = "service_state_name", length = 64)
    private String serviceStateName;

    /**
     * 截止时间
     */
    @Column(name = "end_time")
    private Instant endTime;

    /**
     * 业务数据
     */
    @NotNull
    @Column(name = "service_data", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> serviceData;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 创建人
     */
    @Size(max = 32)
    @Column(name = "create_user", length = 32)
    private String createUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * 更新人
     */
    @Size(max = 32)
    @Column(name = "update_user", length = 32)
    private String updateUser;

}