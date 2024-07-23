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

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 资源与权限的映射
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
public class ResourcePermissionMapping {

    private String resourceUrl;

    private String httpMethod;

    private boolean authenticatedAccess = false;

    private Collection<String> authorities;

    public static ResourcePermissionMapping of(String requestUrl, Collection<String> authorities) {
        return of(requestUrl, null, authorities);
    }

    public static ResourcePermissionMapping of(String requestUrl, String httpMethod, Collection<String> authorities) {
        ResourcePermissionMapping resourcePermissionMapping = new ResourcePermissionMapping();
        resourcePermissionMapping.setResourceUrl(requestUrl);
        resourcePermissionMapping.setHttpMethod(httpMethod);
        resourcePermissionMapping.setAuthorities(authorities);
        if (CollectionUtils.isEmpty(authorities)) {
            resourcePermissionMapping.setAuthenticatedAccess(true);
        }
        return resourcePermissionMapping;
    }

    public static ResourcePermissionMapping ofAuthenticatedAccess(String requestUrl, String httpMethod) {
        ResourcePermissionMapping resourcePermissionMapping = new ResourcePermissionMapping();
        resourcePermissionMapping.setResourceUrl(requestUrl);
        resourcePermissionMapping.setHttpMethod(httpMethod);
        resourcePermissionMapping.setAuthenticatedAccess(true);
        return resourcePermissionMapping;
    }

}