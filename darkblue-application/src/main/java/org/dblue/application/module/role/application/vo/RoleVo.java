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

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 14:05:47
 */
@Schema(description = "角色")
@Data
public class RoleVo {
    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private String roleId;

    /**
     * 角色名称
     */

    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String roleCode;

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
     * 是否内置
     */
    @Schema(description = "是否内置")
    private Boolean isBuiltIn;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 拥有权限(PC端)
     */
    @Schema(description = "拥有权限")
    private List<RoleMenuVo> pcMenus;

    /**
     * 拥有权限(移动端)
     */
    @Schema(description = "拥有权限(移动端)")
    private List<RoleMenuVo> appMenus;


}