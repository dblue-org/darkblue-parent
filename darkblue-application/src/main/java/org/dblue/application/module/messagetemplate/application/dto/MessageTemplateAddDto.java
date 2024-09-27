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
package org.dblue.application.module.messagetemplate.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplate;
import org.dblue.common.id.Snowflake;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "")
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageTemplateAddDto extends BaseMessageTemplateDto {

    /**
     * 消息模板组ID
     */
    @NotBlank(message = "消息模板组ID不能为空")
    @Schema(description = "消息模板组ID")
    private String messageTemplateGroupId;

    /**
     * 消息模板类型（1-通知；2-待办）
     */
    @NotNull(message = "消息模板类型不能为空")
    @Schema(description = "消息模板类型（1-通知；2-待办）")
    private Integer messageTemplateType;


    public MessageTemplate asEntity() {
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setMessageTemplateId(Snowflake.stringId());
        messageTemplate.setMessageTemplateGroupId(this.getMessageTemplateGroupId());
        messageTemplate.setMessageTemplateType(this.getMessageTemplateType());
        this.setBaseInfo(messageTemplate);
        this.setRelationship(messageTemplate);
        return messageTemplate;
    }
}