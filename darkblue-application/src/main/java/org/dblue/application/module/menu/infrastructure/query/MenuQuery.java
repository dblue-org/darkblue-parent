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
package org.dblue.application.module.menu.infrastructure.query;

import org.dblue.application.commons.db.jpa.BaseJpaQuery;
import org.dblue.application.commons.db.jpa.ConditionPredicate;
import org.dblue.application.commons.db.jpa.Conditions;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.core.enums.PlatformEnum;

import java.util.Collection;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface MenuQuery extends BaseJpaQuery<Menu> {


    MenuQuery menuId(String menuId);

    MenuQuery menuIdIn(Collection<String> menuId);

    MenuQuery parentId(String parentId);

    default MenuQuery platform(PlatformEnum platform) {
        return platform(platform.getValue());
    }

    default MenuQuery platform(Integer platform) {
        return this.platform(platform, Conditions::always);
    }

    MenuQuery platform(Integer platform, ConditionPredicate<Integer> condition);

    default MenuQuery platform(PlatformEnum platform, ConditionPredicate<Integer> condition) {
        return platform(platform.getValue(), condition);
    }

    default MenuQuery menuName(String menuName) {
        return menuName(menuName, Conditions::always);
    }

    MenuQuery menuName(String menuName, ConditionPredicate<String> condition);

    default MenuQuery menuNameLike(String menuName) {
        return menuNameLike(menuName, Conditions::always);
    }

    MenuQuery menuNameLike(String menuName, ConditionPredicate<String> condition);

    default MenuQuery menuUrlLike(String menuName) {
        return menuUrlLike(menuName, Conditions::always);
    }

    MenuQuery menuUrlLike(String menuUrl, ConditionPredicate<String> condition);

    default MenuQuery enabled() {
        return this.isEnable(true);
    }

    default MenuQuery isEnable(Boolean isEnable) {
        return isEnable(isEnable, Conditions::always);
    }

    MenuQuery isEnable(Boolean isEnable, ConditionPredicate<Boolean> condition);

    default MenuQuery visible() {
        return this.isVisible(true);
    }

    default MenuQuery isVisible(Boolean isVisible) {
        return isVisible(isVisible, Conditions::always);
    }

    MenuQuery isVisible(Boolean isVisible, ConditionPredicate<Boolean> condition);

    default MenuQuery productionVisible() {
        return this.isProductionVisible(true);
    }

    default MenuQuery isProductionVisible(Boolean isProductionVisible) {
        return isProductionVisible(isProductionVisible, Conditions::always);
    }

    MenuQuery isProductionVisible(Boolean isProductionVisible, ConditionPredicate<Boolean> condition);

    MenuQuery roleIdIn(Collection<String> roleId);


    MenuQuery orderBySortNum(boolean isAsc);

    MenuQuery orderByCreateTime(boolean isAsc);
}
