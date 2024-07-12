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
package org.dblue.application.module.resource.infrastructure.query.impl;

import org.apache.commons.lang3.StringUtils;
import org.dblue.application.commons.db.jpa.AbstractBaseJpaQuery;
import org.dblue.application.commons.enums.PlatformEnum;
import org.dblue.application.module.resource.infrastructure.entity.QResourceGroup;
import org.dblue.application.module.resource.infrastructure.entity.ResourceGroup;
import org.dblue.application.module.resource.infrastructure.query.ResourceGroupQuery;
import org.dblue.application.module.resource.infrastructure.repository.ResourceGroupRepository;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class ResourceGroupQueryImpl extends AbstractBaseJpaQuery<ResourceGroup, String> implements ResourceGroupQuery {

    public ResourceGroupQueryImpl(ResourceGroupRepository resourceGroupRepository) {
        super(resourceGroupRepository);
    }

    @Override
    public ResourceGroupQuery platform(Integer platform) {
        if (platform != null) {
            this.queryBuilder.and(QResourceGroup.resourceGroup.platform.eq(platform));
        }
        return this;
    }

    @Override
    public ResourceGroupQuery platform(PlatformEnum platform) {
        if (platform != null) {
            this.queryBuilder.and(QResourceGroup.resourceGroup.platform.eq(platform.getValue()));
        }
        return this;
    }

    @Override
    public ResourceGroupQuery resourceGroupId(String resourceGroupId) {
        this.queryBuilder.and(QResourceGroup.resourceGroup.resourceGroupId.eq(resourceGroupId));
        return this;
    }

    @Override
    public ResourceGroupQuery groupName(String groupName) {
        if (StringUtils.isNotBlank(groupName)) {
            this.queryBuilder.and(QResourceGroup.resourceGroup.groupName.eq(groupName));
        }
        return this;
    }

    @Override
    public ResourceGroupQuery groupNameLike(String groupName) {
        if (StringUtils.isNotBlank(groupName)) {
            this.queryBuilder.and(QResourceGroup.resourceGroup.groupName.like(groupName));
        }
        return this;
    }

    @Override
    public ResourceGroupQuery orderBySortNum() {
        this.orderSpecifiers.add(QResourceGroup.resourceGroup.sortNum.asc());
        return this;
    }
}