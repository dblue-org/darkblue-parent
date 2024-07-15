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

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.role.infrastructure.entiry.QRole;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 角色
 *
 * @author xie jin
 * @since 1.0.0  2024-07-15 14:12:39
 */
public interface RoleRepository extends BaseJpaRepository<Role, String> {

    /**
     * 查询角色是否重复
     *
     * @param roleCode 角色编号
     * @param roleName 角色名称
     * @return 是否存在
     */
    Boolean existsByRoleCodeOrRoleName(@NonNull String roleCode, @NonNull String roleName);


    /**
     * 更新时查询是否有重复角色信息
     *
     * @param roleCode 角色编号
     * @param roleName 角色名称
     * @param roleId   角色ID
     * @return 是否存在
     */
    default boolean existsByRodeCodeOrRoleNameAndRoleIdNot(
            @Param("roleCode") @NonNull String roleCode, @Param("roleName") @NonNull String roleName,
            @Param("roleId") @NonNull String roleId) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QRole.role.roleCode.eq(roleCode).or(QRole.role.roleName.eq(roleName)));
        builder.and(QRole.role.roleId.ne(roleId));
        return exists(builder);
    }

    /**
     * 分页查询
     *
     * @param roleCode 角色编号
     * @param roleName 角色名称
     * @param pageable 分页
     * @return 角色信息
     */
    default Page<Role> findByRoleCodeLikeAndRoleNameLike(
            @Nullable String roleCode, @Nullable String roleName, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(roleCode)) {
            builder.and(QRole.role.roleCode.like("%" + roleCode + "%"));
        }
        if (StringUtils.isNotBlank(roleName)) {
            builder.and(QRole.role.roleName.like("%" + roleName + "%"));
        }
        return page(builder, pageable);
    }

    /**
     * 根据权限ID获取角色信息
     *
     * @param permissionId 权限ID
     * @return 角色
     */
    default Page<Role> getRoleByPermissionId(String permissionId, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QRole.role.permissions.any().permissionId.eq(permissionId));
        return page(builder, pageable);
    }

    /**
     * 获取全部可用角色
     *
     * @return 角色
     */
    List<Role> findByIsEnableTrue();


}