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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 允许传入过个忽略策略，任何一个生效都将忽略此操作
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class DelegatingIgnoreStrategy implements IgnoreStrategy {

    private final List<IgnoreStrategy> ignoreStrategies = new ArrayList<>();

    public DelegatingIgnoreStrategy() {
    }

    public DelegatingIgnoreStrategy(List<IgnoreStrategy> ignoreStrategies) {
        this.ignoreStrategies.addAll(ignoreStrategies);
    }

    public void addStrategy(IgnoreStrategy ignoreStrategy) {
        this.ignoreStrategies.add(ignoreStrategy);
    }

    @Override
    public boolean isIgnore(Method method, Object target) {
        for (IgnoreStrategy ignoreStrategy : ignoreStrategies) {
            if (ignoreStrategy.isIgnore(method, target)) {
                return true;
            }
        }

        return false;
    }
}