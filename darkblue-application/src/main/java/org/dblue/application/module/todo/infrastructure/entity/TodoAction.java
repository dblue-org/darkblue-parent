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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 待办消息操作
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_todo_action")
public class TodoAction {
    /**
     * 操作ID
     */
    @Id
    @Size(max = 32)
    @Column(name = "todo_action_id", nullable = false, length = 32)
    private String todoActionId;

    /**
     * 待办ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "todo_id", nullable = false, length = 32)
    private String todoId;

    /**
     * 操作名称
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "action_name", nullable = false, length = 32)
    private String actionName;

    /**
     * 操作标识
     */
    @Size(max = 32)
    @Column(name = "action_mark", length = 32)
    private String actionMark;

    /**
     * 操作类型（1-路由跳转；2-宏）
     */
    @NotNull
    @Column(name = "action_type", nullable = false)
    private Integer actionType;

    /**
     * 匹配状态（0-全部；1-未处理；2-已处理）
     */
    @Column(name = "match_state")
    private Integer matchState;

    /**
     * 排序
     */
    @Column(name = "sort_num")
    private Integer sortNum;

    /**
     * 宏编码
     */
    @Size(max = 256)
    @Column(name = "macro_code", length = 256)
    private String macroCode;

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

}