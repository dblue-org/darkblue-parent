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
package org.dblue.application.module.dictionary.domain.service.cache;

import org.dblue.core.caching.CachingInitService;

import java.util.Map;
import java.util.Set;

/**
 * 字典缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface DictionaryCacheService extends CachingInitService {

    /**
     * 设置字典缓存数据
     *
     * @param dictionaryCode 字典编码
     * @param code           字典项值
     * @param name           字典项名称
     */
    void set(String dictionaryCode, Integer code, String name);

    /**
     * 移除字典
     *
     * @param dictionaryCode 字典编码
     */
    void removeDictionary(String dictionaryCode);

    /**
     * 移除字典项
     *
     * @param dictionaryCode 字典编码
     * @param code           字典项值
     */
    void removeDictionaryItem(String dictionaryCode, Integer code);

    /**
     * 获取所有字典数据
     *
     * @param dictionaryCode 字典编码
     * @return 字典数据：key-字典项值；value-字典项名称
     */
    Map<Integer, String> getAll(String dictionaryCode);

    /**
     * 获取所有字典数据
     *
     * @param dictionaryCode 字典编码
     * @param codes          字典项值列表
     * @return 字典数据：key-字典项值；value-字典项名称
     */
    Map<Integer, String> getAll(String dictionaryCode, Set<Integer> codes);

    /**
     * 获取字典项名称
     *
     * @param dictionaryCode 字典编码
     * @param code           字典项值
     * @return 字典项名称
     */
    String getName(String dictionaryCode, Integer code);

}
