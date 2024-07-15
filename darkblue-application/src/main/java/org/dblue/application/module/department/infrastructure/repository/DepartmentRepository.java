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

package org.dblue.application.module.department.infrastructure.repository;

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.department.infrastructure.entity.Department;
import org.dblue.application.module.department.infrastructure.entity.QDepartment;
import org.dblue.application.module.department.infrastructure.query.DepartmentQuery;
import org.dblue.application.module.department.infrastructure.query.DepartmentQueryImpl;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * 组织
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 10:53:42
 */
public interface DepartmentRepository extends BaseJpaRepository<Department, String> {

    /**
     * 查询组织下是否有重复名称
     *
     * @param parentId 上级组织ID
     * @param deptName 部门名称
     * @return 组织信息
     */
    default Optional<Department> findByParentIdAndDeptNameAndIsDelIsFalse(String parentId, String deptName) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(parentId)) {
            builder.and(QDepartment.department.parentId.eq(parentId));
        }
        builder.and(QDepartment.department.deptName.eq(deptName));
        builder.and(QDepartment.department.isDel.isFalse());
        return getOne(builder);
    }


    /**
     * 查询当前组织下是否有重复名称(更新查询使用)
     *
     * @param parentId 上级组织ID
     * @param deptName 部门名称
     * @param deptId   部门ID
     * @return 组织信息
     */
    default Optional<Department> findByParentIdAndDeptNameAndDeptIdNotAndIsDelFalse(
            String parentId, @NonNull String deptName, @NonNull String deptId) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(parentId)) {
            builder.and(QDepartment.department.parentId.eq(parentId));
        }
        builder.and(QDepartment.department.deptName.eq(deptName));
        builder.and(QDepartment.department.deptId.eq(deptId));
        builder.and(QDepartment.department.isDel.isFalse());
        return getOne(builder);
    }

    /**
     * 组织单个查询
     *
     * @param deptId 部门ID
     * @return 组织信息
     */
    Optional<Department> findByDeptIdAndIsDelFalse(@NonNull String deptId);


    default DepartmentQuery createQuery() {
        return new DepartmentQueryImpl(this);
    }

}