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

package org.dblue.application.module.permission.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 权限选择框
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 上午11:26
 */
@Schema(description = "权限选择框")
@Data
public class PermissionCheckBoxVo {

    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private String menuId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;

    /**
     * 适用平台(1-PC；2-APP)从菜单代入
     */
    @Schema(description = "适用平台(1-PC；2-APP)")
    private Integer platform;

    /**
     * 权限
     */
    @Schema(description = "权限")
    private List<PermissionCheckBoxDetailVo> permissions;

}
