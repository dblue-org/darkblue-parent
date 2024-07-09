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
package org.dblue.application.module.setting.domain.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dblue.application.commons.bus.EventBus;
import org.dblue.application.module.setting.domain.event.PropertyAddEvent;
import org.dblue.application.module.setting.domain.event.PropertyDeleteEvent;
import org.dblue.application.module.setting.domain.service.PropertySettingDomainService;
import org.dblue.application.module.setting.errors.PropertySettingError;
import org.dblue.application.module.setting.infrastructure.entity.PropertySetting;
import org.dblue.application.module.setting.infrastructure.repository.PropertySettingRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Service
public class PropertySettingDomainServiceImpl implements PropertySettingDomainService {


    private final PropertySettingRepository propertySettingRepository;

    private final EventBus eventBus;

    public PropertySettingDomainServiceImpl(PropertySettingRepository propertySettingRepository, EventBus eventBus) {
        this.propertySettingRepository = propertySettingRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void add(PropertySetting propertySetting, boolean fireEvent) {
        PropertySetting dbPropertySetting = this.propertySettingRepository.findByPropertyCode(propertySetting.getPropertyCode());
        if (dbPropertySetting != null) {
            throw new ServiceException(PropertySettingError.PROPERTY_CODE_EXIST);
        }

        if (StringUtils.isBlank(propertySetting.getPropertyId())) {
            propertySetting.setPropertyId(Snowflake.stringId());
        }
        propertySetting.setValue(propertySetting.getDefaultValue());
        this.propertySettingRepository.save(propertySetting);

        if (fireEvent) {
            this.eventBus.fireEventAfterCommit(new PropertyAddEvent(this, propertySetting));
        }
    }

    @Override
    public void update(PropertySetting propertySetting, boolean fireEvent) {
        PropertySetting dbPropertySetting = this.propertySettingRepository.findByPropertyCode(propertySetting.getPropertyCode());
        if (dbPropertySetting != null && !Objects.equals(dbPropertySetting.getPropertyId(), propertySetting.getPropertyId())) {
            throw new ServiceException(PropertySettingError.PROPERTY_CODE_EXIST);
        }

        this.propertySettingRepository.save(propertySetting);

        if (fireEvent) {
            this.eventBus.fireEventAfterCommit(new PropertyAddEvent(this, propertySetting));
        }
    }

    @Override
    public void delete(String propertyId, boolean fireEvent) {
        PropertySetting dbPropertySetting = this.checkExistAndGet(propertyId);
        this.propertySettingRepository.delete(dbPropertySetting);

        if (fireEvent) {
            this.eventBus.fireEventAfterCommit(new PropertyDeleteEvent(this, dbPropertySetting));
        }
    }

    @Override
    public void changePropertyValue(String propertyId, String value) {
        PropertySetting dbPropertySetting = this.checkExistAndGet(propertyId);
        dbPropertySetting.setValue(value);
        this.propertySettingRepository.save(dbPropertySetting);
    }

    @Override
    public PropertySetting get(String propertyId) {
        return checkExistAndGet(propertyId);
    }

    private PropertySetting checkExistAndGet(String propertyId) {
        Optional<PropertySetting> propertySettingOptional = this.propertySettingRepository.findById(propertyId);
        if (propertySettingOptional.isEmpty()) {
            throw new ServiceException(PropertySettingError.NOT_EXIST);
        }
        return propertySettingOptional.get();
    }
}