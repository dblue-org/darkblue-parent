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

package org.dblue.application.module.user.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.user.application.dto.UserPageDto;
import org.dblue.application.module.user.domain.service.UserDomainQueryService;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.dblue.application.module.user.infrastructure.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户领域查询服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 上午11:19
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class UserDomainQueryServiceImpl implements UserDomainQueryService {

    private final UserRepository userRepository;

    /**
     * 获取一个用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
    public User getOne(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        Optional<User> optional = userRepository.findById(userId);
        return optional.orElse(null);
    }

    /**
     * 分页
     *
     * @param pageDto 查询参数
     * @return 用户信息
     */
    @Override
    public Page<User> page(UserPageDto pageDto) {
        return userRepository.page(pageDto, pageDto.toJpaPage());
    }

    /**
     * 下拉列表使用
     *
     * @param name 用户名/姓名
     * @return 用户信息
     */
    @Override
    public List<User> selectByNameOrUserName(String name) {
        return userRepository.findByNameLikeAndUsernameLikeAndIsDelFalseAndIsEnableTrue(name);
    }

    /**
     * 根据角色ID查询用户
     *
     * @param roleId 角色ID
     * @return 用户
     */
    @Override
    public Page<User> getUserByRoleId(String roleId, Pageable pageable) {
        return userRepository.getUserByRoleId(roleId, pageable);

    }

    /**
     * 根据职位ID查询用户信息
     *
     * @param positionIdSet 职位ID集合
     * @return 用户信息
     */
    @Override
    public List<User> findByPositionIdIn(Set<String> positionIdSet) {
        if (CollectionUtils.isEmpty(positionIdSet)) {
            return List.of();
        }
        return userRepository.findByPositionIdIn(positionIdSet);
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userIdSet 用户ID
     * @return 用户
     */
    @Override
    public List<User> getAllByUserId(Set<String> userIdSet) {
        if (CollectionUtils.isEmpty(userIdSet)) {
            return List.of();
        }
        return userRepository.findAllById(userIdSet);
    }
}
