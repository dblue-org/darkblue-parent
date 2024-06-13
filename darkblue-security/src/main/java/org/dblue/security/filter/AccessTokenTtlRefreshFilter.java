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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dblue.security.converter.AccessTokenFinder;
import org.dblue.security.token.TokenManager;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 自动刷新AccessToken
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 15:15]
 */
@Setter
@Slf4j
public class AccessTokenTtlRefreshFilter extends OncePerRequestFilter {

    private TokenManager tokenManager;

    private AccessTokenFinder accessTokenFinder;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String accessToken = this.accessTokenFinder.getAccessToken(request);
        if (StringUtils.isNotBlank(accessToken)) {
            this.tokenManager.refreshAccessToken(accessToken);
            log.info("Reset access token ttl.");
        }
        filterChain.doFilter(request, response);
    }
}
