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

import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


public interface RoleRepository extends JpaRepository<Role, String> {



    /**
     * 查询角色是否重复
     * @param roleCode 角色编号
     * @param roleName 角色名称
     * @return 是否存在
     */
    Boolean existsByRoleCodeOrRoleName(@NonNull String roleCode, @NonNull String roleName);


    /**
     * 更新时查询是否有重复角色信息
     * @param roleCode 角色编号
     * @param roleName 角色名称
     * @param roleId 角色ID
     * @return 是否存在
     */
    @Query("""
            select (count(r) > 0) from Role r
            where (r.roleCode = :roleCode or r.roleName = :roleName) and r.roleId <> :roleId""")
    boolean existsByRodeCodeOrRoleNameAndRoleId(
            @Param("roleCode") @NonNull String roleCode, @Param("roleName") @NonNull String roleName, @Param("roleId") @NonNull String roleId);

    /**
     * 分页查询
     * @param roleCode 角色编号
     * @param roleName 角色名称
     * @param pageable 分页
     * @return 角色信息
     */
    Page<Role> findByRoleCodeAndRoleName(@Nullable String roleCode, @Nullable String roleName, Pageable pageable);


}