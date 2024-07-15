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

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.dictionary.application.vo.DictionaryItemPageVo;
import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.dblue.application.module.dictionary.infrastructure.entity.QDictionaryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 数据字典条目
 *
 * @author xie jin
 * @since 1.0.0  2024-07-12 14:07:49
 */
public interface DictionaryItemRepository extends BaseJpaRepository<DictionaryItem, String> {

    /**
     * 获取当前层级下字典信息
     *
     * @param parentId 父节点ID
     * @return 字典信息
     */
    List<DictionaryItem> findByParentIdAndIsDeleteFalseOrderByOrderNumDesc(@NonNull String parentId);


    /**
     * 获取当前层级下字典信息
     *
     * @param dictionaryId 字典ID
     * @return 字典信息
     */
    List<DictionaryItem> findByDictionaryIdAndIsDeleteFalseOrderByItemLevelDesc(@NonNull String dictionaryId);

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
     * @param dictionaryId 字典ID
     * @param code         字典编码
     * @return 字典项
     */
    Optional<DictionaryItem> findByDictionaryIdAndCodeAndIsDeleteFalse(
            @NonNull String dictionaryId, @NonNull Integer code);


    /**
     * 更新用
     *
     * @param dictionaryId     字典ID
     * @param code             字典编码
     * @param dictionaryItemId ID
     * @return 字典项
     */
    Optional<DictionaryItem> findByDictionaryIdAndCodeAndDictionaryItemIdNotAndIsDeleteFalse(
            @NonNull String dictionaryId, @NonNull Integer code, @NonNull String dictionaryItemId);


    /**
     * 根据字典ID查询字典项信息
     *
     * @param dictionaryId 字典ID
     * @return 字典项信息
     */
    List<DictionaryItem> findByDictionaryIdAndIsDeleteFalse(@NonNull String dictionaryId);


    /**
     * 分页查询
     *
     * @param itemPageVo 查询参数
     * @param pageable   分页参数
     * @return 字典项
     */
    default Page<DictionaryItem> page(DictionaryItemPageVo itemPageVo, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (Objects.nonNull(itemPageVo.getCode())) {
            builder.and(QDictionaryItem.dictionaryItem.code.eq(itemPageVo.getCode()));
        }
        if (StringUtils.isNotBlank(itemPageVo.getName())) {
            builder.and(QDictionaryItem.dictionaryItem.name.eq(itemPageVo.getName()));
        }
        if (StringUtils.isNotBlank(itemPageVo.getExtension())) {
            builder.and(QDictionaryItem.dictionaryItem.extension.likeIgnoreCase(itemPageVo.getExtension()));
        }
        builder.and(QDictionaryItem.dictionaryItem.isDelete.isFalse());
        QSort qSort = new QSort(QDictionaryItem.dictionaryItem.orderNum.asc());
        return page(builder, pageable, qSort);
    }


}