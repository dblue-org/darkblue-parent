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

package org.dblue.application.module.role.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 角色
 *
 * @author xie jin
 * @since 1.0.0  2024-07-09 15:32:48
 */
@Schema(description = "角色")
@Data
public class RoleDto {


    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @Size(max = 64)
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    @Size(max = 64)
    @NotBlank(message = "角色编码不能为空")
    private String roleCode;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500)
    private String remark;



}