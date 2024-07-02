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

import org.dblue.application.module.department.application.dto.DepartmentAddDto;
import org.dblue.application.module.department.application.dto.DepartmentUpdateDto;

/**
 * 部门领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/21 下午1:32
 */
public interface DepartmentDomainService {


    /**
     * 新增
     * @param addDto 新增信息
     */
    void add(DepartmentAddDto addDto);

    /**
     * 更新
     * @param updateDto 更新信息
     */
    void update(DepartmentUpdateDto updateDto);

    /**
     * 删除
     * @param departmentId 部门ID
     */
    void delete(String departmentId);
}
