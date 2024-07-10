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
package org.dblue.application.module.setting.domain.converter;

import java.util.Optional;

/**
 * 参数值转换，必须为其实现类通过 {@link org.springframework.core.annotation.Order} 来指定其优先级，
 * 否则与默认转换器处于同一个优先级将可能出现无法正常解析的问题。
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface PropertyValueConverter<T> {

    /**
     * 是否支持处理此参数类型
     *
     * @param propertyType 参数类型
     * @return true-支持
     */
    boolean isSupported(int propertyType);

    /**
     * 获取转换后的数据
     *
     * @param value 参数值
     * @return 具体对象，如用户对应User等
     */
    Optional<T> convert(String value);

    /**
     * 获取显示名称
     *
     * @param value 值
     * @return 显示名称
     */
    String getValueName(String value);
}
