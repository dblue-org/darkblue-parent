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
package org.dblue.security.exception;

import lombok.extern.slf4j.Slf4j;
import org.dblue.common.error.CommonError;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 权限不足时抛出的异常的处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class AccessDeniedExceptionHandler {

    /**
     * 数据异常返回
     *
     * @param serviceException 业务异常
     * @return 应答信息
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseBean<Void> serviceException(AccessDeniedException serviceException) {
        log.error(serviceException.getMessage(), serviceException);
        return ResponseBean.failure(CommonError.ACCESS_DENIED);
    }
}