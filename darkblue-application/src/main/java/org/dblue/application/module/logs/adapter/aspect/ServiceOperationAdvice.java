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
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dblue.application.module.logs.adapter.aspect.ignore.IgnoreStrategy;
import org.dblue.application.module.logs.adapter.aspect.ignore.NoopIgnoreStrategy;
import org.dblue.application.module.logs.adapter.aspect.name.DefaultOperationNameGetter;
import org.dblue.application.module.logs.adapter.aspect.name.OperationNameGetter;
import org.dblue.application.module.logs.domain.service.OperationLogDomainService;
import org.dblue.application.module.logs.infrastructure.entity.OperationLog;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.Method;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public class ServiceOperationAdvice extends AbstractOperationRecorder implements AfterReturningAdvice, ThrowsAdvice, MethodBeforeAdvice, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private TransactionTemplate transactionTemplate;

    @Setter
    private IgnoreStrategy ignoreStrategy = new NoopIgnoreStrategy();
    @Setter
    private OperationNameGetter operationNameGetter = new DefaultOperationNameGetter();

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void before(@NonNull Method method, @NonNull Object[] args, Object target) {
        if (this.ignoreStrategy.isIgnore(method, target)) {
            return;
        }
        this.start(method);
    }

    @Override
    public void afterReturning(Object returnValue, @NonNull Method method, @NonNull Object[] args, Object target) {
        if (this.ignoreStrategy.isIgnore(method, target)) {
            return;
        }

        boolean isMe = this.getContext().isMe(method);

        // 嵌套处理
        if (isMe) {
            String operationName = this.operationNameGetter.getName(method, target);

            OperationLog operationLog = this.createBuilder(operationName, method, args)
                    .isError(false)
                    .result(this.buildResult(returnValue))
                    .build();
            this.transactionTemplate.executeWithoutResult(status -> this.getOperationLogDomainService().save(operationLog));
            this.clear();
        }
    }

    protected OperationLogDomainService getOperationLogDomainService() {
        return this.applicationContext.getBean(OperationLogDomainService.class);
    }

    /**
     * 此方法会被 IDEA 提醒 unused 但是实际是会被调用的。Spring 的 {@link ThrowsAdvice} 接口没有任何方法，Spring在处理此接口时是通过一些规范来识别的，具体见文档：
     * <a href="https://docs.spring.io/spring-framework/reference/core/aop-api/advice.html#aop-api-advice-throws">...</a>
     */
    @SuppressWarnings("unused")
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        if (this.ignoreStrategy.isIgnore(method, target)) {
            return;
        }

        boolean isMe = this.getContext().isMe(method);

        if (isMe) {
            String operationName = this.operationNameGetter.getName(method, target);

            OperationLog operationLog = this.createBuilder(operationName, method, args)
                    .isError(true)
                    .errorDetails(ex)
                    .build();
            this.getTransactionTemplate().executeWithoutResult(status -> this.getOperationLogDomainService().save(operationLog));
        }

    }

    public synchronized TransactionTemplate getTransactionTemplate() {
        if (this.transactionTemplate == null) {
            PlatformTransactionManager transactionManager = this.applicationContext.getBean(PlatformTransactionManager.class);
            this.transactionTemplate = new TransactionTemplate(
                    transactionManager, new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRES_NEW)
            );
        }
        return this.transactionTemplate;
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        return this.applicationContext.getBean(ObjectMapper.class);
    }

}