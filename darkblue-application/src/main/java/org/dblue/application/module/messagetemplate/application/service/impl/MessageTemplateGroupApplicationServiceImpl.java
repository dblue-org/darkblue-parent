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
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateGroupAddDto;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateGroupUpdateDto;
import org.dblue.application.module.messagetemplate.application.service.MessageTemplateGroupApplicationService;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateGroupVo;
import org.dblue.application.module.messagetemplate.domain.service.MessageTemplateGroupDomainService;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class MessageTemplateGroupApplicationServiceImpl implements MessageTemplateGroupApplicationService {

    private final MessageTemplateGroupDomainService messageTemplateGroupDomainService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(MessageTemplateGroupAddDto groupAddDto) {
        MessageTemplateGroup messageTemplateGroup = this.messageTemplateGroupDomainService.createGroup(groupAddDto.getMessageTemplateGroupName());
        this.messageTemplateGroupDomainService.add(messageTemplateGroup);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(MessageTemplateGroupUpdateDto updateDto) {
        this.messageTemplateGroupDomainService.update(updateDto.asEntity());
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<MessageTemplateGroupVo> findAll() {
        List<MessageTemplateGroup> groupList = this.messageTemplateGroupDomainService.createQuery().list();
        return groupList.stream().map(MessageTemplateGroupVo::of).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String messageTemplateGroupId) {
        // TODO 检查消息模板组是否被使用
        this.messageTemplateGroupDomainService.delete(messageTemplateGroupId);
    }
}