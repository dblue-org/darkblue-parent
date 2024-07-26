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

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractAuditingEntity;
import org.dblue.application.module.permission.infrastructure.entiry.PermissionResource;

import java.util.List;

/**
 * 资源
 *
 * @author xie jin
 * @since 1.0.0  2024-07-03 09:56:43
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
     * 请求方式
     */
    @Size(max = 20)
    @Column(name = "request_method", length = 20)
    private String requestMethod;

    /**
     * 控制层类
     */
    @Size(max = 500)
    @Column(name = "controller", length = 500)
    private String controller;

    /**
     * 控制层方法
     */
    @Size(max = 500)
    @Column(name = "method", length = 500)
    private String method;

    /**
     * 是否登录即可访问
     */
    @Column(name = "is_authed_access")
    private Boolean isAuthedAccess;

    /**
     * 适用平台(1-PC；2-APP)
     */
    @Column(name = "platform")
    private Integer platform;

    /**
     * 是否违法
     */
    @Column(name = "is_invalid")
    private Boolean isInvalid;

    @OneToMany(mappedBy = "resourceId")
    private List<PermissionResource> permissionResourceList;

    public void markInvalid() {
        this.isInvalid = true;
    }

}