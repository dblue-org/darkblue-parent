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

package org.dblue.application.module.resource.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dblue.application.module.resource.application.dto.ResourceGroupAddDto;
import org.dblue.application.module.resource.application.dto.ResourceGroupUpdateDto;
import org.dblue.application.module.resource.domain.service.ResourceGroupDomainService;
import org.dblue.application.module.resource.errors.ResourceGroupErrors;
import org.dblue.application.module.resource.infrastructure.entity.ResourceGroup;
import org.dblue.application.module.resource.infrastructure.repository.ResourceGroupRepository;
import org.dblue.application.module.resource.infrastructure.repository.ResourceRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 资源组
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 下午4:37
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ResourceGroupDomainServiceImpl implements ResourceGroupDomainService {

    private final ResourceGroupRepository resourceGroupRepository;
    private final ResourceRepository resourceRepository;

    /**
     * 资源组添加
     *
     * @param addDto 添加信息
     * @return 资源组ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String add(ResourceGroupAddDto addDto) {
        Optional<ResourceGroup> groupOptional = resourceGroupRepository.findByGroupNameAndPlatform(addDto.getGroupName(), addDto.getPlatform());
        if (groupOptional.isPresent()) {
            throw new ServiceException(ResourceGroupErrors.RESOURCE_GROUP_EXITS);
        }
        ResourceGroup resourceGroup = new ResourceGroup();
        BeanUtils.copyProperties(addDto, resourceGroup);
        resourceGroup.setResourceGroupId(Snowflake.stringId());
        resourceGroupRepository.save(resourceGroup);
        return resourceGroup.getResourceGroupId();
    }

    /**
     * 资源组更新
     *
     * @param updateDto 更新信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ResourceGroupUpdateDto updateDto) {
        Optional<ResourceGroup> optional = resourceGroupRepository.findById(updateDto.getResourceGroupId());
        if (optional.isEmpty()) {
            throw new ServiceException(ResourceGroupErrors.RESOURCE_GROUP_IS_NOT_FOUND);
        }
        Optional<ResourceGroup> optionalResourceGroup = resourceGroupRepository.findByGroupNameAndPlatformAndResourceGroupIdNot(updateDto.getGroupName(), optional.get()
                                                                                                                                                                  .getPlatform(), updateDto.getResourceGroupId());
        if (optionalResourceGroup.isPresent()) {
            throw new ServiceException(ResourceGroupErrors.RESOURCE_GROUP_EXITS);
        }
        BeanUtils.copyProperties(updateDto, optional.get());
        resourceGroupRepository.save(optional.get());
    }

    /**
     * 资源组删除
     *
     * @param resourceGroupId 资源组ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String resourceGroupId) {
        Optional<ResourceGroup> optional = resourceGroupRepository.findById(resourceGroupId);
        if (optional.isEmpty()) {
            throw new ServiceException(ResourceGroupErrors.RESOURCE_GROUP_IS_NOT_FOUND);
        }

        boolean exists = resourceRepository.existsByResourceGroupId(resourceGroupId);
        if (Boolean.TRUE.equals(exists)) {
            throw new ServiceException(ResourceGroupErrors.RESOURCE_GROUP_HAS_RESOURCE);
        }
        resourceGroupRepository.deleteById(resourceGroupId);
    }

    /**
     * 获取全部资源组信息
     *
     * @return 资源组信息
     */
    @Override
    public List<ResourceGroup> getAll(Integer platform) {
        return resourceGroupRepository.createQuery()
                                      .platform(platform)
                                      .orderBySortNum()
                                      .list();
    }

    /**
     * 获取资源分组分平台
     *
     * @param resourceGroupId 分组ID
     * @param platform        平台
     */
    @Override
    public ResourceGroup getOneByPlatform(String resourceGroupId, Integer platform) {
        Optional<ResourceGroup> optional = resourceGroupRepository.findByResourceGroupIdAndPlatform(resourceGroupId, platform);
        if (optional.isEmpty()) {
            throw new ServiceException(ResourceGroupErrors.RESOURCE_GROUP_IS_NOT_FOUND);
        }
        return optional.get();
    }

    /**
     * 更新或者添加 (仅用于自动扫描添加资源组使用)
     *
     * @param groupAddDto 资源组信息
     * @return 资源组ID
     */
    @Override
    public String addOrUpdate(ResourceGroupAddDto groupAddDto) {
        Optional<ResourceGroup> groupOptional = resourceGroupRepository.findByGroupNameAndPlatform(groupAddDto.getGroupName(), groupAddDto.getPlatform());
        if (groupOptional.isPresent()) {
            groupOptional.get().setGroupName(groupAddDto.getGroupName());
            resourceGroupRepository.save(groupOptional.get());
            return groupOptional.get().getResourceGroupId();
        }
        ResourceGroup resourceGroup = new ResourceGroup();
        BeanUtils.copyProperties(groupAddDto, resourceGroup);
        resourceGroup.setResourceGroupId(Snowflake.stringId());
        resourceGroupRepository.save(resourceGroup);
        return resourceGroup.getResourceGroupId();
    }
}
