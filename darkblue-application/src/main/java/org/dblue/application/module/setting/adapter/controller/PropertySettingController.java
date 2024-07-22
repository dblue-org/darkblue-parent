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
package org.dblue.application.module.setting.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.dblue.application.module.setting.application.dto.PropertySettingAddDto;
import org.dblue.application.module.setting.application.dto.PropertySettingChangeValueDto;
import org.dblue.application.module.setting.application.dto.PropertySettingQueryDto;
import org.dblue.application.module.setting.application.dto.PropertySettingUpdateDto;
import org.dblue.application.module.setting.application.service.PropertySettingApplicationService;
import org.dblue.application.module.setting.application.vo.PropertySettingPageListVo;
import org.dblue.core.annotation.Platform;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 系统参数配置
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Tag(name = "系统参数配置")
@Platform
@RestController
@RequestMapping("/api/property-setting")
public class PropertySettingController {

    private final PropertySettingApplicationService propertySettingApplicationService;

    public PropertySettingController(PropertySettingApplicationService propertySettingApplicationService) {
        this.propertySettingApplicationService = propertySettingApplicationService;
    }


    /**
     * 添加参数
     *
     * @param addDto 参数信息
     */
    @Operation(summary = "添加参数")
    @PostMapping("/add")
    public ResponseBean<Void> add(@RequestBody @Valid PropertySettingAddDto addDto) {
        this.propertySettingApplicationService.add(addDto);
        return ResponseBean.success();
    }

    /**
     * 更新参数
     *
     * @param updateDto 参数信息
     */
    @Operation(summary = "更新参数")
    @PutMapping("/update")
    public ResponseBean<Void> update(@RequestBody @Valid PropertySettingUpdateDto updateDto) {
        this.propertySettingApplicationService.update(updateDto);
        return ResponseBean.success();
    }

    /**
     * 删除参数
     *
     * @param propertyId 参数ID
     */
    @Operation(summary = "删除参数")
    @DeleteMapping("/delete/{propertyId}")
    public ResponseBean<Void> delete(@PathVariable String propertyId) {
        this.propertySettingApplicationService.delete(propertyId);
        return ResponseBean.success();
    }

    /**
     * 更新参数的值
     *
     * @param changeValueDto 参数值
     */
    @Operation(summary = "更新参数的值")
    @PatchMapping("/changeValue")
    public ResponseBean<Void> changeValue(@RequestBody @Valid PropertySettingChangeValueDto changeValueDto) {
        this.propertySettingApplicationService.changeValue(changeValueDto.getPropertyId(), changeValueDto.getValue());
        return ResponseBean.success();
    }

    /**
     * 分页查询参数信息
     *
     * @param queryDto 查询条件
     * @return 参数信息
     */
    @Operation(summary = "分页查询参数信息")
    @GetMapping("/findByPage")
    public PageResponseBean<PropertySettingPageListVo> findByPage(PropertySettingQueryDto queryDto) {
        Page<PropertySettingPageListVo> voPage = this.propertySettingApplicationService.findByPage(queryDto);
        return PageResponseBean.success(voPage);
    }

}
