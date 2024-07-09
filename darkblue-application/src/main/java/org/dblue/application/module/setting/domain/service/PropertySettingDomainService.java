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
package org.dblue.application.module.setting.domain.service;

import org.dblue.application.module.setting.infrastructure.entity.PropertySetting;

/**
 * 系统参数配置
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface PropertySettingDomainService {

    /**
     * 添加参数
     *
     * @param propertySetting 参数信息
     * @param fireEvent       是否触发添加事件
     */
    void add(PropertySetting propertySetting, boolean fireEvent);

    /**
     * 更新参数
     *
     * @param propertySetting 参数信息
     * @param fireEvent       是否触发更新事件
     */
    void update(PropertySetting propertySetting, boolean fireEvent);

    /**
     * 删除参数
     *
     * @param propertyId 参数ID
     * @param fireEvent  是否触发删除事件
     */
    void delete(String propertyId, boolean fireEvent);

    /**
     * 更新参数的值
     *
     * @param propertyId 参数ID
     * @param value      参数值
     */
    void changePropertyValue(String propertyId, String value);


    /**
     * 获取配置参数，当数据不存在时抛出异常
     *
     * @param propertyId 参数ID
     * @return 参数信息
     */
    PropertySetting get(String propertyId);
}
