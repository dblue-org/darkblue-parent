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

package org.dblue.security.error;

import org.dblue.common.error.ErrorInfo;

/**
 * 安全相关异常信息
 *
 * @author Wang Chengwei
 * @since 1.0.0 2022/3/17 12:20
 */
public enum SecurityError implements ErrorInfo {

    /**
     * 认证失败
     */
    BAD_CREDENTIALS("GSE_002", "用户名/密码错误"),


    REFRESH_TOKEN_INVALID("GSE_003", "RefreshToken已过期"),
    REFRESH_TOKEN_IS_NULL("GSE_004", "RefreshToken不能为空"),
    LOGIN_FAIL("GSE_005", "登录失败"),
    NOT_ALLOWED_REPETITIVE_SUBMISSION("GSE_006", "数据已提交，请勿重复提交"),
    ;

    private final String errorCode;
    private final String errorMessage;

    SecurityError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

}