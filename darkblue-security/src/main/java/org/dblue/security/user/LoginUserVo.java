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
import org.dblue.security.token.AccessToken;
import org.dblue.security.token.Tokens;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录成功后返回到前端的用户信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
public class LoginUserVo {

    private String userId;

    private String username;

    private String phoneNumber;

    /**
     * real name or nickname
     */
    private String name;

    private AccessToken accessToken;

    private List<String> authorities;

    public static LoginUserVo create(SecurityUser user, Tokens tokens) {
        LoginUserVo userVo = new LoginUserVo();
        userVo.setUserId(user.getUserId());
        userVo.setUsername(user.getUsername());
        userVo.setName(user.getName());
        userVo.setPhoneNumber(user.getPhoneNumber());
        userVo.setAccessToken(tokens.getAccessToken());
        userVo.setAuthorities(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return userVo;
    }
}
