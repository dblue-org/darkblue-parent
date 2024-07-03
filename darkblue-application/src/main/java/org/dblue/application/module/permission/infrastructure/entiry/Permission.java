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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractAuditingEntity;


/**
 * 权限
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_permission")
public class Permission extends AbstractAuditingEntity {
    /**
     * 权限ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "permission_id", nullable = false, length = 64)
    private String permissionId;

    /**
     * 菜单ID
     */
    @Size(max = 64)
    @Column(name = "menu_id", length = 64)
    private String menuId;

    /**
     * 适用平台(1-PC；2-APP)从菜单代入
     */
    @Column(name = "platform")
    private Integer platform;

    /**
     * 权限名称
     */
    @Size(max = 100)
    @Column(name = "permission_name", length = 100)
    private String permissionName;

    /**
     * 权限标识
     */
    @Size(max = 64)
    @Column(name = "permission_code", length = 64)
    private String permissionCode;



}