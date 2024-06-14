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

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_sys_permission")
public class Permission {
    @Id
    @Size(max = 64)
    @Column(name = "permission_id", nullable = false, length = 64)
    private String permissionId;

    @Size(max = 64)
    @Column(name = "menu_id", length = 64)
    private String menuId;

    @Column(name = "platform")
    private Integer platform;

    @Size(max = 100)
    @Column(name = "permission_name", length = 100)
    private String permissionName;

    @Size(max = 64)
    @Column(name = "permission_code", length = 64)
    private String permissionCode;

    @Column(name = "create_time")
    private Instant createTime;

    @Size(max = 64)
    @Column(name = "create_user", length = 64)
    private String createUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Size(max = 64)
    @Column(name = "update_user", length = 64)
    private String updateUser;

}