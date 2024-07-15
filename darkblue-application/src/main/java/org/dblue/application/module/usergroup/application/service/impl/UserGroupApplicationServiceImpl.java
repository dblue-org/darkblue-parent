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

package org.dblue.application.module.usergroup.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dblue.application.module.role.domain.service.RoleDomainQueryService;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.application.module.user.application.helper.UserVoHelper;
import org.dblue.application.module.user.domain.service.UserDomainQueryService;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.dblue.application.module.usergroup.application.dto.UserGroupPageDto;
import org.dblue.application.module.usergroup.application.dto.UserGroupRefQueryDto;
import org.dblue.application.module.usergroup.application.service.UserGroupApplicationService;
import org.dblue.application.module.usergroup.application.vo.UserGroupPageVo;
import org.dblue.application.module.usergroup.application.vo.UserGroupRoleVo;
import org.dblue.application.module.usergroup.application.vo.UserGroupUserVo;
import org.dblue.application.module.usergroup.application.vo.UserGroupVo;
import org.dblue.application.module.usergroup.domain.service.UserGroupDomainQueryService;
import org.dblue.application.module.usergroup.errors.UserGroupErrors;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroup;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupRole;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupUser;
import org.dblue.common.assertion.ServiceAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户组应用层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 下午4:04
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserGroupApplicationServiceImpl implements UserGroupApplicationService {

    private final UserGroupDomainQueryService userGroupDomainQueryService;

    private final UserDomainQueryService userDomainQueryService;
    private final RoleDomainQueryService roleDomainQueryService;
    private final UserVoHelper userVoHelper;

    /**
     * 获取单独一个
     *
     * @param userGroupId 用户组ID
     * @return 用户组信息
     */
    @Override
    public UserGroupVo getOne(String userGroupId) {
        UserGroup userGroup = userGroupDomainQueryService.getOne(userGroupId);
        ServiceAssert.notNull(userGroup, UserGroupErrors.USER_GROUP_IS_NOT_FOUND);
        return UserGroupVo.of(userGroup);
    }

    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 分组
     */
    @Override
    public Page<UserGroupPageVo> page(UserGroupPageDto pageDto) {
        Page<UserGroup> page = userGroupDomainQueryService.page(pageDto);
        if (page.isEmpty()) {
            return Page.empty();
        }
        Set<String> userGroupIdSet = page.stream().map(UserGroup::getUserGroupId).collect(Collectors.toSet());
        List<UserGroupUser> userGroupUserList = userGroupDomainQueryService.getUserGroupUserByUserGroupId(userGroupIdSet);
        Map<String, Long> userNumMap = userGroupUserList.stream()
                .collect(Collectors.groupingBy(UserGroupUser::getUserGroupId, Collectors.counting()));
        return page.map(userGroup -> {
            UserGroupPageVo userGroupPageVo = new UserGroupPageVo();
            BeanUtils.copyProperties(userGroup, userGroupPageVo);
            userGroupPageVo.setUserNum(userNumMap.getOrDefault(userGroup.getUserGroupId(), 0L).intValue());
            return userGroupPageVo;
        });
    }

    @Override
    public Page<UserGroupRoleVo> findUserGroupRoles(UserGroupRefQueryDto queryDto) {
        Pageable pageable = queryDto.toJpaPage();
        Page<UserGroupRole> userGroupRolePage = this.userGroupDomainQueryService.findRoleReference(queryDto.getUserGroupId(), pageable);
        if (userGroupRolePage.isEmpty()) {
            return Page.empty();
        }

        Map<String, String> refMap = userGroupRolePage.stream().collect(Collectors.toMap(UserGroupRole::getRoleId, UserGroupRole::getUserGroupRoleId));
        List<Role> roleList = roleDomainQueryService.getMoreByIds(refMap.keySet());
        List<UserGroupRoleVo> list = roleList.stream().map(role -> {
            UserGroupRoleVo vo = UserGroupRoleVo.of(role);
            vo.setUserGroupRoleId(refMap.get(vo.getRoleId()));
            return vo;
        }).toList();

        return new PageImpl<>(list, pageable, userGroupRolePage.getTotalElements());
    }

    @Override
    public Page<UserGroupUserVo> findUserGroupUsers(UserGroupRefQueryDto queryDto) {
        Pageable pageable = queryDto.toJpaPage();
        Page<UserGroupUser> userGroupUserPage = this.userGroupDomainQueryService.findUserReference(queryDto.getUserGroupId(), pageable);
        if (userGroupUserPage.isEmpty()) {
            return Page.empty();
        }

        Map<String, String> refMap = userGroupUserPage.stream().collect(Collectors.toMap(UserGroupUser::getUserId, UserGroupUser::getUserGroupUserId));
        List<User> userList = userDomainQueryService.getAllByUserId(refMap.keySet());
        List<UserGroupUserVo> userVoList = userList.stream().map(user -> {
            UserGroupUserVo vo = UserGroupUserVo.of(user);
            vo.setUserGroupUserId(refMap.get(vo.getUserId()));
            return vo;
        }).toList();
        this.userVoHelper.setExternalNames(userVoList);
        return new PageImpl<>(userVoList, pageable, userGroupUserPage.getTotalElements());
    }
}
