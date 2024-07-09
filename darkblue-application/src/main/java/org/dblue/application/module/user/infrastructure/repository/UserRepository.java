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
package org.dblue.application.module.user.infrastructure.repository;

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.user.infrastructure.entity.QUser;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * 用户
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface UserRepository extends BaseJpaRepository<User, String> {

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);


    /**
     * 更新检测用户名是否重复
     *
     * @param username 用户名
     * @param userId   用户ID
     * @return 用户
     */
    Optional<User> findByUsernameAndUserIdAndIsDelIsFalse(@NonNull String username, @NonNull String userId);


    /**
     * 分页查询
     *
     * @param name        姓名
     * @param username    用户名
     * @param phoneNumber 手机号
     * @param deptId      部门ID
     * @param pageable    分页参数
     * @return 用户信息
     */
    default Page<User> findByNameLikeAndUsernameLikeAndPhoneNumberLike(
            @Nullable String name, @Nullable String username, @Nullable String phoneNumber, String deptId,
            Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(deptId)) {
            builder.and(QUser.user.deptId.eq(deptId));
        }
        if (StringUtils.isNotBlank(name)) {
            builder.and(QUser.user.name.likeIgnoreCase(name));
        }
        if (StringUtils.isNotBlank(username)) {
            builder.and(QUser.user.username.likeIgnoreCase(username));
        }
        if (StringUtils.isNotBlank(phoneNumber)) {
            builder.and(QUser.user.phoneNumber.likeIgnoreCase(phoneNumber));
        }
        return page(builder, pageable);
    }


    /**
     * 姓名或用户名查询可用用户信息
     *
     * @param name 姓名/用户名
     * @return 用户信息
     */
    default List<User> findByNameLikeAndUsernameLikeAndIsDelFalseAndIsEnableTrue(String name) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QUser.user.isDel.isFalse());
        builder.and(QUser.user.isEnable.isTrue());
        if (StringUtils.isNotBlank(name)) {
            builder.and(QUser.user.name.likeIgnoreCase(name).or(QUser.user.username.likeIgnoreCase(name)));
        }
        return getList(builder);
    }

    /**
     * 根据角色ID查询用户
     *
     * @param roleId 角色ID
     * @return 用户
     */
    default List<User> getUserByRoleId(String roleId) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QUser.user.roles.any().roleId.eq(roleId));
        return getList(builder);
    }


}