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
package org.dblue.application.module.position.adapter.listener;

import lombok.RequiredArgsConstructor;
import org.dblue.application.module.position.domain.cache.PositionCacheService;
import org.dblue.application.module.position.domain.event.PositionAddEvent;
import org.dblue.application.module.position.domain.event.PositionUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 职位缓存监听处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class PositionEventForCacheListener {

    private final PositionCacheService positionCacheService;

    @EventListener(PositionAddEvent.class)
    public void onAdd(PositionAddEvent positionAddEvent) {
        this.positionCacheService.save(positionAddEvent.getPosition());
    }

    @EventListener(PositionAddEvent.class)
    public void onUpdate(PositionUpdateEvent positionUpdateEvent) {
        this.positionCacheService.save(positionUpdateEvent.getPosition());
    }
}