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
import org.dblue.application.module.messagetemplate.domain.errors.MessageTemplateGroupErrors;
import org.dblue.application.module.messagetemplate.domain.service.MessageTemplateGroupDomainService;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateGroup;
import org.dblue.application.module.messagetemplate.infrastructure.query.MessageTemplateGroupQuery;
import org.dblue.application.module.messagetemplate.infrastructure.query.impl.MessageTemplateGroupQueryImpl;
import org.dblue.application.module.messagetemplate.infrastructure.repository.MessageTemplateGroupRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 消息模板分组业务
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class MessageTemplateGroupDomainServiceImpl implements MessageTemplateGroupDomainService {

    private final MessageTemplateGroupRepository messageTemplateGroupRepository;

    @Override
    public MessageTemplateGroup createGroup(String messageTemplateGroupName) {
        MessageTemplateGroup messageTemplateGroup = new MessageTemplateGroup();
        messageTemplateGroup.setMessageTemplateGroupName(messageTemplateGroupName);
        messageTemplateGroup.setMessageTemplateGroupId(Snowflake.stringId());
        return messageTemplateGroup;
    }

    @Override
    public Optional<MessageTemplateGroup> get(String messageTemplateGroupId) {
        return this.messageTemplateGroupRepository.findById(messageTemplateGroupId);
    }

    @Override
    public void add(MessageTemplateGroup messageTemplateGroup) {
        long existCount = this.createQuery()
                .messageTemplateGroupName(messageTemplateGroup.getMessageTemplateGroupName())
                .count();
        if (existCount > 0) {
            throw new ServiceException(MessageTemplateGroupErrors.NAME_EXIST);
        }
        this.messageTemplateGroupRepository.save(messageTemplateGroup);
    }

    @Override
    public void update(MessageTemplateGroup messageTemplateGroup) {
        Optional<MessageTemplateGroup> messageTemplateGroupOptional = this.get(messageTemplateGroup.getMessageTemplateGroupId());
        if (messageTemplateGroupOptional.isEmpty()) {
            throw new ServiceException(MessageTemplateGroupErrors.NOT_EXIST);
        }

        long existCount = this.createQuery()
                .messageTemplateGroupName(messageTemplateGroup.getMessageTemplateGroupName())
                .messageTemplateGroupIdNot(messageTemplateGroup.getMessageTemplateGroupId())
                .count();
        if (existCount > 0) {
            throw new ServiceException(MessageTemplateGroupErrors.NAME_EXIST);
        }

        MessageTemplateGroup dbMessageTemplateGroup = messageTemplateGroupOptional.get();
        dbMessageTemplateGroup.setMessageTemplateGroupName(messageTemplateGroup.getMessageTemplateGroupName());
        this.messageTemplateGroupRepository.save(dbMessageTemplateGroup);
    }

    @Override
    public void delete(String messageTemplateGroupId) {
        Optional<MessageTemplateGroup> messageTemplateGroupOptional = this.get(messageTemplateGroupId);
        if (messageTemplateGroupOptional.isEmpty()) {
            throw new ServiceException(MessageTemplateGroupErrors.NOT_EXIST);
        }
        this.messageTemplateGroupRepository.deleteById(messageTemplateGroupId);
    }

    @Override
    public MessageTemplateGroupQuery createQuery() {
        return new MessageTemplateGroupQueryImpl(this.messageTemplateGroupRepository);
    }
}