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
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.springframework.beans.BeanUtils;

/**
 * 角色下拉查询
 *
 * @author xie jin
 * @since 1.0.0  2024-07-09 13:31:09
 */
@Schema(description = "角色下拉查询")
@Data
public class SimpleRoleVo {


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


    public static SimpleRoleVo of(Role role) {
        SimpleRoleVo simpleRoleVo = new SimpleRoleVo();
        BeanUtils.copyProperties(role, simpleRoleVo);
        return simpleRoleVo;
    }
}