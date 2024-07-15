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

package org.dblue.application.module.role.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.role.application.dto.RoleAddDto;
import org.dblue.application.module.role.application.dto.RoleEnableDto;
import org.dblue.application.module.role.application.dto.RolePermissionDto;
import org.dblue.application.module.role.application.dto.RoleUpdateDto;
import org.dblue.application.module.role.domain.service.RoleDomainService;
import org.dblue.application.module.role.errors.RoleErrors;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.application.module.role.infrastructure.entiry.RoleMenu;
import org.dblue.application.module.role.infrastructure.entiry.RolePermission;
import org.dblue.application.module.role.infrastructure.repository.RoleMenuRepository;
import org.dblue.application.module.role.infrastructure.repository.RolePermissionRepository;
import org.dblue.application.module.role.infrastructure.repository.RoleRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 角色领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/18 上午9:50
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleDomainServiceImpl implements RoleDomainService {

    private final RoleRepository roleRepository;
    private final RoleMenuRepository roleMenuRepository;
    private final RolePermissionRepository rolePermissionRepository;

    /**
     * 角色添加
     *
     * @param roleAddDto 角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(RoleAddDto roleAddDto) {
        Boolean exists = roleRepository.existsByRoleCodeOrRoleName(roleAddDto.getRoleCode(), roleAddDto.getRoleName());
        if (Boolean.TRUE.equals(exists)) {
            throw new ServiceException(RoleErrors.ROLE_EXITS);
        }
        saveRole(roleAddDto);
    }

    private void saveRole(RoleAddDto roleAddDto) {
        Role role = new Role();
        role.setRoleId(Snowflake.stringId());
        BeanUtils.copyProperties(roleAddDto, role);
        role.setIsEnable(Boolean.TRUE);
        role.setIsBuiltIn(Boolean.FALSE);
        roleRepository.save(role);
    }

    /**
     * 角色更新
     *
     * @param roleUpdateDto 角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(RoleUpdateDto roleUpdateDto) {
        Optional<Role> optional = roleRepository.findById(roleUpdateDto.getRoleId());
        if (optional.isEmpty()) {
            throw new ServiceException(RoleErrors.ROLE_IS_NOT_FOUND);
        }
        boolean exists = roleRepository.existsByRodeCodeOrRoleNameAndRoleIdNot(roleUpdateDto.getRoleCode(), roleUpdateDto.getRoleName(), roleUpdateDto.getRoleId());
        if (exists) {
            throw new ServiceException(RoleErrors.ROLE_EXITS);
        }

        BeanUtils.copyProperties(roleUpdateDto, optional.get());
        roleRepository.save(optional.get());

    }

    /**
     * 角色删除
     *
     * @param roleId 角色ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String roleId) {
        Optional<Role> optional = roleRepository.findById(roleId);
        if (optional.isEmpty()) {
            throw new ServiceException(RoleErrors.ROLE_IS_NOT_FOUND);
        }
        roleRepository.deleteById(roleId);
        roleMenuRepository.deleteByRoleId(roleId);
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    /**
     * 设置权限
     *
     * @param permissionDto 权限信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setPermission(RolePermissionDto permissionDto) {
        Optional<Role> optional = roleRepository.findById(permissionDto.getRoleId());
        if (optional.isEmpty()) {
            throw new ServiceException(RoleErrors.ROLE_IS_NOT_FOUND);
        }

        roleMenuRepository.deleteByRoleId(permissionDto.getRoleId());
        saveRoleMenu(permissionDto, optional.get());

        rolePermissionRepository.deleteByRoleId(permissionDto.getRoleId());
        saveRolePermission(permissionDto, optional.get());
    }

    private void saveRoleMenu(RolePermissionDto permissionDto, Role role) {
        if (CollectionUtils.isNotEmpty(permissionDto.getMenuIdList())) {
            for (String menuId : permissionDto.getMenuIdList()) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleMenuId(Snowflake.stringId());
                roleMenu.setRoleId(role.getRoleId());
                roleMenu.setMenuId(menuId);
                roleMenuRepository.save(roleMenu);
            }
        }
    }

    private void saveRolePermission(RolePermissionDto permissionDto, Role role) {
        if (CollectionUtils.isNotEmpty(permissionDto.getPermissionIdList())) {
            for (String permissionId : permissionDto.getPermissionIdList()) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRolePermissionId(Snowflake.stringId());
                rolePermission.setPermissionId(permissionId);
                rolePermission.setRoleId(role.getRoleId());
                rolePermissionRepository.save(rolePermission);
            }
        }
    }

    /**
     * 启用禁用
     *
     * @param enableDto 启用禁用信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enable(RoleEnableDto enableDto) {
        Optional<Role> optional = roleRepository.findById(enableDto.getRoleId());
        if (optional.isEmpty()) {
            throw new ServiceException(RoleErrors.ROLE_IS_NOT_FOUND);
        }
        if (Boolean.TRUE.equals(enableDto.getEnable())) {
            optional.get().enable();
        } else {
            optional.get().disable();
        }
        roleRepository.save(optional.get());
    }
}
