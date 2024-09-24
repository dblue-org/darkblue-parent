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

import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.commons.db.jpa.BaseJpaQuery;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateGroup;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消息模板分组查询
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface MessageTemplateGroupQuery extends BaseJpaQuery<MessageTemplateGroup> {


    MessageTemplateGroupQuery messageTemplateGroupName(String messageTemplateGroupName);

    MessageTemplateGroupQuery messageTemplateGroupNameLike(String messageTemplateGroupName);

    MessageTemplateGroupQuery messageTemplateGroupIdIn(Collection<String> messageTemplateGroupIdList);

    MessageTemplateGroupQuery messageTemplateGroupIdNot(String messageTemplateGroupId);



    @Override
    default Map<String, String> nameMap() {
        List<MessageTemplateGroup> groupList = this.list();
        if (CollectionUtils.isEmpty(groupList)) {
            return Map.of();
        }
        return groupList.stream().collect(Collectors.toMap(
                MessageTemplateGroup::getMessageTemplateGroupId, MessageTemplateGroup::getMessageTemplateGroupName
        ));
    }
}
