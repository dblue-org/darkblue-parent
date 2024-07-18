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
package org.dblue.application.module.permission.infrastructure.query.impl;

import org.dblue.application.commons.db.jpa.AbstractBaseJpaQuery;
import org.dblue.application.commons.db.jpa.ConditionPredicate;
import org.dblue.application.commons.enums.PlatformEnum;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.permission.infrastructure.entiry.QPermission;
import org.dblue.application.module.permission.infrastructure.query.PermissionQuery;
import org.dblue.application.module.permission.infrastructure.repository.PermissionRepository;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class PermissionQueryImpl extends AbstractBaseJpaQuery<Permission, String> implements PermissionQuery {

    public PermissionQueryImpl(PermissionRepository permissionRepository) {
        super(permissionRepository);
    }

    @Override
    public PermissionQuery permissionId(String permissionId) {
        this.queryBuilder.and(QPermission.permission.permissionId.eq(permissionId));
        return this;
    }

    @Override
    public PermissionQuery platform(Integer platform) {
        if (platform != null) {
            this.queryBuilder.and(QPermission.permission.platform.eq(platform));
        }
        return this;
    }

    @Override
    public PermissionQuery platform(PlatformEnum platform) {
        if (platform != null) {
            this.queryBuilder.and(QPermission.permission.platform.eq(platform.getValue()));
        }
        return this;
    }

    @Override
    public PermissionQuery menuId(String menuId) {
        if (menuId != null) {
            this.queryBuilder.and(QPermission.permission.menuId.eq(menuId));
        }
        return this;
    }

    @Override
    public PermissionQuery menuId(String menuId, ConditionPredicate<String> conditionPredicate) {
        if (conditionPredicate.test(menuId)) {
            this.queryBuilder.and(QPermission.permission.menuId.eq(menuId));
        }
        return this;
    }

    @Override
    public PermissionQuery permissionName(String permissionName) {
        if (permissionName != null) {
            this.queryBuilder.and(QPermission.permission.permissionName.eq(permissionName));
        }
        return this;
    }

    @Override
    public PermissionQuery permissionCode(String permissionCode) {
        if (permissionCode != null) {
            this.queryBuilder.and(QPermission.permission.permissionCode.eq(permissionCode));
        }
        return this;
    }

    @Override
    public PermissionQuery permissionNameLike(String permissionName) {
        if (permissionName != null) {
            this.queryBuilder.and(QPermission.permission.permissionName.contains(permissionName));
        }
        return this;
    }

    @Override
    public PermissionQuery permissionCodeLike(String permissionCode) {
        if (permissionCode != null) {
            this.queryBuilder.and(QPermission.permission.permissionCode.contains(permissionCode));
        }
        return this;
    }

    @Override
    public PermissionQuery orderByCreateTime(boolean isAsc) {
        if (isAsc) {
            this.orderSpecifiers.add(QPermission.permission.createTime.asc());
        } else {
            this.orderSpecifiers.add(QPermission.permission.createTime.desc());
        }
        return this;
    }
}