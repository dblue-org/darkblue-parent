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
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.messagetemplate.application.vo.sub.MessageTemplateActionVo;
import org.dblue.application.module.messagetemplate.application.vo.sub.MessageTemplateDirectRouteVo;
import org.dblue.application.module.messagetemplate.application.vo.sub.MessageTemplateTagVo;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplate;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "")
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageTemplateDetailsVo extends MessageTemplateListVo {

    /**
     * 业务编码配置
     */
    @Schema(description = "业务编码配置")
    private String serviceCodeTpl;

    /**
     * 消息内容配置
     */
    @Schema(description = "消息内容配置")
    private String messageContentTpl;

    /**
     * 路由配置
     */
    @Schema(description = "路由配置")
    private List<MessageTemplateDirectRouteVo> routes;

    /**
     * 条件标签配置
     */
    @Schema(description = "条件标签配置")
    private List<MessageTemplateTagVo> tags;

    /**
     * 操作配置
     */
    @Schema(description = "操作配置")
    private List<MessageTemplateActionVo> actions;

    /**
     * 变量信息
     */
    @Schema(description = "变量信息")
    private List<VarTreeNodeVo> variables;


    public static MessageTemplateDetailsVo of(MessageTemplate messageTemplate) {
        MessageTemplateDetailsVo messageTemplateDetailsVo = new MessageTemplateDetailsVo();
        BeanUtils.copyProperties(messageTemplate, messageTemplateDetailsVo);

        if (CollectionUtils.isNotEmpty(messageTemplate.getRoutes())) {
            messageTemplateDetailsVo.setRoutes(
                    messageTemplate.getRoutes().stream().map(MessageTemplateDirectRouteVo::of).toList()
            );
        }
        if (CollectionUtils.isNotEmpty(messageTemplate.getTags())) {
            messageTemplateDetailsVo.setTags(
                    messageTemplate.getTags().stream().map(MessageTemplateTagVo::of).toList()
            );
        }
        if (CollectionUtils.isNotEmpty(messageTemplate.getActions())) {
            messageTemplateDetailsVo.setActions(
                    messageTemplate.getActions().stream().map(MessageTemplateActionVo::of).toList()
            );
        }
        return messageTemplateDetailsVo;
    }
}