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
package org.dblue.application.module.usergroup.errors;

import lombok.Getter;
import org.dblue.common.error.ErrorInfo;

/**
 * 用户组错误
 *
 * @author xie jin
 * @since 1.0.0  2024-06-14 15:29:11
 */
@Getter
public enum UserGroupErrors implements ErrorInfo {


    /**
     *
     */
    USER_GROUP_EXITS("USER_GROUP_0001", "用户组已存在"),
    USER_GROUP_IS_NOT_FOUND("USER_GROUP_0002", "用户组不存在"),
    USER_GROUP_ROLE_EXITS("USER_GROUP_0003", "用户组角色已存在"),
    USER_GROUP_USER_EXITS("USER_GROUP_0004", "用户组用户已存在"),


    ;
    private final String errorCode;
    private final String errorMessage;

    UserGroupErrors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}