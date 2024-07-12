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

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractAuditingEntity;

import java.util.List;

/**
 * 角色
 *
 * @author xie jin
 * @since 1.0.0  2024-07-08 16:21:08
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_role")
public class Role extends AbstractAuditingEntity {
    /**
     * 角色id
     */
    @Id
    @Size(max = 64)
    @Column(name = "role_id", nullable = false, length = 64)
    private String roleId;

    /**
     * 角色名称
     */
    @Size(max = 64)
    @Column(name = "role_name", length = 64)
    private String roleName;

    /**
     * 角色编码
     */
    @Size(max = 64)
    @Column(name = "role_code", length = 64)
    private String roleCode;

    /**
     * 备注
     */
    @Size(max = 500)
    @Column(name = "remark", length = 500)
    private String remark;

    /**
     * 是否可用
     */
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 是否内置
     */
    @Column(name = "is_built_in")
    private Boolean isBuiltIn;

    @OneToMany(mappedBy = "roleId")
    private List<RoleMenu> menus;

    @OneToMany(mappedBy = "roleId")
    private List<RolePermission> permissions;

    public void enable() {
        this.isEnable = true;
    }

    public void disable() {
        this.isEnable = false;
    }

}