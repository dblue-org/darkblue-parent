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
package org.dblue.application.module.messagetemplate.application.service;

import org.dblue.application.macro.Macro;
import org.dblue.application.macro.MacroParameter;
import org.dblue.application.module.messagetemplate.application.vo.MacroVo;

import java.util.List;
import java.util.Map;

/**
 * 宏处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface MacroApplicationService {

    /**
     * 获取系统中所有的宏
     *
     * @return 宏列表
     */
    List<MacroVo> findAll();

    /**
     * 执行宏操作，{@link Macro#execute(String, Map)}
     *
     * @param macroCode        宏编码. {@link Macro#getMacroCode()}
     * @param todoId           待办消息ID.
     * @param macroParamValues 由前端提交的宏参数
     */
    void executeMacro(String macroCode, String todoId, Map<String, Object> macroParamValues);

    /**
     * 根据宏编码获取宏参数列表
     *
     * @param macroCode 宏编码
     * @return 宏参数列表
     */
    List<MacroParameter> getMacroParameters(String macroCode);
}