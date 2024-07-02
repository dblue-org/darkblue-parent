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

package org.dblue.application.module.department.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 组织架构树
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 11:30:24
 */
@Schema(description = "组织架构树")
@Data
public class DepartmentTreeVo  {
    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private String deptId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    private String parentId;

    /**
     * 部门主管
     */
    @Schema(description = "部门主管")
    private String masterUserId;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean isEnable;

    /**
     * 是否删除
     */
    @Schema(description = "是否删除")
    private Boolean isDel;

    /**
     * 子节点
     */
    @Schema(description = "子节点")
    private List<DepartmentTreeVo> children;

}