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
package org.dblue.common.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取枚举名称，仅支持 {@link EnumState} 和 {@link EnumType} 类型的枚举。
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class EnumNameUtils {

    private static final Map<Class<?>, Map<Integer, String>> VALUES_MAP = new ConcurrentHashMap<>();

    private EnumNameUtils() {
    }

    /**
     * 获取名称
     *
     * @param enumClass 枚举类
     * @param value     枚举值
     * @return 枚举名称
     */
    public static String getName(Class<? extends Enum<?>> enumClass, Integer value) {
        Map<Integer, String> valueMap = VALUES_MAP.computeIfAbsent(enumClass, key -> {
            Map<Integer, String> map = new HashMap<>();
            for (Enum<?> anEnum : enumClass.getEnumConstants()) {
                if (anEnum instanceof EnumState enumState) {
                    map.put(enumState.getValue(), enumState.getName());
                }
            }
            return map;
        });
        return valueMap.get(value);
    }

}