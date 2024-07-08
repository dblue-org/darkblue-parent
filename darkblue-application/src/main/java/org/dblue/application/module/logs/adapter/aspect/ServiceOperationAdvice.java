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
package org.dblue.application.module.logs.adapter.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.dblue.application.module.logs.domain.builder.OperationLogBuilder;
import org.dblue.application.module.logs.domain.service.OperationLogDomainService;
import org.dblue.application.module.logs.infrastructure.entity.OperationLog;
import org.dblue.core.aspect.ServiceOperation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public class ServiceOperationAdvice implements AfterReturningAdvice, ThrowsAdvice, MethodBeforeAdvice {

    private final OperationLogDomainService operationLogDomainService;

    private final ObjectMapper objectMapper;

    /**
     * 用于记录耗时
     */
    private final ThreadLocal<Long> timeConsumingThreadLocal = new ThreadLocal<>();

    public ServiceOperationAdvice(OperationLogDomainService operationLogDomainService, ObjectMapper objectMapper) {
        this.operationLogDomainService = operationLogDomainService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // record start time
        this.timeConsumingThreadLocal.remove();
        this.timeConsumingThreadLocal.set(System.currentTimeMillis());
    }

    @Override
    public void afterReturning(Object returnValue, @NonNull Method method, @NonNull Object[] args, Object target) throws Throwable {
        ServiceOperation serviceOperation = AnnotationUtils.getAnnotation(method, ServiceOperation.class);
        if (serviceOperation == null) {
            return;
        }

        OperationLog operationLog = this.createOperationLog(serviceOperation, method, args, target)
                .isError(false)
                .result(this.buildResult(returnValue))
                .build();
        this.operationLogDomainService.save(operationLog);

    }

    private OperationLogBuilder createOperationLog(ServiceOperation serviceOperation, Method method, Object[] args, Object target) {
        long start = this.timeConsumingThreadLocal.get();
        int timeConsuming = (int) (System.currentTimeMillis() - start);

        String operationName = serviceOperation.value();
        return OperationLogBuilder.newInstance()
                .operationName(operationName)
                .serviceClass(target != null ? target.getClass().getName() : null)
                .serviceMethod(method.getName())
                .methodParams(this.buildMethodParams(method, args))
                .timeConsuming(timeConsuming);
    }

    private String buildResult(Object result) {
        if (result == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    private String buildMethodParams(Method method, Object[] args) {
        Map<String, Object> params = new LinkedHashMap<>();
        for (int i = 0; i < method.getParameters().length; i++) {
            Parameter parameter = method.getParameters()[i];
            Object value = args[i];
            if (value instanceof ServletRequest || value instanceof ServletResponse || value instanceof MultipartFile) {
                params.put(parameter.getName(), value.getClass().getName());
            } else {
                params.put(parameter.getName(), args[i]);
            }
        }
        try {
            return objectMapper.writeValueAsString(params);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        // Do something with all arguments
        ServiceOperation serviceOperation = AnnotationUtils.getAnnotation(method, ServiceOperation.class);
        if (serviceOperation == null) {
            return;
        }

        OperationLog operationLog = this.createOperationLog(serviceOperation, method, args, target)
                .isError(true)
                .errorDetails(ex)
                .build();
        this.operationLogDomainService.save(operationLog);

    }
}