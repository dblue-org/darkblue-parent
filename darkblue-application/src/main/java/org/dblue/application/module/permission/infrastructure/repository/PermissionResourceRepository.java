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

package org.dblue.application.module.permission.infrastructure.repository;

import org.dblue.application.module.permission.infrastructure.entiry.PermissionResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
/**
 * 权限资源
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 17:26:49
 */
public interface PermissionResourceRepository extends JpaRepository<PermissionResource, String> {

    /**
     * 根据权限删除
     * @param permissionId 权限Id
     */
    void deleteByPermission_PermissionId(@NonNull String permissionId);


    /**
     * 根据资源删除
     * @param resourceId 资源ID
     */
    void deleteByResource_ResourceId(@NonNull String resourceId);


}