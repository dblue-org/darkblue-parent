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
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

/**
 * 日期工具类
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public final class LocalDateUtils {

    private LocalDateUtils() {
    }


    /**
     * 获取当月最后一天
     *
     * @param localDate 入参时间
     * @return 当月最后一天
     */
    public static LocalDate lastDayOfMonth(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }


    /**
     * 获取当月第一天
     *
     * @param localDate 入参时间
     * @return 当月第一天
     */
    public static LocalDate firstDayOfMonth(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 将日期类型转为日期时间类型 {@code LocalDateTime}
     *
     * @param localDate 日期
     * @param localTime 时间
     * @return 转换后的日期时间
     */
    public static LocalDateTime toLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * 将传入的日期转为日期时间类型，时间为当天的最大时间
     *
     * @param localDate 日期
     * @return 日期时间
     */
    public static LocalDateTime maxOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    /**
     * 将传入的日期转为日期时间类型，时间为当天的最小时间
     *
     * @param localDate 日期
     * @return 日期时间
     */
    public static LocalDateTime minOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }
}