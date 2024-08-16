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
package org.dblue.core.caching;

import java.util.List;
import java.util.Optional;

/**
 * 缓存服务接口
 *
 * @author Wang Chengwei
 * @since 1.0.0
 * @param <E> 实体类型
 * @param <C> 缓存对象类型
 */
public interface CachingService<E, C> {

    /**
     * 获取缓存数据
     *
     * @param id ID
     * @return 缓存数据
     */
    Optional<C> get(String id);

    /**
     * 获取所有缓存数据
     *
     * @return 缓存数据列表
     */
    List<C> getAll();

    /**
     * 添加或更新缓存
     *
     * @param entity 数据
     */
    void save(E entity);

    /**
     * 删除缓存
     *
     * @param id 数据ID
     */
    void delete(String id);
}
