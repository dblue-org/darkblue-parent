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
package org.dblue.application.module.user.adapter.listener;

import org.dblue.application.module.user.domain.cache.UserCacheService;
import org.dblue.application.module.user.domain.event.UserAddEvent;
import org.dblue.application.module.user.domain.event.UserDeleteEvent;
import org.dblue.application.module.user.domain.event.UserUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 用户事件监听，更新用户缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Component
public class UserEventListener {

    private final UserCacheService userCacheService;

    public UserEventListener(UserCacheService userCacheService) {
        this.userCacheService = userCacheService;
    }

    @Async
    @EventListener(UserAddEvent.class)
    public void onAdd(UserAddEvent userAddEvent) {
        this.userCacheService.save(userAddEvent.getUser());
    }

    @Async
    @EventListener(UserUpdateEvent.class)
    public void onUpdate(UserUpdateEvent userUpdateEvent) {
        this.userCacheService.save(userUpdateEvent.getUser());
    }

    @Async
    @EventListener(UserDeleteEvent.class)
    public void onDelete(UserDeleteEvent userDeleteEvent) {
        this.userCacheService.delete(userDeleteEvent.getUser().getUserId());
    }
}