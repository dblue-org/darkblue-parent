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

package org.dblue.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.dblue.security.authentication.AccessTokenAuthenticationToken;
import org.dblue.security.converter.AccessTokenAuthenticationConverter;
import org.dblue.security.token.TokenCache;
import org.dblue.security.token.TokenManager;
import org.dblue.security.user.SecurityUser;
import org.dblue.security.user.UserStoreService;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 11:30]
 */
@Setter
public class PreAccessTokenProcessingFilter extends OncePerRequestFilter {

    private TokenManager tokenManager;

    private UserStoreService userStoreService;

    private AccessTokenAuthenticationConverter accessTokenAuthenticationConverter;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = this.accessTokenAuthenticationConverter.convert(request);
        if (authentication != null) {
            String accessToken = (String) authentication.getCredentials();
            TokenCache tokenCache = this.tokenManager.getUserByAccessToken(accessToken);
            if (tokenCache != null) {
                String userId = tokenCache.getUserId();
                SecurityUser securityUser = this.userStoreService.getUser(userId);
                AccessTokenAuthenticationToken authenticationToken = new AccessTokenAuthenticationToken(
                        accessToken, securityUser, securityUser.getAuthorities()
                );
                authenticationToken.setDetails(authentication.getDetails());
                authenticationToken.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                SecurityContextHolder.getContext().setAuthentication(null);
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
