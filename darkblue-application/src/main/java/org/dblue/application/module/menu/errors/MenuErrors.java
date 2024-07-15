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
package org.dblue.application.module.menu.errors;

import lombok.Getter;
import org.dblue.common.error.ErrorInfo;

/**
 * 菜单错误
 *
 * @author xie jin
 * @since 1.0.0  2024-06-14 15:29:11
 */
@Getter
public enum MenuErrors implements ErrorInfo {


    /**
     *
     */
    MENU_EXITS("MENU_0001", "菜单名称已存在"),
    MENU_IS_NOT_FOUND("MENU_0002", "菜单信息不存在"),
    MENU_ROLE_EXITS("MENU_0003", "角色和菜单有关联关系"),
    NOT_ALLOWED_DELETE_HAS_SUB_MENUS( "MENU_0004", "此菜单下有子菜单，不允许删除"),
    NOT_ALLOWED_DELETE_HAS_PERMISSIONS("MENU_0005", "此菜单下有权限配置，不允许删除"),
    NOT_ALLOWED_DELETE_HAS_RESOURCES("MENU_0006", "此菜单下有资源配置，不允许删除"),
    MENU_URL_NOT_BLANK("MENU_0007", "菜单地址不能为空"),
    PARENT_MENU_NOT_EXIST("MENU_0008", "父菜单不存在"),
    NOT_ALLOW_TO_ADD_SUB_MENU("MENU_0009", "菜单项下不允许添加菜单项"),
    PLATFORM_MUST_SAME("MENU_0010", "子菜单和父菜单的适用平台必须保持一致"),
    NOT_CHANGE_HAS_SUB_MENUS("MENU_0011", "此菜单下有子菜单，不允许修改为菜单类型"),
    MENU_ID_IS_NOT_BLANK("MENU_0002", "菜单ID不能为空"),

    ;
    private final String errorCode;
    private final String errorMessage;

    MenuErrors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}