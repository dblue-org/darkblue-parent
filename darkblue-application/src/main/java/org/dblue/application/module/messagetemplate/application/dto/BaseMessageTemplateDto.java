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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplate;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateAction;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateDirectRoute;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateTag;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息模板基础DTO
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "消息模板基础DTO")
@Data
public class BaseMessageTemplateDto {

    /**
     * 消息模板编码
     */
    @NotBlank(message = "消息模板编码不能为空")
    @Schema(description = "消息模板编码")
    private String messageTemplateCode;

    /**
     * 消息模板名称
     */
    @NotBlank(message = "消息模板名称不能为空")
    @Schema(description = "消息模板名称")
    private String messageTemplateName;

    /**
     * 业务编码配置
     */
    @Schema(description = "业务编码配置")
    private String serviceCodeTpl;

    /**
     * 消息标题配置
     */
    @NotBlank(message = "消息标题不能为空")
    @Schema(description = "消息标题配置")
    private String messageTitleTpl;

    /**
     * 消息内容配置
     */
    @NotBlank(message = "消息内容不能为空")
    @Schema(description = "消息内容配置")
    private String messageContentTpl;

    /**
     * 直接路由配置
     */
    @Valid
    @Schema(description = "直接路由配置")
    private List<MessageTemplateDirectRouteDto> directRouters;

    /**
     * 条件标签配置
     */
    @Valid
    @Schema(description = "条件标签配置")
    private List<MessageTemplateTagDto> tags;

    /**
     * 操作配置
     */
    @Valid
    @Schema(description = "操作配置")
    private List<MessageTemplateActionDto> actions;

    protected void setBaseInfo(MessageTemplate messageTemplate) {
        messageTemplate.setMessageTemplateCode(this.getMessageTemplateCode());
        messageTemplate.setMessageTemplateName(this.getMessageTemplateName());
        messageTemplate.setServiceCodeTpl(this.getServiceCodeTpl());
        messageTemplate.setMessageTitleTpl(this.getMessageTitleTpl());
        messageTemplate.setMessageContentTpl(this.getMessageContentTpl());
    }

    protected void setRelationship(MessageTemplate messageTemplate) {
        // 这里不能直接 .toList()，JPA会报错。
        if (CollectionUtils.isNotEmpty(this.getDirectRouters())) {
            List<MessageTemplateDirectRoute> newRoutes = this.getDirectRouters().stream()
                    .map(routeDto -> routeDto.asEntity(messageTemplate.getMessageTemplateId()))
                    .collect(Collectors.toList());
            messageTemplate.addRouters(newRoutes);
        }

        if (CollectionUtils.isNotEmpty(this.getTags())) {
            List<MessageTemplateTag> newTags = this.getTags().stream()
                    .map(tagDto -> tagDto.asEntity(messageTemplate.getMessageTemplateId()))
                    .collect(Collectors.toList());
            messageTemplate.addTags(newTags);
        }

        if (CollectionUtils.isNotEmpty(this.getActions())) {
            List<MessageTemplateAction> newActions = this.getActions().stream()
                    .map(actionDto -> actionDto.asEntity(messageTemplate.getMessageTemplateId()))
                    .collect(Collectors.toList());
            messageTemplate.addActions(newActions);
        }
    }
}