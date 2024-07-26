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

package org.dblue.application.module.position.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.module.position.application.dto.PositionAddDto;
import org.dblue.application.module.position.application.dto.PositionEnableDto;
import org.dblue.application.module.position.application.dto.PositionPageDto;
import org.dblue.application.module.position.application.dto.PositionUpdateDto;
import org.dblue.application.module.position.application.service.PositionApplicationService;
import org.dblue.application.module.position.application.vo.PositionPageVo;
import org.dblue.application.module.position.application.vo.PositionVo;
import org.dblue.application.module.position.application.vo.SimplePositionVo;
import org.dblue.application.module.position.domain.service.PositionDomainService;
import org.dblue.core.annotation.Platform;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 职位管理
 *
 * @author xie jin
 * @since 1.0.0  2024/7/9 上午10:58
 */
@Tag(name = "职位管理", description = "职位控制层")
@Platform
@RestController
@RequestMapping("/api/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionApplicationService positionApplicationService;
    private final PositionDomainService positionDomainService;

    /**
     * 职位添加
     *
     * @param addDto 职位信息
     */
    @Operation(summary = "职位添加", description = "职位添加")
    @PostMapping("/add")
    public ResponseBean<Void> add(@Valid @RequestBody PositionAddDto addDto) {
        positionDomainService.add(addDto);
        return ResponseBean.success();
    }


    /**
     * 职位更新
     *
     * @param updateDto 职位信息
     */
    @Operation(summary = "职位更新", description = "职位更新")
    @PutMapping("/update")
    public ResponseBean<Void> update(@Valid @RequestBody PositionUpdateDto updateDto) {
        positionDomainService.update(updateDto);
        return ResponseBean.success();
    }

    /**
     * 职位删除
     *
     * @param positionId 职位ID
     */
    @Parameter(name = "positionId", description = "职位ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "职位删除", description = "职位删除")
    @DeleteMapping("/delete/{positionId}")
    public ResponseBean<Void> delete(@PathVariable("positionId") String positionId) {
        positionDomainService.delete(positionId);
        return ResponseBean.success();
    }

    /**
     * 启用禁用
     *
     * @param enableDto 启用禁用信息
     */
    @Operation(summary = "职位启用禁用", description = "启用禁用")
    @PatchMapping("/enable")
    public ResponseBean<Void> enable(@Valid @RequestBody PositionEnableDto enableDto) {
        positionDomainService.toggleState(enableDto);
        return ResponseBean.success();
    }


    /**
     * 职位单个查询
     *
     * @param positionId 职位ID
     * @return 职位信息
     */
    @Parameter(name = "positionId", description = "职位ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "职位单个查询", description = "职位单个查询")
    @GetMapping("/getOne/{positionId}")
    public ResponseBean<PositionVo> getOne(@PathVariable("positionId") String positionId) {
        return ResponseBean.success(positionApplicationService.getOne(positionId));
    }

    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 职位信息
     */
    @Operation(summary = "职位分页查询", description = "分页")
    @GetMapping("/page")
    public PageResponseBean<PositionPageVo> page(PositionPageDto pageDto) {
        return PageResponseBean.success(positionApplicationService.page(pageDto));
    }

    /**
     * 查询所有职位信息
     *
     * @param keyword 关键字，名称或编码
     * @return 职位列表
     */
    @Operation(summary = "查询所有职位信息")
    @GetMapping("/findAll")
    public ResponseBean<List<SimplePositionVo>> findAll(String keyword) {
        List<SimplePositionVo> voList = this.positionApplicationService.findAll(keyword);
        return ResponseBean.success(voList);
    }
}
