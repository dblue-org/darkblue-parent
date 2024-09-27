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

import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplate;
import org.dblue.application.module.messagetemplate.infrastructure.query.MessageTemplateQuery;

/**
 * 消息模板管理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface MessageTemplateDomainService {

    /**
     * 添加消息模板
     *
     * @param messageTemplate 消息模板
     * @return 消息模板
     */
    MessageTemplate add(MessageTemplate messageTemplate);

    /**
     * 更新消息模板
     *
     * @param messageTemplate 消息模板
     * @return 消息模板
     */
    MessageTemplate update(MessageTemplate messageTemplate);

    /**
     * 清除消息模板的附属信息
     *
     * @param messageTemplateId 消息模板ID
     */
    void clearAffiliatedData(String messageTemplateId);

    /**
     * 删除消息模板
     *
     * @param messageTemplateId 消息模板ID
     */
    void delete(String messageTemplateId);

    /**
     * 创建查询器
     *
     * @return 查询器
     */
    MessageTemplateQuery createQuery();

}
