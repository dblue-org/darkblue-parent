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
package org.dblue.application.module.setting.adapter.listener;

import org.dblue.application.module.setting.domain.cache.PropertySettingCacheService;
import org.dblue.application.module.setting.domain.event.PropertyAddEvent;
import org.dblue.application.module.setting.domain.event.PropertyDeleteEvent;
import org.dblue.application.module.setting.domain.event.PropertyUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 参数配置缓存处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Component
public class PropertySettingCachingListener {

    private final PropertySettingCacheService propertySettingCacheService;

    public PropertySettingCachingListener(PropertySettingCacheService propertySettingCacheService) {
        this.propertySettingCacheService = propertySettingCacheService;
    }

    @Async
    @EventListener(PropertyAddEvent.class)
    public void onAdd(PropertyAddEvent event) {
        this.propertySettingCacheService.save(event.getPropertySetting());
    }

    @Async
    @EventListener(PropertyUpdateEvent.class)
    public void onUpdate(PropertyUpdateEvent event) {
        this.propertySettingCacheService.save(event.getPropertySetting());
    }

    @Async
    @EventListener(PropertyDeleteEvent.class)
    public void onDelete(PropertyDeleteEvent event) {
        this.propertySettingCacheService.save(event.getPropertySetting());
    }
}