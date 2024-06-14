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

package org.dblue.application.module.permission.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.dblue.application.commons.enums.PlatformEnum;
import org.dblue.common.validation.annotation.EnumValues;

/**
 * 权限
 */
@Schema(description = "权限")
@Data
public class PermissionBaseDto {


    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    @Size(max = 64)
    @NotBlank(message = "菜单ID不能为空")
    private String menuId;

    /**
     * 适用平台(1-PC；2-APP)从菜单代入
     */
    @Schema(description = "适用平台(1-PC；2-APP)从菜单代入")
    @NotNull(message = "适用平台不能为空")
    @EnumValues(message = "适用平台字段不正确",clazz = PlatformEnum.class)
    private Integer platform;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    @Size(max = 100)
    @NotBlank(message = "适用平台不能为空")
    private String permissionName;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    @Size(max = 64)
    @NotBlank(message = "适用平台不能为空")
    private String permissionCode;



}