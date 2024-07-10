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
package org.dblue.application.module.setting.domain.enums;

import lombok.Getter;
import org.dblue.common.enums.EnumType;

import java.util.Objects;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Getter
public enum PropertyType implements EnumType {
    STRING(1, "字符串"),
    NUMBER(2, "数值"),
    DATE(3, "日期"),
    DATETIME(4, "日期时间"),
    BOOL(5, "布尔"),
    COLOUR(6, "颜色"),
    LIST(7, "列表"),
    ENUMS(8, "枚举"),
    USER(9, "用户"),
    ;
    private final int value;
    private final String name;

    PropertyType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getName(Integer value) {
        for (PropertyType propertyType : PropertyType.values()) {
            if (Objects.equals(propertyType.getValue(), value)) {
                return propertyType.getName();
            }
        }
        return "";
    }
}
