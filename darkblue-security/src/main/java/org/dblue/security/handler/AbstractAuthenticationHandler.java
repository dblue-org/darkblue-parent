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

import jakarta.servlet.http.HttpServletResponse;
import org.dblue.common.exception.ServiceException;
import org.dblue.core.web.result.ResponseBean;
import org.dblue.utils.json.JacksonUtils;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 抽象认证处理
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 14:13]
 */
public abstract class AbstractAuthenticationHandler {

    protected void print(HttpServletResponse response, ResponseBean<?> responseBean) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        try {
            PrintWriter out = response.getWriter();
            out.print(JacksonUtils.toJsonString(responseBean));
        } catch (IOException e) {
            throw new ServiceException("处理应答时失败");
        }
    }
}
