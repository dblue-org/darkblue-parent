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

package org.dblue.application.module.role.infrastructure.domain.service;

/**
 * 角色权限查询领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/17 下午4:02
 */
public interface RolePermissionDomainService {

    /**
     * 统计角色权限关系数量
     * @param permissionId 权限ID
     * @return 数量
     */
    long countByPermissionId(String permissionId);
}
