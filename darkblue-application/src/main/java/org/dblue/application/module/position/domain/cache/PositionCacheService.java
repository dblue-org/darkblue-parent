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
package org.dblue.application.module.position.domain.cache;

import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.core.caching.CachingService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 职位缓存处理业务
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface PositionCacheService extends CachingService<Position, PositionCacheObject> {

    /**
     * 根据职位编码获取职位信息
     *
     * @param positionCode 职位编码
     * @return 职位信息
     */
    @SuppressWarnings("unused")
    Optional<PositionCacheObject> getByPositionCode(String positionCode);

    /**
     * 根据职位编码获取职位信息（批量）
     *
     * @param positionCodes 职位编码
     * @return 职位信息列表
     */
    @SuppressWarnings("unused")
    List<PositionCacheObject> getByPositionCode(Collection<String> positionCodes);
}
