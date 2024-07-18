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
import org.dblue.application.module.resource.application.dto.ResourceAddDto;
import org.dblue.application.module.resource.application.dto.ResourceGroupAddDto;
import org.dblue.application.module.resource.application.service.ResourceBatchAddService;
import org.dblue.application.module.resource.application.service.SpringAnnotationService;
import org.dblue.application.module.resource.application.vo.ResourceControllerVo;
import org.dblue.application.module.resource.application.vo.ResourceMappingVo;
import org.dblue.application.module.resource.domain.service.ResourceDomainService;
import org.dblue.application.module.resource.domain.service.ResourceGroupDomainService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源批量添加
 *
 * @author xie jin
 * @since 1.0.0  2024/7/18 上午10:12
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ResourceBatchAddServiceImpl implements ResourceBatchAddService {
    private final SpringAnnotationService springAnnotationService;
    private final ResourceDomainService resourceDomainService;
    private final ResourceGroupDomainService resourceGroupDomainService;

    /**
     * 批量添加，用于首次添加资源信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchAdd() {
        List<ResourceControllerVo> resourceControllerVoList = springAnnotationService.getResourceController();
        for (ResourceControllerVo resourceControllerVo : resourceControllerVoList) {
            ResourceGroupAddDto resourceGroupAddDto = new ResourceGroupAddDto();
            resourceGroupAddDto.setPlatform(1);
            resourceGroupAddDto.setGroupName(resourceControllerVo.getTagName());
            resourceGroupAddDto.setSortNum(1);
            String resourceGroupId = resourceGroupDomainService.add(resourceGroupAddDto);
            List<ResourceMappingVo> mappings = resourceControllerVo.getMappings();
            if (CollectionUtils.isNotEmpty(mappings)) {
                for (ResourceMappingVo mapping : mappings) {
                    ResourceAddDto resourceAddDto = new ResourceAddDto();
                    resourceAddDto.setPlatform(1);
                    resourceAddDto.setResourceGroupId(resourceGroupId);
                    resourceAddDto.setIsAuthedAccess(Boolean.FALSE);
                    BeanUtils.copyProperties(mapping, resourceAddDto);
                    resourceDomainService.add(resourceAddDto);
                }
            }
        }

    }
}
