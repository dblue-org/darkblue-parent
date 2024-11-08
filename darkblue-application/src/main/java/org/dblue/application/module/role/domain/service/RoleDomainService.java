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

import org.dblue.application.module.role.application.dto.RoleAddDto;
import org.dblue.application.module.role.application.dto.RoleEnableDto;
import org.dblue.application.module.role.application.dto.RolePermissionDto;
import org.dblue.application.module.role.application.dto.RoleUpdateDto;

/**
 * 角色领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/18 上午9:45
 */
public interface RoleDomainService {

    /**
     * 角色添加
     *
     * @param roleAddDto 角色信息
     */
    void add(RoleAddDto roleAddDto);

    /**
     * 角色更新
     *
     * @param roleUpdateDto 角色信息
     */
    void update(RoleUpdateDto roleUpdateDto);

    /**
     * 角色删除
     * @param roleId 角色ID
     */
    void delete(String roleId);

    /**
     * 设置权限
     * @param permissionDto 权限信息
     */
    void setPermission(RolePermissionDto permissionDto);

    /**
     * 启用禁用
     *
     * @param enableDto 启用禁用信息
     */
    void toggleState(RoleEnableDto enableDto);
}
