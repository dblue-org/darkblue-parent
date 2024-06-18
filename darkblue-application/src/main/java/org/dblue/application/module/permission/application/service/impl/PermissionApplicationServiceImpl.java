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
import org.dblue.application.module.permission.application.dto.PermissionQueryDto;
import org.dblue.application.module.permission.application.service.PermissionApplicationService;
import org.dblue.application.module.permission.application.vo.PermissionVo;
import org.dblue.application.module.permission.domain.PermissionDomainQueryService;
import org.dblue.application.module.permission.domain.service.PermissionDomainService;
import org.dblue.application.module.permission.errors.PermissionErrors;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.role.domain.service.RolePermissionDomainQueryService;
import org.dblue.common.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    /**
     * 权限删除
     *
     * @param id 权限id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        long count = rolePermissionDomainQueryService.countByPermissionId(id);
        if(count > 0){
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
    @Override
    public Page<PermissionVo> findByPage(PermissionQueryDto query) {
        Page<Permission> page = permissionDomainQueryService.findByPage(query);
        if(page.isEmpty()){

            return Page.empty(query.toJpaPage());
        }
        return page.map(this::build);
    }

    private PermissionVo build(Permission permission) {
        PermissionVo permissionVo = new PermissionVo();
        BeanUtils.copyProperties(permission,permissionVo);
        return  permissionVo;
    }
}
