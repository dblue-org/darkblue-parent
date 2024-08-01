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

package org.dblue.application.module.dictionary.domain.service;

import org.dblue.application.module.dictionary.application.dto.*;

/**
 * 字典领域服务层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/12 下午2:13
 */
public interface DictionaryDomainService {


    /**
     * 字典添加
     *
     * @param addDto 字典信息
     */
    void add(DictionaryAddDto addDto);


    /**
     * 字典更新
     *
     * @param updateDto 字典信息
     */
    void update(DictionaryUpdateDto updateDto);


    /**
     * 字典删除
     *
     * @param dictionaryId 字典ID
     */
    void delete(String dictionaryId);


    /**
     * 字典添加
     *
     * @param addDto 字典信息
     */
    void addItem(DictionaryItemAddDto addDto);


    /**
     * 字典更新
     *
     * @param updateDto 字典信息
     */
    void updateItem(DictionaryItemUpdateDto updateDto);


    /**
     * 字典项删除
     *
     * @param dictionaryItemId 字典项ID
     */
    void deleteItem(String dictionaryItemId);

    /**
     * 字典项启用禁用
     *
     * @param enableDto 启用禁用信息
     */
    void toggleItemState(DictionaryItemEnableDto enableDto);
}
