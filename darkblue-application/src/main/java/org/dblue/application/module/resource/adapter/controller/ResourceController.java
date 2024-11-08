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

package org.dblue.application.module.resource.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.module.resource.application.dto.*;
import org.dblue.application.module.resource.application.service.ResourceApplicationService;
import org.dblue.application.module.resource.application.service.SpringMvcMappingService;
import org.dblue.application.module.resource.application.vo.ResourceControllerVo;
import org.dblue.application.module.resource.application.vo.ResourceInvalidVo;
import org.dblue.application.module.resource.application.vo.ResourceMappingVo;
import org.dblue.application.module.resource.application.vo.ResourcePageVo;
import org.dblue.application.module.resource.domain.service.ResourceDomainService;
import org.dblue.core.annotation.Platform;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源管理
 *
 * @author xie jin
 * @since 1.0.0  2024/7/3 上午9:45
 */
@Tag(name = "资源管理", description = "资源管理")
@Platform
@RequestMapping("/api/resource")
@RestController
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceApplicationService resourceApplicationService;
    private final ResourceDomainService resourceDomainService;
    private final SpringMvcMappingService springMvcMappingService;

    /**
     * 资源添加
     *
     * @param addDto 信息
     */
    @Operation(summary = "资源添加", description = "资源添加")
    @PostMapping("/add")
    public ResponseBean<Void> add(@Valid @RequestBody ResourceAddDto addDto) {
        resourceDomainService.add(addDto);
        return ResponseBean.success();
    }


    /**
     * 资源更新
     *
     * @param updateDto 信息
     */
    @Operation(summary = "资源更新", description = "资源更新")
    @PutMapping("/update")
    public ResponseBean<Void> update(@Valid @RequestBody ResourceUpdateDto updateDto) {
        resourceDomainService.update(updateDto);
        return ResponseBean.success();
    }


    /**
     * 资源删除
     *
     * @param resourceId 资源Id
     */
    @Parameter(name = "resourceId", description = "资源Id", in = ParameterIn.PATH, required = true)
    @Operation(summary = "资源删除", description = "资源删除")
    @DeleteMapping("/delete/{resourceId}")
    public ResponseBean<Void> delete(@PathVariable("resourceId") String resourceId) {
        resourceApplicationService.delete(resourceId);
        return ResponseBean.success();
    }


    /**
     * 分页查询
     *
     * @param pageDto 查询参数
     * @return 资源
     */
    @Operation(summary = "分页查询资源", description = "分页查询")
    @GetMapping("/findByPage")
    public PageResponseBean<ResourcePageVo> findByPage(ResourcePageDto pageDto) {
        return PageResponseBean.success(resourceApplicationService.findByPage(pageDto));
    }

    /**
     * 获取资源信息
     *
     * @return 资源信息
     */
    @Parameter(name = "platform", in = ParameterIn.QUERY)
    @Operation(summary = "获取资源信息", description = "获取资源信息")
    @GetMapping("/getResourceMappings")
    public ResponseBean<List<ResourceControllerVo>> getResourceMappings(
            @RequestParam(required = false) Integer platform) {
        return ResponseBean.success(springMvcMappingService.getResourceMappings(platform));
    }

    /**
     * 设置权限
     *
     * @param permissionDto 权限信息
     */
    @Operation(summary = "为资源设置访问权限", description = "设置权限")
    @PostMapping("/setPermission")
    public ResponseBean<Void> setPermission(@Valid @RequestBody ResourcePermissionDto permissionDto) {
        resourceApplicationService.setPermission(permissionDto);
        return ResponseBean.success();
    }

    /**
     * 批量添加或者更新
     */
    @Operation(summary = "批量添加或者更新资源", description = "批量添加或者更新")
    @PostMapping("/batchAddOrUpdate")
    public ResponseBean<Void> batchAddOrUpdate() {
        resourceApplicationService.batchAddOrUpdate();
        return ResponseBean.success();
    }


    /**
     * 批量添加
     *
     * @param batchAddDto 资源信息
     */
    @Operation(summary = "批量添加资源", description = "批量添加")
    @PostMapping("/batchAdd")
    public ResponseBean<Void> batchAdd(@RequestBody @Valid ResourceBatchAddDto batchAddDto) {
        resourceApplicationService.batchAdd(batchAddDto);
        return ResponseBean.success();
    }

    /**
     * 检测资源合法性
     */
    @Operation(summary = "检测资源合法性", description = "检测资源合法性")
    @PutMapping("/checkResourceValidity")
    public ResponseBean<List<ResourceInvalidVo>> checkResourceValidity() {
        return ResponseBean.success(resourceApplicationService.checkResourceValidity());
    }

    /**
     * 根据资源地址获取资源信息
     *
     * @param getDto 资源查询参数
     * @return 资源信息
     */
    @Operation(summary = "根据资源地址获取资源信息", description = "此接口获取的是Spring MVC中的资源信息，并不是数据库中的资源信息，目的是为了在更新时直接同步部分信息")
    @GetMapping("/getMapping")
    public ResponseBean<ResourceMappingVo> getMapping(MappingGetDto getDto) {
        ResourceMappingVo mappingVo = this.springMvcMappingService.getMapping(
                getDto.getRequestMethod(), getDto.getResourceUrl()
        );
        return ResponseBean.success(mappingVo);
    }
}
