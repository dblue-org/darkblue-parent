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
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.usergroup.application.dto.UserGroupPageDto;
import org.dblue.application.module.usergroup.infrastructure.entity.QUserGroup;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * 用户组
 *
 * @author xie jin
 * @since 1.0.0  2024-07-10 14:58:10
 */
public interface UserGroupRepository extends BaseJpaRepository<UserGroup, String> {

    /**
     * 新增查询用
     *
     * @param userGroupName 用户组名称
     * @return 用户组
     */
    Optional<UserGroup> findByUserGroupName(@NonNull String userGroupName);


    /**
     * 更新查询用
     *
     * @param userGroupName 用户组名称
     * @param userGroupId   用户组ID
     * @return 用户组
     */
    Optional<UserGroup> findByUserGroupNameAndUserGroupIdNot(
            @NonNull String userGroupName, @NonNull String userGroupId);


    /**
     * 分页
     *
     * @param pageDto  查询参数
     * @param pageable 分页参数
     * @return 用户组
     */
    default Page<UserGroup> page(UserGroupPageDto pageDto, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(pageDto.getUserGroupName())) {
            builder.and(QUserGroup.userGroup.userGroupName.like("%" + pageDto.getUserGroupName() + "%"));
        }
        QSort qSort = new QSort(QUserGroup.userGroup.sortNum.asc());
        return page(builder, pageable, qSort);
    }
}