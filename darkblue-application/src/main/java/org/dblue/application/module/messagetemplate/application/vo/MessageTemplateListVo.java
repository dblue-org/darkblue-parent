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
package org.dblue.application.module.messagetemplate.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplate;

import java.time.LocalDateTime;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "")
@Data
public class MessageTemplateListVo {

    /**
     * 消息模板ID
     */
    @Schema(description = "消息模板ID")
    private String messageTemplateId;

    /**
     * 消息模板组ID
     */
    @Schema(description = "消息模板组ID")
    private String messageTemplateGroupId;

    /**
     * 消息模板组名称
     */
    @Schema(description = "消息模板组名称")
    private String messageTemplateGroupName;

    /**
     * 消息模板编码
     */
    @Schema(description = "消息模板编码")
    private String messageTemplateCode;

    /**
     * 消息模板名称
     */
    @Schema(description = "消息模板名称")
    private String messageTemplateName;

    /**
     * 消息模板类型（1-通知；2-待办）
     */
    @Schema(description = "消息模板类型（1-通知；2-待办）")
    private Integer messageTemplateType;

    /**
     * 消息标题配置
     */
    @Schema(description = "消息标题配置")
    private String messageTitleTpl;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    public static MessageTemplateListVo of(MessageTemplate messageTemplate) {
        MessageTemplateListVo messageTemplateListVo = new MessageTemplateListVo();
        messageTemplateListVo.setMessageTemplateId(messageTemplate.getMessageTemplateId());
        messageTemplateListVo.setMessageTemplateGroupId(messageTemplate.getMessageTemplateGroupId());
        messageTemplateListVo.setMessageTemplateCode(messageTemplate.getMessageTemplateCode());
        messageTemplateListVo.setMessageTemplateName(messageTemplate.getMessageTemplateName());
        messageTemplateListVo.setMessageTemplateType(messageTemplate.getMessageTemplateType());
        messageTemplateListVo.setMessageTitleTpl(messageTemplate.getMessageTitleTpl());
        messageTemplateListVo.setCreateTime(messageTemplate.getCreateTime());
        return messageTemplateListVo;
    }
}