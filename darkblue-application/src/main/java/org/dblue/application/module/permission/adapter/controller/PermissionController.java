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

package org.dblue.application.module.permission.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.module.permission.application.dto.PermissionAddDto;
import org.dblue.application.module.permission.application.dto.PermissionPageDto;
import org.dblue.application.module.permission.application.dto.PermissionResourceDto;
import org.dblue.application.module.permission.application.dto.PermissionUpdateDto;
import org.dblue.application.module.permission.application.service.PermissionApplicationService;
import org.dblue.application.module.permission.application.vo.PermissionPageVo;
import org.dblue.application.module.permission.application.vo.PermissionVo;
import org.dblue.application.module.permission.domain.service.PermissionDomainService;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

/**
 * 权限控制层
 *
 * @author xie jin
 * @since 1.0.0  2024/6/14 下午5:30
 */
@Tag(name = "权限控制层", description = "权限控制层")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/permission")
public class PermissionController {

    private final PermissionDomainService permissionDomainService;
    private final PermissionApplicationService permissionApplicationService;

    /**
     * 权限添加
     *
     * @param permissionAddDto 权限信息
     */
    @Operation(summary = "权限添加", description = "权限添加")
    @PostMapping("/add")
    public ResponseBean<String> add(@RequestBody @Valid PermissionAddDto permissionAddDto) {
        permissionDomainService.add(permissionAddDto);
        return ResponseBean.success();
    }


    /**
     * 权限更新
     *
     * @param permissionUpdateDto 权限信息
     */
    @Operation(summary = "权限更新", description = "权限更新")
    @PostMapping("/update")
    public ResponseBean<String> update(@RequestBody @Valid PermissionUpdateDto permissionUpdateDto) {
        permissionDomainService.update(permissionUpdateDto);
        return ResponseBean.success();
    }


    /**
     * 权限删除
     *
     * @param permissionId 权限id
     */
    @Parameter(name = "permissionId", description = "权限id", in = ParameterIn.PATH, required = true)
    @Operation(summary = "权限删除", description = "权限删除")
    @DeleteMapping("/delete/{permissionId}")
    public ResponseBean<String> delete(@PathVariable("permissionId") String permissionId) {
        permissionApplicationService.delete(permissionId);
        return ResponseBean.success();
    }

    /**
     * 分页查询权限信息
     *
     * @param query 查询条件
     * @return 权限列表
     */
    @Operation(summary = "分页查询权限信息", description = "分页查询权限信息")
    @GetMapping("/findByPage")
    public PageResponseBean<PermissionPageVo> findByPage(PermissionPageDto query) {
        return PageResponseBean.success(permissionApplicationService.findByPage(query));
    }


    /**
     * 绑定资源
     *
     * @param resourceDto 信息
     */
    @Operation(summary = "绑定资源", description = "绑定资源")
    @PostMapping("/setResource")
    public ResponseBean<String> setResource(@RequestBody @Valid PermissionResourceDto resourceDto) {
        permissionDomainService.setResource(resourceDto);
        return ResponseBean.success();
    }

    /**
     * 权限信息
     *
     * @param permissionId 权限ID
     * @return 权限信息
     */
    @Parameter(name = "permissionId", description = "权限ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "权限信息", description = "权限信息")
    @GetMapping("/getOne/{permissionId}")
    public ResponseBean<PermissionVo> getOne(@PathVariable("permissionId") String permissionId) {
        return ResponseBean.success(permissionApplicationService.getOne(permissionId));
    }
}
