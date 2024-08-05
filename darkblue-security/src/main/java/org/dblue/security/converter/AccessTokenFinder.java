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

/**
 * 获取AccessToken
 *
 * @author Wang Chengwei
 * @since 1.0.0 2022/3/17 11:23
 */
public interface AccessTokenFinder {

    /**
     * Token 的 Header 名称
     */
    String DEFAULT_AUTHORIZATION_HEADER_NAME = "Authorization";

    /**
     * Token 前缀
     */
    String DEFAULT_AUTHORIZATION_SCHEME_BASIC = "Bearer";

    /**
     * 获取AccessToken
     *
     * @param request 请求上下文
     * @return AccessToken
     */
    String getAccessToken(HttpServletRequest request);
}
