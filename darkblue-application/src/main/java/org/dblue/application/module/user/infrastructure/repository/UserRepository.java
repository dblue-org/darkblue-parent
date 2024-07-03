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
import org.dblue.application.module.user.infrastructure.entity.QUser;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
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
public interface UserRepository extends JpaRepository<User, String>, QuerydslPredicateExecutor<User> {

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);


    /**
     * 更新检测用户名是否重复
     * @param username 用户名
     * @param userId 用户ID
     * @return 用户
     */
    Optional<User> findByUsernameAndUserIdAndIsDelIsFalse(@NonNull String username, @NonNull String userId);


    /**
     * 分页查询
     * @param name 姓名
     * @param username 用户名
     * @param phoneNumber 手机号
     * @param pageable 分页参数
     * @return 用户信息
     */
//    Page<User> findByNameLikeAndUsernameLikeAndPhoneNumberLike(
//            @Nullable String name, @Nullable String username, @Nullable String phoneNumber, Pageable pageable);

    default Page<User> findByNameLikeAndUsernameLikeAndPhoneNumberLike(
            @Nullable String name, @Nullable String username, @Nullable String phoneNumber, Pageable pageable){

        BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isNotBlank(name)){
            builder.and(QUser.user.name.likeIgnoreCase(name));
        }
        if(StringUtils.isNotBlank(username)){
            builder.and(QUser.user.username.likeIgnoreCase(username));
        }
        if(StringUtils.isNotBlank(phoneNumber)){
            builder.and(QUser.user.phoneNumber.likeIgnoreCase(phoneNumber));
        }
        return this.findAll(builder,pageable);

    }


    /**
     * 姓名或用户名查询可用用户信息
     * @param name 姓名
     * @param username 用户名
     * @return 用户信息
     */
    List<User> findByNameLikeAndUsernameLikeAndIsDelFalseAndIsEnableTrue(
            @Nullable String name, @Nullable String username);


}