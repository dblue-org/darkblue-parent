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

import org.dblue.application.commons.db.jpa.BaseJpaQuery;
import org.dblue.application.commons.db.jpa.ConditionPredicate;
import org.dblue.application.commons.db.jpa.Conditions;
import org.dblue.application.module.department.infrastructure.entity.Department;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface DepartmentQuery extends BaseJpaQuery<Department> {

    DepartmentQuery deptId(String deptId);

    DepartmentQuery deptIdIn(Collection<String> deptIds);

    default DepartmentQuery deptName(String deptName) {
        return this.deptName(deptName, Conditions::always);
    }

    DepartmentQuery deptName(String deptName, ConditionPredicate<String> conditionPredicate);

    default DepartmentQuery deptNameLike(String deptName) {
        return this.deptNameLike(deptName, Conditions::always);
    }

    DepartmentQuery deptNameLike(String deptName, ConditionPredicate<String> conditionPredicate);

    default DepartmentQuery parentId(String parentId) {
        return this.parentId(parentId, Conditions::always);
    }

    DepartmentQuery parentId(String parentId, ConditionPredicate<String> conditionPredicate);

    @Override
    default Map<String, String> nameMap() {
        List<Department> departmentList = this.list();
        return departmentList.stream().collect(Collectors.toMap(Department::getDeptId, Department::getDeptName));
    }

    @Override
    default Map<String, Department> toMap() {
        List<Department> departmentList = this.list();
        return departmentList.stream().collect(Collectors.toMap(Department::getDeptId, o -> o));
    }
}
