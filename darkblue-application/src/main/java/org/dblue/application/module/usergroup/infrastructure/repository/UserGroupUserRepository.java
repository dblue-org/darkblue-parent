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

import com.querydsl.core.BooleanBuilder;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.usergroup.infrastructure.entity.QUserGroupUser;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface UserGroupUserRepository extends BaseJpaRepository<UserGroupUser, String> {

    /**
     * 根据用户组ID和用户ID查询其关联关系是否存在
     *
     * @param userGroupId 用户组ID
     * @param userIds 用户ID
     * @return 用户组用户
     */
    List<UserGroupUser> findByUserGroupIdAndUserIdIn(@NonNull String userGroupId, @NonNull Collection<String> userIds);


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

    /**
     * 分页查询用户组关联的用户（关联关系）
     *
     * @param userGroupId 用户组ID
     * @param pageable    分页参数
     * @return 关联关系列表
     */
    Page<UserGroupUser> findByUserGroupId(String userGroupId, Pageable pageable);

    /**
     * 根据用户组和用户获取其关联关系
     *
     * @param userGroupId 用户组ID
     * @param userId      用户ID
     * @return 关联关系
     */
    UserGroupUser findOneByUserGroupIdAndUserId(@NonNull String userGroupId, @NonNull String userId);

    /**
     * 根据用户ID获取用户组用户
     *
     * @param userId 用户ID
     * @return 用户组用户
     */
    default List<UserGroupUser> getUserGroupUserByUserId(String userId) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QUserGroupUser.userGroupUser.userId.eq(userId));
        builder.and(QUserGroupUser.userGroupUser.userGroup.isEnable.isTrue());
        return getList(builder);
    }

    /**
     * 用户组用户删除
     *
     * @param userId 用户ID
     */
    void deleteByUserId(@NonNull String userId);


}