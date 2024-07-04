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

package org.dblue.application.module.resource.infrastructure.repository;

import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.resource.infrastructure.entity.Resource;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * 资源
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 17:26:36
 */
public interface ResourceRepository extends BaseJpaRepository<Resource, String> {

    /**
     * 判断资源组下是否有资源
     * @param resourceGroupId 资源组ID
     * @return 是否存在
     */
    boolean existsByResourceGroupId(@NonNull String resourceGroupId);

    /**
     * 添加排重用
     * @param resourceName  资源名称
     * @param controlLayerClass 控制器类
     * @param controlLayerMethod 控制器方法
     * @return 资源
     */
    Optional<Resource> findByResourceNameAndControlLayerClassAndControlLayerMethod(
            @NonNull String resourceName, @NonNull String controlLayerClass, @NonNull String controlLayerMethod);




    /**
     * 更新排重用
     * @param resourceName  资源名称
     * @param controlLayerClass 控制器类
     * @param controlLayerMethod 控制器方法
     * @param resourceId 资源ID
     * @return 资源
     */
    Optional<Resource> findByResourceNameAndControlLayerClassAndControlLayerMethodAndResourceIdNot(
            @NonNull String resourceName, @NonNull String controlLayerClass, @NonNull String controlLayerMethod,@NonNull String resourceId);

}