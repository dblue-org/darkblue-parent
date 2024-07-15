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
import org.dblue.application.module.usergroup.errors.UserGroupErrors;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroup;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupRole;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupUser;
import org.dblue.application.module.usergroup.infrastructure.repository.UserGroupRepository;
import org.dblue.application.module.usergroup.infrastructure.repository.UserGroupRoleRepository;
import org.dblue.application.module.usergroup.infrastructure.repository.UserGroupUserRepository;
import org.dblue.common.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final UserGroupRoleRepository userGroupRoleRepository;

    /**
     * 获取单独一个
     *
     * @param userGroupId 用户组ID
     * @return 用户组信息
     */
    @Override
    public UserGroup getOne(String userGroupId) {
        if (StringUtils.isBlank(userGroupId)) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_ID_IS_NOT_BLANK);
        }
        Optional<UserGroup> optional = userGroupRepository.findById(userGroupId);
        if (optional.isEmpty()) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_IS_NOT_FOUND);
        }
        return optional.get();
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

    /**
     * 根据用户ID获取用户组角色
     *
     * @param userId 用户ID
     * @return 用户组角色
     */
    @Override
    public List<UserGroupRole> getUserGroupRoleByUserId(String userId) {
        List<UserGroupUser> userGroupUsers = userGroupUserRepository.getUserGroupUserByUserId(userId);
        if (CollectionUtils.isEmpty(userGroupUsers)) {
            return List.of();
        }
        Set<String> userGroupIdSet = userGroupUsers.stream().map(UserGroupUser::getUserGroupId)
                                                   .collect(Collectors.toSet());
        return userGroupRoleRepository.findByUserGroupIdIn(userGroupIdSet);
    }
}
