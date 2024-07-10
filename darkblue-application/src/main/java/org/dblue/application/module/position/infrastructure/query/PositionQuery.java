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
package org.dblue.application.module.position.infrastructure.query;

import org.dblue.application.commons.db.jpa.BaseJpaQuery;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.utils.date.LocalDateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface PositionQuery extends BaseJpaQuery<Position> {

    PositionQuery positionId(String positionId);


    PositionQuery positionIdIn(Collection<String> positionIdList);

    PositionQuery positionCode(String positionCode);

    PositionQuery positionCodeLike(String positionCode);

    PositionQuery positionName(String positionName);

    PositionQuery positionNameLike(String positionName);

    default PositionQuery createTimeBetween(LocalDate start, LocalDate end) {
        LocalDateTime startDateTime = start != null ? start.atStartOfDay() : null;
        LocalDateTime endDateTime = end != null ? LocalDateUtils.maxOfDay(end) : null;
        return createTimeBetween(startDateTime, endDateTime);
    }

    PositionQuery createTimeBetween(LocalDateTime start, LocalDateTime end);

    default Map<String, String> nameMap() {
        List<Position> positionList = this.list();
        return positionList.stream().collect(Collectors.toMap(Position::getPositionId, Position::getPositionName));
    }

}
