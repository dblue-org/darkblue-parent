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

import org.dblue.common.exception.OperationNotSupportedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface BaseJpaQuery<T> {


    /**
     * 获取所有数据
     *
     * @return 数据列表
     */
    List<T> list();

    /**
     * 分页查询数据
     *
     * @param page     页面
     * @param pageSize 每页数量量
     * @return 数据
     */
    Page<T> page(int page, int pageSize);

    /**
     * 分页查询数据
     *
     * @return 数据
     */
    Page<T> page(Pageable pageable);

    /**
     * 获取单条数据
     *
     * @return 数据
     */
    Optional<T> single();

    /**
     * 获取数量
     *
     * @return 数量
     */
    long count();

    /**
     * 获取名称映射
     *
     * @return 名称映射
     */
    default Map<String, String> nameMap() {
        throw new OperationNotSupportedException();
    }

    /**
     * 结果转为Map
     *
     * @return 可以-ID, T-实体
     */
    default Map<String, T> toMap() {
        throw new OperationNotSupportedException();
    }
}
