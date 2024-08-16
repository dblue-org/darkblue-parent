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
package org.dblue.core.caching.vo;

import lombok.Data;
import org.dblue.core.caching.CachingInitService;

/**
 * 缓存信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
public class CachingVo {

    /**
     * 缓存编码
     */
    private String cacheCode;

    /**
     * 缓存名称
     */
    private String cacheName;

    /**
     * 缓存Key前缀
     */
    private String keyPrefix;

    /**
     * 缓存数量
     */
    private Long keyNum = 0L;

    /**
     * 从缓存服务中获取缓存信息
     *
     * @param cachingInitService 缓存服务
     * @return 缓存信息
     */
    public static CachingVo toCachingVo(CachingInitService cachingInitService) {
        CachingVo cachingVo = new CachingVo();
        cachingVo.setCacheCode(cachingInitService.getCacheCode());
        cachingVo.setCacheName(cachingInitService.getCacheName());
        cachingVo.setKeyPrefix(cachingInitService.getCachePrefix());
        cachingVo.setKeyNum(cachingInitService.getKeyNums());
        return cachingVo;
    }
}