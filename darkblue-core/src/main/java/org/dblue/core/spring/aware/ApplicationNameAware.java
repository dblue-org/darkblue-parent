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

import org.springframework.beans.factory.Aware;

/**
 * 应用名称注入，Spring Boot项目的中的 {@code spring.application.name}
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface ApplicationNameAware extends Aware {

    /**
     * Set application name
     *
     * @param applicationName application Name
     */
    void setApplicationName(String applicationName);

}