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

package org.dblue.application.module.user.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractAuditingEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户
 * @author xie jin
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_user")
public class User extends AbstractAuditingEntity {
    /**
     * 用户ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    /**
     * 用户名
     */
    @Size(max = 64)
    @Column(name = "username", length = 64)
    private String username;

    /**
     * 密码
     */
    @Size(max = 128)
    @Column(name = "password", length = 128)
    private String password;

    /**
     * 姓名
     */
    @Size(max = 64)
    @Column(name = "name", length = 64)
    private String name;

    /**
     * 性别（1-男；2-女）
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 所属部门
     */
    @Size(max = 64)
    @Column(name = "dept_id", length = 64)
    private String deptId;

    /**
     * 职务ID
     */
    @Size(max = 64)
    @Column(name = "position_id", length = 64)
    private String positionId;

    /**
     * 手机号
     */
    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    /**
     * 身份证号码
     */
    @Size(max = 20)
    @Column(name = "identity_no", length = 20)
    private String identityNo;

    /**
     * 是否可用
     */
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 最后登录日期
     */
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 密码更新时间
     */
    @Column(name = "password_update_time")
    private LocalDateTime passwordUpdateTime;

    /**
     * 是否超级管理员
     */
    @Column(name = "is_admin")
    private Boolean isAdmin;

    /**
     * 是否删除
     */
    @Column(name = "is_del")
    private Boolean isDel;


    @OneToMany(mappedBy = "userId")
    private List<UserRole> roles;

    public void changeLastLoginTimeToNow() {
        this.lastLoginTime = LocalDateTime.now();
    }

    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
        this.passwordUpdateTime = LocalDateTime.now();
    }
}