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

package org.dblue.application.module.setting.infrastructure.repository;

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.setting.infrastructure.entity.PropertySetting;
import org.dblue.application.module.setting.infrastructure.entity.QPropertySetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertySettingRepository extends BaseJpaRepository<PropertySetting, String> {

    /**
     * 根据参数编码查询参数信息
     *
     * @param propertyCode 参数编码
     * @return 参数信息
     */
    PropertySetting findByPropertyCode(String propertyCode);

    /**
     * 分页查询参数配置信息
     *
     * @param propertyCode 参数编码
     * @param propertyName 参数名称
     * @param pageable     分页参数
     * @return 参数列表
     */
    default Page<PropertySetting> findByPage(String propertyCode, String propertyName, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(propertyCode)) {
            builder.and(QPropertySetting.propertySetting.propertyCode.like(propertyCode));
        }
        if (StringUtils.isNotBlank(propertyName)) {
            builder.and(QPropertySetting.propertySetting.propertyName.like(propertyName));
        }
        return this.page(builder, pageable);
    }
}