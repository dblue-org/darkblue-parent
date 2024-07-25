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
package org.dblue.application.module.department.infrastructure.query;

import org.dblue.application.commons.db.jpa.AbstractBaseJpaQuery;
import org.dblue.application.commons.db.jpa.ConditionPredicate;
import org.dblue.application.module.department.infrastructure.entity.Department;
import org.dblue.application.module.department.infrastructure.entity.QDepartment;
import org.dblue.application.module.department.infrastructure.repository.DepartmentRepository;

import java.util.Collection;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class DepartmentQueryImpl extends AbstractBaseJpaQuery<Department, String> implements DepartmentQuery {

    public DepartmentQueryImpl(DepartmentRepository departmentRepository) {
        super(departmentRepository);
    }

    @Override
    public DepartmentQuery deptId(String deptId) {
        this.queryBuilder.and(QDepartment.department.deptId.eq(deptId));
        return this;
    }

    @Override
    public DepartmentQuery deptIdNot(String deptId) {
        this.queryBuilder.and(QDepartment.department.deptId.ne(deptId));
        return this;
    }

    @Override
    public DepartmentQuery deptIdIn(Collection<String> deptIds) {
        this.queryBuilder.and(QDepartment.department.deptId.in(deptIds));
        return this;
    }

    @Override
    public DepartmentQuery deptName(String deptName, ConditionPredicate<String> conditionPredicate) {
        if (conditionPredicate.test(deptName)) {
            this.queryBuilder.and(QDepartment.department.deptName.eq(deptName));
        }
        return this;
    }

    @Override
    public DepartmentQuery deptNameLike(String deptName, ConditionPredicate<String> conditionPredicate) {
        if (conditionPredicate.test(deptName)) {
            this.queryBuilder.and(QDepartment.department.deptName.contains(deptName));
        }
        return this;
    }

    @Override
    public DepartmentQuery parentId(String parentId, ConditionPredicate<String> conditionPredicate) {
        if (conditionPredicate.test(parentId)) {
            this.queryBuilder.and(QDepartment.department.deptId.eq(parentId));
        }
        return this;
    }
}