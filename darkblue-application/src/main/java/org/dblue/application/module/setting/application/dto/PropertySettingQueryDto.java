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
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.setting.application.vo.PropertySettingPageListVo;
import org.dblue.core.web.param.PageParamImpl;


/**
 * 系统参数配置(tb_sys_property_setting)表实体类
 *
 * @author Wang Chengwei
 * @since 1.0.0 2024-07-09 11:40:06
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统参数配置")
@Data
public class PropertySettingQueryDto extends PageParamImpl<PropertySettingPageListVo> {

    /**
     * 参数编码
     */
    @Schema(description = "参数编码")
    private String propertyCode;

    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    private String propertyName;


    /**
     * 参数类型（系统枚举定义）
     */
    @Schema(description = "参数类型（系统枚举定义）")
    private Integer type;

}


