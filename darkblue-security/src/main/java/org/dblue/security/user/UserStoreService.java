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


import org.dblue.security.token.Tokens;

/**
 * 用户信息缓存
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/3/25 15:55]
 */
public interface UserStoreService {

    /**
     * 保存用户信息到缓存中。
     *
     * @param user   用户信息
     * @param tokens Token信息
     */
    void save(SecurityUser user, Tokens tokens);


    /**
     * 更新用户信息
     *
     * @param securityUser 用户信息
     */
    void update(SecurityUser securityUser);


    /**
     * 删除用户缓存信息
     *
     * @param userId 用户ID
     */
    void remove(String userId);

    /**
     * 刷新用户缓存时间
     *
     * @param userId 用户ID
     */
    void refreshUserCache(String userId);

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    SecurityUser getUser(String userId);

    /**
     * 获取用户对应的AccessToken
     *
     * @param userId 用户ID
     * @return AccessToken
     */
    String getAccessToken(String userId);
}
