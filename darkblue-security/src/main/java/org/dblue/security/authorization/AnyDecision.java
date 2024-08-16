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
package org.dblue.security.authorization;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authorization.AuthorizationDecision;

import java.util.Collection;

/**
 * 如果资源对应了多个权限，只需要用户有其中一个权限即可访问
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class AnyDecision implements Decision {

    /**
     * 资源对应的权限
     */
    private final Collection<String> authorities;

    /**
     * 权限推断
     *
     * @param authorities 需要的权限
     */
    public AnyDecision(Collection<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public AuthorizationDecision decide(SecurityExpressionRoot expressionRoot) {
        for (String authority : authorities) {
            if (expressionRoot.hasAuthority(authority)) {
                return Decision.ALLOW;
            }
        }
        return Decision.DENY;
    }
}
