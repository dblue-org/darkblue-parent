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

package org.dblue.security.user;

import jakarta.annotation.Resource;
import org.dblue.security.properties.SecurityProperties;
import org.dblue.security.token.CacheKeyProvider;
import org.dblue.security.token.Tokens;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 用户信息缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/3/26 11:48]
 */
@Component
public class UserStoreServiceImpl implements UserStoreService {

    private final SecurityProperties securityProperties;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, CachedSecurityUser> valueOperations;

    public UserStoreServiceImpl(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


    @Override
    public void save(SecurityUser user, Tokens tokens) {
        CachedSecurityUser cachedSecurityUser = CachedSecurityUser.create(user);
        cachedSecurityUser.setAccessToken(tokens.getAccessToken().getTokenValue());
        String cacheKey = CacheKeyProvider.getUserCacheKey(user.getUserId());
        this.valueOperations.set(cacheKey, cachedSecurityUser, securityProperties.getAccessTokenTtl(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void update(SecurityUser user) {
        String cacheKey = CacheKeyProvider.getUserCacheKey(user.getUserId());
        CachedSecurityUser cachedSecurityUser = this.valueOperations.get(cacheKey);
        if (cachedSecurityUser == null) {
            return;
        }

        CachedSecurityUser newCachedSecurityUser = CachedSecurityUser.create(user);
        newCachedSecurityUser.setAccessToken(cachedSecurityUser.getAccessToken());
        Long ttl = this.valueOperations.getOperations().getExpire(cacheKey, TimeUnit.MILLISECONDS);
        this.valueOperations.set(cacheKey, cachedSecurityUser, ttl != null ? ttl : securityProperties.getAccessTokenTtl(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void remove(String userId) {
        String cacheKey = CacheKeyProvider.getUserCacheKey(userId);
        this.valueOperations.getOperations().delete(cacheKey);
    }

    @Override
    public void refreshUserCache(String userId) {
        String cacheKey = CacheKeyProvider.getUserCacheKey(userId);
        this.valueOperations.getOperations().expire(cacheKey, this.securityProperties.getAccessTokenTtl(), TimeUnit.MILLISECONDS);
    }

    @Override
    public SecurityUser getUser(String userId) {
        String cacheKey = CacheKeyProvider.getUserCacheKey(userId);
        CachedSecurityUser cachedSecurityUser = this.valueOperations.get(cacheKey);
        if (cachedSecurityUser != null) {
            return cachedSecurityUser.toSecurityUser();
        } else {
            return null;
        }
    }

    @Override
    public String getAccessToken(String userId) {
        String cacheKey = CacheKeyProvider.getUserCacheKey(userId);
        CachedSecurityUser cachedSecurityUser = this.valueOperations.get(cacheKey);
        if (cachedSecurityUser != null) {
            return cachedSecurityUser.getAccessToken();
        } else {
            return null;
        }
    }
}
