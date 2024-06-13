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

package org.dblue.security.authentication;

import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * AccessToken认证，用于登录后将认证信息添加到 SecurityContext 中
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
public class AccessTokenAuthenticationToken extends AbstractAuthenticationToken {

    private final transient Object credentials;

    private final transient Object principal;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param credentials Access Token
     * @param principal   User Info
     * @param authorities the collection of <b>GrantedAuthority</b>s for the principal represented by this authentication object.
     */
    public AccessTokenAuthenticationToken(Object credentials, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.credentials = credentials;
        this.principal = principal;
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param credentials Access Token
     */
    public AccessTokenAuthenticationToken(Object credentials) {
        super(Collections.emptyList());
        this.credentials = credentials;
        this.principal = null;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
