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
package org.dblue.application.module.resource.adapter.security;

import lombok.RequiredArgsConstructor;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.permission.infrastructure.entiry.PermissionResource;
import org.dblue.application.module.permission.infrastructure.repository.PermissionRepository;
import org.dblue.application.module.permission.infrastructure.repository.PermissionResourceRepository;
import org.dblue.application.module.resource.infrastructure.entity.Resource;
import org.dblue.application.module.resource.infrastructure.repository.ResourceRepository;
import org.dblue.security.authorization.AbstractDatabaseAuthorizationManager;
import org.dblue.security.authorization.ResourcePermissionMapping;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class DefaultDatabaseAuthorizationManager extends AbstractDatabaseAuthorizationManager {

    private final ResourceRepository resourceRepository;
    private final PermissionResourceRepository permissionResourceRepository;
    private final PermissionRepository permissionRepository;


    @Override
    protected List<ResourcePermissionMapping> loadAllResources() {

        var resources = resourceRepository.findAll();
        var permissions = permissionRepository.findAll();
        var permissionResources = permissionResourceRepository.findAll();

        var resourcePermissionMap = permissionResources.stream()
                .collect(groupingBy(PermissionResource::getResourceId, mapping(PermissionResource::getPermissionId, toSet())));
        var resourceMap = resources.stream().collect(toMap(Resource::getResourceId, r -> r));
        var permissionMap = permissions.stream().collect(toMap(Permission::getPermissionId, r -> r));

        List<ResourcePermissionMapping> resourcePermissionMappingList = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : resourcePermissionMap.entrySet()) {
            resourcePermissionMappingList.add(
                    this.createResourcePermissionMapping(entry, resourceMap, permissionMap)
            );
        }

        return resourcePermissionMappingList;
    }

    private ResourcePermissionMapping createResourcePermissionMapping(
            Map.Entry<String, Set<String>> entry, Map<String, Resource> resourceMap, Map<String, Permission> permissionMap) {
        String resourceId = entry.getKey();


        Resource resource = resourceMap.get(resourceId);
        if (Boolean.TRUE.equals(resource.getIsAuthedAccess())) {
            return ResourcePermissionMapping.ofAuthenticatedAccess(resource.getResourceUrl(), resource.getRequestMethod());
        }

        Set<String> permissionIdSet = entry.getValue();
        Set<String> permissionCodes = new HashSet<>();
        for (String permissionId : permissionIdSet) {
            Permission permission = permissionMap.get(permissionId);
            if (permission != null) {
                permissionCodes.add(permission.getPermissionCode());
            }
        }
        return ResourcePermissionMapping.of(resource.getResourceUrl(), resource.getRequestMethod(), permissionCodes);
    }
}