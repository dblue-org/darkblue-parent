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
package org.dblue.application.module.setting.domain.cache;

import org.dblue.application.module.setting.domain.converter.PropertyValueConverter;
import org.dblue.application.module.setting.infrastructure.entity.PropertySetting;
import org.dblue.core.caching.CachingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 参数缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface PropertySettingCacheService extends CachingService<PropertySetting, PropertySettingCacheObject> {

    /**
     * 获取默认值，默认值咱不提供类型处理功能
     *
     * @param propertyCode 参数编码
     * @return 默认值
     */
    String getDefaultValue(String propertyCode);

    /**
     * 获取值
     *
     * @param propertyCode 参数编码
     * @return 值
     */
    String getValue(String propertyCode);

    /**
     * 获取值，并转为 {@link Integer} 类型
     *
     * @param propertyCode 参数编码
     * @return 值
     */
    Integer getIntValue(String propertyCode);

    /**
     * 获取值，并转为 {@link java.math.BigDecimal} 类型
     *
     * @param propertyCode 参数编码
     * @return 值
     */
    BigDecimal getDecimalValue(String propertyCode);

    /**
     * 获取值，并转为 {@link Boolean} 类型
     *
     * @param propertyCode 参数编码
     * @return 值
     */
    Boolean getBooleanValue(String propertyCode);

    /**
     * 获取值，并转为 {@link LocalDate} 类型。仅支持 yyyy-MM-dd 格式的日期
     *
     * @param propertyCode 参数编码
     * @return 值
     */
    LocalDate getDateValue(String propertyCode);

    /**
     * 获取值，并转为 {@link LocalDateTime} 类型。仅支持 yyyy-MM-dd HH:mm:ss 格式的日期
     *
     * @param propertyCode 参数编码
     * @return 值
     */
    LocalDateTime getDateTimeValue(String propertyCode);

    /**
     * 获取原始类型，通过 {@link PropertyValueConverter} 转换后的值
     *
     * @param propertyCode 参数编码
     * @param <T>          值类型
     * @return 类型数据
     */
    <T> T getConvertedValue(String propertyCode);
}
