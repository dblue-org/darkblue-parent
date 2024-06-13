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
import org.apache.commons.lang3.StringUtils;
import org.dblue.core.web.result.ResponseBean;
import org.dblue.security.error.SecurityError;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 14:19]
 */
public class DefaultAuthenticationFailureHandler extends AbstractAuthenticationHandler
        implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResponseBean<String> responseBean = ResponseBean.failure(SecurityError.LOGIN_FAIL);
        if (exception instanceof BadCredentialsException) {
            responseBean.setErrorCode(SecurityError.BAD_CREDENTIALS.getErrorCode());
            responseBean.setMessage(SecurityError.BAD_CREDENTIALS.getErrorMessage());
        } else {
            if (StringUtils.isNotBlank(exception.getMessage())) {
                responseBean.setMessage(exception.getMessage());
            }
        }
        this.print(response, responseBean);
    }
}
