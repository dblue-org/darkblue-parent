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
package org.dblue.application.module.setting.application.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.setting.application.dto.PropertySettingAddDto;
import org.dblue.application.module.setting.application.dto.PropertySettingQueryDto;
import org.dblue.application.module.setting.application.dto.PropertySettingUpdateDto;
import org.dblue.application.module.setting.application.service.PropertySettingApplicationService;
import org.dblue.application.module.setting.application.vo.PropertySettingPageListVo;
import org.dblue.application.module.setting.domain.converter.PropertyValueConverterDelegate;
import org.dblue.application.module.setting.domain.enums.PropertyType;
import org.dblue.application.module.setting.domain.service.PropertySettingDomainService;
import org.dblue.application.module.setting.infrastructure.entity.PropertySetting;
import org.dblue.application.module.setting.infrastructure.repository.PropertySettingRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.core.jpa.JpaPageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static org.dblue.application.module.setting.errors.PropertySettingError.VALUE_SCOPE_RESOLVE_ERROR;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Service
public class PropertySettingApplicationServiceImpl implements PropertySettingApplicationService {


    private final PropertySettingRepository propertySettingRepository;

    private final PropertySettingDomainService propertySettingDomainService;

    private final PropertyValueConverterDelegate propertyValueConverterDelegate;

    private final ObjectMapper objectMapper;

    public PropertySettingApplicationServiceImpl(
            PropertySettingRepository propertySettingRepository, PropertySettingDomainService propertySettingDomainService,
            PropertyValueConverterDelegate propertyValueConverterDelegate, ObjectMapper objectMapper) {
        this.propertySettingRepository = propertySettingRepository;
        this.propertySettingDomainService = propertySettingDomainService;
        this.propertyValueConverterDelegate = propertyValueConverterDelegate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void add(PropertySettingAddDto addDto) {
        PropertySetting propertySetting = new PropertySetting();
        BeanUtils.copyProperties(addDto, propertySetting);

        if (addDto.getValueScope() != null) {
            try {
                propertySetting.setValueScope(this.objectMapper.writeValueAsString(addDto.getValueScope()));
            } catch (JsonProcessingException e) {
                throw new ServiceException(VALUE_SCOPE_RESOLVE_ERROR);
            }
        }

        this.propertySettingDomainService.add(propertySetting, true);
    }

    @Override
    public void update(PropertySettingUpdateDto updateDto) {
        PropertySetting propertySetting = this.propertySettingDomainService.get(updateDto.getPropertyId());
        BeanUtils.copyProperties(updateDto, propertySetting);
        this.propertySettingDomainService.update(propertySetting, true);
    }

    @Override
    public void delete(String propertyId) {
        this.propertySettingDomainService.delete(propertyId, true);
    }

    @Override
    public void changeValue(String propertyId, String value) {
        this.propertySettingDomainService.changeValue(propertyId, value, true);
    }

    @Override
    public Page<PropertySettingPageListVo> findByPage(PropertySettingQueryDto queryDto) {
        Page<PropertySetting> entityPage = this.propertySettingRepository.findByPage(
                queryDto.getPropertyCode(), queryDto.getPropertyName(), queryDto.toJpaPage());

        return JpaPageConverter.convert(entityPage, entity -> {
            PropertySettingPageListVo vo = PropertySettingPageListVo.of(entity);
            vo.setDefaultValueToShow(this.propertyValueConverterDelegate.getName(vo.getType(), vo.getDefaultValue()));
            vo.setValueToShow(this.propertyValueConverterDelegate.getName(vo.getType(), vo.getValue()));
            vo.setTypeName(PropertyType.getName(vo.getType()));

            try {
                if (StringUtils.isNotBlank(entity.getValueScope())) {
                    vo.setValueScope(this.objectMapper.readValue(entity.getValueScope(), Object.class));
                }
            } catch (JsonProcessingException e) {
                throw new ServiceException(VALUE_SCOPE_RESOLVE_ERROR);
            }
            return vo;
        });
    }
}