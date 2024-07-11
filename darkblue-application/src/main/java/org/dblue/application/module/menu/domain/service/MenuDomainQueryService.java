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

package org.dblue.application.module.menu.domain.service;

import org.dblue.application.commons.enums.PlatformEnum;
import org.dblue.application.module.menu.infrastructure.entity.Menu;

import java.util.List;
import java.util.Set;

/**
 * 菜单查询领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/17 下午2:16
 */
public interface MenuDomainQueryService {

    /**
     * 获取一条菜单数据
     * @param menuId 菜单Id
     * @return 菜单
     */
    Menu getOne(String menuId);

    /**
     * 获取所有菜单
     *
     * @param platformEnum 平台
     * @return 菜单列表
     */
    List<Menu> findAllMenus(PlatformEnum platformEnum);


    /**
     * 根据角色ID获取菜单信息
     *
     * @param roleIdSet 角色ID
     * @return 菜单信息
     */
    List<Menu> getMenuByRoleIds(Set<String> roleIdSet);

    /**
     * 获取所有菜单
     *
     * @return 菜单列表
     */
    List<Menu> findAllMenus();


    /**
     * 根据菜单ID获取菜单信息
     *
     * @param menuIdSet 菜单ID
     * @return 菜单
     */
    List<Menu> getMenuByMenuIds(Set<String> menuIdSet);


}
