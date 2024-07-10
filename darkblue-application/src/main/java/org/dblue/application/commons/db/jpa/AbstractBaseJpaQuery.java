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
package org.dblue.application.commons.db.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.common.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * JPA 查询构造抽象
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public abstract class AbstractBaseJpaQuery<T, I> implements BaseJpaQuery<T> {

    protected final BaseJpaRepository<T, I> executor;
    protected BooleanBuilder queryBuilder = new BooleanBuilder();

    protected AbstractBaseJpaQuery(BaseJpaRepository<T, I> executor) {
        this.executor = executor;
    }


    @Override
    public List<T> list() {
        Predicate predicate = queryBuilder.getValue();
        if (predicate == null) {
            return this.executor.findAll();
        } else {
            return this.executor.findAll(predicate);
        }
    }

    @Override
    public Page<T> page(int page, int pageSize) {
        Predicate predicate = queryBuilder.getValue();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        if (predicate == null) {
            return this.executor.findAll(pageable);
        } else {
            return this.executor.findAll(predicate, pageable);
        }
    }

    @Override
    public Optional<T> single() {
        Predicate predicate = queryBuilder.getValue();
        if (predicate == null) {
            throw new ServiceException("查询单条数据时必须有条件");
        } else {
            return this.executor.findOne(predicate);
        }
    }
}