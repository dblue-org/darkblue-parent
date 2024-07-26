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
package org.dblue.application.module.user.errors;

import lombok.Getter;
import org.dblue.common.error.ErrorInfo;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Getter
public enum UserErrors implements ErrorInfo {


    /**
     *
     */
    USER_NOT_FOUND("USER_0001", "用户不存在"),
    USERNAME_EXISTS("USER_0002", "用户名已存在"),
    USER_ID_IS_NOT_BLANK("USER_0003", "用户ID不能为空"),
    NOT_ALLOW_CHANGE_PASSWORD("USER_0004", "此用户的密码不允许修改"),
    NOT_ALLOW_RESET_ADMIN_PASSWORD("USER_0005", "不允许重置超级管理员密码"),
    PASSWORD_ERROR("USER_0006", "密码错误"),
    ;
    private final String errorCode;
    private final String errorMessage;

    UserErrors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}