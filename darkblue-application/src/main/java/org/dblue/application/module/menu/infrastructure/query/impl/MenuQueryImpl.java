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
package org.dblue.application.module.menu.infrastructure.query.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.commons.db.jpa.AbstractBaseJpaQuery;
import org.dblue.application.commons.db.jpa.ConditionPredicate;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.application.module.menu.infrastructure.entity.QMenu;
import org.dblue.application.module.menu.infrastructure.query.MenuQuery;
import org.dblue.application.module.menu.infrastructure.repository.MenuRepository;

import java.util.Collection;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class MenuQueryImpl extends AbstractBaseJpaQuery<Menu, String> implements MenuQuery {

    public MenuQueryImpl(MenuRepository menuRepository) {
        super(menuRepository);
    }


    @Override
    public MenuQuery menuId(String menuId) {
        this.queryBuilder.and(QMenu.menu.menuId.eq(menuId));
        return this;
    }

    @Override
    public MenuQuery menuIdIn(Collection<String> menuId) {
        if (CollectionUtils.isNotEmpty(menuId)) {
            this.queryBuilder.and(QMenu.menu.menuId.in(menuId));
        }
        return this;
    }

    @Override
    public MenuQuery parentId(String parentId) {
        this.queryBuilder.and(QMenu.menu.parentId.eq(parentId));
        return this;
    }

    @Override
    public MenuQuery platform(Integer platform, ConditionPredicate<Integer> condition) {
        if (condition.test(platform)) {
            this.queryBuilder.and(QMenu.menu.platform.eq(platform));
        }
        return this;
    }

    @Override
    public MenuQuery menuName(String menuName, ConditionPredicate<String> condition) {
        if (condition.test(menuName)) {
            this.queryBuilder.and(QMenu.menu.menuName.eq(menuName));
        }
        return this;
    }

    @Override
    public MenuQuery menuNameLike(String menuName, ConditionPredicate<String> condition) {
        if (condition.test(menuName)) {
            this.queryBuilder.and(QMenu.menu.menuName.contains(menuName));
        }
        return this;
    }

    @Override
    public MenuQuery menuUrlLike(String menuUrl, ConditionPredicate<String> condition) {
        if (condition.test(menuUrl)) {
            this.queryBuilder.and(QMenu.menu.menuUrl.contains(menuUrl));
        }
        return this;
    }

    @Override
    public MenuQuery isEnable(Boolean isEnable, ConditionPredicate<Boolean> condition) {
        if (condition.test(isEnable)) {
            this.queryBuilder.and(QMenu.menu.isEnable.eq(isEnable));
        }
        return this;
    }

    @Override
    public MenuQuery isVisible(Boolean isVisible, ConditionPredicate<Boolean> condition) {
        if (condition.test(isVisible)) {
            this.queryBuilder.and(QMenu.menu.isVisible.eq(isVisible));
        }
        return this;
    }

    @Override
    public MenuQuery isProductionVisible(Boolean isProductionVisible, ConditionPredicate<Boolean> condition) {
        if (condition.test(isProductionVisible)) {
            this.queryBuilder.and(QMenu.menu.isProductionVisible.eq(isProductionVisible));
        }
        return this;
    }

    @Override
    public MenuQuery roleIdIn(Collection<String> roleId) {
        if (CollectionUtils.isNotEmpty(roleId)) {
            this.queryBuilder.and(QMenu.menu.roleMenuList.any().roleId.in(roleId));
        }
        return this;
    }

    @Override
    public MenuQuery orderBySortNum(boolean isAsc) {
        if (isAsc) {
            this.orderSpecifiers.add(QMenu.menu.sortNum.asc());
        } else {
            this.orderSpecifiers.add(QMenu.menu.sortNum.desc());
        }
        return this;
    }

    @Override
    public MenuQuery orderByCreateTime(boolean isAsc) {
        if (isAsc) {
            this.orderSpecifiers.add(QMenu.menu.createTime.asc());
        } else {
            this.orderSpecifiers.add(QMenu.menu.createTime.desc());
        }
        return this;
    }
}