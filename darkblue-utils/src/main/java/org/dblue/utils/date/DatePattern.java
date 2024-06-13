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

/**
 * 日期格式
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public final class DatePattern {
    public static final String DATE = "yyyy-MM-dd";
    public static final String SHORT_DATE = "yyyyMMdd";
    public static final String DATE_HM = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String SLASH_DELIMITER_DATE = "yyyy/MM/dd";
    public static final String SLASH_DELIMITER_DATE_HM = "yyyy/MM/dd HH:mm:ss";
    public static final String SLASH_DELIMITER_DATE_TIME = "yyyy/MM/dd HH:mm:ss";
    public static final String TIME = "HH:mm:ss";

    private DatePattern() {
    }

}