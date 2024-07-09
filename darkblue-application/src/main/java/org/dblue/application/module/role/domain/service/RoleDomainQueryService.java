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

package org.dblue.application.module.role.domain.service;

import org.dblue.application.module.role.infrastructure.entiry.Role;

import java.util.List;
import java.util.Set;

/**
 * 角色领域查询服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/18 下午3:44
 */
public interface RoleDomainQueryService {

    /**
     * 批量获取角色信息
     * @param roleIdSets 角色ID集合
     * @return 角色信息
     */
    List<Role> getMoreByIds(Set<String> roleIdSets);


    /**
     * 获取单个角色信息
     * @param roleId 角色ID
     * @return 角色
     */
    Role getOne(String roleId);

    /**
     * 根据权限ID获取角色信息
     *
     * @param permissionId 权限ID
     * @return 角色
     */
    List<Role> getRoleByPermissionId(String permissionId);

    /**
     * 获取全部角色
     *
     * @return 角色
     */
    List<Role> getAll();
}
