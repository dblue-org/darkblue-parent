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

package org.dblue.application.module.department.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.core.jpa.AbstractAuditingEntity;
import org.hibernate.annotations.ColumnDefault;

/**
 * 组织架构
 * @author xie jin
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_department")
public class Department extends AbstractAuditingEntity {
    /**
     * 部门ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "dept_id", nullable = false, length = 64)
    private String deptId;

    /**
     * 部门名称
     */
    @Size(max = 200)
    @Column(name = "dept_name", length = 200)
    private String deptName;

    /**
     * 上级ID
     */
    @Size(max = 64)
    @Column(name = "parent_id", length = 64)
    private String parentId;

    /**
     * 部门主管
     */
    @Size(max = 64)
    @Column(name = "master_user_id", length = 64)
    private String masterUserId;

    /**
     * 是否启用
     */
    @ColumnDefault("1")
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 是否删除
     */
    @ColumnDefault("0")
    @Column(name = "is_del")
    private Boolean isDel;


}