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

package org.dblue.application.module.menu.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.module.menu.application.dto.MenuAddDto;
import org.dblue.application.module.menu.application.dto.MenuEnableDto;
import org.dblue.application.module.menu.application.dto.MenuUpdateDto;
import org.dblue.application.module.menu.application.service.MenuApplicationService;
import org.dblue.application.module.menu.application.vo.MenuTreeVo;
import org.dblue.application.module.menu.application.vo.RoleMenuVo;
import org.dblue.application.module.menu.domain.service.MenuDomainService;
import org.dblue.core.annotation.Platform;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理
 *
 * @author xie jin
 * @since 1.0.0  2024/6/17 下午1:49
 */
@Tag(name = "菜单管理", description = "菜单控制层")
@Platform
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuDomainService menuDomainService;
    private final MenuApplicationService menuApplicationService;

    /**
     * 菜单添加
     *
     * @param menuAddDto 菜单信息
     */
    @Operation(summary = "菜单添加", description = "菜单添加")
    @PostMapping("/add")
    public ResponseBean<Void> add(@RequestBody @Valid MenuAddDto menuAddDto) {
        menuDomainService.add(menuAddDto);
        return ResponseBean.success();
    }


    /**
     * 菜单更新
     *
     * @param menuUpdateDto 菜单信息
     */
    @Operation(summary = "菜单更新", description = "菜单更新")
    @PutMapping("/update")
    public ResponseBean<Void> update(@RequestBody @Valid MenuUpdateDto menuUpdateDto) {
        menuDomainService.update(menuUpdateDto);
        return ResponseBean.success();
    }


    /**
     * 菜单删除
     *
     * @param menuId 菜单ID
     */
    @Parameter(name = "menuId", description = "菜单ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "菜单删除", description = "菜单删除")
    @DeleteMapping("/delete/{id}")
    public ResponseBean<Void> delete(@PathVariable("id") String menuId) {
        menuApplicationService.delete(menuId);
        return ResponseBean.success();
    }

    /**
     * 获取所有PC端菜单
     *
     * @return 菜单列表
     */
    @Operation(summary = "获取所有PC端菜单", description = "获取所有PC端菜单")
    @GetMapping("/findAllPcMenus")
    public ResponseBean<List<MenuTreeVo>> findAllPcMenus() {
        return ResponseBean.success(menuApplicationService.findAllPcMenus());
    }

    /**
     * 获取所有APP端菜单
     *
     * @return 菜单列表
     */
    @Operation(summary = "获取所有APP端菜单", description = "获取所有APP端菜单")
    @GetMapping("/findAllAppMenus")
    public ResponseBean<List<MenuTreeVo>> findAllAppMenus() {
        return ResponseBean.success(menuApplicationService.findAllAppMenus());
    }

    /**
     * 菜单启用禁用
     *
     * @param menuEnableDto 菜单信息
     */
    @Operation(summary = "菜单启用禁用", description = "菜单启用禁用")
    @PatchMapping("/toggleState")
    public ResponseBean<Void> toggleState(@Valid @RequestBody MenuEnableDto menuEnableDto) {
        menuDomainService.toggleState(menuEnableDto);
        return ResponseBean.success();
    }

    /**
     * 根据角色ID获取菜单多选框树
     *
     * @param roleId 角色ID
     * @return 菜单多选框树
     */
    @Operation(summary = "根据角色ID获取菜单多选框树")
    @GetMapping("/getMenuCheckBoxTree")
    public ResponseBean<RoleMenuVo> getMenuCheckBoxTree(@RequestParam String roleId) {
        return ResponseBean.success(menuApplicationService.getMenuCheckBoxTree(roleId));
    }
}
