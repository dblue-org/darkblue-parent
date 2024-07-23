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
package org.dblue.application.module.resource.errors;

import lombok.Getter;
import org.dblue.common.error.ErrorInfo;

/**
 * 资源错误
 *
 * @author xie jin
 * @since 1.0.0  2024-06-14 15:29:11
 */
@Getter
public enum ResourceErrors implements ErrorInfo {


    /**
     *
     */
    RESOURCE_EXITS("RESOURCE_0001", "资源地址已存在"),
    RESOURCE_IS_NOT_FOUND("RESOURCE_0002", "资源信息不存在"),
    RESOURCE_METHOD_IS_NOT_SUPPORT("RESOURCE_0003", "资源请求类型不支持"),
    RESOURCE_MUST_PLATFORM("RESOURCE_0004", "资源必有平台类型"),
    THE_RESOURCE_CONTAINS_AN_UNMODIFIED_INVALID_RESOURCE("RESOURCE_0005", "资源包含未修改的非法资源"),
    ;
    private final String errorCode;
    private final String errorMessage;

    ResourceErrors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}