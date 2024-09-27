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

import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateActionRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MessageTemplateActionRouteRepository extends JpaRepository<MessageTemplateActionRoute, String> {


    List<MessageTemplateActionRoute> findByMessageTemplateActionId(String messageTemplateActionId);

    List<MessageTemplateActionRoute> findByMessageTemplateActionIdIn(Collection<String> messageTemplateActionIdSet);

    /**
     * 删除消息模板对应的操作路由信息
     *
     * @param messageTemplateId 消息模板ID
     */
    @Modifying
    @Query("delete from MessageTemplateActionRoute mtar where mtar.messageTemplateId = ?1")
    void deleteByMessageTemplateId(String messageTemplateId);
}