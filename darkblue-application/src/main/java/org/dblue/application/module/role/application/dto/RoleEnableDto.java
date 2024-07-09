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
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 角色启用禁用
 *
 * @author xie jin
 * @since 1.0.0  2024/7/9 上午11:14
 */
@Schema(description = "角色启用禁用")
@Data
public class RoleEnableDto {

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    /**
     * 启用/禁用
     */
    @Schema(description = "启用/禁用")
    @NotNull(message = "启用/禁用不能为空")
    private Boolean enable;
}
