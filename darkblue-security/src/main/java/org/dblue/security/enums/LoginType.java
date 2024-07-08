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
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Getter
public enum LoginType implements EnumType {
    PASSWORD(1, "密码登录"),
    WECHAT(2, "微信登录"),
    ALIPAY(3, "支付宝登录"),
    OTHER(9, "其他"),

    ;
    private final int value;
    private final String name;

    LoginType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getName(Integer value) {
        for (LoginType loginType : LoginType.values()) {
            if (Objects.equals(loginType.getValue(), value)) {
                return loginType.getName();
            }
        }
        return null;
    }
}
