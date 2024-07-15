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
package org.dblue.application.module.dictionary.adapter.listener;

import lombok.RequiredArgsConstructor;
import org.dblue.application.module.dictionary.domain.service.cache.DictionaryCacheService;
import org.dblue.application.module.dictionary.domain.service.event.DictionaryDeleteEvent;
import org.dblue.application.module.dictionary.domain.service.event.DictionaryItemAddEvent;
import org.dblue.application.module.dictionary.domain.service.event.DictionaryItemDeleteEvent;
import org.dblue.application.module.dictionary.domain.service.event.DictionaryItemUpdateEvent;
import org.dblue.application.module.dictionary.infrastructure.entity.Dictionary;
import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监控字典变更，更新字典缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class DictionaryEventForCacheListener {

    private final DictionaryCacheService dictionaryCacheService;

    @EventListener(DictionaryItemAddEvent.class)
    public void onItemAdd(DictionaryItemAddEvent addEvent) {
        Dictionary dictionary = addEvent.getDictionary();
        DictionaryItem dictionaryItem = addEvent.getItem();
        this.dictionaryCacheService.set(dictionary.getDictionaryCode(), dictionaryItem.getCode(), dictionaryItem.getName());
    }

    @EventListener(DictionaryItemUpdateEvent.class)
    public void onItemUpdate(DictionaryItemUpdateEvent updateEvent) {
        Dictionary dictionary = updateEvent.getDictionary();
        DictionaryItem dictionaryItem = updateEvent.getItem();
        this.dictionaryCacheService.set(dictionary.getDictionaryCode(), dictionaryItem.getCode(), dictionaryItem.getName());
    }

    @EventListener(DictionaryItemDeleteEvent.class)
    public void onItemDelete(DictionaryItemDeleteEvent deleteEvent) {
        Dictionary dictionary = deleteEvent.getDictionary();
        DictionaryItem dictionaryItem = deleteEvent.getItem();
        this.dictionaryCacheService.removeDictionaryItem(dictionary.getDictionaryCode(), dictionaryItem.getCode());
    }

    @EventListener(DictionaryDeleteEvent.class)
    public void onDictionaryDelete(DictionaryDeleteEvent deleteEvent) {
        Dictionary dictionary = deleteEvent.getDictionary();
        this.dictionaryCacheService.removeDictionary(dictionary.getDictionaryCode());
    }
}