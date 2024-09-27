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
package org.dblue.application.macro;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dblue.utils.json.JacksonUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DefaultMacroExecutorImpl implements MacroExecutor {

    private final List<Macro> macros;

    @Override
    public void executeMacro(String macroCode, String todoId, Map<String, Object> macroParamValues) {
        log.info("宏编码：{}，待办ID：{}，宏参数：{}", macroCode, todoId, JacksonUtils.toJsonString(macroParamValues));
        for (Macro macro : macros) {
            if (Objects.equals(macroCode, macro.getMacroName())) {
                log.info("匹配到宏：{}，开始执行宏。", macro.getClass().getName());
                macro.execute(todoId, macroParamValues);
                return;
            }
        }
    }

    @Override
    public List<MacroParameter> getMacroParameters(String macroCode) {
        Optional<Macro> macroOptional = this.getMacro(macroCode);
        if (macroOptional.isPresent()) {
            return macroOptional.get().getMacroParameters();
        } else {
            return List.of();
        }
    }

    @Override
    public Optional<Macro> getMacro(String macroCode) {
        log.info("获取宏参数，宏编码：{}", macroCode);
        for (Macro macro : macros) {
            if (Objects.equals(macroCode, macro.getMacroCode())) {
                return Optional.of(macro);
            }
        }
        return Optional.empty();
    }
}