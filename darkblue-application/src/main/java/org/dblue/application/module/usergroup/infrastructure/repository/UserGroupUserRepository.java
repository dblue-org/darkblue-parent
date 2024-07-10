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

import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 用户组用户
 *
 * @author xie jin
 * @since 1.0.0  2024-07-10 14:59:19
 */
public interface UserGroupUserRepository extends JpaRepository<UserGroupUser, String> {


    /**
     * 根据用户ID查询
     *
     * @param userIds 用户ID
     * @return 用户组用户
     */
    List<UserGroupUser> findByUserIdIn(@NonNull Collection<String> userIds);


    /**
     * 根据用户组ID获取用户组用户信息
     *
     * @param userGroupIdSet 用户组ID
     * @return 用户组用户
     */
    List<UserGroupUser> findByUserGroupIdIn(@NonNull Set<String> userGroupIdSet);

    /**
     * 用户组用户删除
     *
     * @param userGroupId 用户组ID
     */
    void deleteByUserGroupId(@NonNull String userGroupId);
}