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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存用户信息，为了避免权限信息转换错误，统一采用String类型来存储权限编码
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 13:18]
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CachedSecurityUser {

    private List<String> authorities;

    private String userId;

    /**
     * 部门ID
     */
    private String deptId;


    private String username;

    private String phoneNumber;

    /**
     * 数据范围
     */
    private UserDataScope dataScope;

    /**
     * real name or nickname
     */
    private String name;

    private boolean admin = false;

    private String accessToken;

    /**
     * 创建缓存用户对象
     *
     * @param user 用户信息
     * @return 缓存用户对象
     */
    public static CachedSecurityUser create(SecurityUser user) {
        CachedSecurityUser securityUser = new CachedSecurityUser();
        securityUser.setUserId(user.getUserId());
        securityUser.setUsername(user.getUsername());
        securityUser.setPhoneNumber(user.getPhoneNumber());
        securityUser.setName(user.getName());
        securityUser.setAdmin(user.isAdmin());
        securityUser.setDataScope(user.getDataScope());
        securityUser.setDeptId(user.getDeptId());

        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        securityUser.setAuthorities(authorities);
        return securityUser;
    }

    /**
     * 将缓存的用户对象转为用户对象
     *
     * @return 用户对象
     */
    public SecurityUser toSecurityUser() {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUserId(this.userId);
        securityUser.setUsername(this.username);
        securityUser.setPhoneNumber(this.phoneNumber);
        securityUser.setName(this.name);
        securityUser.setAdmin(this.admin);
        securityUser.setDataScope(this.dataScope);
        securityUser.setDeptId(this.deptId);

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        for (String authority : this.authorities) {
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(authority));
        }
        securityUser.setAuthorities(simpleGrantedAuthorityList);
        return securityUser;
    }
}
