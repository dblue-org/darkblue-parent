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
package org.dblue.application.module.messagetemplate.infrastructure.query.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.commons.db.jpa.AbstractBaseJpaQuery;
import org.dblue.application.module.messagetemplate.domain.enums.ActionTypes;
import org.dblue.application.module.messagetemplate.infrastructure.entity.*;
import org.dblue.application.module.messagetemplate.infrastructure.query.MessageTemplateQuery;
import org.dblue.application.module.messagetemplate.infrastructure.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class MessageTemplateQueryImpl extends AbstractBaseJpaQuery<MessageTemplate, String> implements MessageTemplateQuery {

    private final MessageTemplateDirectRouteRepository messageTemplateDirectRouteRepository;
    private final MessageTemplateTagRepository messageTemplateTagRepository;
    private final MessageTemplateActionRepository messageTemplateActionRepository;
    private final MessageTemplateActionRouteRepository messageTemplateActionRouteRepository;
    private boolean includeRouters = false;
    private boolean includeTags = false;
    private boolean includeActions = false;

    public MessageTemplateQueryImpl(
            MessageTemplateRepository messageTemplateRepository, MessageTemplateDirectRouteRepository messageTemplateDirectRouteRepository,
            MessageTemplateTagRepository messageTemplateTagRepository, MessageTemplateActionRepository messageTemplateActionRepository,
            MessageTemplateActionRouteRepository messageTemplateActionRouteRepository) {
        super(messageTemplateRepository);
        this.messageTemplateDirectRouteRepository = messageTemplateDirectRouteRepository;
        this.messageTemplateTagRepository = messageTemplateTagRepository;
        this.messageTemplateActionRepository = messageTemplateActionRepository;
        this.messageTemplateActionRouteRepository = messageTemplateActionRouteRepository;
    }

    @Override
    public MessageTemplateQuery messageTemplateId(String messageTemplateId) {
        this.queryBuilder.and(QMessageTemplate.messageTemplate.messageTemplateId.eq(messageTemplateId));
        return this;
    }

    @Override
    public MessageTemplateQuery messageTemplateIdNot(String messageTemplateId) {
        if (messageTemplateId != null) {
            this.queryBuilder.and(QMessageTemplate.messageTemplate.messageTemplateId.ne(messageTemplateId));
        }
        return this;
    }

    @Override
    public MessageTemplateQuery messageTemplateGroupId(String messageTemplateGroupId) {
        if (StringUtils.isNotBlank(messageTemplateGroupId)) {
            this.queryBuilder.and(QMessageTemplate.messageTemplate.messageTemplateGroupId.eq(messageTemplateGroupId));
        }
        return this;
    }

    @Override
    public MessageTemplateQuery messageTemplateCodeLike(String messageTemplateCode) {
        if (StringUtils.isNotBlank(messageTemplateCode)) {
            this.queryBuilder.and(QMessageTemplate.messageTemplate.messageTemplateCode.contains(messageTemplateCode));
        }
        return this;
    }

    @Override
    public MessageTemplateQuery messageTemplateCode(String messageTemplateCode) {
        if (StringUtils.isNotBlank(messageTemplateCode)) {
            this.queryBuilder.and(QMessageTemplate.messageTemplate.messageTemplateCode.eq(messageTemplateCode));
        }
        return this;
    }

    @Override
    public MessageTemplateQuery messageTemplateNameLike(String messageTemplateName) {
        if (StringUtils.isNotBlank(messageTemplateName)) {
            this.queryBuilder.and(QMessageTemplate.messageTemplate.messageTemplateName.contains(messageTemplateName));
        }
        return this;
    }

    @Override
    public MessageTemplateQuery messageTemplateName(String messageTemplateName) {
        if (StringUtils.isNotBlank(messageTemplateName)) {
            this.queryBuilder.and(QMessageTemplate.messageTemplate.messageTemplateName.eq(messageTemplateName));
        }
        return this;
    }

    @Override
    public MessageTemplateQuery messageTemplateType(Integer messageTemplateType) {
        if (messageTemplateType != null) {
            this.queryBuilder.and(QMessageTemplate.messageTemplate.messageTemplateType.eq(messageTemplateType));
        }
        return this;
    }

    @Override
    public MessageTemplateQuery includeRouters() {
        this.includeRouters = true;
        return this;
    }

    @Override
    public MessageTemplateQuery includeTags() {
        this.includeTags = true;
        return this;
    }

    @Override
    public MessageTemplateQuery includeActions() {
        this.includeActions = true;
        return this;
    }

    @Override
    public List<MessageTemplate> list() {
        List<MessageTemplate> messageTemplates = super.list();
        this.setRelationship(messageTemplates);
        return messageTemplates;
    }

    @Override
    public Page<MessageTemplate> page(int page, int pageSize) {
        Page<MessageTemplate> messageTemplatePage = super.page(page, pageSize);
        this.setRelationship(messageTemplatePage.getContent());
        return messageTemplatePage;
    }

    @Override
    public Page<MessageTemplate> page(Pageable pageable) {
        Page<MessageTemplate> messageTemplatePage = super.page(pageable);
        this.setRelationship(messageTemplatePage.getContent());
        return messageTemplatePage;
    }

    @Override
    public Optional<MessageTemplate> single() {
        Optional<MessageTemplate> messageTemplateOptional = super.single();
        if (messageTemplateOptional.isPresent()) {
            MessageTemplate messageTemplate = messageTemplateOptional.get();
            this.setRelationship(List.of(messageTemplate));
        }
        return messageTemplateOptional;
    }

    private void setRelationship(List<MessageTemplate> messageTemplates) {
        if (CollectionUtils.isEmpty(messageTemplates)) {
            return;
        }
        Set<String> messageTemplateIdSet = messageTemplates.stream().map(MessageTemplate::getMessageTemplateId).collect(Collectors.toSet());
        Map<String, List<MessageTemplateDirectRoute>> routeMap = Map.of();
        Map<String, List<MessageTemplateTag>> tagMap = Map.of();
        Map<String, List<MessageTemplateAction>> actionMap = Map.of();
        if (this.includeRouters) {
            routeMap = this.getRouters(messageTemplateIdSet);
        }
        if (this.includeTags) {
            tagMap = this.getTags(messageTemplateIdSet);
        }
        if (this.includeActions) {
            actionMap = this.getActions(messageTemplateIdSet);
        }
        for (MessageTemplate messageTemplate : messageTemplates) {
            if (routeMap.get(messageTemplate.getMessageTemplateId()) != null) {
                messageTemplate.setRoutes(new ArrayList<>(routeMap.get(messageTemplate.getMessageTemplateId())));
            }
            if (tagMap.get(messageTemplate.getMessageTemplateId()) != null) {
                messageTemplate.setTags(new ArrayList<>(tagMap.get(messageTemplate.getMessageTemplateId())));
            }
            if (actionMap.get(messageTemplate.getMessageTemplateId()) != null) {
                messageTemplate.setActions(new ArrayList<>(actionMap.get(messageTemplate.getMessageTemplateId())));
            }
        }
    }

    private Map<String, List<MessageTemplateDirectRoute>> getRouters(Set<String> messageTemplateIdSet) {
        List<MessageTemplateDirectRoute> messageTemplateDirectRoutes = this.messageTemplateDirectRouteRepository
                .findByMessageTemplateIdIn(messageTemplateIdSet);
        if (CollectionUtils.isEmpty(messageTemplateDirectRoutes)) {
            return Map.of();
        }
        return messageTemplateDirectRoutes.stream().collect(Collectors.groupingBy(MessageTemplateDirectRoute::getMessageTemplateId));
    }

    private Map<String, List<MessageTemplateTag>> getTags(Set<String> messageTemplateIdSet) {
        List<MessageTemplateTag> messageTemplateTags = this.messageTemplateTagRepository.findByMessageTemplateIdIn(messageTemplateIdSet);
        if (CollectionUtils.isEmpty(messageTemplateTags)) {
            return Map.of();
        }
        return messageTemplateTags.stream().collect(Collectors.groupingBy(MessageTemplateTag::getMessageTemplateId));
    }

    private Map<String, List<MessageTemplateAction>> getActions(Set<String> messageTemplateIdSet) {
        List<MessageTemplateAction> messageTemplateActions = this.messageTemplateActionRepository.findByMessageTemplateIdIn(messageTemplateIdSet);

        Set<String> messageTemplateActionIdSet = messageTemplateActions.stream()
                .filter(o -> ActionTypes.LINK.equalsTo(o.getActionType()))
                .map(MessageTemplateAction::getMessageTemplateActionId)
                .collect(Collectors.toSet());

        List<MessageTemplateActionRoute> messageTemplateActionRoutes = this.messageTemplateActionRouteRepository
                .findByMessageTemplateActionIdIn(messageTemplateActionIdSet);
        Map<String, List<MessageTemplateActionRoute>> messageTemplateActionRouteMap = messageTemplateActionRoutes
                .stream().collect(Collectors.groupingBy(MessageTemplateActionRoute::getMessageTemplateActionId));
        for (MessageTemplateAction messageTemplateAction : messageTemplateActions) {
            if (ActionTypes.LINK.equalsTo(messageTemplateAction.getActionType())) {
                messageTemplateAction.setRoutes(messageTemplateActionRouteMap.get(messageTemplateAction.getMessageTemplateActionId()));
            }
        }
        return messageTemplateActions.stream().collect(Collectors.groupingBy(MessageTemplateAction::getMessageTemplateId));
    }
}