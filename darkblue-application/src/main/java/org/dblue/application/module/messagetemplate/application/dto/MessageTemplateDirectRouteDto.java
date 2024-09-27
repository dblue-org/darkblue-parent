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

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateDirectRoute;
import org.dblue.common.id.Snowflake;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageTemplateDirectRouteDto extends BaseMessageTemplateRouteDto {

    private String messageTemplateId;

    public MessageTemplateDirectRoute asEntity(String messageTemplateId) {
        MessageTemplateDirectRoute messageTemplateDirectRoute = new MessageTemplateDirectRoute();
        messageTemplateDirectRoute.setMessageTemplateDirectRouteId(Snowflake.stringId());
        messageTemplateDirectRoute.setMessageTemplateId(messageTemplateId);
        messageTemplateDirectRoute.setRouterType(this.getRouterType());
        messageTemplateDirectRoute.setRouterLink(this.getRouterLink());
        return messageTemplateDirectRoute;
    }
}