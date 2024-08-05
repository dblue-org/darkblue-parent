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

import lombok.Setter;
import org.dblue.common.exception.ServiceException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 此类抽象了部分从数据库组装资源权限关系的功能，用于扩展从数据库读取资源权限的功能。
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public abstract class AbstractDatabaseAuthorizationManager implements DatabaseAuthorizationManager, InitializingBean, RefreshableAuthorizationManager {

    private final Map<RequestMatcher, Decision> requestMatcherMap = new LinkedHashMap<>();
    @Setter
    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    @Setter
    private String defaultRolePrefix = "ROLE_";

    @Override
    public void refresh() {
        this.requestMatcherMap.clear();
        try {
            this.afterPropertiesSet();
        } catch (Exception e) {
            throw new ServiceException("刷新资源权限信息失败");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<ResourcePermissionMapping> resources = this.loadAllResources();
        resources.forEach(resourcePermissionMapping -> {
            AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(
                    resourcePermissionMapping.getResourceUrl(), resourcePermissionMapping.getHttpMethod());

            Decision decision;
            if (CollectionUtils.isEmpty(resourcePermissionMapping.getAuthorities()) || resourcePermissionMapping.isAuthenticatedAccess()) {
                decision = new AuthenticatedDecision();
            } else {
                decision = new AnyDecision(resourcePermissionMapping.getAuthorities());
            }
            requestMatcherMap.put(requestMatcher, decision);
        });
        requestMatcherMap.put(
                new AntPathRequestMatcher("/**"),
                new AuthenticatedDecision()
        );

    }

    /**
     * 获取数据库中所有的资源权限对应信息
     *
     * @return 资源权限列表
     */
    protected abstract List<ResourcePermissionMapping> loadAllResources();

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        SecurityExpressionRoot securityExpressionRoot = this.createSecurityExpressionRoot(authentication, context);

        for (Map.Entry<RequestMatcher, Decision> requestMatcherEntry : requestMatcherMap.entrySet()) {
            RequestMatcher requestMatcher = requestMatcherEntry.getKey();
            RequestMatcher.MatchResult matchResult = requestMatcher.matcher(context.getRequest());
            if (matchResult.isMatch()) {
                var decision = requestMatcherEntry.getValue();
                return decision.decide(securityExpressionRoot);
            }
        }

        return Decision.DENY;
    }

    /**
     * 通过给定的认证信息和上下文信息，创建一个 {@link SecurityExpressionRoot}
     *
     * @param authentication 认证信息
     * @param context        上下文信息
     * @return SecurityExpressionRoot
     */
    protected SecurityExpressionRoot createSecurityExpressionRoot(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        WebSecurityExpressionRoot securityExpressionRoot = new WebSecurityExpressionRoot(authentication, context.getRequest());
        securityExpressionRoot.setDefaultRolePrefix(this.defaultRolePrefix);
        securityExpressionRoot.setTrustResolver(this.trustResolver);
        return securityExpressionRoot;
    }


}