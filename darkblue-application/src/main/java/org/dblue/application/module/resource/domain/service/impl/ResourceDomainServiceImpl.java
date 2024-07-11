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
import org.dblue.application.module.resource.application.dto.ResourceAddDto;
import org.dblue.application.module.resource.application.dto.ResourcePageDto;
import org.dblue.application.module.resource.application.dto.ResourceUpdateDto;
import org.dblue.application.module.resource.domain.service.ResourceDomainService;
import org.dblue.application.module.resource.errors.ResourceErrors;
import org.dblue.application.module.resource.infrastructure.entity.Resource;
import org.dblue.application.module.resource.infrastructure.repository.ResourceRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 资源领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/3 上午9:25
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ResourceDomainServiceImpl implements ResourceDomainService {

    private final ResourceRepository resourceRepository;

    /**
     * 资源添加
     *
     * @param addDto 信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ResourceAddDto addDto) {
        Optional<Resource> optional = resourceRepository.findByResourceNameAndControllerAndMethod(addDto.getResourceName(), addDto.getController(), addDto.getMethod());
        if (optional.isPresent()) {
            throw new ServiceException(ResourceErrors.RESOURCE_EXITS);
        }

        Resource resource = new Resource();
        BeanUtils.copyProperties(addDto, resource);
        resource.setResourceId(Snowflake.stringId());
        resourceRepository.save(resource);

    }

    /**
     * 资源更新
     *
     * @param updateDto 信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ResourceUpdateDto updateDto) {

        Optional<Resource> optionalResource = resourceRepository.findById(updateDto.getResourceId());
        if (optionalResource.isEmpty()) {
            throw new ServiceException(ResourceErrors.RESOURCE_IS_NOT_FOUND);
        }

        Optional<Resource> optional = resourceRepository.findByResourceNameAndControllerAndMethodAndResourceIdNot(updateDto.getResourceName(), updateDto.getController(), updateDto.getRequestMethod(), updateDto.getResourceId());
        if (optional.isPresent()) {
            throw new ServiceException(ResourceErrors.RESOURCE_EXITS);
        }

        BeanUtils.copyProperties(updateDto, optionalResource.get());
        resourceRepository.save(optionalResource.get());
    }

    /**
     * 资源删除
     *
     * @param resourceId 资源Id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String resourceId) {
        Optional<Resource> optionalResource = resourceRepository.findById(resourceId);
        if (optionalResource.isEmpty()) {
            throw new ServiceException(ResourceErrors.RESOURCE_IS_NOT_FOUND);
        }
        resourceRepository.deleteById(resourceId);


    }

    /**
     * 分页查询
     *
     * @param pageDto 查询参数
     * @return 资源
     */
    @Override
    public Page<Resource> page(ResourcePageDto pageDto) {
        return resourceRepository.page(pageDto, pageDto.toJpaPage());
    }
}
