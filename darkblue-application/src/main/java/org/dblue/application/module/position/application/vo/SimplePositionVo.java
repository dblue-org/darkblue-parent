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

package org.dblue.application.module.position.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.springframework.beans.BeanUtils;

/**
 * 职位
 *
 * @author xie jin
 * @since 1.0.0  2024-07-09 15:32:38
 */
@Schema(description = "职位")
@Data
public class SimplePositionVo {
    /**
     * 职位ID
     */
    @Schema(description = "职位ID")
    private String positionId;

    /**
     * 职位编码
     */
    @Schema(description = "职位编码")
    private String positionCode;

    /**
     * 职位名称
     */
    @Schema(description = "职位名称")
    private String positionName;


    public static SimplePositionVo of(Position position) {
        SimplePositionVo vo = new SimplePositionVo();
        BeanUtils.copyProperties(position, vo);
        return vo;
    }

}