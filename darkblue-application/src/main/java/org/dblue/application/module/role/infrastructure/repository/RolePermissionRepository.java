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

package org.dblue.application.module.role.infrastructure.repository;

import org.dblue.application.module.role.infrastructure.entiry.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {

    /**
     * 统计角色权限关系数量
     * @param permissionId 权限ID
     * @return 数量
     */
    long countByPermissionId(@NonNull String permissionId);

    /**
     * 根据角色ID删除关联信息
     * @param roleId 角色ID
     */
    void deleteByRoleId(@NonNull String roleId);
}