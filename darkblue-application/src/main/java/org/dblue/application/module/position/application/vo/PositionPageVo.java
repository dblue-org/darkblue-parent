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
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 分页查询结果
 *
 * @author xie jin
 * @since 1.0.0  2024/7/9 上午10:24
 */
@Schema(description = "分页查询结果")
@Data
public class PositionPageVo {

    /**
     * 职位ID
     */
    @Schema(description = "职位ID")
    @Id
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

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean isEnable;

    /**
     * 是否内置
     */
    @Schema(description = "是否内置")
    private Boolean isBuiltIn;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 用户数量
     */
    @Schema(description = "用户数量")
    private Integer userNums = 0;

}
