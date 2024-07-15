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

package org.dblue.application.jpa;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;

import java.util.List;

/**
 * 基础查询接口继承
 *
 * @author xie jin
 * @since 1.0.0  2024/7/4 下午2:08
 */
@SuppressWarnings("java:S119")
public interface BaseJpaRepository<T, ID> extends JpaRepository<T, ID>, ListQuerydslPredicateExecutor<T> {

    /**
     * 分页查询
     *
     * @param builder  查询参数
     * @param pageable 分页参数
     * @return 返回结果
     */
    default Page<T> page(BooleanBuilder builder, Pageable pageable) {
        if (builder.getValue() != null) {
            return this.findAll(builder.getValue(), pageable);
        } else {
            return this.findAll(pageable);
        }
    }

    /**
     * 分页查询
     *
     * @param builder  查询参数
     * @param pageable 分页参数
     * @param sort     排序
     * @return 返回结果
     */
    default Page<T> page(BooleanBuilder builder, Pageable pageable, QSort sort) {

        QPageRequest qPageRequest = QPageRequest.of(pageable.getPageSize(), pageable.getPageNumber(), sort);
        if (builder.getValue() != null) {
            return this.findAll(builder.getValue(), qPageRequest);
        } else {
            return this.findAll(qPageRequest);
        }
    }

    /**
     * 查询返回集合
     *
     * @param builder 查询参数
     * @return 返回结果
     */
    default List<T> getList(BooleanBuilder builder) {
        return this.findAll(builder);
    }
}
