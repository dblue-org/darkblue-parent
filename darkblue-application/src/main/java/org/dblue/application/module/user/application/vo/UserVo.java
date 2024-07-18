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

package org.dblue.application.module.user.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.usergroup.application.vo.UserGroupVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户
 * @author xie jin
 */
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户")
@Data
public class UserVo extends BaseUserVo {

    /**
     * 性别（1-男；2-女）
     */
    @Schema(description = "性别（1-男；2-女）")
    private Integer sex;

    /**
     * 身份证号码
     */
    @Schema(description = "身份证号码")
    private String identityNo;

    /**
     * 是否可用
     */

    @Schema(description = "是否可用")
    private Boolean isEnable;

    /**
     * 最后登录日期
     */
    @Schema(description = "最后登录日期")
    private LocalDateTime lastLoginTime;

    /**
     * 密码更新时间
     */
    @Schema(description = "密码更新时间")
    private LocalDateTime passwordUpdateTime;

    /**
     * 是否超级管理员
     */
    @Schema(description = "是否超级管理员")
    private Boolean isAdmin;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private List<UserRoleVo> roles;

    /**
     * 用户组名称
     */
    private List<UserGroupVo> userGroups;

    /**
     * 拥有的PC端权限
     */
    @Schema(description = "拥有的APP端权限")
    private List<UserMenuWithPermissionVo> pcMenus;

    /**
     * 拥有的APP端权限
     */
    @Schema(description = "拥有的APP端权限")
    private List<UserMenuWithPermissionVo> appMenus;

}