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
package org.dblue.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aopalliance.aop.Advice;
import org.dblue.application.module.logs.adapter.aspect.ServiceOperationAdvice;
import org.dblue.application.module.logs.adapter.aspect.ignore.AnnotatedIgnoreStrategy;
import org.dblue.application.module.logs.domain.service.OperationLogDomainService;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 操作AOP监控
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Configuration
public class OperationLogAdviceConfiguration {

    @Bean
    public Advisor serviceOperationAdvisor(Advice serviceOperationAdvice) {
        var pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(org.dblue.core.aspect.ServiceOperation)");
        return new DefaultPointcutAdvisor(pointcut, serviceOperationAdvice);
    }

    @Bean
    public Advice serviceOperationAdvice(
            OperationLogDomainService operationLogDomainService, ObjectMapper objectMapper,
            PlatformTransactionManager transactionManager) {

        ServiceOperationAdvice serviceOperationAdvice = new ServiceOperationAdvice(
                operationLogDomainService, objectMapper, transactionManager
        );
        serviceOperationAdvice.setIgnoreStrategy(new AnnotatedIgnoreStrategy());
        return serviceOperationAdvice;
    }
}