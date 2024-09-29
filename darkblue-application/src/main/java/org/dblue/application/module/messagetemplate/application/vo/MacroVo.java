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
package org.dblue.application.module.messagetemplate.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dblue.application.macro.Macro;

/**
 * 宏信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "宏信息")
@Data
public class MacroVo {

    /**
     * 宏编码
     */
    @Schema(description = "宏编码")
    private String macroCode;

    /**
     * 宏名称
     */
    @Schema(description = "宏名称")
    private String macroName;

    /**
     * 宏对应的类
     */
    @Schema(description = "宏对应的类")
    private String macroClass;

    public static MacroVo of(Macro macro) {
        MacroVo macroVo = new MacroVo();
        macroVo.setMacroCode(macro.getMacroCode());
        macroVo.setMacroName(macro.getMacroName());
        macroVo.setMacroClass(macro.getClass().getName());
        return macroVo;
    }
}