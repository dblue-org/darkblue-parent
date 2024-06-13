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
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;


/**
 * 从Header中获取AccessToken
 *
 * @author Wang Chengwei
 * @since 1.0.0 2022/3/17 11:22
 */
@Setter
public class HttpHeaderAccessTokenFinder implements AccessTokenFinder {

    private String headerName = DEFAULT_AUTHORIZATION_HEADER_NAME;

    private String scheme = DEFAULT_AUTHORIZATION_SCHEME_BASIC;

    @Override
    public String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader(headerName);
        if (header == null) {
            return null;
        }
        if (!StringUtils.startsWithIgnoreCase(header, this.scheme)) {
            return null;
        }

        // 不能只有Schema，没有AccessToken的值
        if (header.equalsIgnoreCase(this.scheme)) {
            throw new AuthenticationCredentialsNotFoundException("AccessToken信息不存在");
        }
        return header.substring(this.scheme.length() + 1);
    }
}
