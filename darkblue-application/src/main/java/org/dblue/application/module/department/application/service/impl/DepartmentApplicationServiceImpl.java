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

package org.dblue.application.module.department.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.department.application.service.DepartmentApplicationService;
import org.dblue.application.module.department.application.vo.DepartmentTreeVo;
import org.dblue.application.module.department.application.vo.DepartmentVo;
import org.dblue.application.module.department.domain.service.DepartmentDomainQueryService;
import org.dblue.application.module.department.infrastructure.entity.Department;
import org.dblue.application.module.user.domain.service.UserDomainQueryService;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 部门应用服务层
 *
 * @author xie jin
 * @since 1.0.0  2024/6/21 下午2:04
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class DepartmentApplicationServiceImpl implements DepartmentApplicationService {

    private final DepartmentDomainQueryService departmentDomainQueryService;


    private final UserDomainQueryService userDomainQueryService;

    /**
     * 获取部门树
     *
     * @return 部门树
     */
    @Transactional(readOnly = true)
    @Override
    public List<DepartmentTreeVo> getAll() {
        List<Department> departmentList = departmentDomainQueryService.getAll();
        if (CollectionUtils.isEmpty(departmentList)) {
            return List.of();
        }
        List<Department> rootDepartmentList = departmentList.stream()
                                                            .filter(department -> StringUtils.isBlank(department.getParentId()))
                                                            .toList();

        List<Department> childrenList = departmentList.stream()
                                                      .filter(department -> StringUtils.isNotBlank(department.getParentId()))
                                                      .toList();
        if (CollectionUtils.isEmpty(childrenList)) {
            return rootDepartmentList.stream().map(department -> {
                DepartmentTreeVo departmentTreeVo = new DepartmentTreeVo();
                BeanUtils.copyProperties(department, departmentTreeVo);
                return departmentTreeVo;
            }).toList();
        }
        Map<String, List<Department>> departmentMap = childrenList.stream()
                                                                  .collect(Collectors.groupingBy(Department::getParentId));

        return rootDepartmentList.stream().map(department -> toTree(department, departmentMap))
                                 .toList();

    }

    /**
     * 单个获取
     *
     * @param deptId 部门ID
     * @return 组织信息
     */
    @Transactional(readOnly = true)
    @Override
    public DepartmentVo getDetails(String deptId) {
        Department department = departmentDomainQueryService.getOne(deptId);
        DepartmentVo departmentVo = new DepartmentVo();
        if (Objects.nonNull(department)) {
            BeanUtils.copyProperties(department, departmentVo);
            if (StringUtils.isNotBlank(department.getMasterUserId())) {
                User user = userDomainQueryService.getOne(department.getMasterUserId());
                departmentVo.setMasterUserName(user.getName());
            }
        }
        return departmentVo;
    }

    public DepartmentTreeVo toTree(Department department, Map<String, List<Department>> departmentMap) {
        DepartmentTreeVo departmentTreeVo = new DepartmentTreeVo();
        BeanUtils.copyProperties(department, departmentTreeVo);
        List<Department> children = departmentMap.get(department.getDeptId());
        if (CollectionUtils.isNotEmpty(children)) {
            departmentTreeVo.setChildren(children.stream().map(child -> toTree(child, departmentMap))
                                                 .toList());
        }
        return departmentTreeVo;
    }
}
