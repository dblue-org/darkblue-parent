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

package org.dblue.application.module.menu.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.dblue.application.commons.enums.MenuTypeEnum;
import org.dblue.application.commons.enums.PlatformEnum;
import org.dblue.common.validation.annotation.EnumValues;

/**
 * 菜单
 *
 * @author xie jin
 * @since 1.0.0  2024-07-03 09:56:51
 */
@Schema(description = "菜单")
@Data
public class MenuDto {

    /**
     * 上级菜单ID
     */
    @Schema(description = "上级菜单ID")
    @Size(max = 64)
    private String parentId;

    /**
     * 菜单适用平台(1-PC；2-APP)
     */
    @Schema(description = "菜单适用平台(1-PC；2-APP)")
    @EnumValues(message = "适用平台不正确",clazz = PlatformEnum.class)
    private Integer platform;

    /**
     * 菜单类型(1-目录;2-菜单)
     */
    @Schema(description = "菜单类型(1-目录;2-菜单)")
    @EnumValues(message = "菜单类型不正确",clazz = MenuTypeEnum.class)
    private Integer menuType;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @Size(max = 64)
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 地址名
     */
    @Schema(description = "地址名")
    @Size(max = 256)
    @NotBlank(message = "地址名不能为空")
    private String urlName;

    /**
     * 菜单url
     */
    @Schema(description = "菜单url")
    @Size(max = 128)
    private String menuUrl;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    @NotNull(message = "显示顺序不能为空")
    private Integer sortNum;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    @Size(max = 128)
    private String menuIcon;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500)
    private String remark;



    /**
     * 是否可见
     */
    @Schema(description = "是否可见")
    @NotNull(message = "是否可见不能为空")
    private Boolean isVisible;

    /**
     * 是否生产环境可见
     */
    @Schema(description = "是否生产环境可见")
    @NotNull(message = "是否生产环境可见不能为空")
    private Boolean isProductionVisible;

}