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

/**
 * 权限选择框
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 上午11:26
 */
@Data
public class PermissionCheckBoxDetailVo {


    /**
     * 权限ID
     */
    @Schema(description = "权限ID")
    private String permissionId;


    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String permissionName;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String permissionCode;


    /**
     * 是否选中
     */
    @Schema(description = "是否选中")
    private Boolean checked = false;
}
