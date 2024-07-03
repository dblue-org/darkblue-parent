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
package org.dblue.application.module.caching;

import org.dblue.application.module.user.infrastructure.entity.User;
import org.dblue.application.module.user.infrastructure.repository.UserRepository;
import org.dblue.core.caching.AbstractCachingService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Component
public class UserCacheService extends AbstractCachingService<User, UserCacheObject> {

    private final UserRepository userRepository;

    public UserCacheService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected List<User> getAllEntities() {
        return this.userRepository.findAll();
    }

    @Override
    protected UserCacheObject toCacheObject(User user) {
        UserCacheObject cacheObject = new UserCacheObject();
        BeanUtils.copyProperties(user, cacheObject);
        return cacheObject;
    }

    @Override
    protected String getId(User user) {
        return user.getUserId();
    }

    @Override
    public String getCacheName() {
        return "用户缓存";
    }

    @Override
    public String getCacheCode() {
        return "User";
    }
}