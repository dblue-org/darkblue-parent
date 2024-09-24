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
package org.dblue.application.module.messagetemplate.domain.service;

import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateGroup;
import org.dblue.application.module.messagetemplate.infrastructure.query.MessageTemplateGroupQuery;

import java.util.Optional;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface MessageTemplateGroupDomainService {

    /**
     * 构造一个消息模板分组
     *
     * @param messageTemplateGroupName 消息模板分组名称
     * @return 消息模板分组名称
     */
    MessageTemplateGroup createGroup(String messageTemplateGroupName);

    /**
     * 获取消息模板分组
     *
     * @param messageTemplateGroupId 消息模板分组ID
     * @return 消息模板分组
     */
    Optional<MessageTemplateGroup> get(String messageTemplateGroupId);


    /**
     * 添加消息模板分组
     *
     * @param messageTemplateGroup 消息模板分组
     */
    void add(MessageTemplateGroup messageTemplateGroup);

    /**
     * 更新消息模板分组
     *
     * @param messageTemplateGroup 消息模板分组
     */
    void update(MessageTemplateGroup messageTemplateGroup);

    /**
     * 删除消息模板分组
     *
     * @param messageTemplateGroupId 消息模板分组
     */
    void delete(String messageTemplateGroupId);

    /**
     * 创检查询
     *
     * @return 查询构造器
     */
    MessageTemplateGroupQuery createQuery();
}
