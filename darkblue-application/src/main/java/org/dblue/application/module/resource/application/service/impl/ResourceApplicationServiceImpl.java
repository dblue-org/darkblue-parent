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
import org.dblue.application.module.resource.application.dto.*;
import org.dblue.application.module.resource.application.service.ResourceApplicationService;
import org.dblue.application.module.resource.application.service.SpringMvcMappingService;
import org.dblue.application.module.resource.application.vo.*;
import org.dblue.application.module.resource.domain.service.ResourceDomainService;
import org.dblue.application.module.resource.domain.service.ResourceGroupDomainService;
import org.dblue.application.module.resource.errors.ResourceErrors;
import org.dblue.application.module.resource.infrastructure.entity.Resource;
import org.dblue.application.module.resource.infrastructure.entity.ResourceGroup;
import org.dblue.common.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    private final SpringMvcMappingService springMvcMappingService;
    private final ResourceGroupDomainService resourceGroupDomainService;


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
    public Page<ResourcePageVo> findByPage(ResourcePageDto pageDto) {
        Page<Resource> page = resourceDomainService.page(pageDto);
        if (page.isEmpty()) {
            return Page.empty();
        }
        return page.map(resource -> {
            ResourcePageVo resourcePageVo = new ResourcePageVo();
            BeanUtils.copyProperties(resource, resourcePageVo);
            List<Permission> permissionList = permissionDomainQueryService.getPermissionByResourceId(resourcePageVo.getResourceId());
            ResourceMappingVo mappingVo = springMvcMappingService.getMapping(resourcePageVo.getRequestMethod(), resourcePageVo.getResourceUrl());

            if (mappingVo != null) {
                resourcePageVo.setIsInvalid(false);
                resourcePageVo.setIsAnythingChanged(this.isAnythingChange(resourcePageVo, mappingVo));
                resourcePageVo.setResourceMapping(mappingVo);
            } else {
                resourcePageVo.setIsInvalid(true);
            }

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

    private boolean isAnythingChange(ResourcePageVo resourceVo, ResourceMappingVo mappingVo) {
        return !(Objects.equals(resourceVo.getResourceName(), mappingVo.getResourceName())
                && Objects.equals(resourceVo.getController(), mappingVo.getController())
                && Objects.equals(resourceVo.getMethod(), mappingVo.getMethod()));
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

    /**
     * 批量添加或者更新
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    @SuppressWarnings("java:S6809")
    public void batchAddOrUpdate() {
        List<ResourceInvalidVo> resourceInvalidVoList = this.checkResourceValidity();
        if (CollectionUtils.isNotEmpty(resourceInvalidVoList)) {
            throw new ServiceException(ResourceErrors.THE_RESOURCE_CONTAINS_AN_UNMODIFIED_INVALID_RESOURCE);
        }
        List<ResourceControllerVo> resourceControllerVoList = springMvcMappingService.getResourceMappings(null);
        for (ResourceControllerVo resourceControllerVo : resourceControllerVoList) {
            ResourceGroupAddDto resourceGroupAddDto = new ResourceGroupAddDto();
            resourceGroupAddDto.setPlatform(resourceControllerVo.getPlatform());
            resourceGroupAddDto.setGroupName(resourceControllerVo.getTagName());
            resourceGroupAddDto.setSortNum(1);
            String resourceGroupId = resourceGroupDomainService.addOrUpdate(resourceGroupAddDto);
            List<ResourceMappingVo> mappings = resourceControllerVo.getMappings();
            if (CollectionUtils.isNotEmpty(mappings)) {
                for (ResourceMappingVo mapping : mappings) {
                    ResourceAddDto resourceAddDto = new ResourceAddDto();
                    resourceAddDto.setPlatform(resourceControllerVo.getPlatform());
                    resourceAddDto.setResourceGroupId(resourceGroupId);
                    resourceAddDto.setIsAuthedAccess(Boolean.FALSE);
                    BeanUtils.copyProperties(mapping, resourceAddDto);
                    resourceDomainService.addOrUpdate(resourceAddDto);
                }
            }
        }
    }

    /**
     * 批量添加
     *
     * @param batchAddDto 资源信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchAdd(ResourceBatchAddDto batchAddDto) {
        ResourceGroup resourceGroup = resourceGroupDomainService.getOneByPlatform(batchAddDto.getResourceGroupId(), batchAddDto.getPlatform());
        for (ResourceDto mapping : batchAddDto.getMappings()) {
            ResourceAddDto resourceAddDto = new ResourceAddDto();
            BeanUtils.copyProperties(resourceGroup, resourceAddDto);
            BeanUtils.copyProperties(mapping, resourceAddDto);
            resourceDomainService.add(resourceAddDto);
        }
    }

    /**
     * 检测资源合法性
     */
    @Transactional(readOnly = true)
    @Override
    public List<ResourceInvalidVo> checkResourceValidity() {
        List<Resource> resourceList = resourceDomainService.getAll();

        List<ResourceInvalidVo> resourceInvalidVoList = new ArrayList<>();

        for (Resource resource : resourceList) {
            ResourceMappingVo mappingVo = springMvcMappingService.getMapping(resource.getRequestMethod(), resource.getResourceUrl());
            if (mappingVo == null) {
                ResourceInvalidVo resourceInvalidVo = new ResourceInvalidVo();
                BeanUtils.copyProperties(resource, resourceInvalidVo);
                resourceInvalidVoList.add(resourceInvalidVo);
            }
        }

        if (CollectionUtils.isEmpty(resourceInvalidVoList)) {
            return resourceInvalidVoList;
        }

        Set<String> resourceGroupIdSet = resourceInvalidVoList.stream().map(ResourceInvalidVo::getResourceGroupId).collect(Collectors.toSet());
        Map<String, String> groupNameMap = this.resourceGroupDomainService.createQuery()
                .resourceGroupIdIn(resourceGroupIdSet)
                .nameMap();

        resourceInvalidVoList.forEach(resource -> resource.setResourceGroupName(groupNameMap.get(resource.getResourceGroupId())));

        return resourceInvalidVoList;
    }
}
