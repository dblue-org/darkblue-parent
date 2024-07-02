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
import org.dblue.application.module.resource.application.service.ResourceGroupApplicationService;
import org.dblue.application.module.resource.application.vo.ResourceGroupVo;
import org.dblue.application.module.resource.domain.service.ResourceGroupDomainService;
import org.dblue.application.module.resource.infrastructure.entity.ResourceGroup;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源组应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 下午5:20
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ResourceGroupApplicationServiceImpl implements ResourceGroupApplicationService {

    private final ResourceGroupDomainService resourceGroupDomainService;
    /**
     * 获取全部资源组信息
     *
     * @return 资源组信息
     */
    @Override
    public List<ResourceGroupVo> getAll() {
        List<ResourceGroup> resourceGroupList = resourceGroupDomainService.getAll();
        if(CollectionUtils.isNotEmpty(resourceGroupList)){
            return resourceGroupList.stream().map(resourceGroup -> {
                ResourceGroupVo resourceGroupVo = new ResourceGroupVo();
                BeanUtils.copyProperties(resourceGroup,resourceGroupVo);
                return resourceGroupVo;
            }).toList();
        }
        return List.of();
    }
}
