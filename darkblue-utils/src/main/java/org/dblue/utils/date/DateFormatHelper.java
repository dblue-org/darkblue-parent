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
package org.dblue.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式化工具
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public enum DateFormatHelper {
    DATE("yyyy-MM-dd"),
    SHORT_DATE("yyyyMMdd"),
    DATE_HM("yyyy-MM-dd HH:mm"),
    DATE_TIME("yyyy-MM-dd HH:mm:ss"),
    SLASH_DELIMITER_DATE("yyyy/MM/dd"),
    SLASH_DELIMITER_DATE_HM("yyyy/MM/dd HH:mm:ss"),
    SLASH_DELIMITER_DATE_TIME("yyyy/MM/dd HH:mm:ss"),
    TIME("HH:mm:ss"),

    ;
    private final String pattern;

    DateFormatHelper(String pattern) {
        this.pattern = pattern;
    }

    public String format(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(this.pattern));
    }

    public String format(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(this.pattern));
    }

    public String format(LocalTime localTime) {
        return localTime.format(DateTimeFormatter.ofPattern(this.pattern));
    }
}