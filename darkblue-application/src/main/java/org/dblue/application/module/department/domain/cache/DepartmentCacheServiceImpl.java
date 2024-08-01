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
import org.dblue.application.module.department.infrastructure.repository.DepartmentRepository;
import org.dblue.core.caching.AbstractCachingService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 部门缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Service
public class DepartmentCacheServiceImpl extends AbstractCachingService<Department, DepartmentCacheObject> implements DepartmentCacheService {

    private final DepartmentRepository departmentRepository;

    public DepartmentCacheServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentCacheObject> getAllById(Collection<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return List.of();
        }
        Set<String> cacheKeys = ids.stream().map(this::getCacheKey).collect(Collectors.toSet());
        return this.valueOperations.multiGet(cacheKeys);
    }

    @Override
    protected List<Department> getAllEntities() {
        return this.departmentRepository.findAll();
    }

    @Override
    protected DepartmentCacheObject toCacheObject(Department department) {
        return DepartmentCacheObject.of(department);
    }

    @Override
    protected String getId(Department department) {
        return department.getDeptId();
    }

    @Override
    public String getCacheName() {
        return "部门缓存";
    }

    @Override
    public String getCacheCode() {
        return "Department";
    }
}