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

package org.dblue.application.module.department.application.service;

import org.dblue.application.module.department.application.vo.DepartmentTreeVo;
import org.dblue.application.module.department.application.vo.DepartmentVo;

import java.util.List;

/**
 * 部门应用服务层
 *
 * @author xie jin
 * @since 1.0.0  2024/6/21 下午1:40
 */
public interface DepartmentApplicationService {

    /**
     * 获取部门树
     * @return 部门树
     */
    List<DepartmentTreeVo> getAll();

    /**
     * 单个获取
     * @param deptId 部门ID
     * @return 组织信息
     */
    DepartmentVo getOne(String deptId);
}
