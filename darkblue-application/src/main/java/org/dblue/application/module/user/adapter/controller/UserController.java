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
package org.dblue.application.module.user.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.module.user.application.dto.UserAddDto;
import org.dblue.application.module.user.application.dto.UserEnableDto;
import org.dblue.application.module.user.application.dto.UserPageDto;
import org.dblue.application.module.user.application.dto.UserUpdateDto;
import org.dblue.application.module.user.application.service.UserApplicationService;
import org.dblue.application.module.user.application.vo.UserMenuVo;
import org.dblue.application.module.user.application.vo.UserPageVo;
import org.dblue.application.module.user.application.vo.UserSelectVo;
import org.dblue.application.module.user.application.vo.UserVo;
import org.dblue.application.module.user.domain.service.UserDomainService;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制层
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Tag(name = "用户控制层", description = "用户控制层")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserDomainService userDomainService;
    private final UserApplicationService userApplicationService;

    /**
     * 用户添加
     *
     * @param addDto 用户信息
     */
    @Operation(summary = "用户添加", description = "用户添加")
    @PostMapping("/add")
    public ResponseBean<String> add(@Valid @RequestBody UserAddDto addDto) {
        userDomainService.add(addDto);
        return ResponseBean.success();
    }

    /**
     * 用户更新
     *
     * @param updateDto 用户信息
     */
    @Operation(summary = "用户更新", description = "用户更新")
    @PutMapping("/update")
    public ResponseBean<String> update(@Valid @RequestBody UserUpdateDto updateDto) {
        userDomainService.update(updateDto);
        return ResponseBean.success();
    }

    /**
     * 用户删除
     *
     * @param userId 用户ID
     */
    @Parameter(name = "userId", description = "用户ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "用户删除", description = "用户删除")
    @DeleteMapping("/delete/{userId}")
    public ResponseBean<String> delete(@PathVariable("userId") String userId) {
        userDomainService.delete(userId);
        return ResponseBean.success();
    }

    /**
     * 用户启用/禁用
     *
     * @param enableDto 启用/禁用信息
     */
    @Operation(summary = "用户启用/禁用", description = "用户启用/禁用")
    @PatchMapping("/enable")
    public ResponseBean<String> enable(@Valid @RequestBody UserEnableDto enableDto) {
        userDomainService.enable(enableDto);
        return ResponseBean.success();
    }


    /**
     * 分页查询
     *
     * @param pageDto 查询参数
     * @return 用户信息
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public PageResponseBean<UserPageVo> page(@Valid UserPageDto pageDto) {
        return PageResponseBean.success(userApplicationService.page(pageDto));
    }

    /**
     * 单个信息获取
     *
     * @param userId 用户ID
     * @return 单个信息
     */
    @Parameter(name = "userId", description = "用户ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "单个信息获取", description = "单个信息获取")
    @GetMapping("/getOne/{userId}")
    public ResponseBean<UserVo> getOne(@PathVariable("userId") String userId) {
        return ResponseBean.success(userApplicationService.getOne(userId));
    }

    /**
     * 下拉列表使用
     * @param name 用户名/姓名
     * @return 用户信息
     */
    @Parameter(name = "name", description = "用户名/姓名", in = ParameterIn.QUERY)
    @Operation(summary = "下拉列表使用", description = "下拉列表使用")
    @GetMapping("/selectByNameOrUserName")
    public ResponseBean<List<UserSelectVo>> selectByNameOrUserName(@RequestParam(required = false) String name) {
        return ResponseBean.success(userApplicationService.selectByNameOrUserName(name));
    }

    /**
     * 获取用户菜单权限
     *
     * @return 用户菜单权限
     */
    @GetMapping("/getUserMenu/pc")
    public ResponseBean<List<UserMenuVo>> getUserMenu() {
        return ResponseBean.success(userApplicationService.getUserMenu());
    }
}