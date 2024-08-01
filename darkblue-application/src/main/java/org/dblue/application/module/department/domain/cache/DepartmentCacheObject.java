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

import lombok.Data;
import org.dblue.application.module.department.infrastructure.entity.Department;

/**
 * 部门缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
public class DepartmentCacheObject {

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 上级ID
     */
    private String parentId;

    /**
     * 部门主管
     */
    private String masterUserId;

    /**
     * 是否启用
     */
    private Boolean isEnable;

    public static DepartmentCacheObject of(Department department) {
        DepartmentCacheObject cacheObject = new DepartmentCacheObject();
        cacheObject.setDeptId(department.getDeptId());
        cacheObject.setDeptName(department.getDeptName());
        cacheObject.setParentId(department.getParentId());
        cacheObject.setMasterUserId(department.getMasterUserId());
        cacheObject.setIsEnable(department.getIsEnable());
        return cacheObject;
    }
}