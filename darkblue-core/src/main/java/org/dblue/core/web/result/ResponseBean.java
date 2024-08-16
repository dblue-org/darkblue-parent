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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dblue.common.error.ErrorInfo;
import org.dblue.core.web.result.strict.StrictResponseBean;

/**
 * 非严格模式下的应答数据，非严格模式仅扩展信息不必须要求泛型类型。 真实数据同样需要泛型类型。
 *
 * <p>如果需要在Swagger API 文档中显示扩展数据的字段信息，请使用 {@link StrictResponseBean}</p>
 *
 * @author Wang Chengwei
 * @since 1.0.0
 * @param <T> 应答数据类型
 */
@Schema(description = "应答数据")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseBean<T> extends BaseResponseBean<Object> {

    /**
     * 应答数据
     */
    @Schema(description = "应答数据")
    private T data;


    public ResponseBean(T data) {
        super(true);
        this.data = data;
    }

    public ResponseBean(boolean success) {
        super(success);
    }

    public ResponseBean(boolean success, T data) {
        super(success);
        this.data = data;
    }

    public ResponseBean(String errorCode, String message) {
        super(errorCode, message);
    }

    public ResponseBean(String errorCode, String message, T data) {
        super(errorCode, message);
        this.data = data;
    }

    public ResponseBean(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public ResponseBean(ErrorInfo errorInfo, T data) {
        super(errorInfo);
        this.data = data;
    }

    public static <T> ResponseBean<T> success() {
        return new ResponseBean<>(true);
    }

    public static <T> ResponseBean<T> success(T data) {
        return new ResponseBean<>(data);
    }

    public static <T> ResponseBean<T> failure(String errorCode, String message) {
        ResponseBean<T> responseBean = failure(message);
        responseBean.setErrorCode(errorCode);
        return responseBean;
    }

    public static <T> ResponseBean<T> failure(String message) {
        ResponseBean<T> responseBean = failure();
        responseBean.setMessage(message);
        return responseBean;
    }

    public static <T> ResponseBean<T> failure() {
        return new ResponseBean<>(false);
    }

    public static <T> ResponseBean<T> failure(ErrorInfo errorInfo) {
        return new ResponseBean<>(errorInfo);
    }
}