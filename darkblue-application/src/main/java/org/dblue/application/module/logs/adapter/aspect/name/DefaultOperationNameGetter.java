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
package org.dblue.application.module.logs.adapter.aspect.name;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class DefaultOperationNameGetter implements OperationNameGetter {

    public final List<OperationNameGetter> getters = new ArrayList<>();

    public DefaultOperationNameGetter(OperationNameGetter... getters) {
        this.getters.addAll(List.of(getters));
    }

    public DefaultOperationNameGetter() {
        getters.add(new AnnotatedOperationNameGetter());
        getters.add(new SwaggerOperationNameGetter());
    }

    @Override
    public String getName(Method method, Object target) {
        String name = null;

        for (OperationNameGetter getter : getters) {
            String resolvedName = getter.getName(method, target);
            if (resolvedName != null) {
                name = resolvedName;
            }
        }

        return name == null ? method.getName() : name;
    }
}