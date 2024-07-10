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
package org.dblue.application.module.setting.application.service;

import org.dblue.application.module.setting.application.dto.PropertySettingAddDto;
import org.dblue.application.module.setting.application.dto.PropertySettingQueryDto;
import org.dblue.application.module.setting.application.dto.PropertySettingUpdateDto;
import org.dblue.application.module.setting.application.vo.PropertySettingPageListVo;
import org.springframework.data.domain.Page;

/**
 * 配置参数暴露接口
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface PropertySettingApplicationService {

    /**
     * 添加参数
     *
     * @param addDto 参数信息
     */
    void add(PropertySettingAddDto addDto);

    /**
     * 更新参数
     *
     * @param updateDto 参数信息
     */
    void update(PropertySettingUpdateDto updateDto);

    /**
     * 删除参数
     *
     * @param propertyId 参数ID
     */
    void delete(String propertyId);

    /**
     * 更新参数的值
     *
     * @param propertyId 参数ID
     * @param value      参数值
     */
    void changeValue(String propertyId, String value);

    /**
     * 分页查询参数信息
     *
     * @param queryDto 查询条件
     * @return 参数信息
     */
    Page<PropertySettingPageListVo> findByPage(PropertySettingQueryDto queryDto);
}
