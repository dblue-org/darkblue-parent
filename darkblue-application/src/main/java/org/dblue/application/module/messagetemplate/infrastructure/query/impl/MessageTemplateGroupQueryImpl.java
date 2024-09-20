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
import org.dblue.application.commons.db.jpa.AbstractBaseJpaQuery;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateGroup;
import org.dblue.application.module.messagetemplate.infrastructure.entity.QMessageTemplateGroup;
import org.dblue.application.module.messagetemplate.infrastructure.query.MessageTemplateGroupQuery;

import java.util.Collection;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class MessageTemplateGroupQueryImpl extends AbstractBaseJpaQuery<MessageTemplateGroup, String> implements MessageTemplateGroupQuery {


    public MessageTemplateGroupQueryImpl(BaseJpaRepository<MessageTemplateGroup, String> executor) {
        super(executor);
    }

    @Override
    public MessageTemplateGroupQuery messageTemplateGroupNameLike(String messageTemplateGroupName) {
        if (messageTemplateGroupName != null) {
            this.queryBuilder.and(QMessageTemplateGroup.messageTemplateGroup.messageTemplateGroupName.like(messageTemplateGroupName));
        }
        return this;
    }

    @Override
    public MessageTemplateGroupQuery messageTemplateGroupIdIn(Collection<String> messageTemplateGroupIdList) {
        if (CollectionUtils.isNotEmpty(messageTemplateGroupIdList)) {
            this.queryBuilder.and(QMessageTemplateGroup.messageTemplateGroup.messageTemplateGroupId.in(messageTemplateGroupIdList));
        }
        return this;
    }
}