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

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.application.module.position.infrastructure.repository.PositionRepository;
import org.dblue.core.caching.AbstractCachingService;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 职位缓存服务
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class PositionCacheServiceImpl extends AbstractCachingService<Position, PositionCacheObject> implements PositionCacheService {

    private final PositionRepository positionRepository;

    @Override
    public Optional<PositionCacheObject> getByPositionCode(String positionCode) {
        return this.getAll().stream().filter(p -> Objects.equals(p.getPositionCode(), positionCode)).findAny();
    }

    @Override
    public List<PositionCacheObject> getByPositionCode(Collection<String> positionCodes) {
        return this.getAll().stream().filter(p -> positionCodes.contains(p.getPositionCode())).toList();
    }

    @Override
    public List<PositionCacheObject> getAllById(Collection<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return List.of();
        }
        Set<String> cacheKeys = ids.stream().map(this::getCacheKey).collect(Collectors.toSet());
        return this.valueOperations.multiGet(cacheKeys);
    }

    @Override
    protected List<Position> getAllEntities() {
        return this.positionRepository.findAll();
    }

    @Override
    protected PositionCacheObject toCacheObject(Position position) {
        return PositionCacheObject.of(position);
    }

    @Override
    protected String getId(Position position) {
        return position.getPositionId();
    }

    @Override
    public String getCacheName() {
        return "职位缓存";
    }

    @Override
    public String getCacheCode() {
        return "Position";
    }
}