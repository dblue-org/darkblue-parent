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

import org.aopalliance.aop.Advice;
import org.dblue.application.module.logs.adapter.aspect.ServiceOperationAdvice;
import org.dblue.application.module.logs.adapter.aspect.ignore.AnnotatedIgnoreStrategy;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 操作AOP监控
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@SuppressWarnings("java:S1118")
@Configuration
public class OperationLogAdviceConfiguration {

    /**
     * 此项配置会导致项目在启动时报一个警告：Bean 'serviceOperationAdvisor' of type [org.springframework.aop.support.DefaultPointcutAdvisor] is not eligible for getting processed
     * by all BeanPostProcessors... 此警告不用处理。具体见：
     * <a href="https://docs.spring.io/spring-framework/reference/core/beans/factory-extension.html#beans-factory-extension-bpp">BeanPostProcessor instances
     * and AOP auto-proxying</a>
     */
    @Bean
    public static Advisor serviceOperationAdvisor() {
        var pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(org.dblue.core.aspect.ServiceOperation)");
        return new DefaultPointcutAdvisor(pointcut, serviceOperationAdvice());
    }

    @Bean
    public static Advice serviceOperationAdvice() {
        ServiceOperationAdvice serviceOperationAdvice = new ServiceOperationAdvice();
        serviceOperationAdvice.setIgnoreStrategy(new AnnotatedIgnoreStrategy());
        return serviceOperationAdvice;
    }
}