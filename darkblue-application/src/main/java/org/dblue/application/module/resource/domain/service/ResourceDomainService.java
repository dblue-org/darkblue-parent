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

import org.dblue.application.module.resource.application.dto.ResourceAddDto;
import org.dblue.application.module.resource.application.dto.ResourcePageDto;
import org.dblue.application.module.resource.application.dto.ResourceUpdateDto;
import org.dblue.application.module.resource.infrastructure.entity.Resource;
import org.springframework.data.domain.Page;

/**
 * 资源领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/3 上午9:22
 */
public interface ResourceDomainService {

    /**
     * 资源添加
     * @param addDto 信息
     */
    void add(ResourceAddDto addDto);


    /**
     * 资源更新
     * @param updateDto 信息
     */
    void update(ResourceUpdateDto updateDto);


    /**
     * 资源删除
     * @param resourceId 资源Id
     */
    void delete(String resourceId);


    /**
     * 分页查询
     *
     * @param pageDto 查询参数
     * @return 资源
     */
    Page<Resource> page(ResourcePageDto pageDto);
}
