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

package org.dblue.application.module.permission.domain.service;


import org.dblue.application.module.permission.application.dto.PermissionPageDto;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 权限领域查询服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/17 下午2:55
 */
public interface PermissionDomainQueryService {


    /**
     * 查询菜单下权限
     * @param menuId 菜单ID
     * @return 数量
     */
    long countByMenuId( String menuId);

    /**
     * 分页查询权限信息
     *
     * @param query 查询条件
     * @return 权限列表
     */
    Page<Permission> findByPage(PermissionPageDto query);

    /**
     * 权限信息
     * @param permissionId 权限ID
     * @return 权限信息
     */
    Permission getOne(String permissionId);

    /**
     * 根据资源ID查询权限信息
     *
     * @param resourceId 资源ID
     * @return 权限
     */
    List<Permission> getPermissionByResourceId(String resourceId);


    /**
     * 根据角色ID获取权限
     *
     * @param roleIdSet 角色ID
     * @return 权限
     */
    List<Permission> getPermissionByRoleId(Set<String> roleIdSet);


    /**
     * 根据菜单ID获取权限
     *
     * @param menuIdSet 菜单ID
     * @return 权限
     */
    List<Permission> getPermissionByMenuId(Set<String> menuIdSet);

    /**
     * 根据权限ID获取权限
     *
     * @param permissionIdSet 权限
     * @return 权限
     */
    List<Permission> getPermissionByPermissionIds(Set<String> permissionIdSet);

    /**
     * 获取全部权限
     *
     * @return 权限
     */
    List<Permission> getAll();

    /**
     * 根据权限ID获取资源数量
     *
     * @param permissionIdSet 权限ID
     * @return key:权限ID value:数量
     */
    Map<String, Long> getResourceNumByPermissionIds(Set<String> permissionIdSet);
}
