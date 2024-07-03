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
package org.dblue.application.module.caching;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
public class UserCacheObject {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（1-男；2-女）
     */
    private Integer sex;

    /**
     * 所属部门
     */
    private String deptId;

    /**
     * 职务ID
     */
    private String positionId;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 身份证号码
     */
    private String identityNo;

    /**
     * 是否可用
     */
    private Boolean isEnable;

    /**
     * 最后登录日期
     */
    private LocalDateTime lastLoginTime;

    /**
     * 密码更新时间
     */
    private LocalDateTime passwordUpdateTime;

    /**
     * 是否超级管理员
     */
    private Boolean isAdmin;

    /**
     * 是否删除
     */
    private Boolean isDel;
}