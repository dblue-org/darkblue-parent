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
package org.dblue.core.web.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dblue.common.error.ErrorInfo;

/**
 * 基础应答信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(name = "应答信息")
@NoArgsConstructor
@Data
public class BaseResponseBean<T> {

    /**
     * 业务处理是否成功
     */
    @Schema(title = "业务处理是否成功", description = "业务处理是否成功", example = "true")
    private boolean success;

    /**
     * 错误编码
     */
    @Schema(title = "错误编码", description = "错误编码", example = "ERR_1001")
    private String errorCode;

    /**
     * 消息（成功消息或失败消息）
     */
    @Schema(title = "消息", description = "消息", example = "Token失效，请重新登录")
    private String message;

    /**
     * 扩展数据
     */
    @Schema(title = "扩展数据", description = "扩展数据")
    private T extension;

    /**
     * 错误详情
     */
    @Schema(title = "错误详情", description = "错误详情")
    private Object errorDetails;


    protected BaseResponseBean(boolean success) {
        this.success = success;
    }

    protected BaseResponseBean(ErrorInfo errorInfo) {
        this(errorInfo.getErrorCode(), errorInfo.getErrorMessage());
    }

    protected BaseResponseBean(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.success = false;
    }
}