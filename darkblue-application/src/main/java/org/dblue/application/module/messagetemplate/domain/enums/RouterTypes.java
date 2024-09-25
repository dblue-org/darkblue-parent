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
package org.dblue.application.module.messagetemplate.domain.enums;

import lombok.Getter;
import org.dblue.application.commons.EnumValue;
import org.dblue.common.enums.EnumType;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由类型(1-PC;2-Android;3-IOS;4-小程序;5-H5)
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Getter
public enum RouterTypes implements EnumType {
    PC(1, "PC"),
    ANDROID(2, "Android"),
    IOS(3, "IOS"),
    MINI_PROGRAM(4, "小程序"),
    H5(5, "H5"),

    ;
    private final int value;
    private final String name;

    RouterTypes(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static List<EnumValue> all() {
        List<EnumValue> values = new ArrayList<>();
        for (RouterTypes routerType : RouterTypes.values()) {
            values.add(new EnumValue(routerType.getValue(), routerType.getName()));
        }
        return values;
    }
}
