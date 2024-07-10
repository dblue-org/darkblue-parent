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

package org.dblue.application.module.setting.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 系统参数配置(tb_sys_property_setting)表实体类
 *
 * @author Wang Chengwei
 * @since 1.0.0 2024-07-09 11:40:06
 */
@Schema(description = "系统参数配置")
@Data
public class PropertySettingChangeValueDto {

    /**
     * 参数ID
     */
    @NotBlank(message = "参数ID不能为空")
    @Schema(description = "参数ID")
    private String propertyId;

    /**
     * 参数值
     */
    @NotBlank(message = "参数值不能为空")
    @Schema(description = "参数值")
    private String value;

}


