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

/**
 * 用于判断用户是否有资源的访问权限
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface Decision {
    /**
     * 拒绝访问
     */
    AuthorizationDecision DENY = new AuthorizationDecision(false);
    /**
     * 允许访问
     */
    AuthorizationDecision ALLOW = new AuthorizationDecision(true);

    /**
     * 判断用户是否有访问权限
     *
     * @param expressionRoot 用户的上下文
     * @return 判断结果
     */
    AuthorizationDecision decide(SecurityExpressionRoot expressionRoot);
}
