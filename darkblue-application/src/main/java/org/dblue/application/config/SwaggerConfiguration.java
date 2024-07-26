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

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 文档描述配置
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI().info(
                new Info().title("深蓝管理系统")
                        .description("Dark Blue（深蓝）是一个开源的脚手架项目。提供了一系列的基础功能，包括用户管理、权限管理、日志查看、字典管理、系统参数管理等功能，帮助开发者快速构建应用。")
                        .version("V1.0.0")
        );
    }
}