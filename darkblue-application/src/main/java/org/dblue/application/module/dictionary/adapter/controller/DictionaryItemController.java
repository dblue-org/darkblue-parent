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

package org.dblue.application.module.dictionary.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.module.dictionary.application.dto.DictionaryItemAddDto;
import org.dblue.application.module.dictionary.application.dto.DictionaryItemEnableDto;
import org.dblue.application.module.dictionary.application.dto.DictionaryItemPageDto;
import org.dblue.application.module.dictionary.application.dto.DictionaryItemUpdateDto;
import org.dblue.application.module.dictionary.application.service.DictionaryApplicationService;
import org.dblue.application.module.dictionary.application.vo.DictionaryItemPageVo;
import org.dblue.application.module.dictionary.application.vo.DictionaryItemTreeVo;
import org.dblue.application.module.dictionary.domain.service.DictionaryDomainService;
import org.dblue.core.annotation.Platform;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典管理
 *
 * @author xie jin
 * @since 1.0.0  2024/7/15 上午10:59
 */
@Tag(name = "字典管理", description = "字典控制层")
@Platform
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dictionary")
public class DictionaryItemController {

    private final DictionaryApplicationService dictionaryApplicationService;
    private final DictionaryDomainService dictionaryDomainService;


    /**
     * 字典项添加
     *
     * @param addDto 字典信息
     */
    @Operation(summary = "字典项添加", description = "字典添加")
    @PostMapping("/addItem")
    public ResponseBean<Void> addItem(@Valid @RequestBody DictionaryItemAddDto addDto) {
        dictionaryDomainService.addItem(addDto);
        return ResponseBean.success();
    }

    /**
     * 字典项更新
     *
     * @param updateDto 字典信息
     */
    @Operation(summary = "字典项更新", description = "字典更新")
    @PutMapping("/updateItem")
    public ResponseBean<Void> updateItem(@Valid @RequestBody DictionaryItemUpdateDto updateDto) {
        dictionaryDomainService.updateItem(updateDto);
        return ResponseBean.success();
    }

    /**
     * 字典项删除
     *
     * @param dictionaryItemId 字典项ID
     */
    @Parameter(name = "dictionaryItemId", description = "字典项ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "字典项删除", description = "字典项删除")
    @DeleteMapping("/deleteItem/{dictionaryItemId}")
    public ResponseBean<Void> deleteItem(@PathVariable("dictionaryItemId") String dictionaryItemId) {
        dictionaryDomainService.deleteItem(dictionaryItemId);
        return ResponseBean.success();
    }

    /**
     * 字典项启用禁用
     *
     * @param enableDto 启用禁用信息
     */
    @Operation(summary = "字典项启用禁用", description = "字典项启用禁用")
    @PatchMapping("/toggleItemState")
    public ResponseBean<Void> toggleItemState(@Valid @RequestBody DictionaryItemEnableDto enableDto) {
        dictionaryDomainService.toggleItemState(enableDto);
        return ResponseBean.success();
    }


    /**
     * 获取树形字典项
     *
     * @param dictionaryId 字典ID
     * @return 字典
     */
    @Parameter(name = "dictionaryId", description = "字典ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "获取树形字典项", description = "获取树形字典项")
    @GetMapping("/getItemTree/{dictionaryId}")
    public ResponseBean<List<DictionaryItemTreeVo>> getItemTree(@PathVariable("dictionaryId") String dictionaryId) {
        return ResponseBean.success(dictionaryApplicationService.getItemTree(dictionaryId));
    }

    /**
     * 字典项分页
     *
     * @param itemPageDto 查询参数
     * @return 字典项
     */
    @Operation(summary = "字典项分页", description = "字典项分页")
    @GetMapping("/findItemByPage")
    public PageResponseBean<DictionaryItemPageVo> findItemByPage(DictionaryItemPageDto itemPageDto) {
        return PageResponseBean.success(dictionaryApplicationService.findItemByPage(itemPageDto));
    }

}
