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

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractCachingInitService<T, C> implements CachingInitService {

    @Resource(name = "redisTemplate")
    protected ValueOperations<String, C> valueOperations;

    @Override
    public Long getKeyNums() {
        String keyPattern = this.getKeyPattern();
        Set<String> keys = this.valueOperations.getOperations().keys(keyPattern);
        return keys != null ? keys.size() : 0L;
    }

    @Override
    public void init() {
        String keyPattern = this.getKeyPattern();
        Set<String> keys = this.valueOperations.getOperations().keys(keyPattern);
        if (!CollectionUtils.isEmpty(keys)) {
            log.info("{} 缓存已初始化，无需重复初始化", this.getCacheName());
            return;
        }
        log.info("初始化缓存：{}", this.getCacheName());
        List<T> entities = this.getAllEntities();
        Map<String, C> cacheMap = new HashMap<>(entities.size());
        for (T entity : entities) {
            String cacheKey = this.getCacheKey(entity);
            C cacheObject = this.toCacheObject(entity);
            cacheMap.put(cacheKey, cacheObject);
        }
        this.valueOperations.multiSet(cacheMap);
    }

    @Override
    public void refresh() {
        String keyPattern = this.getKeyPattern();
        Set<String> keys = this.valueOperations.getOperations().keys(keyPattern);
        if (!CollectionUtils.isEmpty(keys)) {
            this.valueOperations.getOperations().delete(keys);
        }
        this.init();
    }

    private String getKeyPattern() {
        return this.getCachePrefix().endsWith(":") ? this.getCachePrefix() + "*" : this.getCachePrefix() + ":*";
    }

    protected abstract List<T> getAllEntities();

    protected String getCacheKey(T t) {
        String id = getId(t);
        return this.getCacheKey(id);
    }

    protected abstract C toCacheObject(T t);

    protected abstract String getId(T t);

    protected String getCacheKey(String id) {
        String prefix = this.getCachePrefix();
        if (prefix.endsWith(":")) {
            return prefix + id;
        } else {
            return prefix + ":" + id;
        }
    }
}