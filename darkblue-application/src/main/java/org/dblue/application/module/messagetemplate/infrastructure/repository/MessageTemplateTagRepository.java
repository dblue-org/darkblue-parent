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

import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MessageTemplateTagRepository extends JpaRepository<MessageTemplateTag, String> {

    /**
     * 根据消息模板ID获取标签
     *
     * @param messageTemplateId 消息模板ID
     * @return 标签列表
     */
    List<MessageTemplateTag> findByMessageTemplateId(String messageTemplateId);

    /**
     * 根据消息模板ID列表获取标签
     *
     * @param messageTemplateIdSet 消息模板ID列表
     * @return 标签列表
     */
    List<MessageTemplateTag> findByMessageTemplateIdIn(Collection<String> messageTemplateIdSet);

    /**
     * 删除消息模板对应的标签
     *
     * @param messageTemplateId 消息模板ID
     */
    @Modifying
    @Query("delete from MessageTemplateTag mtt where mtt.messageTemplateId = ?1")
    void deleteByMessageTemplateId(String messageTemplateId);
}