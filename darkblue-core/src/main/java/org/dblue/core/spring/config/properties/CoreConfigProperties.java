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
package org.dblue.core.spring.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration for redis, jackson etc.
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.core")
@Data
public class CoreConfigProperties {

    /**
     * 是否启用乐观锁
     */
    private boolean enableOptimisticLocker = true;

    /**
     * 是否自动注入插入和更新信息
     */
    private boolean autoSetCreateUpdateInfo = true;

    /**
     * 使用默认的异常处理器
     */
    private boolean useDefaultExceptionHandler = true;

    /**
     * 是否启用web配置，此项功能用户在单元测试时关闭Web配置
     */
    private boolean enableWebConfig = true;

    private boolean showMyBatisPlusLogging = true;

    /**
     *
     */
    private String internalErrorPrefix = "";

    /**
     * 生产环境的Profile名称
     */
    private String productionProfile = "prod";
}