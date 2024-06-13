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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * 日期时间工具类
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public final class LocalDateTimeUtils {
    private LocalDateTimeUtils() {
    }


    /**
     * 获取当前时间（天）最小值
     *
     * @param localDateTime 入参时间
     * @return 当前时间天最小值
     */
    public static LocalDateTime minTimeOfDay(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.with(LocalTime.MIN);
    }


    /**
     * 获取当前时间（天）最大值
     *
     * @param localDateTime 入参时间
     * @return 当前时间天最大值
     */
    public static LocalDateTime maxTimeOfDay(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.with(LocalTime.MAX);
    }


    /**
     * 获取当月最后一天最大值
     *
     * @param localDateTime 入参时间
     * @return 当月最后一天最大值
     */
    public static LocalDateTime maxLastDayOfMonth(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
    }

    /**
     * 获取当月最后一天最小值
     *
     * @param localDateTime 入参时间
     * @return 当月最后一天最小值
     */
    public static LocalDateTime minLastDayOfMonth(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MIN);
    }


    /**
     * 获取当月第一天最大值
     *
     * @param localDateTime 入参时间
     * @return 当月第一天最大值
     */
    public static LocalDateTime maxFirstDayOfMonth(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MAX);
    }

    /**
     * 获取当月第一天最小值
     *
     * @param localDateTime 入参时间
     * @return 当月第一天最小值
     */
    public static LocalDateTime minFirstDayOfMonth(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
    }

    /**
     * date 转 LocalDateTime
     *
     * @param date 时间
     * @return 时间
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        return zonedDateTime.toLocalDateTime();
    }
}