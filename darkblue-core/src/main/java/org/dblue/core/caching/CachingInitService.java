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
package org.dblue.core.caching;

/**
 * 缓存服务
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface CachingInitService {


    /**
     * 获取缓存名称
     *
     * @return 缓存名称
     */
    String getCacheName();

    /**
     * 获取缓存数量
     *
     * @return 缓存数量
     */
    Long getKeyNums();

    /**
     * 初始化缓存
     */
    void init();

    /**
     * 刷新缓存
     */
    void refresh();

    /**
     * 获取缓存Key的前缀
     *
     * @return 缓存Key前缀
     */
    default String getCachePrefix() {
        return "Public:" + this.getCacheCode();
    }

    /**
     * 获取缓存编码
     *
     * @return 缓存编码
     */
    String getCacheCode();

}
