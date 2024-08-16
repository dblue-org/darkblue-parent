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

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dblue.core.web.result.ResponseBean;
import org.dblue.security.event.LoginSuccessEvent;
import org.dblue.security.token.TokenManager;
import org.dblue.security.token.Tokens;
import org.dblue.security.user.LoginUserVo;
import org.dblue.security.user.SecurityUser;
import org.dblue.security.user.UserStoreService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 登录成功的处理
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 14:21]
 */
public class DefaultAuthenticationSuccessHandler extends AbstractAuthenticationHandler
        implements AuthenticationSuccessHandler, ApplicationEventPublisherAware {

    private final TokenManager tokenManager;
    private final UserStoreService userStoreService;
    private ApplicationEventPublisher applicationEventPublisher;


    public DefaultAuthenticationSuccessHandler(TokenManager tokenManager, UserStoreService userStoreService) {
        this.tokenManager = tokenManager;
        this.userStoreService = userStoreService;
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

        Tokens tokens = this.tokenManager.createToken(securityUser.getUserId());
        this.userStoreService.save(securityUser, tokens);

        this.applicationEventPublisher.publishEvent(new LoginSuccessEvent(this, securityUser, request));

        LoginUserVo userVo = LoginUserVo.create(securityUser, tokens);
        this.print(response, ResponseBean.success(userVo));
    }
}
