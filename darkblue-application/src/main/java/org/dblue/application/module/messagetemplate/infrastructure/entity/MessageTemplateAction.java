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

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractCreateAuditingEntity;

import java.util.List;

/**
 * 模板操作配置
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_message_template_action")
public class MessageTemplateAction extends AbstractCreateAuditingEntity {
    /**
     * 按钮ID
     */
    @Id
    @Size(max = 32)
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
     * 操作名称
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "action_name", nullable = false, length = 32)
    private String actionName;

    /**
     * 操作标识
     */
    @Size(max = 64)
    @Column(name = "action_mark", length = 64)
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
    @NotNull
    @Column(name = "match_state", nullable = false)
    private Integer matchState;

    /**
     * 排序
     */
    @Column(name = "sort_num")
    private Integer sortNum;

    /**
     * 按钮条件
     */
    @Size(max = 512)
    @Column(name = "show_conditional", length = 512)
    private String showConditional;

    /**
     * 宏编码
     */
    @Size(max = 256)
    @Column(name = "macro_code", length = 256)
    private String macroCode;

    /**
     * 操作对应的路由
     */
    @JoinColumn(name = "message_template_action_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MessageTemplateActionRoute> routes;
}