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
 * 抽象缓存初始化部分逻辑
 *
 * @param <T> 实体类型
 * @param <C> 缓存对象类型
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractCachingInitService<T, C> implements CachingInitService {

    /**
     * Redis 客户端操作对象
     */
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

    /**
     * 获取所有要缓存的数据
     *
     * @return 原始数据列表
     */
    protected abstract List<T> getAllEntities();

    /**
     * 获取缓存 Key
     *
     * @param t 实体对象
     * @return 缓存Key
     */
    protected String getCacheKey(T t) {
        String id = getId(t);
        return this.getCacheKey(id);
    }

    /**
     * 获取实体数据的ID，此ID作为缓存Key生成的基础
     *
     * @param t 实体对象
     * @return 缓存Key
     */
    protected abstract String getId(T t);

    /**
     * 获取缓存 Key
     *
     * @param id 实体数据的ID
     * @return 缓存 Key
     */
    protected String getCacheKey(String id) {
        String prefix = this.getCachePrefix();
        if (prefix.endsWith(":")) {
            return prefix + id;
        } else {
            return prefix + ":" + id;
        }
    }

    /**
     * 将实体对象转为缓存对象
     *
     * @param t 实体对象
     * @return 缓存对象
     */
    protected abstract C toCacheObject(T t);
}