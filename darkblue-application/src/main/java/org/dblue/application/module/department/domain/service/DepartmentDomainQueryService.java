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

package org.dblue.application.module.department.domain.service;

import org.dblue.application.module.department.infrastructure.entity.Department;
import org.dblue.application.module.department.infrastructure.query.DepartmentQuery;

import java.util.List;

/**
 * 部门领域查询服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/21 下午1:33
 */
public interface DepartmentDomainQueryService {

    /**
     * 获取全部部门
     * @return 部门信息
     */
    List<Department> getAll();

    /**
     * 获取单个部门信息
     * @param departmentId 部门ID
     * @return 部门信息
     */
    Department getOne(String departmentId);

    /**
     * 创建查询器
     *
     * @return 查询器
     */
    DepartmentQuery createQuery();
}
