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

package org.dblue.application.module.permission.infrastructure.repository;

import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, String> {

    /**
     * 根据授权标识查询授权标识是否已存在
     *
     * @param permissionCode 授权标识
     * @return 授权信息
     */
    Optional<Permission> findByPermissionCode(@NonNull String permissionCode);


    /**
     * 更新是判断新修改标识是否存在
     *
     * @param permissionCode 授权标识
     * @param permissionId   授权ID
     * @return 授权
     */
    boolean existsByPermissionCodeAndPermissionIdNot(@NonNull String permissionCode, @NonNull String permissionId);

    /**
     * 查询菜单下权限
     * @param menuId 菜单ID
     * @return 数量
     */
    long countByMenuId(@NonNull String menuId);


}