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

import lombok.Data;
import org.dblue.common.id.RandomUtils;


/**
 * 访问Token
 *
 * @author Wang Chengwei
 * @since 1.0.0 2022/3/17 10:35
 */
@Data
public class AccessToken {


    /**
     * Token的值
     */
    private String tokenValue;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 创建Access Token
     *
     * @param ttl 过期时间
     * @return Access Token
     */
    public static AccessToken create(Long ttl) {
        AccessToken accessToken = new AccessToken();
        accessToken.setTokenValue(RandomUtils.nextLong().toString());
        accessToken.setCreateTime(System.currentTimeMillis());
        accessToken.setExpireTime(accessToken.getCreateTime() + ttl);
        return accessToken;
    }
}
