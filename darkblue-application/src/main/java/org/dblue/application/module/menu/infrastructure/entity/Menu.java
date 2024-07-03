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

package org.dblue.application.module.menu.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractAuditingEntity;
import org.hibernate.annotations.ColumnDefault;

/**
 * 用户更新
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 11:31:33
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_menu")
public class Menu extends AbstractAuditingEntity {
    /**
     * 菜单ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "menu_id", nullable = false, length = 64)
    private String menuId;

    /**
     * 上级菜单ID
     */
    @Size(max = 64)
    @Column(name = "parent_id", length = 64)
    private String parentId;


    @Transient
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Menu parentMenu;

    /**
     * 菜单适用平台(1-PC；2-APP)
     */
    @Column(name = "platform")
    private Integer platform;

    /**
     * 菜单类型(1-目录;2-菜单)
     */
    @Column(name = "menu_type")
    private Integer menuType;

    /**
     * 菜单名称
     */
    @Size(max = 64)
    @Column(name = "menu_name", length = 64)
    private String menuName;

    /**
     * 地址名
     */
    @Size(max = 256)
    @Column(name = "url_name", length = 256)
    private String urlName;

    /**
     * 菜单url
     */
    @Size(max = 128)
    @Column(name = "menu_url", length = 128)
    private String menuUrl;

    /**
     * 菜单层级
     */
    @Column(name = "level")
    private Integer level;

    /**
     * 显示顺序
     */
    @Column(name = "sort_num")
    private Integer sortNum;

    /**
     * 菜单图标
     */
    @Size(max = 128)
    @Column(name = "menu_icon", length = 128)
    private String menuIcon;

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
     * 是否可见
     */
    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_visible", nullable = false)
    private Boolean isVisible = false;

    /**
     * 是否生产环境可见
     */
    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_production_visible", nullable = false)
    private Boolean isProductionVisible = false;

}