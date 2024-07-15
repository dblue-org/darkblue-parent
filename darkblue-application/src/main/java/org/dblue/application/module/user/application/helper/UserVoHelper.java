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
package org.dblue.application.module.user.application.helper;

import org.dblue.application.module.department.domain.service.DepartmentDomainQueryService;
import org.dblue.application.module.department.infrastructure.entity.Department;
import org.dblue.application.module.position.domain.service.PositionDomainService;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.application.module.user.application.vo.BaseUserVo;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 设置部门名称和职位名称的工具
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Component
public class UserVoHelper {

    private final PositionDomainService positionDomainService;

    private final DepartmentDomainQueryService departmentDomainQueryService;

    public UserVoHelper(PositionDomainService positionDomainService, DepartmentDomainQueryService departmentDomainQueryService) {
        this.positionDomainService = positionDomainService;
        this.departmentDomainQueryService = departmentDomainQueryService;
    }

    public void setExternalNames(BaseUserVo userVo) {
        Position position = this.positionDomainService.getOne(userVo.getPositionId());
        if (position != null) {
            userVo.setPositionName(position.getPositionName());
        }
        Department department = this.departmentDomainQueryService.getOne(userVo.getDeptId());
        if (department != null) {
            userVo.setDeptName(department.getDeptName());
        }
    }

    public void setExternalNames(List<? extends BaseUserVo> userVoList) {

        Set<String> deptIdSet = new HashSet<>();
        Set<String> positionIdSet = new HashSet<>();
        userVoList.forEach(user -> {
            deptIdSet.add(user.getUserId());
            positionIdSet.add(user.getPositionId());
        });

        Map<String, String> deptNameMap = this.departmentDomainQueryService.createQuery()
                .deptIdIn(deptIdSet).nameMap();
        Map<String, String> positionNameMap = this.positionDomainService.createQuery()
                .positionIdIn(positionIdSet).nameMap();

        userVoList.forEach(user -> {
            user.setDeptName(deptNameMap.get(user.getDeptId()));
            user.setPositionName(positionNameMap.get(user.getPositionId()));
        });
    }
}