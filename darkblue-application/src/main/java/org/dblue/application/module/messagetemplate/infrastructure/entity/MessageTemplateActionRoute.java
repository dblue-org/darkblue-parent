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

package org.dblue.application.module.messagetemplate.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractCreateAuditingEntity;

/**
 * 模板按钮路由
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_message_template_action_route")
public class MessageTemplateActionRoute extends AbstractCreateAuditingEntity {
    /**
     * 路由ID
     */
    @Id
    @Size(max = 32)
    @Column(name = "message_template_action_route_id", nullable = false, length = 32)
    private String messageTemplateActionRouteId;

    /**
     * 按钮ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "message_template_action_id", nullable = false, length = 32)
    private String messageTemplateActionId;

    /**
     * 模板ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "message_template_id", nullable = false, length = 32)
    private String messageTemplateId;

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

}