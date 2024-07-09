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

package org.dblue.application.module.setting.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

/**
 * 系统参数配置
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_property_setting")
public class PropertySetting {
    /**
     * 属性ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "property_id", nullable = false, length = 64)
    private String propertyId;

    /**
     * 参数编码
     */
    @Size(max = 512)
    @Column(name = "property_code", length = 512)
    private String propertyCode;

    /**
     * 参数名称
     */
    @Size(max = 512)
    @Column(name = "property_name", length = 512)
    private String propertyName;

    /**
     * 参数说明
     */
    @Lob
    @Column(name = "remark")
    private String remark;

    /**
     * 参数类型（系统枚举定义）
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 取值范围
     */
    @Column(name = "value_scope")
    @JdbcTypeCode(SqlTypes.JSON)
    private String valueScope;

    /**
     * 参数默认值
     */
    @Size(max = 256)
    @Column(name = "default_value", length = 256)
    private String defaultValue;

    /**
     * 参数当前值
     */
    @Size(max = 256)
    @Column(name = "value", length = 256)
    private String value;

    /**
     * 单位
     */
    @Size(max = 64)
    @Column(name = "unit", length = 64)
    private String unit;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Size(max = 64)
    @Column(name = "create_user", length = 64)
    private String createUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @Size(max = 64)
    @Column(name = "update_user", length = 64)
    private String updateUser;
}