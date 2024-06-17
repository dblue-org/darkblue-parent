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

package org.dblue.application.commons.enums;

import lombok.Getter;
import org.dblue.common.enums.EnumState;

/**
 * 菜单类型(1-目录;2-菜单)
 *
 * @author xie jin
 * @since 1.0.0  2024/6/17 上午10:08
 */
@Getter
public enum MenuTypeEnum implements EnumState {

    /**
     *
     */
    CATALOGUE(1,"CATALOGUE"),
    MENU(2,"MENU"),
            ;

    private final int value;
    private final String name;


    MenuTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public boolean equalsTo(Integer type) {
        return type != null && this.value == type;
    }

    public String getTypeName(Integer state) {
        for (MenuTypeEnum type : MenuTypeEnum.values()) {
            if (type.equalsTo(state)) {
                return type.getName();
            }
        }
        return null;
    }
}
