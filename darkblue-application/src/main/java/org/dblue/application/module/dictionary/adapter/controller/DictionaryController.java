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
import org.dblue.application.module.dictionary.application.dto.DictionaryAddDto;
import org.dblue.application.module.dictionary.application.dto.DictionaryUpdateDto;
import org.dblue.application.module.dictionary.application.service.DictionaryApplicationService;
import org.dblue.application.module.dictionary.application.vo.DictionaryForSelectVo;
import org.dblue.application.module.dictionary.application.vo.DictionaryVo;
import org.dblue.application.module.dictionary.domain.service.DictionaryDomainService;
import org.dblue.core.annotation.Platform;
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
public class DictionaryController {

    private final DictionaryApplicationService dictionaryApplicationService;
    private final DictionaryDomainService dictionaryDomainService;

    /**
     * 字典添加
     *
     * @param addDto 字典信息
     */
    @Operation(summary = "字典添加", description = "字典添加")
    @PostMapping("/add")
    public ResponseBean<Void> add(@Valid @RequestBody DictionaryAddDto addDto) {
        dictionaryDomainService.add(addDto);
        return ResponseBean.success();
    }


    /**
     * 字典更新
     *
     * @param updateDto 字典信息
     */
    @Operation(summary = "字典更新", description = "字典更新")
    @PutMapping("/update")
    public ResponseBean<Void> update(@Valid @RequestBody DictionaryUpdateDto updateDto) {
        dictionaryDomainService.update(updateDto);
        return ResponseBean.success();
    }

    /**
     * 字典删除
     *
     * @param dictionaryId 字典ID
     */
    @Parameter(name = "dictionaryId", description = "字典ID", in = ParameterIn.PATH, required = true)
    @Operation(summary = "字典删除", description = "字典删除")
    @DeleteMapping("/delete/{dictionaryId}")
    public ResponseBean<Void> delete(@PathVariable("dictionaryId") String dictionaryId) {
        dictionaryDomainService.delete(dictionaryId);
        return ResponseBean.success();
    }


    /**
     * 获取全部字典信息
     *
     * @return 字典信息
     */
    @Operation(summary = "获取全部字典信息", description = "获取全部字典信息")
    @GetMapping("/getAll")
    public ResponseBean<List<DictionaryVo>> getAll() {
        return ResponseBean.success(dictionaryApplicationService.getAll());
    }

    /**
     * 获取字典数据（用于Select、SelectTree组件）
     *
     * @param dictionaryCode 字典编码
     * @return 字典数据
     */
    @Operation(summary = "获取字典数据（用于Select、SelectTree组件）")
    @GetMapping("/getDictionaryForSelect")
    public ResponseBean<DictionaryForSelectVo> getDictionaryForSelect(String dictionaryCode) {
        DictionaryForSelectVo selectVo = this.dictionaryApplicationService.getDictionaryForSelect(dictionaryCode);
        return ResponseBean.success(selectVo);
    }
}
