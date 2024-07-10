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

package org.dblue.application.module.usergroup.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.module.usergroup.application.dto.*;
import org.dblue.application.module.usergroup.application.service.UserGroupApplicationService;
import org.dblue.application.module.usergroup.application.vo.UserGroupPageVo;
import org.dblue.application.module.usergroup.application.vo.UserGroupVo;
import org.dblue.application.module.usergroup.domain.service.UserGroupDomainService;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

/**
 * 用户组控制器
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 下午4:32
 */
@Tag(name = "用户组控制器", description = "用户组控制器")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/group")
public class UserGroupController {


    private final UserGroupDomainService userGroupDomainService;
    private final UserGroupApplicationService userGroupApplicationService;

    /**
     * 用户组添加
     *
     * @param addDto 用户组信息
     */
    @Operation(summary = "用户组添加", description = "用户组添加")
    @PostMapping("/add")
    public ResponseBean<String> add(@Valid @RequestBody UserGroupAddDto addDto) {
        userGroupDomainService.add(addDto);
        return ResponseBean.success();
    }


    /**
     * 用户组更新
     *
     * @param updateDto 用户组信息
     */
    @Operation(summary = "用户组更新", description = "用户组更新")
    @PutMapping("/update")
    public ResponseBean<String> update(@Valid @RequestBody UserGroupUpdateDto updateDto) {
        userGroupDomainService.update(updateDto);
        return ResponseBean.success();
    }

    /**
     * 用户组删除
     *
     * @param userGroupId 用户组ID
     */
    @Parameter(name = "userGroupId", description = "用户组ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "用户组删除", description = "用户组删除")
    @DeleteMapping("/delete/{userGroupId}")
    public ResponseBean<String> delete(@PathVariable("userGroupId") String userGroupId) {
        userGroupDomainService.delete(userGroupId);
        return ResponseBean.success();
    }

    /**
     * 用户组启用/禁用
     *
     * @param enableDto 用户组启用/禁用信息
     */
    @Operation(summary = "用户组启用/禁用", description = "用户组启用/禁用")
    @PatchMapping("/enable")
    public ResponseBean<String> enable(@Valid @RequestBody UserGroupEnableDto enableDto) {
        userGroupDomainService.enable(enableDto);
        return ResponseBean.success();
    }


    /**
     * 用户组角色添加
     *
     * @param roleAddDto 用户组角色信息
     */
    @Operation(summary = "用户组角色添加", description = "用户组角色添加")
    @PostMapping("/addRole")
    public ResponseBean<String> addRole(@Valid @RequestBody UserGroupRoleAddDto roleAddDto) {
        userGroupDomainService.addRole(roleAddDto);
        return ResponseBean.success();
    }


    /**
     * 用户组角色删除
     *
     * @param userGroupRoleId 用户组角色Id
     */
    @Parameter(name = "userGroupRoleId", description = "用户组角色Id", in = ParameterIn.PATH, required = true)
    @Operation(summary = "用户组角色删除", description = "用户组角色删除")
    @DeleteMapping("/deleteRole/{userGroupRoleId}")
    public ResponseBean<String> deleteRole(@PathVariable("userGroupRoleId") String userGroupRoleId) {
        userGroupDomainService.deleteRole(userGroupRoleId);
        return ResponseBean.success();
    }


    /**
     * 用户组用户添加
     *
     * @param userAddDto 用户组用户信息
     */
    @Operation(summary = "用户组用户添加", description = "用户组用户添加")
    @PostMapping("/addUser")
    public ResponseBean<String> addUser(@Valid @RequestBody UserGroupUserAddDto userAddDto) {
        userGroupDomainService.addUser(userAddDto);
        return ResponseBean.success();
    }

    /**
     * 用户组用户删除
     *
     * @param userGroupUserId 用户组用户Id
     */
    @Parameter(name = "userGroupUserId", description = "用户组用户Id", in = ParameterIn.PATH, required = true)
    @Operation(summary = "用户组用户删除", description = "用户组用户删除")
    @DeleteMapping("/deleteUser/{userGroupUserId}")
    public ResponseBean<String> deleteUser(@PathVariable("userGroupUserId") String userGroupUserId) {
        userGroupDomainService.deleteUser(userGroupUserId);
        return ResponseBean.success();
    }

    /**
     * 获取单独一个
     *
     * @param userGroupId 用户组ID
     * @return 用户组信息
     */
    @Parameter(name = "userGroupId", description = "用户组ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "获取单独一个", description = "获取单独一个")
    @GetMapping("/getOne/{userGroupUserId}")
    public ResponseBean<UserGroupVo> getOne(@PathVariable("userGroupUserId") String userGroupId) {
        return ResponseBean.success(userGroupApplicationService.getOne(userGroupId));
    }

    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 分组
     */
    @Operation(summary = "分页", description = "分页")
    @GetMapping("/page")
    public PageResponseBean<UserGroupPageVo> page(UserGroupPageDto pageDto) {
        return PageResponseBean.success(userGroupApplicationService.page(pageDto));
    }
}
