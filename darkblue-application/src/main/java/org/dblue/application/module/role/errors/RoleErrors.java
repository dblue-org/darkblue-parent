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
package org.dblue.application.module.role.errors;

import lombok.Getter;
import org.dblue.common.error.ErrorInfo;

/**
 * 角色错误
 *
 * @author xie jin
 * @since 1.0.0  2024-06-14 15:29:11
 */
@Getter
public enum RoleErrors implements ErrorInfo {


    /**
     *
     */
    ROLE_EXITS("ROLE_0001", "角色已存在"),
    ROLE_IS_NOT_FOUND("ROLE_0002", "角色不存在"),
    ROLE_USER_IS_EXITS("ROLE_0003", "用户角色存在关联关系"),


    ;
    private final String errorCode;
    private final String errorMessage;

    RoleErrors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}