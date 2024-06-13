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
package org.dblue.core.spring.converter;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

/**
 * 字符串转日期时间类型
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    private final List<DateTimeFormatter> formatters = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.CHINA),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withLocale(Locale.CHINA)
    );


    @Override
    public LocalDateTime convert(@NonNull String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(source, formatter);
            } catch (DateTimeParseException ex) {
                log.warn(ex.getMessage());
            }
        }
        throw new IllegalArgumentException("Invalid java.time.LocalDateTime value '" + source + "'");
    }
}