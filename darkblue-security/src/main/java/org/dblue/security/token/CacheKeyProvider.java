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
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 11:34]
 */
public class CacheKeyProvider {
    private static final String COMMON_CACHE_NS = "Security:";

    private CacheKeyProvider() {
    }

    public static String getAccessTokenCacheKey(String accessToken) {
        return COMMON_CACHE_NS + "AccessToken:" + accessToken;
    }

    public static String getUserCacheKey(String userId) {
        return COMMON_CACHE_NS + "User:" + userId;
    }
}
