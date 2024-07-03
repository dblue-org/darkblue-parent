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

package org.dblue.application.module.department.adpater.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.module.department.application.dto.DepartmentAddDto;
import org.dblue.application.module.department.application.dto.DepartmentUpdateDto;
import org.dblue.application.module.department.application.service.DepartmentApplicationService;
import org.dblue.application.module.department.application.vo.DepartmentTreeVo;
import org.dblue.application.module.department.application.vo.DepartmentVo;
import org.dblue.application.module.department.domain.service.DepartmentDomainService;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门控制层
 *
 * @author xie jin
 * @since 1.0.0  2024/6/21 下午3:38
 */
@Tag(name = "部门控制层", description = "部门控制层")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentApplicationService departmentApplicationService;
    private final DepartmentDomainService departmentDomainService;


    /**
     * 获取部门树
     *
     * @return 部门树
     */
    @Operation(summary = "获取部门树", description = "获取部门树")
    @GetMapping("/getAll")
    public ResponseBean<List<DepartmentTreeVo>> getAll() {
        return ResponseBean.success(departmentApplicationService.getAll());
    }

    /**
     * 新增
     *
     * @param addDto 新增信息
     */
    @Operation(summary = "新增", description = "新增")
    @PostMapping("/add")
    public ResponseBean<String> add(@Valid @RequestBody DepartmentAddDto addDto) {
        departmentDomainService.add(addDto);
        return ResponseBean.success();
    }

    /**
     * 更新
     *
     * @param updateDto 更新信息
     */
    @Operation(summary = "更新", description = "更新")
    @PutMapping("/update")
    public ResponseBean<String> update(@Valid @RequestBody DepartmentUpdateDto updateDto) {
        departmentDomainService.update(updateDto);
        return ResponseBean.success();
    }

    /**
     * 删除
     *
     * @param deptId 部门ID
     */
    @Parameter(name = "deptId", description = "部门ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/delete/{deptId}")
    public ResponseBean<String> delete(@PathVariable("deptId") String deptId) {
        departmentDomainService.delete(deptId);
        return ResponseBean.success();
    }

    /**
     * 单个获取
     *
     * @param deptId 部门ID
     * @return 组织信息
     */
    @Parameter(name = "deptId", description = "部门ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "单个获取", description = "单个获取")
    @GetMapping("/getOne/{deptId}")
    public ResponseBean<DepartmentVo> getOne(@PathVariable("deptId") String deptId) {
        return ResponseBean.success(departmentApplicationService.getOne(deptId));
    }
}
