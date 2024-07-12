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
import org.dblue.application.module.resource.application.dto.ResourceGroupAddDto;
import org.dblue.application.module.resource.application.dto.ResourceGroupUpdateDto;
import org.dblue.application.module.resource.application.service.ResourceGroupApplicationService;
import org.dblue.application.module.resource.application.vo.ResourceGroupVo;
import org.dblue.application.module.resource.domain.service.ResourceGroupDomainService;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源组控制层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 下午4:58
 */
@Tag(name = "资源组控制层", description = "资源组控制层")
@RequestMapping("/api/resource/group")
@RestController
@RequiredArgsConstructor
public class ResourceGroupController {

    private final ResourceGroupDomainService resourceGroupDomainService;
    private final ResourceGroupApplicationService resourceGroupApplicationService;

    /**
     * 资源组添加
     *
     * @param addDto 添加信息
     */
    @Operation(summary = "资源组添加", description = "资源组添加")
    @PostMapping("/add")
    public ResponseBean<String> add(@Valid @RequestBody ResourceGroupAddDto addDto) {
        resourceGroupDomainService.add(addDto);
        return ResponseBean.success();
    }


    /**
     * 资源组更新
     *
     * @param updateDto 更新信息
     */
    @Operation(summary = "资源组更新", description = "资源组更新")
    @PostMapping("/update")
    public ResponseBean<String> update(@Valid @RequestBody ResourceGroupUpdateDto updateDto) {
        resourceGroupDomainService.update(updateDto);
        return ResponseBean.success();
    }

    /**
     * 资源组删除
     *
     * @param resourceGroupId 资源组ID
     */
    @Parameter(name = "resourceGroupId", description = "资源组ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "资源组删除", description = "资源组删除")
    @DeleteMapping("/delete/{resourceGroupId}")
    public ResponseBean<String> delete(@PathVariable("resourceGroupId") String resourceGroupId) {
        resourceGroupDomainService.delete(resourceGroupId);
        return ResponseBean.success();
    }

    /**
     * 获取全部资源组信息
     *
     * @param platform 适用平台(1-PC；2-APP)
     * @return 资源组信息
     */
    @Parameter(name = "platform", description = "适用平台(1-PC；2-APP)", in = ParameterIn.PATH, required = true)
    @Operation(summary = "获取全部资源组信息", description = "获取全部资源组信息")
    @GetMapping("/getAll/{platform}")
    public ResponseBean<List<ResourceGroupVo>> getAll(@PathVariable Integer platform) {
        return ResponseBean.success(resourceGroupApplicationService.getAll(platform));
    }
}
