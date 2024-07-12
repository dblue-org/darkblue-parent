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

package org.dblue.application.module.resource.domain.service;

import org.dblue.application.module.resource.application.dto.ResourceGroupAddDto;
import org.dblue.application.module.resource.application.dto.ResourceGroupUpdateDto;
import org.dblue.application.module.resource.infrastructure.entity.ResourceGroup;

import java.util.List;

/**
 * 资源组
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 下午4:32
 */
public interface ResourceGroupDomainService {

    /**
     * 资源组添加
     *
     * @param addDto 添加信息
     */
    void add(ResourceGroupAddDto addDto);


    /**
     * 资源组更新
     *
     * @param updateDto 更新信息
     */
    void update(ResourceGroupUpdateDto updateDto);


    /**
     * 资源组删除
     *
     * @param resourceGroupId 资源组ID
     */
    void delete(String resourceGroupId);


    /**
     * 获取全部资源组信息
     *
     * @param platform 适用平台(1-PC；2-APP)
     * @return 资源组信息
     */
    List<ResourceGroup> getAll(Integer platform);


}
