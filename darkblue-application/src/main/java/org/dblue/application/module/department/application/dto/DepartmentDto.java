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

package org.dblue.application.module.department.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 组织架构
 * @author xie jin
 */
@Schema(description = "组织架构")
@Data
public class DepartmentDto {


    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @Size(max = 200)
    @NotBlank(message = "部门名称不能为空")
    private String deptName;

    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    @Size(max = 64)
    private String parentId;

    /**
     * 部门主管
     */
    @Schema(description = "部门主管")
    @Size(max = 64)
    private String masterUserId;


}