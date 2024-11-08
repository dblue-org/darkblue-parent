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

package org.dblue.application.module.department.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dblue.application.commons.bus.EventBus;
import org.dblue.application.commons.db.jpa.Conditions;
import org.dblue.application.module.department.application.dto.DepartmentAddDto;
import org.dblue.application.module.department.application.dto.DepartmentUpdateDto;
import org.dblue.application.module.department.domain.event.DepartmentAddEvent;
import org.dblue.application.module.department.domain.event.DepartmentDeleteEvent;
import org.dblue.application.module.department.domain.event.DepartmentUpdateEvent;
import org.dblue.application.module.department.domain.service.DepartmentDomainService;
import org.dblue.application.module.department.errors.DepartmentErrors;
import org.dblue.application.module.department.infrastructure.entity.Department;
import org.dblue.application.module.department.infrastructure.repository.DepartmentRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.dblue.core.aspect.ServiceOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 部门领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 上午10:47
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class DepartmentDomainServiceImpl implements DepartmentDomainService {

    private final DepartmentRepository departmentRepository;
    private final EventBus eventBus;

    /**
     * 新增
     *
     * @param addDto 新增信息
     */
    @ServiceOperation("新增部门")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(DepartmentAddDto addDto, boolean fireAddEvent) {
        Optional<Department> optional = departmentRepository.findByParentIdAndDeptNameAndIsDelIsFalse(addDto.getParentId(), addDto.getDeptName());
        if (optional.isPresent()) {
            throw new ServiceException(DepartmentErrors.DEPARTMENT_EXITS);
        }
        Department department = new Department();
        department.setDeptId(Snowflake.stringId());
        BeanUtils.copyProperties(addDto, department);
        departmentRepository.save(department);

        if (fireAddEvent) {
            this.eventBus.fireEventAfterCommit(new DepartmentAddEvent(this, department));
        }
    }

    /**
     * 更新
     *
     * @param updateDto 更新信息
     */
    @ServiceOperation("更新部门")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(DepartmentUpdateDto updateDto, boolean fireUpdateEvent) {

        Optional<Department> optionalDepartment = departmentRepository.findByDeptIdAndIsDelFalse(updateDto.getDeptId());
        if (optionalDepartment.isEmpty()) {
            throw new ServiceException(DepartmentErrors.DEPARTMENT_IS_NOT_FOUND);
        }
        Optional<Department> optional = this.departmentRepository.createQuery()
                .parentId(updateDto.getParentId(), Conditions::isNotEmpty)
                .deptName(updateDto.getDeptName())
                .deptIdNot(updateDto.getDeptId())
                .single();
        if (optional.isPresent()) {
            throw new ServiceException(DepartmentErrors.DEPARTMENT_EXITS);
        }
        Department department = optionalDepartment.get();
        BeanUtils.copyProperties(updateDto, department);
        departmentRepository.save(department);

        if (fireUpdateEvent) {
            this.eventBus.fireEventAfterCommit(new DepartmentUpdateEvent(this, department));
        }
    }

    /**
     * 删除
     *
     * @param departmentId 部门ID
     */
    @ServiceOperation("删除部门")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String departmentId, boolean fireDeleteEvent) {
        Optional<Department> optionalDepartment = departmentRepository.findByDeptIdAndIsDelFalse(departmentId);
        if (optionalDepartment.isEmpty()) {
            throw new ServiceException(DepartmentErrors.DEPARTMENT_IS_NOT_FOUND);
        }
        optionalDepartment.get().setIsDel(Boolean.TRUE);
        departmentRepository.save(optionalDepartment.get());

        if (fireDeleteEvent) {
            this.eventBus.fireEventAfterCommit(new DepartmentDeleteEvent(this, optionalDepartment.get()));
        }

    }
}
