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

package org.dblue.application.module.permission.application.service;

import org.dblue.application.module.permission.application.dto.PermissionCheckBoxDto;
import org.dblue.application.module.permission.application.dto.PermissionPageDto;
import org.dblue.application.module.permission.application.dto.PermissionRoleQueryDto;
import org.dblue.application.module.permission.application.vo.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 权限应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/14 下午5:11
 */
public interface PermissionApplicationService {

    /**
     * 权限删除
     * @param id 权限id
     */
    void delete(String id);

    /**
     * 分页查询权限信息
     *
     * @param query 查询条件
     * @return 权限列表
     */
    Page<PermissionPageVo> findByPage(PermissionPageDto query);

    /**
     * 权限信息
     * @param permissionId 权限ID
     * @return 权限信息
     */
    PermissionVo getOne(String permissionId);

    /**
     * 获取权限资源信息
     *
     * @param permissionId 权限ID
     * @return 权限资源信息
     */
    List<PermissionResourceVo> findPermissionResource(String permissionId);

    /**
     * 获取权限关联的角色
     *
     * @param queryDto 分页查询参数
     * @return 权限资源信息
     */
    Page<PermissionRoleVo> findPermissionRoles(PermissionRoleQueryDto queryDto);

    /**
     * 获取权限信息并标记是否选中
     *
     * @param checkBoxDto 查询信息
     * @return 权限信息
     */
    List<PermissionCheckBoxVo> getPermissionCheckBox(PermissionCheckBoxDto checkBoxDto);

}
