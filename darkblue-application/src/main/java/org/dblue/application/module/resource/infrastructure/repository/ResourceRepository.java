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

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.resource.application.dto.ResourcePageDto;
import org.dblue.application.module.resource.infrastructure.entity.QResource;
import org.dblue.application.module.resource.infrastructure.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.Objects;
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
     *
     * @param resourceGroupId 资源组ID
     * @return 是否存在
     */
    boolean existsByResourceGroupId(@NonNull String resourceGroupId);

    /**
     * 添加排重用
     *
     * @param resourceName       资源名称
     * @param controller  控制器类
     * @param method 控制器方法
     * @return 资源
     */
    Optional<Resource> findByResourceNameAndControllerAndMethod(
            @NonNull String resourceName, @NonNull String controller, @NonNull String method);


    /**
     * 更新排重用
     *
     * @param resourceName       资源名称
     * @param controller  控制器类
     * @param method 控制器方法
     * @param resourceId         资源ID
     * @return 资源
     */
    Optional<Resource> findByResourceNameAndControllerAndMethodAndResourceIdNot(
            @NonNull String resourceName, @NonNull String controller, @NonNull String method,
            @NonNull String resourceId);


    /**
     * 分页查询
     *
     * @param pageDto  查询参数
     * @param pageable 分页参数
     * @return 资源
     */
    default Page<Resource> page(ResourcePageDto pageDto, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(pageDto.getResourceGroupId())) {
            builder.and(QResource.resource.resourceGroupId.eq(pageDto.getResourceGroupId()));
        }
        if (StringUtils.isNotBlank(pageDto.getResourceName())) {
            builder.and(QResource.resource.resourceName.likeIgnoreCase(pageDto.getResourceName()));
        }
        if (StringUtils.isNotBlank(pageDto.getResourceUrl())) {
            builder.and(QResource.resource.resourceUrl.likeIgnoreCase(pageDto.getResourceUrl()));
        }
        if (StringUtils.isNotBlank(pageDto.getController())) {
            builder.and(QResource.resource.controller.likeIgnoreCase(pageDto.getController()));
        }
        if (StringUtils.isNotBlank(pageDto.getMethod())) {
            builder.and(QResource.resource.method.likeIgnoreCase(pageDto.getMethod()));
        }
        if (Objects.nonNull(pageDto.getIsAuthedAccess())) {
            builder.and(QResource.resource.isAuthedAccess.eq(pageDto.getIsAuthedAccess()));
        }
        return page(builder, pageable);
    }
}