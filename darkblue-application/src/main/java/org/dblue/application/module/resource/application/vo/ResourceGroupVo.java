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

package org.dblue.application.module.resource.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 资源组
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 17:26:26
 */
@Schema(description = "资源组")
@Data
public class ResourceGroupVo  {
    /**
     * 资源组ID
     */
    @Schema(description = "资源组ID")
    private String resourceGroupId;

    /**
     * 资源组名称
     */
    @Schema(description = "资源组名称")
    private String groupName;

    /**
     * 适用平台(1-PC；2-APP)
     */
    @Schema(description = "适用平台(1-PC；2-APP)")
    private Integer platform;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private Integer sortNum;
}