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

import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

/**
 * 主要用于控制控制层的方法，忽略 GET 请求
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class GetMappingIgnoreStrategy implements IgnoreStrategy {

    @Override
    public boolean isIgnore(Method method, Object target) {
        MergedAnnotation<RequestMapping> mergedAnnotation = MergedAnnotations
                .from(method, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(RequestMapping.class);
        if (mergedAnnotation.isPresent()) {
            RequestMethod[] methods = mergedAnnotation.synthesize().method();
            for (RequestMethod requestMethod : methods) {
                if (requestMethod == RequestMethod.GET) {
                    return true;
                }
            }
        }
        return false;
    }
}