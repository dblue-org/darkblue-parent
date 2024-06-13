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
package org.dblue.core.web.result.strict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dblue.common.error.ErrorInfo;
import org.dblue.core.web.result.BaseResponseBean;

/**
 * 严格模式下，所有泛型类型必须明确指定。
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(title = "应答数据")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class StrictResponseBean<T, E> extends BaseResponseBean<E> {

    @Schema(title = "应答数据", description = "应答数据")
    private T data;

    public StrictResponseBean(boolean success) {
        super(success);
    }

    public StrictResponseBean(T data) {
        super(true);
        this.data = data;
    }


    public StrictResponseBean(boolean success, T data) {
        super(success);
        this.data = data;
    }

    public StrictResponseBean(String errorCode, String message) {
        super(errorCode, message);
    }

    public StrictResponseBean(String errorCode, String message, T data) {
        super(errorCode, message);
        this.data = data;
    }

    public StrictResponseBean(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public StrictResponseBean(ErrorInfo errorInfo, T data) {
        super(errorInfo);
        this.data = data;
    }

    public static <T, E> StrictResponseBean<T, E> success() {
        return new StrictResponseBean<>(true);
    }

    public static <T, E> StrictResponseBean<T, E> success(T data) {
        return new StrictResponseBean<>(data);
    }

    public static <T, E> StrictResponseBean<T, E> success(T data, E extension) {
        StrictResponseBean<T, E> responseBean = new StrictResponseBean<>(data);
        responseBean.setExtension(extension);
        return responseBean;
    }

    public static <T, E> StrictResponseBean<T, E> failure(ErrorInfo errorInfo) {
        return failure(errorInfo.getErrorCode(), errorInfo.getErrorMessage());
    }

    public static <T, E> StrictResponseBean<T, E> failure(String errorCode, String message) {
        StrictResponseBean<T, E> responseBean = failure(message);
        responseBean.setErrorCode(errorCode);
        return responseBean;
    }

    public static <T, E> StrictResponseBean<T, E> failure(String message) {
        StrictResponseBean<T, E> responseBean = failure();
        responseBean.setMessage(message);
        return responseBean;
    }

    public static <T, E> StrictResponseBean<T, E> failure() {
        return new StrictResponseBean<>(false);
    }

    public static <T, E> StrictResponseBean<T, E> failure(ErrorInfo errorInfo, E extension) {
        return failure(errorInfo.getErrorCode(), errorInfo.getErrorMessage(), extension);
    }

    public static <T, E> StrictResponseBean<T, E> failure(String errorCode, String message, E extension) {
        StrictResponseBean<T, E> responseBean = failure(message);
        responseBean.setErrorCode(errorCode);
        responseBean.setExtension(extension);
        return responseBean;
    }
}