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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Set;

/**
 * 权限选择框查询
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 上午11:30
 */
@Data
@ParameterObject
public class PermissionCheckBoxDto {

    /**
     * 角色ID
     */
    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    /**
     * 菜单ID
     */
    @NotEmpty(message = "菜单ID不能为空")
    private Set<String> menuIdList;
}
