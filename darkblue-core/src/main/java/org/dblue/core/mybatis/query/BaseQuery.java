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
package org.dblue.core.mybatis.query;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 基础查询接口
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface BaseQuery<T> {

    /**
     * 获取所有数据
     *
     * @return 数据列表
     */
    List<T> list();

    /**
     * 分页查询数据
     *
     * @param page     页面
     * @param pageSize 每页数量量
     * @return 数据
     */
    IPage<T> page(long page, long pageSize);

    /**
     * 获取单条数据
     *
     * @return 数据
     */
    T single();
}
