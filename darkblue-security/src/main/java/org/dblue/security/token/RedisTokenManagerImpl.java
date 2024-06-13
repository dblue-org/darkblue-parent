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

package org.dblue.security.token;

import jakarta.annotation.Resource;
import org.dblue.security.properties.SecurityProperties;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Token管理实现
 *
 * @author Wang Chengwei
 * @since 1.0.0 2022/3/17 10:47
 */
@Component
public class RedisTokenManagerImpl implements TokenManager {

    private final SecurityProperties securityProperties;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, TokenCache> valueOperations;

    public RedisTokenManagerImpl(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public Tokens createToken(String userId) {

        AccessToken accessToken = AccessToken.create(this.securityProperties.getAccessTokenTtl());

        TokenCache accessTokenCacheObject = new TokenCache();
        accessTokenCacheObject.setUserId(userId);
        String accessTokenCacheKey = this.getAccessTokenCacheKey(accessToken.getTokenValue());
        this.valueOperations.set(accessTokenCacheKey, accessTokenCacheObject,
                this.securityProperties.getAccessTokenTtl(), TimeUnit.MILLISECONDS);

        Tokens tokens = new Tokens();
        tokens.setAccessToken(accessToken);
        return tokens;
    }

    @Override
    public void refreshAccessToken(String accessToken) {
        String accessTokenCacheKey = this.getAccessTokenCacheKey(accessToken);
        TokenCache accessTokenCacheObject = this.valueOperations.get(accessTokenCacheKey);
        if (accessTokenCacheObject != null) {
            this.valueOperations.getOperations().expire(accessTokenCacheKey,
                    this.securityProperties.getAccessTokenTtl(), TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void removeByAccessToken(String accessToken) {
        String accessTokenCacheKey = this.getAccessTokenCacheKey(accessToken);
        TokenCache accessTokenCacheObject = this.valueOperations.get(accessTokenCacheKey);
        if (accessTokenCacheObject != null) {
            this.valueOperations.getOperations().delete(accessTokenCacheKey);
        }
    }


    @Override
    public TokenCache getUserByAccessToken(String accessToken) {
        String accessTokenCacheKey = this.getAccessTokenCacheKey(accessToken);
        return this.valueOperations.get(accessTokenCacheKey);
    }


    private String getAccessTokenCacheKey(String accessToken) {
        return CacheKeyProvider.getAccessTokenCacheKey(accessToken);
    }

}

