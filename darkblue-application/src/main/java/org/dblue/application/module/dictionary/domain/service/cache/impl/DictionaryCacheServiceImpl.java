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
package org.dblue.application.module.dictionary.domain.service.cache.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.dictionary.domain.service.cache.DictionaryCacheService;
import org.dblue.application.module.dictionary.infrastructure.entity.Dictionary;
import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.dblue.application.module.dictionary.infrastructure.repository.DictionaryItemRepository;
import org.dblue.application.module.dictionary.infrastructure.repository.DictionaryRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
@Service
public class DictionaryCacheServiceImpl implements DictionaryCacheService {

    private final DictionaryRepository dictionaryRepository;
    private final DictionaryItemRepository dictionaryItemRepository;
    @Resource(name = "redisTemplate")
    private HashOperations<String, Integer, String> hashOperations;

    public DictionaryCacheServiceImpl(DictionaryRepository dictionaryRepository, DictionaryItemRepository dictionaryItemRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryItemRepository = dictionaryItemRepository;
    }

    @Override
    public void set(String dictionaryCode, Integer code, String name) {
        String cacheKey = this.getCacheKey(dictionaryCode);
        this.hashOperations.put(cacheKey, code, name);
    }

    @Override
    public void removeDictionary(String dictionaryCode) {
        String cacheKey = this.getCacheKey(dictionaryCode);
        this.hashOperations.getOperations().delete(cacheKey);
    }

    @Override
    public void removeDictionaryItem(String dictionaryCode, Integer code) {
        String cacheKey = this.getCacheKey(dictionaryCode);
        this.hashOperations.delete(cacheKey, code);
    }

    @Override
    public Map<Integer, String> getAll(String dictionaryCode) {
        String cacheKey = this.getCacheKey(dictionaryCode);
        if (Boolean.TRUE.equals(this.hashOperations.getOperations().hasKey(cacheKey))) {
            Set<Integer> codes = this.hashOperations.keys(cacheKey);
            return this.getAll(dictionaryCode, codes);
        }
        return Map.of();
    }

    @Override
    public Map<Integer, String> getAll(String dictionaryCode, Set<Integer> codes) {
        String cacheKey = this.getCacheKey(dictionaryCode);
        Map<Integer, String> itemMap = new LinkedHashMap<>();
        for (Integer code : codes) {
            String name = this.hashOperations.get(cacheKey, code);
            itemMap.put(code, name);
        }
        return itemMap;
    }

    @Override
    public String getName(String dictionaryCode, Integer code) {
        String cacheKey = this.getCacheKey(dictionaryCode);
        return this.hashOperations.get(cacheKey, code);
    }

    private String getCacheKey(String dictionaryCode) {
        return this.getCachePrefix() + ":" + dictionaryCode;
    }    @Override
    public String getCacheName() {
        return "字典缓存";
    }

    @Override
    public Long getKeyNums() {
        String cacheKeyPattern = this.getCacheKey("*");
        Set<String> keys = this.hashOperations.getOperations().keys(cacheKeyPattern);
        if (CollectionUtils.isEmpty(keys)) {
            return 0L;
        }
        return (long) keys.size();
    }

    @Override
    public void init() {
        String cacheKeyPattern = this.getCacheKey("*");
        Set<String> keys = this.hashOperations.getOperations().keys(cacheKeyPattern);
        if (CollectionUtils.isNotEmpty(keys)) {
            log.info("{} 缓存已初始化，无需重复初始化", this.getCacheName());
            return;
        }

        log.info("初始化缓存：{}", this.getCacheName());

        List<Dictionary> dictionaryList = this.dictionaryRepository.findAll();
        List<DictionaryItem> dictionaryItems = this.dictionaryItemRepository.findAll();

        Map<String, List<DictionaryItem>> dictionaryItemMap = dictionaryItems.stream()
                .collect(Collectors.groupingBy(DictionaryItem::getDictionaryId));

        for (Dictionary dictionary : dictionaryList) {

            List<DictionaryItem> items = dictionaryItemMap.get(dictionary.getDictionaryId());
            if (CollectionUtils.isNotEmpty(items)) {
                String cacheKey = this.getCacheKey(dictionary.getDictionaryCode());
                Map<Integer, String> itemMap = items.stream().collect(
                        Collectors.toMap(DictionaryItem::getCode, DictionaryItem::getName));
                this.hashOperations.putAll(cacheKey, itemMap);
            }
        }
    }

    @Override
    public void refresh() {
        String cacheKeyPattern = this.getCacheKey("*");
        Set<String> keys = this.hashOperations.getOperations().keys(cacheKeyPattern);
        if (CollectionUtils.isNotEmpty(keys)) {
            for (String key : keys) {
                this.hashOperations.getOperations().delete(key);
            }
        }
        this.init();
    }

    @Override
    public String getCacheCode() {
        return "Dictionary";
    }


}