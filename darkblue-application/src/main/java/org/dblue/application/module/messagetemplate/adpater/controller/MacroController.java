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
package org.dblue.application.module.messagetemplate.adpater.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.macro.Macro;
import org.dblue.application.macro.MacroParameter;
import org.dblue.application.module.messagetemplate.application.dto.MacroExecuteDto;
import org.dblue.application.module.messagetemplate.application.service.MacroApplicationService;
import org.dblue.application.module.messagetemplate.application.vo.MacroVo;
import org.dblue.core.annotation.Platform;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 消息模板
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Platform
@RequiredArgsConstructor
@Tag(name = "消息模板-宏处理")
@RequestMapping("/api/message-template/macro")
@RestController
public class MacroController {

    private final MacroApplicationService macroApplicationService;

    /**
     * 获取系统中所有的宏
     *
     * @return 宏列表
     */
    @Operation(summary = "获取系统中所有的宏")
    @GetMapping("/findAll")
    public ResponseBean<List<MacroVo>> findAll() {
        List<MacroVo> voList = this.macroApplicationService.findAll();
        return ResponseBean.success(voList);
    }


    /**
     * 执行宏操作，{@link Macro#execute(String, Map)}
     *
     * @param macroExecuteDto 执行宏的参数信息
     */
    @Operation(summary = "执行宏操作")
    @PostMapping("/executeMacro")
    public ResponseBean<Void> executeMacro(@RequestBody @Valid MacroExecuteDto macroExecuteDto) {
        this.macroApplicationService.executeMacro(macroExecuteDto.getMacroCode(), macroExecuteDto.getTodoId(), macroExecuteDto.getMacroParamValues());
        return ResponseBean.success();
    }

    /**
     * 根据宏编码获取宏参数列表
     *
     * @param macroCode 宏编码
     * @return 宏参数列表
     */
    @Operation(summary = "根据宏编码获取宏参数列表")
    @GetMapping("/getMacroParameters/{macroCode}")
    public ResponseBean<List<MacroParameter>> getMacroParameters(@PathVariable String macroCode) {
        List<MacroParameter> macroParameters = this.macroApplicationService.getMacroParameters(macroCode);
        return ResponseBean.success(macroParameters);
    }

}