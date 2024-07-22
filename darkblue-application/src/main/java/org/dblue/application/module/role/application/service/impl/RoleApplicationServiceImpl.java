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

package org.dblue.application.module.role.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.commons.menu.MenuTreeUtils;
import org.dblue.application.module.menu.domain.service.MenuDomainQueryService;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.application.module.permission.domain.service.PermissionDomainQueryService;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.role.application.dto.RolePageDto;
import org.dblue.application.module.role.application.dto.RoleUserQueryDto;
import org.dblue.application.module.role.application.service.RoleApplicationService;
import org.dblue.application.module.role.application.vo.*;
import org.dblue.application.module.role.domain.service.RoleDomainQueryService;
import org.dblue.application.module.role.domain.service.RoleDomainService;
import org.dblue.application.module.role.errors.RoleErrors;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.application.module.role.infrastructure.repository.RoleRepository;
import org.dblue.application.module.user.application.helper.UserVoHelper;
import org.dblue.application.module.user.domain.service.UserDomainQueryService;
import org.dblue.application.module.user.domain.service.UserRoleDomainService;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.dblue.application.module.usergroup.domain.service.UserGroupDomainService;
import org.dblue.common.exception.ServiceException;
import org.dblue.core.enums.PlatformEnum;
import org.dblue.core.jpa.JpaPageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/18 下午3:23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleApplicationServiceImpl implements RoleApplicationService {

    private final UserRoleDomainService userRoleDomainService;
    private final RoleDomainService roleDomainService;
    private final RoleRepository roleRepository;
    private final RoleDomainQueryService roleDomainQueryService;
    private final MenuDomainQueryService menuDomainQueryService;
    private final PermissionDomainQueryService permissionDomainQueryService;
    private final UserDomainQueryService userDomainQueryService;
    private final UserGroupDomainService userGroupDomainService;
    private final UserVoHelper userVoHelper;

    /**
     * 角色删除
     *
     * @param roleId 角色ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String roleId) {
        boolean exists = userRoleDomainService.existsUserRoleByRoleId(roleId);
        if (exists) {
            throw new ServiceException(RoleErrors.ROLE_USER_IS_EXITS);
        }
        roleDomainService.delete(roleId);
        userGroupDomainService.deleteRoleByRoleId(roleId);
    }

    /**
     * 分页查询角色信息
     *
     * @param query 查询条件
     * @return 角色列表
     */
    @Override
    public Page<RolePageVo> findByPage(RolePageDto query) {
        Page<Role> page = roleRepository.findByRoleCodeLikeAndRoleNameLike(query.getRoleCode(), query.getRoleName(), query.toJpaPage());
        if (page.isEmpty()) {
            return Page.empty();
        }
        Map<String, Long> userRoleNumMap = userRoleDomainService.getUserRoleNum(page.getContent().stream()
                .map(Role::getRoleId)
                .collect(Collectors.toSet()));

        return page.map(role -> {
            RolePageVo rolePageVo = new RolePageVo();
            BeanUtils.copyProperties(role, rolePageVo);
            rolePageVo.setUserNums(userRoleNumMap.getOrDefault(rolePageVo.getRoleId(), 0L).intValue());
            return rolePageVo;
        });
    }

    /**
     * 获取单个角色信息
     *
     * @param roleId 角色ID
     * @return 角色
     */
    @Override
    public RoleVo getOne(String roleId) {
        Role role = roleDomainQueryService.getOne(roleId);
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(role, roleVo);
        List<Menu> menuList = menuDomainQueryService.getMenuByRoleIds(Set.of(roleId));
        List<Permission> permissionList = permissionDomainQueryService.getPermissionByRoleId(Set.of(roleId));
        Map<String, List<Permission>> permissionMap = permissionList.stream()
                .collect(Collectors.groupingBy(Permission::getMenuId));


        List<Menu> allPcMenus = new ArrayList<>();
        List<Menu> allAppMenus = new ArrayList<>();

        menuList.forEach(menu -> {
            if (PlatformEnum.PC.equalsTo(menu.getPlatform())) {
                allPcMenus.add(menu);
            } else {
                allAppMenus.add(menu);
            }
        });


        roleVo.setPcMenus(MenuTreeUtils.buildTree(allPcMenus, menu -> this.convertToRoleMenuVo(menu, permissionMap)));
        roleVo.setAppMenus(MenuTreeUtils.buildTree(allAppMenus, menu -> this.convertToRoleMenuVo(menu, permissionMap)));
        return roleVo;
    }

    private RoleMenuVo convertToRoleMenuVo(Menu menu, Map<String, List<Permission>> permissionMap) {
        RoleMenuVo roleMenuVo = RoleMenuVo.of(menu);
        List<Permission> permissionList = permissionMap.get(menu.getMenuId());
        if (CollectionUtils.isNotEmpty(permissionList)) {
            roleMenuVo.setPermissions(permissionList.stream().map(RolePermissionVo::of).toList());
        }
        return roleMenuVo;
    }

    @Override
    public Page<RoleUserVo> findRefUsers(RoleUserQueryDto queryDto) {
        Page<User> userList = userDomainQueryService.getUserByRoleId(queryDto.getRoleId(), queryDto.toJpaPage());
        if (userList.isEmpty()) {
            return Page.empty();
        }

        Page<RoleUserVo> userVoPage = JpaPageConverter.convert(userList, RoleUserVo::of);
        this.userVoHelper.setExternalNames(userVoPage.getContent());
        return userVoPage;
    }

    /**
     * 获取全部角色信息
     *
     * @return 角色信息
     */
    @Override
    public List<SimpleRoleVo> getAllForSelect() {
        List<Role> roleList = roleDomainQueryService.getAll();
        if (CollectionUtils.isEmpty(roleList)) {
            return List.of();
        }

        return roleList.stream().map(SimpleRoleVo::of).toList();

    }
}
