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
package org.dblue.application.module.messagetemplate.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.messagetemplate.domain.enums.ActionTypes;
import org.dblue.application.module.messagetemplate.domain.errors.MessageTemplateErrors;
import org.dblue.application.module.messagetemplate.domain.service.MessageTemplateDomainService;
import org.dblue.application.module.messagetemplate.infrastructure.entity.*;
import org.dblue.application.module.messagetemplate.infrastructure.query.MessageTemplateQuery;
import org.dblue.application.module.messagetemplate.infrastructure.query.impl.MessageTemplateQueryImpl;
import org.dblue.application.module.messagetemplate.infrastructure.repository.*;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 消息模板处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class MessageTemplateDomainServiceImpl implements MessageTemplateDomainService {

    private final MessageTemplateRepository messageTemplateRepository;

    private final MessageTemplateDirectRouteRepository messageTemplateDirectRouteRepository;

    private final MessageTemplateTagRepository messageTemplateTagRepository;

    private final MessageTemplateActionRepository messageTemplateActionRepository;

    private final MessageTemplateActionRouteRepository messageTemplateActionRouteRepository;


    @Override
    public MessageTemplate add(MessageTemplate messageTemplate) {
        long count = this.createQuery()
                .messageTemplateCode(messageTemplate.getMessageTemplateCode())
                .count();
        if (count > 0) {
            throw new ServiceException(MessageTemplateErrors.CODE_EXIST);
        }

        if (StringUtils.isNotBlank(messageTemplate.getMessageTemplateId())) {
            messageTemplate.setMessageTemplateId(Snowflake.stringId());
        }
        this.messageTemplateRepository.save(messageTemplate);
        this.saveRoutes(messageTemplate.getMessageTemplateId(), messageTemplate.getRoutes(), false);
        this.saveTags(messageTemplate.getMessageTemplateId(), messageTemplate.getTags(), false);
        this.saveActions(messageTemplate.getMessageTemplateId(), messageTemplate.getActions(), false);
        return messageTemplate;
    }

    @Override
    public MessageTemplate update(MessageTemplate messageTemplate) {
        long count = this.createQuery()
                .messageTemplateCode(messageTemplate.getMessageTemplateCode())
                .messageTemplateIdNot(messageTemplate.getMessageTemplateId())
                .count();
        if (count > 0) {
            throw new ServiceException(MessageTemplateErrors.CODE_EXIST);
        }
        this.messageTemplateRepository.save(messageTemplate);
        this.saveRoutes(messageTemplate.getMessageTemplateId(), messageTemplate.getRoutes(), true);
        this.saveTags(messageTemplate.getMessageTemplateId(), messageTemplate.getTags(), true);
        this.saveActions(messageTemplate.getMessageTemplateId(), messageTemplate.getActions(), true);
        return messageTemplate;
    }

    @Override
    public void delete(String messageTemplateId) {
        Optional<MessageTemplate> messageTemplateOptional = this.messageTemplateRepository.findById(messageTemplateId);
        if (messageTemplateOptional.isEmpty()) {
            throw new ServiceException(MessageTemplateErrors.NOT_EXIST);
        }
        this.messageTemplateRepository.deleteById(messageTemplateId);
        this.messageTemplateDirectRouteRepository.deleteByMessageTemplateId(messageTemplateId);
        this.messageTemplateTagRepository.deleteByMessageTemplateId(messageTemplateId);
        this.messageTemplateActionRepository.deleteByMessageTemplateId(messageTemplateId);
        this.messageTemplateActionRouteRepository.deleteByMessageTemplateId(messageTemplateId);
    }

    @Override
    public MessageTemplateQuery createQuery() {
        return new MessageTemplateQueryImpl(messageTemplateRepository, messageTemplateDirectRouteRepository,
                messageTemplateTagRepository, messageTemplateActionRepository, messageTemplateActionRouteRepository);
    }

    private void saveRoutes(String messageTemplateId, List<MessageTemplateDirectRoute> routeList, boolean isUpdate) {
        if (isUpdate) {
            this.messageTemplateDirectRouteRepository.deleteByMessageTemplateId(messageTemplateId);
        }
        if (CollectionUtils.isEmpty(routeList)) {
            return;
        }
        for (MessageTemplateDirectRoute messageTemplateDirectRoute : routeList) {
            messageTemplateDirectRoute.setMessageTemplateDirectRouteId(Snowflake.stringId());
            messageTemplateDirectRoute.setMessageTemplateId(messageTemplateId);
        }
        this.messageTemplateDirectRouteRepository.saveAll(routeList);
    }

    private void saveTags(String messageTemplateId, List<MessageTemplateTag> messageTemplateTags, boolean isUpdate) {
        if (isUpdate) {
            this.messageTemplateTagRepository.deleteByMessageTemplateId(messageTemplateId);
        }
        if (CollectionUtils.isEmpty(messageTemplateTags)) {
            return;
        }
        for (MessageTemplateTag messageTemplateTag : messageTemplateTags) {
            messageTemplateTag.setMessageTemplateTagId(Snowflake.stringId());
            messageTemplateTag.setMessageTemplateId(messageTemplateId);
        }
        this.messageTemplateTagRepository.saveAll(messageTemplateTags);
    }

    private void saveActions(String messageTemplateId, List<MessageTemplateAction> messageTemplateActions, boolean isUpdate) {
        if (isUpdate) {
            this.messageTemplateActionRepository.deleteByMessageTemplateId(messageTemplateId);
            this.messageTemplateActionRouteRepository.deleteByMessageTemplateId(messageTemplateId);
        }
        if (CollectionUtils.isEmpty(messageTemplateActions)) {
            return;
        }
        List<MessageTemplateActionRoute> messageTemplateActionRoutes = new ArrayList<>();
        for (MessageTemplateAction messageTemplateAction : messageTemplateActions) {
            messageTemplateAction.setMessageTemplateId(messageTemplateId);
            messageTemplateAction.setMessageTemplateActionId(Snowflake.stringId());

            if (ActionTypes.LINK.equalsTo(messageTemplateAction.getActionType())) {
                if (CollectionUtils.isEmpty(messageTemplateAction.getRoutes())) {
                    throw new ServiceException(MessageTemplateErrors.ACTION_ROUTE_NOT_EMPTY);
                }
                for (MessageTemplateActionRoute route : messageTemplateAction.getRoutes()) {
                    route.setMessageTemplateActionRouteId(Snowflake.stringId());
                    route.setMessageTemplateId(messageTemplateId);
                    route.setMessageTemplateActionId(messageTemplateAction.getMessageTemplateActionId());
                }
                messageTemplateActionRoutes.addAll(messageTemplateAction.getRoutes());
            }

        }
        this.messageTemplateActionRepository.saveAll(messageTemplateActions);
        if (CollectionUtils.isEmpty(messageTemplateActionRoutes)) {
            this.messageTemplateActionRouteRepository.saveAll(messageTemplateActionRoutes);
        }
    }
}