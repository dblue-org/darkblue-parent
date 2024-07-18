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

package org.dblue.application.module.resource.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.permission.domain.service.PermissionDomainQueryService;
import org.dblue.application.module.permission.domain.service.PermissionDomainService;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.resource.application.dto.ResourcePageDto;
import org.dblue.application.module.resource.application.dto.ResourcePermissionDto;
import org.dblue.application.module.resource.application.service.ResourceApplicationService;
import org.dblue.application.module.resource.application.vo.ResourcePageVo;
import org.dblue.application.module.resource.application.vo.ResourcePermissionVo;
import org.dblue.application.module.resource.domain.service.ResourceDomainService;
import org.dblue.application.module.resource.infrastructure.entity.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/3 上午9:40
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ResourceApplicationServiceImpl implements ResourceApplicationService {

    private final ResourceDomainService resourceDomainService;
    private final PermissionDomainService permissionDomainService;
    private final PermissionDomainQueryService permissionDomainQueryService;


    /**
     * 删除资源
     *
     * @param resourceId 资源ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String resourceId) {
        resourceDomainService.delete(resourceId);
        permissionDomainService.deletePermissionResourceByResourceId(resourceId);
    }

    /**
     * 分页查询
     *
     * @param pageDto 查询参数
     * @return 资源
     */
    @Override
    public Page<ResourcePageVo> page(ResourcePageDto pageDto) {
        Page<Resource> page = resourceDomainService.page(pageDto);
        if (page.isEmpty()) {
            return Page.empty();
        }
        return page.map(resource -> {
            ResourcePageVo resourcePageVo = new ResourcePageVo();
            BeanUtils.copyProperties(resource, resourcePageVo);
            List<Permission> permissionList = permissionDomainQueryService.getPermissionByResourceId(resourcePageVo.getResourceId());

            if (CollectionUtils.isNotEmpty(permissionList)) {
                resourcePageVo.setPermissions(permissionList.stream().map(permission -> {
                    ResourcePermissionVo resourcePermissionVo = new ResourcePermissionVo();
                    BeanUtils.copyProperties(permission, resourcePermissionVo);
                    return resourcePermissionVo;
                }).toList());
            }
            return resourcePageVo;

        });
    }

    /**
     * 设置权限
     *
     * @param permissionDto 权限信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setPermission(ResourcePermissionDto permissionDto) {
        resourceDomainService.setPermission(permissionDto);
        permissionDomainService.setPermission(permissionDto);
    }
}
