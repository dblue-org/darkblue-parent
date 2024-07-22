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
import org.dblue.application.module.permission.application.dto.*;
import org.dblue.application.module.permission.application.service.PermissionApplicationService;
import org.dblue.application.module.permission.application.vo.*;
import org.dblue.application.module.permission.domain.service.PermissionDomainService;
import org.dblue.core.annotation.Platform;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限管理
 *
 * @author xie jin
 * @since 1.0.0  2024/6/14 下午5:30
 */
@Tag(name = "权限管理", description = "权限控制层")
@Platform
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
    @PutMapping("/update")
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

    /**
     * 获取权限资源信息
     *
     * @param permissionId 权限ID
     * @return 权限资源信息
     */
    @Operation(summary = "获取权限资源信息")
    @GetMapping("/findPermissionResource/{permissionId}")
    public ResponseBean<List<PermissionResourceVo>> findPermissionResource(@PathVariable String permissionId) {
        List<PermissionResourceVo> voList = this.permissionApplicationService.findPermissionResource(permissionId);
        return ResponseBean.success(voList);
    }

    /**
     * 获取权限关联的角色
     *
     * @param queryDto 分页查询参数
     * @return 权限资源信息
     */
    @Operation(summary = "获取权限关联的角色")
    @GetMapping("/findPermissionRoles")
    public PageResponseBean<PermissionRoleVo> findPermissionRoles(PermissionRoleQueryDto queryDto) {
        Page<PermissionRoleVo> voPage = this.permissionApplicationService.findPermissionRoles(queryDto);
        return PageResponseBean.success(voPage);
    }

    /**
     * 获取权限信息并标记是否选中
     *
     * @param checkBoxDto 查询信息
     * @return 权限信息
     */
    @Parameter(name = "checkBoxDto", description = "查询信息", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取权限信息并标记是否选中", description = "获取权限信息并标记是否选中")
    @GetMapping("/getPermissionCheckBox")
    public ResponseBean<List<PermissionCheckBoxVo>> getPermissionCheckBox(@Valid PermissionCheckBoxDto checkBoxDto) {
        return ResponseBean.success(permissionApplicationService.getPermissionCheckBox(checkBoxDto));
    }
}
