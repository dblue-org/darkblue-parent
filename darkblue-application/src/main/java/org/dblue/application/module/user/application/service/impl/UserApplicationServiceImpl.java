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

package org.dblue.application.module.user.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.commons.menu.MenuTreeUtils;
import org.dblue.application.module.department.domain.service.DepartmentDomainQueryService;
import org.dblue.application.module.department.infrastructure.entity.Department;
import org.dblue.application.module.menu.domain.service.MenuDomainQueryService;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.application.module.permission.domain.service.PermissionDomainQueryService;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.position.domain.service.PositionDomainService;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.application.module.role.application.vo.SimpleRoleVo;
import org.dblue.application.module.role.domain.service.RoleDomainQueryService;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.application.module.setting.domain.cache.PropertySettingCacheService;
import org.dblue.application.module.user.application.dto.UserPageDto;
import org.dblue.application.module.user.application.dto.UserPasswordChangeDto;
import org.dblue.application.module.user.application.dto.UserSelfUpdateDto;
import org.dblue.application.module.user.application.helper.UserVoHelper;
import org.dblue.application.module.user.application.service.UserApplicationService;
import org.dblue.application.module.user.application.vo.*;
import org.dblue.application.module.user.domain.service.UserDomainQueryService;
import org.dblue.application.module.user.domain.service.UserDomainService;
import org.dblue.application.module.user.errors.UserErrors;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.dblue.application.module.user.infrastructure.entity.UserRole;
import org.dblue.application.module.usergroup.application.vo.UserGroupVo;
import org.dblue.application.module.usergroup.domain.service.UserGroupDomainQueryService;
import org.dblue.application.module.usergroup.domain.service.UserGroupDomainService;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupRole;
import org.dblue.common.assertion.ServiceAssert;
import org.dblue.common.exception.ServiceException;
import org.dblue.core.enums.PlatformEnum;
import org.dblue.security.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 上午9:52
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class UserApplicationServiceImpl implements UserApplicationService {

    private final DepartmentDomainQueryService departmentDomainQueryService;
    private final RoleDomainQueryService roleDomainQueryService;
    private final UserDomainQueryService userDomainQueryService;
    private final MenuDomainQueryService menuDomainQueryService;
    private final PermissionDomainQueryService permissionDomainQueryService;
    private final PositionDomainService positionDomainService;
    private final UserGroupDomainQueryService userGroupDomainQueryService;
    private final UserVoHelper userVoHelper;
    private final UserDomainService userDomainService;
    private final UserGroupDomainService userGroupDomainService;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final PropertySettingCacheService propertySettingCacheService;


    /**
     * 分页查询
     *
     * @param pageDto 查询参数
     * @return 用户信息
     */
    @Override
    public Page<UserPageVo> page(UserPageDto pageDto) {
        Page<User> page = userDomainQueryService.page(pageDto);
        if (page.isEmpty()) {
            return Page.empty();
        }
        return page.map(user -> {
            UserPageVo userPageVo = new UserPageVo();
            BeanUtils.copyProperties(user, userPageVo);
            Department department = departmentDomainQueryService.getOne(user.getDeptId());
            if (Objects.nonNull(department)) {
                userPageVo.setDeptName(department.getDeptName());
            }
            Position position = this.positionDomainService.getOne(user.getPositionId());
            if (Objects.nonNull(position)) {
                userPageVo.setPositionName(position.getPositionName());
            }
            List<UserRole> userRoleList = user.getRoles();
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                List<Role> roleList = roleDomainQueryService.getMoreByIds(userRoleList.stream()
                        .map(UserRole::getRoleId)
                        .collect(Collectors.toSet()));
                if (CollectionUtils.isNotEmpty(roleList)) {
                    userPageVo.setRoles(roleList.stream().map(SimpleRoleVo::of).toList());
                }
            }
            return userPageVo;
        });
    }

    /**
     * 单个信息获取
     *
     * @param userId 用户ID
     * @return 单个信息
     */
    @Override
    public UserVo getDetails(String userId) {
        User user = userDomainQueryService.getOne(userId);
        ServiceAssert.notNull(user, UserErrors.USER_NOT_FOUND);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);

        this.userVoHelper.setExternalNames(userVo);

        List<UserGroupVo> userGroups = this.userGroupDomainQueryService.findByUserId(userId)
                .stream().map(UserGroupVo::of).toList();
        userVo.setUserGroups(userGroups);

        List<UserRole> userRoleList = user.getRoles();
        List<UserRoleVo> userRoleVoList = getUserRoleVo(userId, userRoleList);
        userVo.setRoles(userRoleVoList);

        setUserMenuVoList(userRoleVoList, userVo);

        return userVo;
    }

    /**
     * 下拉列表使用
     *
     * @param name 用户名/姓名
     * @return 用户信息
     */
    @Override
    public List<UserSelectVo> selectByNameOrUserName(String name) {
        List<User> userList = userDomainQueryService.selectByNameOrUserName(name);
        if (CollectionUtils.isNotEmpty(userList)) {
            return userList.stream().map(user -> {
                UserSelectVo userVo = new UserSelectVo();
                BeanUtils.copyProperties(user, userVo);
                return userVo;
            }).toList();

        }
        return List.of();
    }

    /**
     * 获取用户菜单权限
     *
     * @return 用户菜单权限
     */
    @Override
    public List<UserMenuVo> getUserMenu(Integer platform) {
        User user = userDomainQueryService.getOne(SecurityUtils.getUserId());
        List<Menu> menuList;
        if (Boolean.TRUE.equals(user.getIsAdmin())) {
            menuList = menuDomainQueryService.findAllUsableMenus(platform);
        } else {
            Set<String> roleIdSet = user.getRoles().stream().map(UserRole::getRoleId).collect(Collectors.toSet());
            List<UserGroupRole> groupRoles = userGroupDomainQueryService.getUserGroupRoleByUserId(SecurityUtils.getUserId());
            if (CollectionUtils.isNotEmpty(groupRoles)) {
                roleIdSet.addAll(groupRoles.stream().map(UserGroupRole::getRoleId).collect(Collectors.toSet()));
            }
            if (CollectionUtils.isEmpty(roleIdSet)) {
                return List.of();
            }
            menuList = menuDomainQueryService.findAllUsableMenusByRoleIdIn(platform, roleIdSet);
        }

        return MenuTreeUtils.buildTree(menuList, UserMenuVo::of);
    }


    private List<UserRoleVo> getUserRoleVo(String userId, List<UserRole> userRoleList) {
        List<UserRoleVo> userRoleVoList = new ArrayList<>();

        // 为用户分配的角色
        Set<String> userRoleIdSet = new HashSet<>();
        if (CollectionUtils.isNotEmpty(userRoleList)) {
            Set<String> roleIdSet = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
            userRoleIdSet.addAll(roleIdSet);
            List<Role> roleList = roleDomainQueryService.getMoreByIds(roleIdSet);
            userRoleVoList.addAll(roleList.stream().map(role -> UserRoleVo.of(role, false)).toList());
        }
        List<UserGroupRole> groupRoles = userGroupDomainQueryService.getUserGroupRoleByUserId(userId);
        if (CollectionUtils.isNotEmpty(groupRoles)) {
            List<Role> roles = roleDomainQueryService.getMoreByIds(groupRoles.stream()
                    .map(UserGroupRole::getRoleId)
                    .collect(Collectors.toSet()));
            for (Role role : roles) {
                if (userRoleIdSet.contains(role.getRoleId())) {
                    continue;
                }
                userRoleVoList.add(UserRoleVo.of(role, true));
            }
        }
        return userRoleVoList;
    }

    private void setUserMenuVoList(List<UserRoleVo> userRoleVoList, UserVo userVo) {
        if (Boolean.TRUE.equals(userVo.getIsAdmin())) {
            List<Permission> permissionList = permissionDomainQueryService.getAll();
            List<Menu> pcMenuList = this.menuDomainQueryService.findAllMenus(PlatformEnum.PC);
            List<Menu> appMenuList = this.menuDomainQueryService.findAllMenus(PlatformEnum.APP);
            userVo.setPcMenus(this.buildMenuTree(pcMenuList, permissionList));
            userVo.setAppMenus(this.buildMenuTree(appMenuList, permissionList));
        } else if (CollectionUtils.isNotEmpty(userRoleVoList)) {
            Set<String> roleIdSet = userRoleVoList.stream().map(UserRoleVo::getRoleId)
                    .collect(Collectors.toSet());
            List<Menu> menuList = menuDomainQueryService.getMenuByRoleIds(roleIdSet);

            List<Menu> pcMenuList = menuList.stream().filter(menu -> PlatformEnum.PC.equalsTo(menu.getPlatform())).toList();
            List<Menu> appMenuList = menuList.stream().filter(menu -> PlatformEnum.APP.equalsTo(menu.getPlatform())).toList();

            List<Permission> permissionList = permissionDomainQueryService.getPermissionByRoleId(roleIdSet);

            userVo.setPcMenus(this.buildMenuTree(pcMenuList, permissionList));
            userVo.setAppMenus(this.buildMenuTree(appMenuList, permissionList));
        }
    }

    private List<UserMenuWithPermissionVo> buildMenuTree(List<Menu> menuList, List<Permission> permissionList) {
        if (CollectionUtils.isEmpty(menuList)) {
            return List.of();
        }
        Map<String, List<Permission>> permissionMap = permissionList.stream()
                .collect(Collectors.groupingBy(Permission::getMenuId));

        return MenuTreeUtils.buildTree(menuList, menu -> {
            UserMenuWithPermissionVo vo = UserMenuWithPermissionVo.of(menu);
            List<Permission> permissions = permissionMap.get(menu.getMenuId());
            if (CollectionUtils.isNotEmpty(permissions)) {
                vo.setPermissions(permissions.stream().map(UserPermissionVo::of).toList());
            }
            return vo;
        });
    }

    /**
     * 用户删除
     *
     * @param userId 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String userId) {
        userDomainService.delete(userId);
        userGroupDomainService.deleteUserByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void resetPassword(String userId) {
        User user = this.userDomainQueryService.getOne(userId);
        if (Boolean.TRUE.equals(user.getIsAdmin())) {
            throw new ServiceException(UserErrors.NOT_ALLOW_RESET_ADMIN_PASSWORD);
        }
        String defaultPassword = this.propertySettingCacheService.getValue("user.defaultPassword");
        this.userDomainService.changePassword(user, defaultPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changePassword(UserPasswordChangeDto passwordChangeDto) {
        User user = this.userDomainQueryService.getOne(SecurityUtils.getUserId());
        if (!this.passwordEncoder.matches(passwordChangeDto.getOldPassword(), user.getPassword())) {
            throw new ServiceException(UserErrors.PASSWORD_ERROR);
        }
        this.userDomainService.changePassword(user, passwordChangeDto.getNewPassword());
    }

    @Override
    public SimpleUserVo getMyselfInfo() {
        User user = userDomainQueryService.getOne(SecurityUtils.getUserId());
        SimpleUserVo userVo = new SimpleUserVo();
        BeanUtils.copyProperties(user, userVo);
        this.userVoHelper.setExternalNames(userVo);
        return userVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateMyselfInfo(UserSelfUpdateDto updateDto) {
        User user = userDomainQueryService.getOne(SecurityUtils.getUserId());
        BeanUtils.copyProperties(updateDto, user);
        this.userDomainService.update(user);
    }
}
