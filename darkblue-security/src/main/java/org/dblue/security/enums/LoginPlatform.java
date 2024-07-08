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
package org.dblue.security.enums;

import lombok.Getter;
import org.dblue.common.enums.EnumType;

import java.util.Objects;

/**
 * 登录平台
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Getter
public enum LoginPlatform implements EnumType {

    PC(1, "PC"),
    ANDROID(2, "Android"),
    IOS(3, "IOS"),
    MINI_PROGRAM(4, "小程序"),

    ;
    private final int value;
    private final String name;

    LoginPlatform(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getName(Integer value) {
        for (LoginPlatform loginPlatform : LoginPlatform.values()) {
            if (Objects.equals(loginPlatform.getValue(), value)) {
                return loginPlatform.getName();
            }
        }
        return null;
    }
}
