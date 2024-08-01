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

import lombok.Data;
import org.dblue.application.module.position.infrastructure.entity.Position;

/**
 * 职位缓存数据
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
public class PositionCacheObject {
    /**
     * 职位ID
     */
    private String positionId;

    /**
     * 职位编码
     */
    private String positionCode;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 是否启用
     */
    private Boolean isEnable;

    /**
     * 是否内置
     */
    private Boolean isBuiltIn;

    public static PositionCacheObject of(Position position) {
        PositionCacheObject positionCacheObject = new PositionCacheObject();
        positionCacheObject.setPositionId(position.getPositionId());
        positionCacheObject.setPositionCode(position.getPositionCode());
        positionCacheObject.setPositionName(position.getPositionName());
        positionCacheObject.setIsEnable(position.getIsEnable());
        positionCacheObject.setIsBuiltIn(position.getIsBuiltIn());
        return positionCacheObject;
    }
}