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

package org.dblue.application.module.dictionary.infrastructure.repository;

import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * 数据字典条目
 *
 * @author xie jin
 * @since 1.0.0  2024-07-12 14:07:49
 */
public interface DictionaryItemRepository extends JpaRepository<DictionaryItem, String> {

    /**
     * 判断子项是否存在
     *
     * @param dictionaryId 字典ID
     * @return 是否存在
     */
    boolean existsByDictionaryIdAndIsDeleteFalse(
            String dictionaryId);

    /**
     * 新增查询
     *
     * @param dictionaryId       字典ID
     * @param dictionaryItemCode 字典项编码
     * @return 字典项
     */
    Optional<DictionaryItem> findByDictionaryIdAndDictionaryItemCodeAndIsDeleteFalse(
            @NonNull String dictionaryId, @NonNull String dictionaryItemCode);


    /**
     * 更新用
     *
     * @param dictionaryId       字典ID
     * @param dictionaryItemCode 字典项编码
     * @param dictionaryItemId   字典项ID
     * @return
     */
    Optional<DictionaryItem> findByDictionaryIdAndDictionaryItemCodeAndDictionaryItemIdNotAndIsDeleteFalse(
            @NonNull String dictionaryId, @NonNull String dictionaryItemCode, @NonNull String dictionaryItemId);


}