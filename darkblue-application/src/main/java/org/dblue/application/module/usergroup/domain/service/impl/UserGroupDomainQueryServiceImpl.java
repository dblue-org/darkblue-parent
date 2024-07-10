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

package org.dblue.application.module.usergroup.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.usergroup.application.dto.UserGroupPageDto;
import org.dblue.application.module.usergroup.domain.service.UserGroupDomainQueryService;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroup;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupUser;
import org.dblue.application.module.usergroup.infrastructure.repository.UserGroupRepository;
import org.dblue.application.module.usergroup.infrastructure.repository.UserGroupUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 用户组查询领域
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 下午4:00
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserGroupDomainQueryServiceImpl implements UserGroupDomainQueryService {

    private final UserGroupRepository userGroupRepository;
    private final UserGroupUserRepository userGroupUserRepository;

    /**
     * 获取单独一个
     *
     * @param userGroupId 用户组ID
     * @return 用户组信息
     */
    @Override
    public UserGroup getOne(String userGroupId) {
        if (StringUtils.isBlank(userGroupId)) {
            return null;
        }
        return userGroupRepository.findById(userGroupId).orElse(null);
    }


    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 分组
     */

    @Override
    public Page<UserGroup> page(UserGroupPageDto pageDto) {

        return userGroupRepository.page(pageDto, pageDto.toJpaPage());
    }

    /**
     * 根据用户组ID获取用户组用户信息
     *
     * @param userGroupIdSet 用户组ID
     * @return 用户组用户
     */
    @Override
    public List<UserGroupUser> getUserGroupUserByUserGroupId(Set<String> userGroupIdSet) {
        if (CollectionUtils.isEmpty(userGroupIdSet)) {
            return List.of();
        }
        return userGroupUserRepository.findByUserGroupIdIn(userGroupIdSet);
    }
}