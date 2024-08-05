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

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 缓存处理抽象
 *
 * @author Wang Chengwei
 * @since 1.0.0
 * @param <T> 实体类型
 * @param <C> 缓存对象类型
 */
public abstract class AbstractCachingService<T, C> extends AbstractCachingInitService<T, C> implements CachingService<T, C> {

    @Override
    public Optional<C> get(String id) {
        String cacheKey = this.getCacheKey(id);
        C cacheObject = this.valueOperations.get(cacheKey);
        return Optional.ofNullable(cacheObject);
    }

    @Override
    public List<C> getAll() {
        String keyPattern = this.getCachePrefix().endsWith(":") ? this.getCachePrefix() + "*" : this.getCachePrefix() + ":*";
        Set<String> keys = this.valueOperations.getOperations().keys(keyPattern);
        if (!CollectionUtils.isEmpty(keys)) {
            return this.valueOperations.multiGet(keys);
        }
        return List.of();
    }

    @Override
    public void save(T entity) {
        String id = getId(entity);
        String cacheKey = this.getCacheKey(id);
        C cacheObject = this.toCacheObject(entity);
        this.valueOperations.set(cacheKey, cacheObject);
    }

    @Override
    public void delete(String id) {
        String cacheKey = this.getCacheKey(id);
        this.valueOperations.getOperations().delete(cacheKey);
    }
}