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
package org.dblue.core.spring.config;

import org.dblue.core.spring.config.properties.CoreConfigProperties;
import org.dblue.core.spring.converter.StringToLocalDateConverter;
import org.dblue.core.spring.converter.StringToLocalDateTimeConverter;
import org.dblue.core.spring.exception.CustomErrorAttributes;
import org.dblue.core.spring.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@ConditionalOnProperty(prefix = "app.core", name = "enable-web-config", matchIfMissing = true, havingValue = "true")
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateConverter());
        registry.addConverter(new StringToLocalDateTimeConverter());
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(CoreConfigProperties coreConfigProperties) {
        return new GlobalExceptionHandler(coreConfigProperties);
    }

    @ConditionalOnProperty(prefix = "app.core", name = "use-default-exception-handler", matchIfMissing = true, havingValue = "true")
    @Bean
    public CustomErrorAttributes errorAttributes() {
        return new CustomErrorAttributes();
    }
}