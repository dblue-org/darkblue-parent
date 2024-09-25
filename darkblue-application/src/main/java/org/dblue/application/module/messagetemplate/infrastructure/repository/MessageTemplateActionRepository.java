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

package org.dblue.application.module.messagetemplate.infrastructure.repository;

import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface MessageTemplateActionRepository extends JpaRepository<MessageTemplateAction, String> {

    /**
     * 根据消息模板ID获取操作
     *
     * @param messageTemplateId 消息模板ID
     * @return 操作列表
     */
    List<MessageTemplateAction> findByMessageTemplateId(String messageTemplateId);

    /**
     * 根据消息模板ID列表获取操作
     *
     * @param messageTemplateIdSet 消息模板ID列表
     * @return 操作列表
     */
    List<MessageTemplateAction> findByMessageTemplateIdIn(Collection<String> messageTemplateIdSet);

    /**
     * 删除消息模板对应的操作
     *
     * @param messageTemplateId 消息模板ID
     */
    void deleteByMessageTemplateId(String messageTemplateId);
}