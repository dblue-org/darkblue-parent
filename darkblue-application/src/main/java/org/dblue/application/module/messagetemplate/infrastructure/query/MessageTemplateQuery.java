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
package org.dblue.application.module.messagetemplate.infrastructure.query;

import org.dblue.application.commons.db.jpa.BaseJpaQuery;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplate;

/**
 * 消息模板查询
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface MessageTemplateQuery extends BaseJpaQuery<MessageTemplate> {


    MessageTemplateQuery messageTemplateId(String messageTemplateId);


    MessageTemplateQuery messageTemplateIdNot(String messageTemplateId);


    MessageTemplateQuery messageTemplateGroupId(String messageTemplateGroupId);


    MessageTemplateQuery messageTemplateCodeLike(String messageTemplateCode);


    MessageTemplateQuery messageTemplateCode(String messageTemplateCode);


    MessageTemplateQuery messageTemplateNameLike(String messageTemplateName);


    MessageTemplateQuery messageTemplateName(String messageTemplateName);


    MessageTemplateQuery messageTemplateType(Integer messageTemplateType);


    MessageTemplateQuery includeRouters();


    MessageTemplateQuery includeTags();


    MessageTemplateQuery includeActions();
}
