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
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.commons.enums.MenuTypeEnum;
import org.dblue.application.module.department.domain.service.DepartmentDomainQueryService;
import org.dblue.application.module.department.infrastructure.entity.Department;
import org.dblue.application.module.menu.domain.service.MenuDomainQueryService;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.application.module.permission.domain.service.PermissionDomainQueryService;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.role.domain.service.RoleDomainQueryService;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.application.module.user.application.dto.UserPageDto;
import org.dblue.application.module.user.application.service.UserApplicationService;
import org.dblue.application.module.user.application.vo.*;
import org.dblue.application.module.user.domain.service.UserDomainQueryService;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.dblue.application.module.user.infrastructure.entity.UserRole;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
            List<UserRole> userRoleList = user.getRoles();
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                List<Role> roleList = roleDomainQueryService.getMoreByIds(userRoleList.stream()
                                                                                      .map(UserRole::getRoleId)
                                                                                      .collect(Collectors.toSet()));
                if (CollectionUtils.isNotEmpty(roleList)) {
                    userPageVo.setRoleNameList(roleList.stream().map(Role::getRoleName).toList());
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
    public UserVo getOne(String userId) {
        User user = userDomainQueryService.getOne(userId);
        UserVo userVo = new UserVo();
        if (Objects.nonNull(user)) {
            BeanUtils.copyProperties(user, userVo);
            List<UserRole> userRoleList = user.getRoles();
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                Set<String> roleIdSet = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
                List<Menu> menuList = menuDomainQueryService.getMenuByRoleId(roleIdSet);
                List<Permission> permissionList = permissionDomainQueryService.getPermissionByRoleId(roleIdSet);
                List<Menu> rootMenuList = menuList.stream().filter(menu -> StringUtils.isBlank(menu.getParentId()))
                                                  .toList();
                Map<String, List<Menu>> childrenMap = menuList.stream()
                                                              .filter(menu -> StringUtils.isNotBlank(menu.getParentId()))
                                                              .collect(Collectors.groupingBy(Menu::getParentId));
                Map<String, List<Permission>> permissionMap = permissionList.stream()
                                                                            .collect(Collectors.groupingBy(Permission::getMenuId));

                userVo.setUserMenuVoList(buildMenu(rootMenuList, childrenMap, permissionMap));
            }

        }
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

    public List<UserMenuVo> buildMenu(
            List<Menu> menuList, Map<String, List<Menu>> childrenMap, Map<String, List<Permission>> permissionMap) {
        List<UserMenuVo> userMenuVoList = new ArrayList<>();
        for (Menu menu : menuList) {
            UserMenuVo userMenuVo = new UserMenuVo();
            BeanUtils.copyProperties(menu, userMenuVo);
            if (MenuTypeEnum.MENU.equalsTo(menu.getMenuType())) {
                List<Permission> permissionList = permissionMap.get(menu.getMenuId());
                if (CollectionUtils.isNotEmpty(permissionList)) {
                    userMenuVo.setPermissionVoList(permissionList.stream().map(permission -> {
                        UserPermissionVo userPermissionVo = new UserPermissionVo();
                        BeanUtils.copyProperties(permission, userPermissionVo);
                        return userPermissionVo;
                    }).toList());
                }
            } else {
                List<Menu> menus = childrenMap.get(menu.getMenuId());
                if (CollectionUtils.isNotEmpty(menus)) {
                    userMenuVo.setChildren(buildMenu(menus, childrenMap, permissionMap));
                }
            }
            userMenuVoList.add(userMenuVo);
        }
        return userMenuVoList;
    }
}
