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
package org.dblue.application.module.setting.domain.converter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 默认的类型转换器
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Order
@Component
public class DefaultPropertyValueConverter implements PropertyValueConverter<String> {

    @Override
    public boolean isSupported(int propertyType) {
        return true;
    }

    @Override
    public Optional<String> convert(String value) {
        return Optional.of(value);
    }

    @Override
    public String getValueName(String value) {
        return value;
    }
}