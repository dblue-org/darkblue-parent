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
package org.dblue.application.module.setting.domain.cache.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.setting.domain.cache.PropertySettingCacheObject;
import org.dblue.application.module.setting.domain.cache.PropertySettingCacheService;
import org.dblue.application.module.setting.domain.converter.BooleanValueConverter;
import org.dblue.application.module.setting.domain.converter.LocalDateTimeValueConverter;
import org.dblue.application.module.setting.domain.converter.LocalDateValueConverter;
import org.dblue.application.module.setting.domain.converter.PropertyValueConverterDelegate;
import org.dblue.application.module.setting.infrastructure.entity.PropertySetting;
import org.dblue.application.module.setting.infrastructure.repository.PropertySettingRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.core.caching.AbstractCachingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
@Service
public class PropertySettingCacheServiceImpl extends AbstractCachingService<PropertySetting, PropertySettingCacheObject> implements PropertySettingCacheService {

    private final PropertySettingRepository propertySettingRepository;

    private final PropertyValueConverterDelegate propertyValueConverterDelegate;

    private final BooleanValueConverter booleanValueConverter;

    private final LocalDateValueConverter localDateValueConverter;

    private final LocalDateTimeValueConverter localDateTimeValueConverter;

    public PropertySettingCacheServiceImpl(
            PropertySettingRepository propertySettingRepository, PropertyValueConverterDelegate propertyValueConverterDelegate,
            BooleanValueConverter booleanValueConverter, LocalDateValueConverter localDateValueConverter,
            LocalDateTimeValueConverter localDateTimeValueConverter) {
        this.propertySettingRepository = propertySettingRepository;
        this.propertyValueConverterDelegate = propertyValueConverterDelegate;
        this.booleanValueConverter = booleanValueConverter;
        this.localDateValueConverter = localDateValueConverter;
        this.localDateTimeValueConverter = localDateTimeValueConverter;
    }

    @Override
    protected List<PropertySetting> getAllEntities() {
        return this.propertySettingRepository.findAll();
    }

    @Override
    protected PropertySettingCacheObject toCacheObject(PropertySetting propertySetting) {
        return PropertySettingCacheObject.of(propertySetting);
    }

    @Override
    protected String getId(PropertySetting propertySetting) {
        return propertySetting.getPropertyCode();
    }

    @Override
    public String getDefaultValue(String propertyCode) {
        Optional<PropertySettingCacheObject> cacheObjectOptional = this.get(propertyCode);
        return cacheObjectOptional.map(PropertySettingCacheObject::getDefaultValue).orElse(null);
    }

    @Override
    public String getValue(String propertyCode) {
        Optional<PropertySettingCacheObject> cacheObjectOptional = this.get(propertyCode);
        return cacheObjectOptional.map(PropertySettingCacheObject::getValue).orElse(null);
    }

    @Override
    public Integer getIntValue(String propertyCode) {
        String value = this.getValue(propertyCode);
        if (StringUtils.isNumeric(value)) {
            return Integer.valueOf(value);
        }
        throw new ServiceException("无法将参数值 [" + value + "]转为 Integer 类型");
    }

    @Override
    public BigDecimal getDecimalValue(String propertyCode) {
        String value = this.getValue(propertyCode);
        if (StringUtils.isNotBlank(value)) {
            try {
                return new BigDecimal(value);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }
        throw new ServiceException("无法将参数值 [" + value + "]转为 BigDecimal 类型");
    }

    @Override
    public Boolean getBooleanValue(String propertyCode) {
        String value = this.getValue(propertyCode);
        if (StringUtils.isNotBlank(value)) {
            Optional<Boolean> optional = this.booleanValueConverter.convert(value);
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        throw new ServiceException("无法将参数值 [" + value + "]转为 Boolean 类型");
    }

    @Override
    public LocalDate getDateValue(String propertyCode) {
        String value = this.getValue(propertyCode);
        if (StringUtils.isNotBlank(value)) {
            Optional<LocalDate> optional = this.localDateValueConverter.convert(value);
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        throw new ServiceException("无法将参数值 [" + value + "]转为 LocalDate 类型");
    }

    @Override
    public LocalDateTime getDateTimeValue(String propertyCode) {
        String value = this.getValue(propertyCode);
        if (StringUtils.isNotBlank(value)) {
            Optional<LocalDateTime> optional = this.localDateTimeValueConverter.convert(value);
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        throw new ServiceException("无法将参数值 [" + value + "]转为 LocalDateTime 类型");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getConvertedValue(String propertyCode) {
        Optional<PropertySettingCacheObject> cacheObjectOptional = this.get(propertyCode);
        if (cacheObjectOptional.isPresent()) {
            PropertySettingCacheObject cacheObject = cacheObjectOptional.get();
            Optional<?> optionalObject = this.propertyValueConverterDelegate.get(cacheObject.getType(), cacheObject.getValue());
            if (optionalObject.isPresent()) {
                return (T) optionalObject.get();
            }
        }
        return null;
    }


    @Override
    public String getCacheName() {
        return "配置参数";
    }

    @Override
    public String getCacheCode() {
        return "PropertySettings";
    }
}