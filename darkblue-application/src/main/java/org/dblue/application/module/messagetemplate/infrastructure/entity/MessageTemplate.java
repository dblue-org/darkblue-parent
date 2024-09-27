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
import org.dblue.application.jpa.AbstractAuditingEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_sys_message_template")
public class MessageTemplate extends AbstractAuditingEntity {

    /**
     * 消息模板ID
     */
    @Id
    @Size(max = 32)
    @Column(name = "message_template_id", nullable = false, length = 32)
    private String messageTemplateId;

    /**
     * 消息模板组ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "message_template_group_id", nullable = false, length = 32)
    private String messageTemplateGroupId;

    /**
     * 消息模板编码
     */
    @Size(max = 64)
    @NotNull
    @Column(name = "message_template_code", nullable = false, length = 64)
    private String messageTemplateCode;

    /**
     * 消息模板名称
     */
    @Size(max = 256)
    @NotNull
    @Column(name = "message_template_name", nullable = false, length = 256)
    private String messageTemplateName;

    /**
     * 消息模板类型（1-通知；2-待办）
     */
    @NotNull
    @Column(name = "message_template_type", nullable = false)
    private Integer messageTemplateType;

    /**
     * 业务编码配置
     */
    @Size(max = 256)
    @Column(name = "service_code_tpl", length = 256)
    private String serviceCodeTpl;

    /**
     * 消息标题配置
     */
    @Size(max = 256)
    @NotNull
    @Column(name = "message_title_tpl", nullable = false, length = 256)
    private String messageTitleTpl;

    /**
     * 消息内容配置
     */
    @NotNull
    @Lob
    @Column(name = "message_content_tpl", nullable = false)
    private String messageContentTpl;

    /**
     * 路由列表
     */
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "message_template_id")
    private List<MessageTemplateDirectRoute> routes;

    /**
     * 路由列表
     */
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "message_template_id")
    private List<MessageTemplateTag> tags;

    /**
     * 路由列表
     */
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "message_template_id")
    private List<MessageTemplateAction> actions;

    public void removeAllRouters() {
        if (routes != null) {
            this.routes.clear();
        }
    }

    public void removeAllTags() {
        if (tags != null) {
            this.tags.clear();
        }
    }

    public void removeAllActions() {
        if (actions != null) {
            this.actions.clear();
        }
    }

    public void addRouters(List<MessageTemplateDirectRoute> routes) {
        if (this.routes == null) {
            this.routes = new ArrayList<>();
        }
        this.routes.addAll(routes);
    }

    public void addTags(List<MessageTemplateTag> tags) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.addAll(tags);
    }

    public void addActions(List<MessageTemplateAction> actions) {
        if (this.actions == null) {
            this.actions = new ArrayList<>();
        }
        this.actions.addAll(actions);
    }
}