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

package org.dblue.application.module.role.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.module.role.application.dto.RoleAddDto;
import org.dblue.application.module.role.application.dto.RolePageDto;
import org.dblue.application.module.role.application.dto.RoleUpdateDto;
import org.dblue.application.module.role.application.service.RoleApplicationService;
import org.dblue.application.module.role.application.vo.RolePageVo;
import org.dblue.application.module.role.domain.service.RoleDomainService;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

/**
 * 角色控制器
 *
 * @author xie jin
 * @since 1.0.0  2024/6/18 下午4:00
 */
@Tag(name = "角色控制器", description = "角色控制器")
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleDomainService roleDomainService;
    private final RoleApplicationService roleApplicationService;

    /**
     * 角色添加
     *
     * @param roleAddDto 角色信息
     */
    @Operation(summary = "角色添加", description = "角色添加")
    @PostMapping("/add")
    public ResponseBean<String> add(@RequestBody @Valid RoleAddDto roleAddDto){
        roleDomainService.add(roleAddDto);
        return ResponseBean.success();
    }

    /**
     * 角色更新
     *
     * @param roleUpdateDto 角色信息
     */
    @Operation(summary = "角色更新", description = "角色更新")
    @PostMapping("/update")
    public ResponseBean<String> update(@RequestBody @Valid RoleUpdateDto roleUpdateDto){
        roleDomainService.update(roleUpdateDto);
        return ResponseBean.success();
    }

    /**
     * 角色删除
     * @param roleId 角色ID
     */
    @Parameter(name = "roleId", description = "角色ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "角色删除", description = "角色删除")
    @DeleteMapping("/delete/{roleId}")
    public ResponseBean<String> delete(@PathVariable("roleId") String roleId){
        roleDomainService.delete(roleId);
        return ResponseBean.success();
    }

    /**
     * 分页查询角色信息
     *
     * @param query 查询条件
     * @return 角色列表
     */
    @Operation(summary = "分页查询角色信息", description = "分页查询角色信息")
    @GetMapping("/page")
    public PageResponseBean<RolePageVo> findByPage(RolePageDto query){
        return PageResponseBean.success(roleApplicationService.findByPage(query));
    }
}
