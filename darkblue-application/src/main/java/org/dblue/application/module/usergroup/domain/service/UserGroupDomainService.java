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

package org.dblue.application.module.usergroup.domain.service;

import org.dblue.application.module.usergroup.application.dto.*;

/**
 * 用户组领域信息
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 下午3:17
 */
public interface UserGroupDomainService {

    /**
     * 用户组添加
     *
     * @param addDto 用户组信息
     */
    void add(UserGroupAddDto addDto);


    /**
     * 用户组更新
     *
     * @param updateDto 用户组信息
     */
    void update(UserGroupUpdateDto updateDto);


    /**
     * 用户组删除
     *
     * @param userGroupId 用户组ID
     */
    void delete(String userGroupId);

    /**
     * 用户组启用/禁用
     *
     * @param enableDto 用户组启用/禁用信息
     */
    void toggleState(UserGroupEnableDto enableDto);


    /**
     * 用户组角色添加
     *
     * @param roleAddDto 用户组角色信息
     */
    void addRole(UserGroupRoleAddDto roleAddDto);


    /**
     * 用户组角色删除
     *
     * @param userGroupId 用户组Id
     * @param roleId 组角色Id
     */
    void deleteRoleRef(String userGroupId, String roleId);


    /**
     * 用户组用户添加
     *
     * @param userAddDto 用户组用户信息
     */
    void addUser(UserGroupUserAddDto userAddDto);

    /**
     * 用户组用户删除
     *
     * @param userGroupId 用户组Id
     * @param userId 用户Id
     */
    void deleteUser(String userGroupId, String userId);

    /**
     * 根据角色ID删除用户组和角色关联关系
     *
     * @param roleId 角色ID
     */
    void deleteRoleByRoleId(String roleId);

    /**
     * 用户组用户删除
     *
     * @param userId 用户Id
     */
    void deleteUserByUserId(String userId);

}
