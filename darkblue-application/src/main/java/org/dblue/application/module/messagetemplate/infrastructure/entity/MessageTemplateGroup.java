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
import org.dblue.application.jpa.AbstractAuditingEntity;

/**
 * 消息模板组
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_message_template_group")
public class MessageTemplateGroup extends AbstractAuditingEntity {
    /**
     * 消息模板组ID
     */
    @Id
    @Size(max = 32)
    @Column(name = "message_template_group_id", nullable = false, length = 32)
    private String messageTemplateGroupId;

    /**
     * 消息模板组名称
     */
    @Size(max = 256)
    @NotNull
    @Column(name = "message_template_group_name", nullable = false, length = 256)
    private String messageTemplateGroupName;

}