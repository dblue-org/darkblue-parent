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
import org.dblue.common.error.CommonError;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;


/**
 * 默认的权限不足的处理器
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 14:59]
 */
public class DefaultAccessDeniedHandler extends AbstractAuthenticationHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ResponseBean<Void> responseBean = ResponseBean.failure(CommonError.ACCESS_DENIED);
        this.print(response, responseBean);
    }
}
