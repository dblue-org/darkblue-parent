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
import org.dblue.application.module.user.infrastructure.entity.User;
import org.springframework.beans.BeanUtils;

/**
 * 用户
 *
 * @author xie jin
 */
@Schema(description = "用户")
@Data
public class RoleUserVo {
    /**
     * 用户ID
     */

    @Schema(description = "用户ID")
    private String userId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;


    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;


    /**
     * 所属部门
     */
    @Schema(description = "所属部门")
    private String deptId;

    /**
     * 所属部门名称
     */
    @Schema(description = "所属部门名称")
    private String deptName;

    /**
     * 职务ID
     */
    @Schema(description = "职务ID")
    private String positionId;

    /**
     * 职务名称
     */
    @Schema(description = "职务名称")
    private String positionName;


    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phoneNumber;


    /**
     * 是否可用
     */
    @Schema(description = "是否可用")
    private Boolean isEnable;

    public static RoleUserVo of(User user) {
        RoleUserVo vo = new RoleUserVo();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }
}