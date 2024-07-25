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
package org.dblue.application.module.logs.adapter.aspect.ignore;

import org.dblue.core.aspect.IgnoreOperation;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * 带有 {@link IgnoreOperation} 注解的方法将被忽略，用于精确控制被忽略的方法。
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class AnnotatedIgnoreStrategy implements IgnoreStrategy {

    @Override
    public boolean isIgnore(Method method, Object target) {
        IgnoreOperation ignoreOperation = AnnotationUtils.getAnnotation(method, IgnoreOperation.class);
        return ignoreOperation != null;
    }
}