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

package org.dblue.application.module.user.domain.service;

import org.dblue.application.module.user.application.dto.UserPageDto;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * 用户领域查询服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 上午11:19
 */
public interface UserDomainQueryService {

    /**
     * 获取一个用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    User getOne(String userId);


    /**
     * 分页
     *
     * @param pageDto 查询参数
     * @return 用户信息
     */
    Page<User> page(UserPageDto pageDto);

    /**
     * 下拉列表使用
     *
     * @param name 用户名/姓名
     * @return 用户信息
     */
    List<User> selectByNameOrUserName(String name);


    /**
     * 根据角色ID查询用户
     *
     * @param roleId 角色ID
     * @return 用户
     */
    List<User> getUserByRoleId(String roleId);

    /**
     * 根据职位ID查询用户信息
     *
     * @param positionIdSet 职位ID集合
     * @return 用户信息
     */
    List<User> findByPositionIdIn(Set<String> positionIdSet);


    /**
     * 根据用户ID查询用户信息
     *
     * @param userIdSet 用户ID
     * @return 用户
     */
    List<User> getAllByUserId(Set<String> userIdSet);
}
