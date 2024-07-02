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

package org.dblue.application.module.role.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.role.domain.service.RoleDomainQueryService;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.application.module.role.infrastructure.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 角色领域查询服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 上午10:09
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class RoleDomainQueryServiceImpl implements RoleDomainQueryService {

    private final RoleRepository roleRepository;

    /**
     * 批量获取角色信息
     *
     * @param roleIdSets 角色ID集合
     * @return 角色信息
     */
    @Override
    public List<Role> getMoreByIds(Set<String> roleIdSets) {
        if (CollectionUtils.isEmpty(roleIdSets)) {
            return Collections.emptyList();
        }
        return roleRepository.findAllById(roleIdSets);
    }
}
