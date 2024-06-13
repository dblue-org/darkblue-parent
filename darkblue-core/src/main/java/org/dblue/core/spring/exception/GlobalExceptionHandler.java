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

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dblue.common.error.CommonError;
import org.dblue.common.exception.ServiceException;
import org.dblue.core.spring.config.properties.CoreConfigProperties;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用异常处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@ConditionalOnProperty(prefix = "app.core", name = "use-default-exception-handler", matchIfMissing = true, havingValue = "true")
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final CoreConfigProperties coreConfigProperties;

    public GlobalExceptionHandler(CoreConfigProperties coreConfigProperties) {
        this.coreConfigProperties = coreConfigProperties;
        log.info("已初始化自定义异常处理。");
    }

    /**
     * 数据异常返回
     *
     * @param serviceException 业务异常
     */
    @ExceptionHandler(value = ServiceException.class)
    public ResponseBean<String> serviceException(ServiceException serviceException) {
        ResponseBean<String> result = new ResponseBean<>();
        result.setErrorCode(serviceException.getErrorCode());
        result.setMessage(serviceException.getMessage());
        log.error(serviceException.getErrorCode() + "-" + serviceException.getMessage(), serviceException);
        return result;
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseBean<String> httpClientErrorException(HttpClientErrorException httpClientErrorException) {
        ResponseBean<String> result = new ResponseBean<>();
        result.setSuccess(false);
        result.setErrorCode(httpClientErrorException.getStatusCode().toString());
        result.setMessage(httpClientErrorException.getMessage());
        log.error(httpClientErrorException.getMessage(), httpClientErrorException);
        return result;
    }

    /**
     * 请求方式错误的异常
     *
     * @param ex 异常信息
     * @return 错误应答数据
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseBean<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ResponseBean<String> result = new ResponseBean<>();
        result.setSuccess(false);
        result.setErrorCode(CommonError.INTERNAL_SERVER_ERROR.getErrorCode());
        String message = "Http 请求方法错误，请使用: [" + StringUtils.join(ex.getSupportedHttpMethods(), ",")
                + "] 请求，您当使用的请求方法是：[" + ex.getMethod() + "]";
        result.setMessage(message);
        log.error(ex.getMessage(), ex);
        return result;
    }

    /**
     * 参数异常校验返回
     *
     * @param bindException 参数绑定异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBean<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException bindException) {
        ResponseBean<Map<String, String>> result = ResponseBean.failure(CommonError.ARGUMENTS_ERROR);
        Map<String, String> errorMap = new HashMap<>(
                bindException.getBindingResult().getFieldErrors().size());

        for (FieldError error : bindException.getBindingResult().getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        result.setMessage(StringUtils.join(errorMap.values(), ","));
        result.setErrorDetails(errorMap);
        log.error(bindException.getMessage(), bindException);
        return result;
    }

    /**
     * 参数异常校验返回
     *
     * @param bindException 参数绑定异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseBean<Void> bindException(BindException bindException) {
        ResponseBean<Void> result = ResponseBean.failure(CommonError.ARGUMENTS_ERROR);
        Map<String, String> errorMap = new HashMap<>(
                bindException.getBindingResult().getFieldErrors().size());

        for (FieldError error : bindException.getBindingResult().getFieldErrors()) {
            if (error.contains(ConstraintViolation.class)) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            } else if (error.contains(TypeMismatchException.class)) {
                errorMap.put(error.getField(), "参数[" + error.getField() + "]不支持传入的数据'" + error.getRejectedValue() + "'");
            } else {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
        }
        result.setMessage(StringUtils.join(errorMap.values(), ","));
        result.setErrorDetails(errorMap);
        log.error(bindException.getMessage(), bindException);
        return result;
    }

    /**
     * 参数缺失异常
     *
     * @param exception 异常信息
     * @return 应答数据
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseBean<Void> missingServletRequestParameterException(MissingServletRequestParameterException exception) {
        ResponseBean<Void> result = ResponseBean.failure(CommonError.ARGUMENTS_ERROR);
        result.setMessage("缺少参数：" + exception.getParameterName());
        return result;
    }

    /**
     * 文件上传异常
     *
     * @param exception 文件上传大小限制异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseBean<String> uploadException(MaxUploadSizeExceededException exception) {
        ResponseBean<String> result = ResponseBean.failure(CommonError.SERVICE_ERROR);
        result.setMessage(CommonErrorMessages.FILE_LIMIT_ERROR_MSG);
        log.error(exception.getMessage(), exception);
        return result;
    }


    /**
     * 所有异常拦截
     *
     * @param exception 异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseBean<String> otherException(Exception exception) {
        ResponseBean<String> result = ResponseBean.failure(CommonError.INTERNAL_SERVER_ERROR);
        if (this.coreConfigProperties.getInternalErrorPrefix() != null) {
            result.setErrorCode(this.coreConfigProperties.getInternalErrorPrefix() + "_500");
        }
        result.setMessage(CommonErrorMessages.SYSTEM_ERROR_MSG);
        log.error(exception.getMessage(), exception);
        return result;
    }
}