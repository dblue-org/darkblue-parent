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
import lombok.extern.slf4j.Slf4j;
import org.dblue.application.module.logs.domain.builder.OperationLogBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 抽象操作记录
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractOperationRecorder {


    /**
     * 用于记录耗时
     */
    private final ThreadLocal<Long> timeConsumingThreadLocal = new ThreadLocal<>();

    private final ThreadLocal<Context> contextThreadLocal = new ThreadLocal<>();


    /**
     * 开始记录
     */
    protected void start(Method method) {
        if (contextThreadLocal.get() != null) {
            return;
        }
        this.timeConsumingThreadLocal.set(System.currentTimeMillis());
        this.contextThreadLocal.set(new Context(method));
    }

    protected void clear() {
        this.timeConsumingThreadLocal.remove();
        this.contextThreadLocal.remove();
    }

    protected Context getContext() {
        return this.contextThreadLocal.get();
    }

    protected OperationLogBuilder createBuilder(String operationName, Method method, Object[] args) {
        int timeConsuming = (int) this.stop();

        return OperationLogBuilder.newInstance()
                .operationName(operationName)
                .serviceClass(method.getDeclaringClass().getName())
                .serviceMethod(method.getName())
                .methodParams(this.buildMethodParams(method, args))
                .timeConsuming(timeConsuming);
    }

    /**
     * 结束记录并返回耗时
     */
    protected long stop() {
        long start = this.timeConsumingThreadLocal.get();
        return System.currentTimeMillis() - start;
    }

    protected String buildMethodParams(Method method, Object[] args) {
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
            return this.getObjectMapper().writeValueAsString(params);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    protected abstract ObjectMapper getObjectMapper();

    protected String buildResult(Object result) {
        if (result == null) {
            return null;
        }
        try {
            return this.getObjectMapper().writeValueAsString(result);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    protected static class Context {
        private final Method method;

        protected Context(Method method) {
            this.method = method;
        }

        public boolean isMe(Method method) {
            return this.method.equals(method);
        }
    }
}