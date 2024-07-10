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

import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.setting.domain.enums.PropertyType;
import org.dblue.utils.date.DatePattern;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * 布尔值处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Order(3)
@Component
public class LocalDateValueConverter implements PropertyValueConverter<LocalDate> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DatePattern.DATE);

    @Override
    public boolean isSupported(int propertyType) {
        return PropertyType.DATE.equalsTo(propertyType);
    }

    @Override
    public Optional<LocalDate> convert(String value) {
        if (StringUtils.isBlank(value)) {
            return Optional.empty();
        }
        return Optional.of(LocalDate.parse(value, FORMATTER));
    }

    @Override
    public String getValueName(String value) {
        return value;
    }
}