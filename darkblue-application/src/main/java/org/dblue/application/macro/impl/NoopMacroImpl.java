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
package org.dblue.application.macro.impl;

import lombok.extern.slf4j.Slf4j;
import org.dblue.application.macro.Macro;
import org.dblue.application.macro.MacroParameter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 空操作宏
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Component
@Slf4j
public class NoopMacroImpl implements Macro {

    @Override
    public String getMacroCode() {
        return "Noop";
    }

    @Override
    public String getMacroName() {
        return "空操作";
    }

    @Override
    public List<MacroParameter> getMacroParameters() {
        return List.of();
    }

    @Override
    public void execute(String todoId, Map<String, Object> macroParamValues) {
        log.info("什么也不做");
    }
}