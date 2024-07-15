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

package org.dblue.application.module.usergroup.infrastructure.repository;

import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * 用户组角色
 *
 * @author xie jin
 * @since 1.0.0  2024-07-10 14:58:45
 */
public interface UserGroupRoleRepository extends JpaRepository<UserGroupRole, String> {


    /**
     * 根据角色ID删除用户组和角色关联关系
     *
     * @param roleId 角色ID
     */
    void deleteByRoleId(@NonNull String roleId);

    /**
     * 用户组角色删除
     *
     * @param userGroupId 用户组ID
     */
    void deleteByUserGroupId(@NonNull String userGroupId);

    /**
     * 获取用户组和角色关联
     *
     * @param userGroupId 用户组ID
     * @param roleId      角色ID
     * @return 关联关系
     */
    UserGroupRole findOneByUserGroupIdAndRoleId(@NonNull String userGroupId, @NonNull String roleId);

    /**
     * 根据用户组ID和角色ID列表查询用户组角色关联关系
     *
     * @param userGroupId 用户组ID
     * @param roleIdSet 角色ID列表
     * @return 关联关系列表
     */
    List<UserGroupRole> findByUserGroupIdAndRoleIdIn(@NonNull String userGroupId, @NonNull Collection<String> roleIdSet);


    /**
     * 根据用户组ID获取用户组角色
     *
     * @param userGroupIds 用户组ID
     * @return 用户组角色
     */
    List<UserGroupRole> findByUserGroupIdIn(@NonNull Collection<String> userGroupIds);

    /**
     * 分页查询用户组关联的用户（关联关系）
     *
     * @param userGroupId 用户组ID
     * @param pageable    分页参数
     * @return 关联关系列表
     */
    Page<UserGroupRole> findByUserGroupId(@NonNull String userGroupId, Pageable pageable);

}