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

package org.dblue.application.module.role.infrastructure.entiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.core.jpa.AbstractCreateAuditingEntity;

import java.time.Instant;

/**
 * 角色权限
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_role_permission")
public class RolePermission extends AbstractCreateAuditingEntity {
    /**
     * 角色权限ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "role_permission_id", nullable = false, length = 64)
    private String rolePermissionId;

    /**
     * 角色ID
     */
    @Size(max = 64)
    @Column(name = "role_id", length = 64)
    private String roleId;

    /**
     * 权限ID
     */
    @Size(max = 64)
    @Column(name = "permission_id", length = 64)
    private String permissionId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 创建人
     */
    @Size(max = 64)
    @Column(name = "create_user", length = 64)
    private String createUser;

}