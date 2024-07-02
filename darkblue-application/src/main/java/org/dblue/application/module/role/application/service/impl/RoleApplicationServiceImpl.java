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
import org.dblue.application.module.role.application.dto.RolePageDto;
import org.dblue.application.module.role.application.service.RoleApplicationService;
import org.dblue.application.module.role.application.vo.RolePageVo;
import org.dblue.application.module.role.application.vo.RoleVo;
import org.dblue.application.module.role.domain.service.RoleDomainQueryService;
import org.dblue.application.module.role.domain.service.RoleDomainService;
import org.dblue.application.module.role.errors.RoleErrors;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.application.module.role.infrastructure.repository.RoleRepository;
import org.dblue.application.module.user.domain.service.UserRoleDomainService;
import org.dblue.common.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;
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

    /**
     * 角色删除
     *
     * @param roleId 角色ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String roleId) {
        boolean existsed = userRoleDomainService.existsUserRoleByRoleId(roleId);
        if (existsed) {
            throw new ServiceException(RoleErrors.ROLE_USER_IS_EXITS);
        }
        roleDomainService.delete(roleId);
    }

    /**
     * 分页查询角色信息
     *
     * @param query 查询条件
     * @return 角色列表
     */
    @Override
    public Page<RolePageVo> findByPage(RolePageDto query) {
        Page<Role> page = roleRepository.findByRoleCodeAndRoleName(query.getRoleCode(), query.getRoleName(), query.toJpaPage());
        if (page.isEmpty()) {
            return Page.empty();
        }
        Map<String, Long> userRoleNumMap = userRoleDomainService.getUserRoleNum(page.getContent().stream()
                                                                                    .map(Role::getRoleId)
                                                                                    .collect(Collectors.toSet()));

        return page.map(role -> {
            RolePageVo rolePageVo = new RolePageVo();
            BeanUtils.copyProperties(rolePageVo, rolePageVo);
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
        if (Objects.nonNull(role)) {
            BeanUtils.copyProperties(role, roleVo);
        }
        return roleVo;
    }
}
