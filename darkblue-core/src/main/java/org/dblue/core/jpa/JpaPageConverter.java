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
package org.dblue.core.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.function.Function;

/**
 * JPA page 类型转换
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class JpaPageConverter {

    private JpaPageConverter() {
    }

    public static <T, V> Page<V> convert(Page<T> page, Function<T, V> converter) {
        List<V> voList = page.get().map(converter).toList();
        return new PageImpl<>(voList, page.getPageable(), page.getTotalElements());
    }
}