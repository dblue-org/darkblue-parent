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

package org.dblue.application.module.menu.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单
 *
 * @author xie jin
 */
@Schema(description = "菜单")
@Data
public class MenuVo {

    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private String menuId;

    /**
     * 上级菜单ID
     */
    @Schema(description = "上级菜单ID")
    private String parentId;

    /**
     * 上级菜单名称
     */
    @Schema(description = "上级菜单名称")
    private String parentName;

    /**
     * 菜单适用平台(1-PC；2-APP)
     */
    @Schema(description = "菜单适用平台(1-PC；2-APP)")
    private Integer platform;

    /**
     * 菜单适用平台名称(1-PC；2-APP)
     */
    @Schema(description = "菜单适用平台名称(1-PC；2-APP)")
    private String platformName;

    /**
     * 菜单类型(1-目录;2-菜单)
     */
    @Schema(description = "菜单类型(1-目录;2-菜单)")
    private Integer menuType;

    /**
     * 菜单类型名称(1-目录;2-菜单)
     */
    @Schema(description = "菜单类型名称(1-目录;2-菜单)")
    private String menuTypeName;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;

    /**
     * 地址名
     */
    @Schema(description = "地址名")
    private String urlName;

    /**
     * 菜单url
     */
    @Schema(description = "菜单url")
    private String menuUrl;

    /**
     * 菜单层级
     */
    @Schema(description = "菜单层级")
    private Integer level;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sortNum;

    /**
     * 菜单图标
     */

    @Schema(description = "菜单图标")
    private String menuIcon;

    /**
     * 备注
     */

    @Schema(description = "备注")
    private String remark;

    /**
     * 是否可用
     */
    @Schema(description = "是否可用")
    private Boolean isEnable;

    /**
     * 是否可见
     */
    @Schema(description = "是否可见")
    private Boolean isVisible;

    /**
     * 是否生产环境可见
     */
    @Schema(description = "是否生产环境可见")
    private Boolean isProductionVisible;

}