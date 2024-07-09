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
package org.dblue.application.module.user.domain.cache;

import org.dblue.core.caching.CachingService;

import java.util.Collection;
import java.util.List;

/**
 * 用户缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface UserCacheService extends CachingService<UserCacheObject> {

    /**
     * 根据ID获取用户信息
     *
     * @param userIds 用户ID列表
     * @return 用户列表
     */
    List<UserCacheObject> getAllById(Collection<String> userIds);
}
