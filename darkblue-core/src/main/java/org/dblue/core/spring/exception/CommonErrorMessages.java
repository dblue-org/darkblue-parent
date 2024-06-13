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
package org.dblue.core.spring.exception;

/**
 * 通用错误信息常量
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class CommonErrorMessages {

    /**
     * 文件上传异常消息
     */
    public static final String FILE_LIMIT_ERROR_MSG = "上传文件大小超出限制!";
    /**
     * 系统异常
     */
    public static final String SYSTEM_ERROR_MSG = "系统异常";

    private CommonErrorMessages() {
    }
}