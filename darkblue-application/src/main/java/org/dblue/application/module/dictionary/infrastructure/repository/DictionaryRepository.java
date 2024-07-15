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

import org.dblue.application.module.dictionary.infrastructure.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * 数据字典
 *
 * @author xie jin
 * @since 1.0.0  2024-07-12 14:07:35
 */
public interface DictionaryRepository extends JpaRepository<Dictionary, String> {

    /**
     * 查询未删除的字典
     *
     * @param dictionaryId 字典ID
     * @return 字典
     */
    Optional<Dictionary> findByDictionaryIdAndIsDeleteFalse(@NonNull String dictionaryId);

    /**
     * 新增查询
     *
     * @param dictionaryCode 字典编码
     * @return 字典
     */
    Optional<Dictionary> findByDictionaryCodeAndIsDeleteFalse(@NonNull String dictionaryCode);


    /**
     * 更新查询
     *
     * @param dictionaryCode 字典编码
     * @param dictionaryId   字典ID
     * @return 字典
     */
    Optional<Dictionary> findByDictionaryCodeAndDictionaryIdNotAndIsDeleteFalse(
            String dictionaryCode, @NonNull String dictionaryId);


    /**
     * 查询全部数据
     *
     * @return 字典
     */
    List<Dictionary> findByIsDeleteFalse();
}