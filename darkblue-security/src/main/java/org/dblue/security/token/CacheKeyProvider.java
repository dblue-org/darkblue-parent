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

/**
 * 用于生成Token及用户相关缓存的key
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class CacheKeyProvider {
    private static final String COMMON_CACHE_NS = "Security:";

    /**
     * 创建一个缓存Key生成器
     */
    private CacheKeyProvider() {
    }

    /**
     * 获取 Access Token 缓存key
     *
     * @param accessToken Access Token
     * @return 缓存key
     */
    public static String getAccessTokenCacheKey(String accessToken) {
        return COMMON_CACHE_NS + "AccessToken:" + accessToken;
    }

    /**
     * 获取 用户 缓存key
     *
     * @param userId 用户ID
     * @return 缓存key
     */
    public static String getUserCacheKey(String userId) {
        return COMMON_CACHE_NS + "User:" + userId;
    }
}
