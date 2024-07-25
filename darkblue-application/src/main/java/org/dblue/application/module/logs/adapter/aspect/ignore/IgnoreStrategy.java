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

import org.dblue.application.module.logs.adapter.aspect.ServiceOperationAdvice;
import org.dblue.core.aspect.ServiceOperation;

import java.lang.reflect.Method;

/**
 * 忽略操作的策略，{@link ServiceOperationAdvice} 作为一个通用的切面，关注点在于 pointcut 而不仅仅是 {@link ServiceOperation} 注解。 如果将 pointcut
 * 定位到了应用服务层，或者控制层，那么拦截的方法将会大量增长，并且不许呀拦截的查询方法也会被拦截，因此需要一些策略来处理这种情况。 忽略操作策略就是为了解决这个问题，可以通过自定义忽略策略来是切面忽略掉某些策略。
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface IgnoreStrategy {

    /**
     * 判读此操作是否需要被忽略
     *
     * @param method 方法
     * @param target 操作类的实例
     * @return true-忽略；false-不忽略
     */
    boolean isIgnore(Method method, Object target);
}