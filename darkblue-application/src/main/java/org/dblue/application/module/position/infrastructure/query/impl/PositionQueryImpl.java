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
package org.dblue.application.module.position.infrastructure.query.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.commons.db.jpa.AbstractBaseJpaQuery;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.application.module.position.infrastructure.entity.QPosition;
import org.dblue.application.module.position.infrastructure.query.PositionQuery;
import org.dblue.application.module.position.infrastructure.repository.PositionRepository;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class PositionQueryImpl extends AbstractBaseJpaQuery<Position, String> implements PositionQuery {

    public PositionQueryImpl(PositionRepository positionRepository) {
        super(positionRepository);
        this.queryBuilder.and(QPosition.position.isDel.eq(false));
    }

    @Override
    public PositionQuery positionId(String positionId) {
        if (StringUtils.isNotBlank(positionId)) {
            this.queryBuilder.and(QPosition.position.positionId.eq(positionId));
        }
        return this;
    }

    @Override
    public PositionQuery positionIdIn(Collection<String> positionIdList) {
        if (CollectionUtils.isNotEmpty(positionIdList)) {
            this.queryBuilder.and(QPosition.position.positionId.in(positionIdList));
        }
        return this;
    }

    @Override
    public PositionQuery positionCode(String positionCode) {
        if (StringUtils.isNotBlank(positionCode)) {
            this.queryBuilder.and(QPosition.position.positionCode.eq(positionCode));
        }
        return this;
    }

    @Override
    public PositionQuery positionCodeLike(String positionCode) {
        if (StringUtils.isNotBlank(positionCode)) {
            this.queryBuilder.and(QPosition.position.positionCode.contains(positionCode));
        }
        return this;
    }

    @Override
    public PositionQuery positionName(String positionName) {
        if (StringUtils.isNotBlank(positionName)) {
            this.queryBuilder.and(QPosition.position.positionName.eq(positionName));
        }
        return this;
    }

    @Override
    public PositionQuery positionNameLike(String positionName) {
        if (StringUtils.isNotBlank(positionName)) {
            this.queryBuilder.and(QPosition.position.positionName.like(positionName));
        }
        return this;
    }

    @Override
    public PositionQuery createTimeBetween(LocalDateTime start, LocalDateTime end) {
        if (start != null) {
            this.queryBuilder.and(QPosition.position.createTime.goe(start));
        }
        if (end != null) {
            this.queryBuilder.and(QPosition.position.createTime.loe(end));
        }
        return null;
    }

}