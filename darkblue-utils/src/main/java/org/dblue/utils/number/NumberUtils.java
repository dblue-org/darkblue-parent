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
package org.dblue.utils.number;

import java.math.BigDecimal;

/**
 * 数值类型工具类
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public final class NumberUtils {


    private NumberUtils() {
    }

    /**
     * 判断整数数值是否为正数，大于0
     *
     * @param num 整数数值
     * @return true-正整数；false-负整数或0
     */
    public static boolean isPositive(Integer num) {
        return num != null && num > 0;
    }

    /**
     * 判断数值是否为正数，大于0
     *
     * @param num 数值
     * @return true-正数；false-负数或0
     */
    public static boolean isPositive(BigDecimal num) {
        return num != null && num.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * 判断整数数值是否为负数，小于0
     *
     * @param num 整数数值
     * @return true-负整数；false-正整数或0
     */
    public static boolean isNegative(Integer num) {
        return num != null && num < 0;
    }

    /**
     * 判断数值是否为负数，小于0
     *
     * @param num 数值
     * @return true-负数；false-正数或0
     */
    public static boolean isNegative(BigDecimal num) {
        return num != null && num.compareTo(BigDecimal.ZERO) < 0;
    }

    /**
     * 判断整数数值是否为非负数，大于等于0
     *
     * @param num 整数数值
     * @return true-非负整数；false-负数
     */
    public static boolean isNonnegative(Integer num) {
        return num != null && num >= 0;
    }

    /**
     * 判断数值是否为非负数，大于等于0
     *
     * @param num 数值
     * @return true-非负数；false-负数
     */
    public static boolean isNonnegative(BigDecimal num) {
        return num != null && num.compareTo(BigDecimal.ZERO) >= 0;
    }
}