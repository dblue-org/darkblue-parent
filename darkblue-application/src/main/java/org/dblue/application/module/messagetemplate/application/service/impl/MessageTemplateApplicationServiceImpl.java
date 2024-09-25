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
package org.dblue.application.module.messagetemplate.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateAddDto;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateQueryDto;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateUpdateDto;
import org.dblue.application.module.messagetemplate.application.service.MessageTemplateApplicationService;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateDetailsVo;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateListVo;
import org.dblue.application.module.messagetemplate.domain.errors.MessageTemplateErrors;
import org.dblue.application.module.messagetemplate.domain.service.MessageTemplateDomainService;
import org.dblue.application.module.messagetemplate.domain.service.MessageTemplateGroupDomainService;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplate;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateGroup;
import org.dblue.common.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class MessageTemplateApplicationServiceImpl implements MessageTemplateApplicationService {

    private final MessageTemplateDomainService messageTemplateDomainService;

    private final MessageTemplateGroupDomainService messageTemplateGroupDomainService;

    @Override
    public void add(MessageTemplateAddDto addDto) {
        MessageTemplate messageTemplate = addDto.asEntity();
        this.messageTemplateDomainService.add(messageTemplate);
    }

    @Override
    public void update(MessageTemplateUpdateDto updateDto) {
        Optional<MessageTemplate> messageTemplateOptional = this.messageTemplateDomainService
                .createQuery()
                .messageTemplateId(updateDto.getMessageTemplateId())
                .single();
        if (messageTemplateOptional.isEmpty()) {
            throw new ServiceException(MessageTemplateErrors.NOT_EXIST);
        }

        updateDto.merge(messageTemplateOptional.get());
        this.messageTemplateDomainService.update(messageTemplateOptional.get());
    }

    @Override
    public void delete(String messageTemplateId) {
        this.messageTemplateDomainService.delete(messageTemplateId);
    }

    @Override
    public Page<MessageTemplateListVo> findByPage(MessageTemplateQueryDto queryDto) {
        Page<MessageTemplate> messageTemplatePage = this.messageTemplateDomainService.createQuery()
                .messageTemplateGroupId(queryDto.getMessageTemplateGroupId())
                .messageTemplateCodeLike(queryDto.getMessageTemplateCode())
                .messageTemplateNameLike(queryDto.getMessageTemplateName())
                .messageTemplateType(queryDto.getMessageTemplateType())
                .page(queryDto.toJpaPage());

        Page<MessageTemplateListVo> voPage = messageTemplatePage.map(MessageTemplateListVo::of);

        if (messageTemplatePage.isEmpty()) {
            return voPage;
        }

        Set<String> messageTemplateGroupIdSet = messageTemplatePage
                .stream().map(MessageTemplate::getMessageTemplateGroupId).collect(Collectors.toSet());
        Map<String, String> groupNameMap = this.messageTemplateGroupDomainService.createQuery()
                .messageTemplateGroupIdIn(messageTemplateGroupIdSet)
                .nameMap();

        voPage.stream().forEach(vo -> vo.setMessageTemplateGroupName(groupNameMap.get(vo.getMessageTemplateGroupId())));

        return voPage;
    }

    @Override
    public MessageTemplateDetailsVo getDetails(String messageTemplateId) {
        Optional<MessageTemplate> messageTemplateOptional = this.messageTemplateDomainService
                .createQuery()
                .messageTemplateId(messageTemplateId)
                .includeRouters()
                .includeTags()
                .includeActions()
                .single();
        if (messageTemplateOptional.isEmpty()) {
            throw new ServiceException(MessageTemplateErrors.NOT_EXIST);
        }
        MessageTemplateDetailsVo messageTemplateDetailsVo = MessageTemplateDetailsVo.of(messageTemplateOptional.get());

        Optional<MessageTemplateGroup> messageTemplateGroupOptional = this.messageTemplateGroupDomainService.createQuery()
                .messageTemplateGroupId(messageTemplateDetailsVo.getMessageTemplateGroupId())
                .single();
        messageTemplateGroupOptional.ifPresent(
                messageTemplateGroup -> messageTemplateDetailsVo.setMessageTemplateGroupName(messageTemplateGroup.getMessageTemplateGroupName())
        );
        return messageTemplateDetailsVo;
    }
}