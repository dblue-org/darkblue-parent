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
package org.dblue.application.module.dictionary.domain.service.event;

import lombok.Getter;
import org.dblue.application.module.dictionary.infrastructure.entity.Dictionary;
import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.springframework.context.ApplicationEvent;

/**
 * 字典项添加
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Getter
public class DictionaryItemAddEvent extends ApplicationEvent {

    private final transient Dictionary dictionary;

    private final transient DictionaryItem item;

    public DictionaryItemAddEvent(Object source, Dictionary dictionary, DictionaryItem item) {
        super(source);
        this.dictionary = dictionary;
        this.item = item;
    }
}