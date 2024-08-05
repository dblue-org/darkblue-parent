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
package org.dblue.security.converter;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合多种获取AccessToken的方法
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class CompositeAccessTokenFinder implements AccessTokenFinder {

    /**
     * AccessToken 获取策略默认实例
     */
    public static final CompositeAccessTokenFinder DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new CompositeAccessTokenFinder(
                List.of(
                        new HttpHeaderAccessTokenFinder(),
                        new HttpRequestParamAccessTokenFinder()
                )
        );
    }

    /**
     * AccessToken 获取策略集合
     */
    private final List<AccessTokenFinder> finderList = new ArrayList<>();

    /**
     * 创建一个Access Token 获取策略实例
     */
    public CompositeAccessTokenFinder() {

    }

    /**
     * 创建一个Access Token 获取策略实例
     *
     * @param finderList AccessToken 获取策略集合
     */
    public CompositeAccessTokenFinder(List<AccessTokenFinder> finderList) {
        this.finderList.addAll(finderList);
    }

    /**
     * 添加一个 AccessToken 获取策略
     * @param accessTokenFinder AccessToken 获取策略
     */
    public void registerAccessTokenFinder(AccessTokenFinder accessTokenFinder) {
        finderList.add(accessTokenFinder);
    }

    @Override
    public String getAccessToken(HttpServletRequest request) {
        for (AccessTokenFinder accessTokenFinder : finderList) {
            String accessToken = accessTokenFinder.getAccessToken(request);
            if (StringUtils.isNotBlank(accessToken)) {
                return accessToken;
            }
        }
        return "";
    }
}