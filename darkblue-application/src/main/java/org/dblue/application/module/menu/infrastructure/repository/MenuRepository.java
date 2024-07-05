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

package org.dblue.application.module.menu.infrastructure.repository;

import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, String> {

    /**
     * 同一平台下菜单名称不能重复
     * @param platform 平台
     * @param menuName 菜单名称
     * @return 菜单信息
     */
    Optional<Menu> findByPlatformAndMenuName(@NonNull Integer platform, @NonNull String menuName);


    /**
     * 更新是查询是否重复菜单名称
     * @param platform 平台
     * @param menuName  菜单名称
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    Optional<Menu> findByPlatformAndMenuNameAndMenuIdNot(
            @NonNull Integer platform, @NonNull String menuName, @NonNull String menuId);


    /**
     * 根据平台查询菜单信息
     * @param platform 平台
     * @return 菜单信息
     */
    List<Menu> findByPlatformOrderBySortNum(@NonNull Integer platform);


    /**
     * 查询子节点数量
     * @param parentId 父节点ID
     * @return 子节点数量
     */
    long countByParentId(@NonNull String parentId);


}