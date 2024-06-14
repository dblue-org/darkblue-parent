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

import org.dblue.application.module.permission.application.dto.PermissionAddDto;
import org.dblue.application.module.permission.application.dto.PermissionUpdateDto;

/**
 * 权限领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/14 下午3:05
 */
public interface PermissionDomainService {


    /**
     * 权限添加
     * @param permissionAddDto 权限信息
     */
    void add(PermissionAddDto permissionAddDto);


    /**
     * 权限更新
     * @param permissionUpdateDto 权限信息
     */
    void update(PermissionUpdateDto permissionUpdateDto);


    /**
     * 权限删除
     * @param id 权限id
     */
    void delete(String id);
}
