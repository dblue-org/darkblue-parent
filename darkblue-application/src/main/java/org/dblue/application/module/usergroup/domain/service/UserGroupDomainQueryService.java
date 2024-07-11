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

import org.dblue.application.module.usergroup.application.dto.UserGroupPageDto;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroup;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupRole;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupUser;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * 用户组查询领域
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 下午3:24
 */
public interface UserGroupDomainQueryService {

    /**
     * 获取单独一个
     *
     * @param userGroupId 用户组ID
     * @return 用户组信息
     */
    UserGroup getOne(String userGroupId);

    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 分组
     */
    Page<UserGroup> page(UserGroupPageDto pageDto);


    /**
     * 根据用户组ID获取用户组用户信息
     *
     * @param userGroupIdSet 用户组ID
     * @return 用户组用户
     */
    List<UserGroupUser> getUserGroupUserByUserGroupId(Set<String> userGroupIdSet);


    /**
     * 根据用户ID获取用户组角色
     *
     * @param userId 用户ID
     * @return 用户组角色
     */
    List<UserGroupRole> getUserGroupRoleByUserId(String userId);


}
