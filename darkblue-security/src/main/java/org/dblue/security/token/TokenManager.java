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
 * Token管理
 *
 * @author Wang Chengwei
 * @since 1.0.0 2022/3/17 10:46
 */
public interface TokenManager {

    /**
     * 创建Token信息
     *
     * @param userId 用户ID
     * @return AccessToken and RefreshToken
     * @implNote 如果 {@code rememberMe} 为 {@code true} 则生成RefreshToken，否则不生成。
     */
    Tokens createToken(String userId);

    /**
     * 刷新Token时间
     *
     * @param accessToken AccessToken
     */
    void refreshAccessToken(String accessToken);

    /**
     * 删除AccessToken
     *
     * @param accessToken AccessToken
     */
    void removeByAccessToken(String accessToken);


    /**
     * 获取缓存的Token对应的用户信息
     *
     * @param accessToken Access Token
     * @return 用户信息
     */
    TokenCache getUserByAccessToken(String accessToken);

}
