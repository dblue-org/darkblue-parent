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
package org.dblue.common.error;

/**
 * 通用的错误信息，提供部分常见错误信息，大部分错误信息的编码与Http状态码一致。
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2024/6/13 10:53]
 */
public enum CommonError implements ErrorInfo {

    /**
     * 请求错误
     */
    BAD_REQUEST("400", "请求错误，请检查请求方式或请求参数"),

    /**
     * 未登录
     */
    UNAUTHORIZED("401", "用户未登录，请登录后重试"),

    /**
     * 权限受限
     */
    ACCESS_DENIED("403", "您无权限访问此资源"),

    /**
     * 登录超时
     */
    SESSION_TIMEOUT("401", "登录超时，请重新登录"),

    /**
     * 系统异常
     */
    INTERNAL_SERVER_ERROR("500", "系统发生异常，请稍后重试"),

    /**
     * 通用异常信息，一般情况下，需要提供自己的异常消息（errorMessage）。程序中应仅应用此错误编码和状态码
     */
    SERVICE_ERROR("500", "系统发生异常，请稍后重试"),

    /**
     * 参数发生错误时抛出此异常
     */
    ARGUMENTS_ERROR("ARG_ERROR", "参数异常"),

    ;
    private final String errorCode;


    private final String errorMessage;


    CommonError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }


}
