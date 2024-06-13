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

package org.dblue.security.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 13:14]
 */
@Data
public class SecurityUser implements UserDetails {
    /**
     * 权限角色列表
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 数据权限
     */
    private transient UserDataScope dataScope;

    /**
     * 是否管理员
     */
    private boolean admin = false;

    /**
     * 账户是否未过期
     */
    private boolean accountNonExpired = true;

    /**
     * 账户是否未被锁定
     */
    private boolean accountNonLocked = true;

    /**
     * 密码是否未过期
     */
    private boolean credentialsNonExpired = true;

    /**
     * 是否可用
     */
    private boolean enabled = true;
}
