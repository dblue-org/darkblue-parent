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
package org.dblue.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * 安全相关配置
 * @author Wang Chengwei
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.security")
@Data
public class SecurityProperties {
    /**
     * Access Token 生命周期
     */
    private Long accessTokenTtl = TimeUnit.HOURS.toMillis(8);

    /**
     * Refresh Token 生命周期
     */
    private Long refreshTokenTtl = TimeUnit.DAYS.toMillis(15);
}