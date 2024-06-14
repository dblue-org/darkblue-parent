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

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_sys_user")
public class User {
    @Id
    @Size(max = 64)
    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Size(max = 64)
    @Column(name = "username", length = 64)
    private String username;

    @Size(max = 128)
    @Column(name = "password", length = 128)
    private String password;

    @Size(max = 64)
    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "sex")
    private Integer sex;

    @Size(max = 64)
    @Column(name = "dept_id", length = 64)
    private String deptId;

    @Size(max = 64)
    @Column(name = "position_id", length = 64)
    private String positionId;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Size(max = 20)
    @Column(name = "identity_no", length = 20)
    private String identityNo;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "last_login_time")
    private Instant lastLoginTime;

    @Column(name = "password_update_time")
    private Instant passwordUpdateTime;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "is_del")
    private Boolean isDel;

    @Column(name = "create_time")
    private Instant createTime;

    @Size(max = 64)
    @Column(name = "create_user", length = 64)
    private String createUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Size(max = 64)
    @Column(name = "update_user", length = 64)
    private String updateUser;

    @Transient
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, targetEntity = UserRole.class)
    private List<UserRole> roles;

}