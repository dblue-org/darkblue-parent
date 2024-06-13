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

package org.dblue.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dblue.security.authentication.AccessTokenAuthenticationToken;
import org.dblue.security.token.TokenCache;
import org.dblue.security.token.TokenManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;


/**
 * 退出登录
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 14:25]
 */
public class DefaultLogoutHandler implements LogoutHandler {

    private final TokenManager tokenManager;

    public DefaultLogoutHandler(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication instanceof AccessTokenAuthenticationToken authenticationToken) {
            if (authenticationToken.getCredentials() != null) {
                String accessToken = (String) authenticationToken.getCredentials();
                TokenCache tokenCache = this.tokenManager.getUserByAccessToken(accessToken);
                if (tokenCache != null) {
                    this.tokenManager.removeByAccessToken(accessToken);
                }
            }
        }
    }
}
