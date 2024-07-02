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

package org.dblue.application.module.resource.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.core.jpa.AbstractAuditingEntity;

/**
 * 资源
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 17:27:58
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_resource")
public class Resource extends AbstractAuditingEntity {
    /**
     * 资源ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "resource_id", nullable = false, length = 64)
    private String resourceId;

    /**
     * 资源组ID
     */
    @Size(max = 64)
    @Column(name = "resource_group_id", length = 64)
    private String resourceGroupId;

    /**
     * 资源名称
     */
    @Size(max = 100)
    @Column(name = "resource_name", length = 100)
    private String resourceName;

    /**
     * 资源地址
     */
    @Size(max = 256)
    @Column(name = "resource_url", length = 256)
    private String resourceUrl;

    /**
     * 是否登录即可访问
     */
    @Column(name = "is_authed_access")
    private Boolean isAuthedAccess;

    /**
     * 排序字段
     */
    @Column(name = "sort_num")
    private Integer sortNum;


}