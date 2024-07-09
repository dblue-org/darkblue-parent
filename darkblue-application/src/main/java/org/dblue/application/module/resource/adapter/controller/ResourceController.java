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
import org.dblue.application.module.resource.application.dto.ResourceAddDto;
import org.dblue.application.module.resource.application.dto.ResourcePageDto;
import org.dblue.application.module.resource.application.dto.ResourceUpdateDto;
import org.dblue.application.module.resource.application.service.ResourceApplicationService;
import org.dblue.application.module.resource.application.vo.ResourceControllerVo;
import org.dblue.application.module.resource.application.vo.ResourcePageVo;
import org.dblue.application.module.resource.domain.service.ResourceDomainService;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源控制层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/3 上午9:45
 */
@Tag(name = "资源控制层", description = "资源控制层")
@RequestMapping("/api/resource")
@RestController
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceApplicationService resourceApplicationService;
    private final ResourceDomainService resourceDomainService;

    /**
     * 资源添加
     *
     * @param addDto 信息
     */
    @Operation(summary = "资源添加", description = "资源添加")
    @PostMapping("/add")
    public ResponseBean<String> add(@Valid @RequestBody ResourceAddDto addDto) {
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
    public ResponseBean<String> update(@Valid @RequestBody ResourceUpdateDto updateDto) {
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
    public ResponseBean<String> delete(@PathVariable("resourceId") String resourceId) {
        resourceApplicationService.delete(resourceId);
        return ResponseBean.success();
    }


    /**
     * 分页查询
     *
     * @param pageDto 查询参数
     * @return 资源
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public PageResponseBean<ResourcePageVo> page(ResourcePageDto pageDto) {
        return PageResponseBean.success(resourceApplicationService.page(pageDto));
    }

    /**
     * 获取资源信息
     *
     * @return 资源信息
     */
    @Operation(summary = "获取资源信息", description = "获取资源信息")
    @GetMapping("/getResourceController")
    public ResponseBean<List<ResourceControllerVo>> getResourceController() {
        return ResponseBean.success(resourceApplicationService.getResourceController());
    }
}
