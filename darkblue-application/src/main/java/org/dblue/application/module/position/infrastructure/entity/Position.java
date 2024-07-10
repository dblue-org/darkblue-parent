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

package org.dblue.application.module.position.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractAuditingEntity;
import org.dblue.common.id.Snowflake;
import org.hibernate.annotations.ColumnDefault;

/**
 * 职位
 *
 * @author xie jin
 * @since 1.0.0  2024-07-09 15:32:57
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_position")
public class Position extends AbstractAuditingEntity {
    /**
     * 职位ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "position_id", nullable = false, length = 64)
    private String positionId;

    /**
     * 职位编码
     */
    @Size(max = 64)
    @Column(name = "position_code", length = 64)
    private String positionCode;

    /**
     * 职位名称
     */
    @Size(max = 64)
    @Column(name = "position_name", length = 64)
    private String positionName;

    /**
     * 是否启用
     */
    @ColumnDefault("1")
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 是否内置
     */
    @ColumnDefault("0")
    @Column(name = "is_built_in")
    private Boolean isBuiltIn;

    /**
     * 逻辑删除
     */
    @ColumnDefault("0")
    @Column(name = "is_del")
    private Boolean isDel;

    public void init() {
        this.positionId = Snowflake.stringId();
        this.isDel = false;
        this.isEnable = true;
        this.isBuiltIn = false;
    }

    public void delete() {
        this.isDel = true;
    }

    public void enable() {
        this.isEnable = true;
    }

    public void disable() {
        this.isEnable = false;
    }

}