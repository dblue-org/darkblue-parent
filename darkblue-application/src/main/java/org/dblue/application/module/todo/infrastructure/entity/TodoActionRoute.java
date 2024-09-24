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
 * 待办操作路由
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_todo_action_route")
public class TodoActionRoute {
    /**
     * 待办操作路由ID
     */
    @Id
    @Size(max = 32)
    @Column(name = "todo_action_route_id", nullable = false, length = 32)
    private String todoActionRouteId;

    /**
     * 操作ID
     */
    @Size(max = 32)
    @NotNull
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
     * 路由类型(1-PC;2-Android;3-IOS;4-小程序;5-H5)
     */
    @NotNull
    @Column(name = "router_type", nullable = false)
    private Integer routerType;

    /**
     * 路由地址
     */
    @Size(max = 512)
    @NotNull
    @Column(name = "router_link", nullable = false, length = 512)
    private String routerLink;

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