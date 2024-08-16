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

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.dblue.core.spring.ProductionPredicate;
import org.dblue.core.spring.aware.ApplicationNameAwareProcessor;
import org.dblue.core.spring.config.properties.CoreConfigProperties;
import org.dblue.utils.date.DatePattern;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 应用配置信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties({CoreConfigProperties.class})
@Slf4j
public class ApplicationCoreAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.DATE)));
            jacksonObjectMapperBuilder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.DATE)));
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.DATE_TIME)));
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.DATE_TIME)));
        };
    }

    @Bean
    public static ApplicationNameAwareProcessor applicationNameAwareProcessor() {
        return new ApplicationNameAwareProcessor();
    }

    @Bean
    public ProductionPredicate productionPredicate(
            CoreConfigProperties coreConfigProperties,
            Environment environment) {
        return new ProductionPredicate(coreConfigProperties, environment);
    }
}