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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * 绑定资源
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 14:17:11
 */
@Schema(description = "绑定资源")
@Data
public class PermissionResourceDto {

    /**
     * 权限ID
     */
    @Schema(description = "权限ID")
    @Size(max = 64)
    @NotBlank(message = "权限ID不能为空")
    private String permissionId;


    /**
     * 资源信息
     */
    @Schema(description = "资源信息")
    @NotEmpty(message = "资源不能为空")
    private List<String> resourceIdList;


}