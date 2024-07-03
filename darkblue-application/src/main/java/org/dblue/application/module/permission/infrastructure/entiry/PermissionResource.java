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

package org.dblue.application.module.permission.infrastructure.entiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractCreateAuditingEntity;
import org.dblue.application.module.resource.infrastructure.entity.Resource;

/**
 * 权限资源
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_permission_resource")
public class PermissionResource extends AbstractCreateAuditingEntity {
    /**
     * 权限资源id
     */
    @Id
    @Size(max = 64)
    @Column(name = "permission_resource_id", nullable = false, length = 64)
    private String permissionResourceId;

//    /**
//     * 权限id
//     */
//    @Size(max = 64)
//    @Column(name = "permission_id", length = 64)
//    private String permissionId;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

//    /**
//     * 资源id
//     */
//    @Size(max = 64)
//    @Column(name = "resource_id", length = 64)
//    private String resourceId;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

}