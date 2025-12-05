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
package org.dblue.application.module.department.domain.cache;

import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.department.infrastructure.entity.Department;
import org.dblue.core.caching.CachingService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface DepartmentCacheService extends CachingService<Department, DepartmentCacheObject> {


    default Map<String, String> nameMap(Collection<String> ids) {
        List<DepartmentCacheObject> departmentCacheObjects = this.getAllById(ids);
        departmentCacheObjects = departmentCacheObjects.stream().filter(Objects::nonNull).toList();
        if (CollectionUtils.isEmpty(departmentCacheObjects)) {
            return Collections.emptyMap();
        }
        return departmentCacheObjects.stream().collect(Collectors.toMap(
                DepartmentCacheObject::getDeptId, DepartmentCacheObject::getDeptName
        ));
    }

    List<DepartmentCacheObject> getAllById(Collection<String> ids);

}
