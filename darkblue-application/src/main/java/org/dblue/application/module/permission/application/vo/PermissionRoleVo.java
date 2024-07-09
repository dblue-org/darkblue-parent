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

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 包含权限的角色信息
 *
 * @author xie jin
 * @since 1.0.0  2024-07-08 16:15:09
 */
@Data
public class PermissionRoleVo {
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 是否可用
     */
    private Boolean isEnable;

    /**
     * 是否内置
     */
    private Boolean isBuiltIn;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}