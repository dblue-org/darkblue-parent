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

package org.dblue.application.module.permission.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.menu.domain.service.MenuDomainQueryService;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.application.module.permission.application.dto.PermissionCheckBoxDto;
import org.dblue.application.module.permission.application.dto.PermissionPageDto;
import org.dblue.application.module.permission.application.dto.PermissionRoleQueryDto;
import org.dblue.application.module.permission.application.service.PermissionApplicationService;
import org.dblue.application.module.permission.application.vo.*;
import org.dblue.application.module.permission.domain.service.PermissionDomainQueryService;
import org.dblue.application.module.permission.domain.service.PermissionDomainService;
import org.dblue.application.module.permission.errors.PermissionErrors;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.resource.domain.service.ResourceDomainQueryService;
import org.dblue.application.module.resource.infrastructure.entity.Resource;
import org.dblue.application.module.role.domain.service.RoleDomainQueryService;
import org.dblue.application.module.role.domain.service.RolePermissionDomainQueryService;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.common.exception.ServiceException;
import org.dblue.core.jpa.JpaPageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/14 下午5:12
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionApplicationServiceImpl implements PermissionApplicationService {

    private final PermissionDomainService permissionDomainService;
    private final RolePermissionDomainQueryService rolePermissionDomainQueryService;
    private final PermissionDomainQueryService permissionDomainQueryService;
    private final ResourceDomainQueryService resourceDomainQueryService;
    private final RoleDomainQueryService roleDomainQueryService;
    private final MenuDomainQueryService menuDomainQueryService;

    /**
     * 权限删除
     *
     * @param id 权限id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        long count = rolePermissionDomainQueryService.countByPermissionId(id);
        if (count > 0) {
            throw new ServiceException(PermissionErrors.PERMISSION_ROLE_EXITS);
        }
        permissionDomainService.delete(id);
    }

    /**
     * 分页查询权限信息
     *
     * @param query 查询条件
     * @return 权限列表
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<PermissionPageVo> findByPage(PermissionPageDto query) {
        Page<Permission> page = permissionDomainQueryService.findByPage(query);
        if (page.isEmpty()) {

            return Page.empty(query.toJpaPage());
        }
        List<Menu> menuList = menuDomainQueryService.getMenuByMenuIds(page.stream().map(Permission::getMenuId)
                                                                          .collect(Collectors.toSet()));
        Map<String, String> menuMap = menuList.stream().collect(Collectors.toMap(Menu::getMenuId, Menu::getMenuName));
        Map<String, Long> resourceNumMap = permissionDomainQueryService.getResourceNumByPermissionIds(page.stream()
                                                                                                          .map(Permission::getPermissionId)
                                                                                                          .collect(Collectors.toSet()));
        return page.map(permission -> build(permission, menuMap, resourceNumMap));
    }

    private PermissionPageVo build(
            Permission permission, Map<String, String> menuMap, Map<String, Long> resourceNumMap) {
        PermissionPageVo permissionPageVo = new PermissionPageVo();
        BeanUtils.copyProperties(permission, permissionPageVo);
        permissionPageVo.setMenuName(menuMap.get(permissionPageVo.getMenuId()));
        permissionPageVo.setResourceNum(resourceNumMap.getOrDefault(permission.getPermissionId(), 0L));
        return permissionPageVo;
    }

    /**
     * 权限信息
     *
     * @param permissionId 权限ID
     * @return 权限信息
     */
    @SuppressWarnings("java:S6809")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PermissionVo getDetails(String permissionId) {
        Permission permission = permissionDomainQueryService.getOne(permissionId);
        PermissionVo permissionVo = new PermissionVo();
        if (Objects.nonNull(permission)) {
            BeanUtils.copyProperties(permission, permissionVo);
            permissionVo.setPermissionResourceList(findPermissionResource(permissionId));
        }
        return permissionVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<PermissionResourceVo> findPermissionResource(String permissionId) {
        List<Resource> resourceList = resourceDomainQueryService.getResourceByPermissionId(permissionId);
        if (CollectionUtils.isEmpty(resourceList)) {
            return Collections.emptyList();
        }
        return resourceList.stream().map(resource -> {
            PermissionResourceVo permissionResourceVo = new PermissionResourceVo();
            BeanUtils.copyProperties(resource, permissionResourceVo);
            return permissionResourceVo;
        }).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<PermissionRoleVo> findPermissionRoles(PermissionRoleQueryDto queryDto) {
        Page<Role> roleList = roleDomainQueryService.getRoleByPermissionId(queryDto.getPermissionId(), queryDto.toJpaPage());
        return JpaPageConverter.convert(roleList, PermissionRoleVo::of);
    }

    /**
     * 获取权限信息并标记是否选中
     *
     * @param checkBoxDto 查询信息
     * @return 权限信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<PermissionCheckBoxVo> getPermissionCheckBox(PermissionCheckBoxDto checkBoxDto) {

        List<Permission> permissions = permissionDomainQueryService.getPermissionByMenuId(checkBoxDto.getMenuIdList());
        if (CollectionUtils.isEmpty(permissions)) {
            return List.of();
        }

        Set<String> checkedPermissionIdSet = HashSet.newHashSet(12);
        List<Permission> permissionList = permissionDomainQueryService.getPermissionByRoleId(Collections.singleton(checkBoxDto.getRoleId()));
        if (CollectionUtils.isNotEmpty(permissionList)) {
            checkedPermissionIdSet = permissionList.stream().map(Permission::getPermissionId)
                                                   .collect(Collectors.toSet());
        }

        List<Menu> menuList = menuDomainQueryService.getMenuByMenuIds(checkBoxDto.getMenuIdList());
        if (CollectionUtils.isEmpty(menuList)) {
            return List.of();
        }

        Map<String, List<Permission>> permissionMap = permissions.stream()
                                                                 .collect(Collectors.groupingBy(Permission::getMenuId));
        List<PermissionCheckBoxVo> voList = buildPermissionCheckBoxVo(checkedPermissionIdSet, menuList, permissionMap);
        return voList.stream()
                     .filter(permissionCheckBoxVo -> CollectionUtils.isNotEmpty(permissionCheckBoxVo.getPermissions()))
                     .toList();
    }

    private List<PermissionCheckBoxVo> buildPermissionCheckBoxVo(
            Set<String> checkedPermissionIdSet, List<Menu> menuList, Map<String, List<Permission>> permissionMap) {
        List<PermissionCheckBoxVo> permissionCheckBoxVoList = new ArrayList<>();
        for (Menu menu : menuList) {
            PermissionCheckBoxVo permissionCheckBoxVo = new PermissionCheckBoxVo();
            BeanUtils.copyProperties(menu, permissionCheckBoxVo);
            List<Permission> list = permissionMap.get(menu.getMenuId());
            if (CollectionUtils.isNotEmpty(list)) {
                List<PermissionCheckBoxDetailVo> detailVoList = list.stream().map(permission -> {
                    PermissionCheckBoxDetailVo permissionCheckBoxDetailVo = new PermissionCheckBoxDetailVo();
                    BeanUtils.copyProperties(permission, permissionCheckBoxDetailVo);
                    if (checkedPermissionIdSet.contains(permission.getPermissionId())) {
                        permissionCheckBoxDetailVo.setChecked(Boolean.TRUE);
                    }
                    return permissionCheckBoxDetailVo;
                }).toList();
                permissionCheckBoxVo.setPermissions(detailVoList);
            }
            permissionCheckBoxVoList.add(permissionCheckBoxVo);

        }
        return permissionCheckBoxVoList;
    }
}
