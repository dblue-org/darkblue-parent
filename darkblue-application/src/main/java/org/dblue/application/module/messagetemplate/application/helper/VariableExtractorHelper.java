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
package org.dblue.application.module.messagetemplate.application.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.messagetemplate.application.vars.SpringExpressionVariableExtractor;
import org.dblue.application.module.messagetemplate.application.vars.VelocityVariableExtractor;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateDetailsVo;
import org.dblue.application.module.messagetemplate.application.vo.VarNode;
import org.dblue.application.module.messagetemplate.domain.enums.ActionTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 变量提取
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class VariableExtractorHelper {

    private VariableExtractorHelper() {
    }

    public static List<VarNode> extract(MessageTemplateDetailsVo messageTemplateDetailsVo) {
        List<String> allVars = new ArrayList<>();
        allVars.addAll(VelocityVariableExtractor.extractVariables(messageTemplateDetailsVo.getServiceCodeTpl()));
        allVars.addAll(VelocityVariableExtractor.extractVariables(messageTemplateDetailsVo.getMessageTitleTpl()));
        allVars.addAll(VelocityVariableExtractor.extractVariables(messageTemplateDetailsVo.getMessageContentTpl()));

        if (CollectionUtils.isNotEmpty(messageTemplateDetailsVo.getRoutes())) {
            messageTemplateDetailsVo.getRoutes().forEach(vo -> allVars.addAll(
                    VelocityVariableExtractor.extractVariables(vo.getRouterLink())
            ));
        }
        if (CollectionUtils.isNotEmpty(messageTemplateDetailsVo.getTags())) {
            messageTemplateDetailsVo.getTags().forEach(vo -> allVars.addAll(
                    SpringExpressionVariableExtractor.extractVariables(vo.getShowConditional())
            ));
        }
        if (CollectionUtils.isNotEmpty(messageTemplateDetailsVo.getActions())) {
            messageTemplateDetailsVo.getActions().stream().filter(vo -> Objects.nonNull(vo.getShowConditional()))
                    .forEach(vo -> allVars.addAll(
                            SpringExpressionVariableExtractor.extractVariables(vo.getShowConditional())
                    ));
            messageTemplateDetailsVo.getActions().stream().filter(vo -> ActionTypes.LINK.equalsTo(vo.getActionType()))
                    .flatMap(action -> action.getRouters().stream())
                    .forEach(vo -> allVars.addAll(
                            VelocityVariableExtractor.extractVariables(vo.getRouterLink())
                    ));
        }
        List<VarNode> nodes = allVars.stream().map(VarNode::create).toList();
        return VariableHelper.merge(nodes);
    }

}