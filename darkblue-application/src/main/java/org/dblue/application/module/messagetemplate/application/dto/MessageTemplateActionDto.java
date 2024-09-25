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
import lombok.Data;
import org.dblue.application.module.messagetemplate.domain.enums.ActionTypes;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateAction;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateActionRoute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "")
@Data
public class MessageTemplateActionDto {

    /**
     * 操作名称
     */
    @Schema(description = "操作名称")
    private String actionName;

    /**
     * 操作标识
     */
    @Schema(description = "操作标识")
    private String actionMark;

    /**
     * 操作类型（1-路由跳转；2-宏）
     */
    @Schema(description = "操作类型（1-路由跳转；2-宏）")
    private Integer actionType;

    /**
     * 匹配状态（0-全部；1-未处理；2-已处理）
     */
    @Schema(description = "匹配状态（0-全部；1-未处理；2-已处理）")
    private Integer matchState;

    /**
     * 按钮条件
     */
    @Schema(description = "按钮条件")
    private String showConditional;

    /**
     * 宏编码
     */
    @Schema(description = "宏编码")
    private String macroCode;

    /**
     * 操作跳转链接配置
     */
    @Schema(description = "操作跳转链接配置")
    private List<MessageTemplateActionRouteDto> routers;

    public MessageTemplateAction asEntity() {
        MessageTemplateAction messageTemplateAction = new MessageTemplateAction();
        messageTemplateAction.setActionName(this.getActionName());
        messageTemplateAction.setActionMark(this.getActionMark());
        messageTemplateAction.setActionType(this.getActionType());
        messageTemplateAction.setMatchState(this.getMatchState());
        messageTemplateAction.setShowConditional(this.getShowConditional());
        messageTemplateAction.setMacroCode(this.getMacroCode());
        if (ActionTypes.LINK.equalsTo(this.getActionType())) {
            List<MessageTemplateActionRoute> routes = new ArrayList<>();
            for (MessageTemplateActionRouteDto router : routers) {
                routes.add(router.asEntity());
            }
            messageTemplateAction.setRoutes(routes);
        }
        return messageTemplateAction;
    }
}