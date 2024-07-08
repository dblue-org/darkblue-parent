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
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.resource.domain.service.ResourceDomainQueryService;
import org.dblue.application.module.resource.infrastructure.entity.Resource;
import org.dblue.application.module.resource.infrastructure.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源领域查询服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/8 下午4:08
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ResourceDomainQueryServiceImpl implements ResourceDomainQueryService {


    private final ResourceRepository resourceRepository;

    /**
     * 根据权限ID 查询资源信息
     *
     * @param permissionId 权限ID
     * @return 资源信息
     */
    @Override
    public List<Resource> getResourceByPermissionId(String permissionId) {
        if (StringUtils.isBlank(permissionId)) {
            return List.of();
        }

        return resourceRepository.getResourceByPermissionId(permissionId);
    }
}
