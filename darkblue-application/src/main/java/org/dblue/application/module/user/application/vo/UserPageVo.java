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

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户分页查询VO
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 上午9:18
 */
@Schema(description = "用户分页查询VO")
@Data
public class UserPageVo {

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
     * 职位
     */
    @Schema(description = "职位")
    private String positionId;

    /**
     * 职位名称
     */
    @Schema(description = "职位名称")
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


    /**
     * 最后登录日期
     */
    @Schema(description = "最后登录日期")
    private LocalDateTime lastLoginTime;


    /**
     * 是否超级管理员
     */
    @Schema(description = "是否超级管理员")
    private Boolean isAdmin;

    /**
     * 角色名称集合
     */
    @Schema(description = "角色名称集合")
    private List<String> roleNameList;


}
