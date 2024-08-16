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

package org.dblue.security.converter;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.dblue.security.authentication.AccessTokenAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;


/**
 * 通过AccessToken组装Authentication
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 11:26]
 */
public class AccessTokenAuthenticationConverter implements AuthenticationConverter {

    private final AccessTokenFinder accessTokenFinder;

    private final AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;

    /**
     * 创建一个认证信息转换器
     *
     * @param accessTokenFinder Access Token 获取策略
     */
    public AccessTokenAuthenticationConverter(AccessTokenFinder accessTokenFinder) {
        this(accessTokenFinder, new WebAuthenticationDetailsSource());
    }

    /**
     * 创建一个认证信息转换器
     *
     * @param accessTokenFinder           Access Token 获取策略
     * @param authenticationDetailsSource Web Request
     */
    public AccessTokenAuthenticationConverter(
            AccessTokenFinder accessTokenFinder, AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        this.accessTokenFinder = accessTokenFinder;
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        String accessToken = this.accessTokenFinder.getAccessToken(request);
        if (StringUtils.isNotBlank(accessToken)) {
            AccessTokenAuthenticationToken accessTokenAuthenticationToken = new AccessTokenAuthenticationToken(accessToken);
            accessTokenAuthenticationToken.setDetails(this.authenticationDetailsSource.buildDetails(request));
            return accessTokenAuthenticationToken;
        } else {
            return null;
        }
    }
}
