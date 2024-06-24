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
package org.dblue.core.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;

/**
 * 处理 {@code ApplicationNameAware}
 *
 * <p>
 * 在使用时需要将其配置为 Spring 的 Bean，如下
 * </p>
 *
 * <blockquote><pre class="code">
 * &#064;Configuration
 * public class ApplicationConfiguration {
 *     &#064;Bean
 *     public ApplicationNameAwareProcessor applicationNameAwareProcessor(ApplicationContext applicationContext) {
 *         return new ApplicationNameAwareProcessor(applicationContext);
 *     }
 * }
 * </pre></blockquote>
 *
 * @author Wang Chengwei
 * @see ApplicationNameAware
 * @since 1.0.0
 */
public class ApplicationNameAwareProcessor implements BeanPostProcessor {

    private String applicationName;

    public ApplicationNameAwareProcessor(ApplicationContext applicationContext) {
        this.applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");
        if (this.applicationName == null) {
            this.applicationName = applicationContext.getApplicationName();
        }
    }

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (bean instanceof ApplicationNameAware applicationNameAware) {
            applicationNameAware.setApplicationName(this.applicationName);
        }
        return bean;
    }
}