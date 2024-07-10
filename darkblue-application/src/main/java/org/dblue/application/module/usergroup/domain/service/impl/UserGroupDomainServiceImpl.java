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
import org.dblue.application.module.usergroup.application.dto.*;
import org.dblue.application.module.usergroup.domain.service.UserGroupDomainService;
import org.dblue.application.module.usergroup.errors.UserGroupErrors;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroup;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupRole;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupUser;
import org.dblue.application.module.usergroup.infrastructure.repository.UserGroupRepository;
import org.dblue.application.module.usergroup.infrastructure.repository.UserGroupRoleRepository;
import org.dblue.application.module.usergroup.infrastructure.repository.UserGroupUserRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用户组领域信息
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 下午3:26
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserGroupDomainServiceImpl implements UserGroupDomainService {

    private final UserGroupRepository userGroupRepository;
    private final UserGroupRoleRepository userGroupRoleRepository;
    private final UserGroupUserRepository userGroupUserRepository;

    /**
     * 用户组添加
     *
     * @param addDto 用户组信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserGroupAddDto addDto) {
        Optional<UserGroup> optional = userGroupRepository.findByUserGroupName(addDto.getUserGroupName());
        if (optional.isPresent()) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_EXITS);
        }

        UserGroup userGroup = new UserGroup();
        BeanUtils.copyProperties(addDto, userGroup);
        userGroup.setUserGroupId(Snowflake.stringId());
        userGroup.setIsEnable(Boolean.TRUE);
        userGroupRepository.save(userGroup);

    }

    /**
     * 用户组更新
     *
     * @param updateDto 用户组信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UserGroupUpdateDto updateDto) {
        Optional<UserGroup> optional = userGroupRepository.findById(updateDto.getUserGroupId());
        if (optional.isEmpty()) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_IS_NOT_FOUND);
        }
        Optional<UserGroup> groupOptional = userGroupRepository.findByUserGroupNameAndUserGroupIdNot(updateDto.getUserGroupName(), updateDto.getUserGroupId());
        if (groupOptional.isPresent()) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_EXITS);
        }
        BeanUtils.copyProperties(updateDto, optional.get());
        userGroupRepository.save(optional.get());

    }

    /**
     * 用户组删除
     *
     * @param userGroupId 用户组ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String userGroupId) {
        Optional<UserGroup> optional = userGroupRepository.findById(userGroupId);
        if (optional.isEmpty()) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_IS_NOT_FOUND);
        }
        userGroupRepository.deleteById(userGroupId);
        userGroupRoleRepository.deleteByUserGroupId(userGroupId);
        userGroupUserRepository.deleteByUserGroupId(userGroupId);
    }

    /**
     * 用户组启用/禁用
     *
     * @param enableDto 用户组启用/禁用信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enable(UserGroupEnableDto enableDto) {
        Optional<UserGroup> optional = userGroupRepository.findById(enableDto.getUserGroupId());
        if (optional.isEmpty()) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_IS_NOT_FOUND);
        }
        optional.get().setIsEnable(Boolean.TRUE);
        userGroupRepository.save(optional.get());
    }


    /**
     * 用户组角色添加
     *
     * @param roleAddDto 用户组角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addRole(UserGroupRoleAddDto roleAddDto) {

        Optional<UserGroup> optional = userGroupRepository.findById(roleAddDto.getUserGroupId());
        if (optional.isEmpty()) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_IS_NOT_FOUND);
        }
        List<UserGroupRole> userGroupRoles = userGroupRoleRepository.findByRoleIdIn(roleAddDto.getRoleIdList());
        if (CollectionUtils.isNotEmpty(userGroupRoles)) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_ROLE_EXITS);
        }
        for (String roleId : roleAddDto.getRoleIdList()) {
            UserGroupRole userGroupRole = new UserGroupRole();
            userGroupRole.setUserGroupRoleId(Snowflake.stringId());
            userGroupRole.setRoleId(roleId);
            userGroupRole.setUserGroupId(roleAddDto.getUserGroupId());
            userGroupRoleRepository.save(userGroupRole);
        }

    }

    /**
     * 用户组角色删除
     *
     * @param userGroupRoleId 用户组角色Id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(String userGroupRoleId) {
        userGroupRoleRepository.deleteById(userGroupRoleId);
    }

    /**
     * 用户组用户添加
     *
     * @param userAddDto 用户组用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(UserGroupUserAddDto userAddDto) {
        Optional<UserGroup> optional = userGroupRepository.findById(userAddDto.getUserGroupId());
        if (optional.isEmpty()) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_IS_NOT_FOUND);
        }
        List<UserGroupUser> groupUsers = userGroupUserRepository.findByUserIdIn(userAddDto.getUserIdList());
        if (CollectionUtils.isNotEmpty(groupUsers)) {
            throw new ServiceException(UserGroupErrors.USER_GROUP_USER_EXITS);
        }
        for (String userId : userAddDto.getUserIdList()) {
            UserGroupUser userGroupUser = new UserGroupUser();
            userGroupUser.setUserGroupUserId(Snowflake.stringId());
            userGroupUser.setUserId(userId);
            userGroupUser.setUserGroupId(userAddDto.getUserGroupId());
            userGroupUserRepository.save(userGroupUser);
        }

    }

    /**
     * 用户组用户删除
     *
     * @param userGroupUserId 用户组用户Id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUser(String userGroupUserId) {
        userGroupUserRepository.deleteById(userGroupUserId);
    }
}
