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

package org.dblue.application.module.role.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 角色拥有菜单权限
 *
 * @author xie jin
 * @since 1.0.0  2024/7/8 上午10:47
 */
@Schema(description = "角色拥有菜单权限")
@Data
public class RoleMenuVo {

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
     * 菜单类型(1-目录;2-菜单)
     */
    @Schema(description = "菜单类型(1-目录;2-菜单)")
    private Integer menuType;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;

    /**
     * 子节点
     */
    @Schema(description = "子节点")
    private List<RoleMenuVo> children;

    /**
     * 权限
     */
    @Schema(description = "权限")
    private List<RolePermissionVo> permissionVoList;
}
