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
package org.dblue.core.spring;

import lombok.RequiredArgsConstructor;
import org.dblue.core.spring.config.properties.CoreConfigProperties;
import org.springframework.core.env.Environment;

import java.util.Set;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class ProductionPredicate {

    private final CoreConfigProperties configProperties;
    private final Environment environment;

    public boolean isProduction() {
        Set<String> profiles = Set.of(environment.getActiveProfiles());
        return profiles.contains(configProperties.getProductionProfile());
    }
}