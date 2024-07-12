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

package org.dblue.application.module.dictionary.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.dblue.application.module.dictionary.enums.DictionaryTypeEnums;
import org.dblue.common.validation.annotation.EnumValues;

/**
 * 数据字典
 *
 * @author xie jin
 * @since 1.0.0  2024-07-12 11:15:06
 */
@Schema(description = "数据字典")
@Data
public class DictionaryDto {

    /**
     * 字典ID
     */
    @Schema(description = "字典ID")
    @Size(max = 64)
    @NotBlank(message = "字典ID不能为空")
    private String dictionaryId;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    @Size(max = 64)
    @NotBlank(message = "字典编码不能为空")
    private String dictionaryCode;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    @Size(max = 64)
    @NotBlank(message = "字典名称不能为空")
    private String dictionaryName;

    /**
     * 字典类型（1-普通字典；2-树形字典）
     */
    @Schema(description = "字典类型（1-普通字典；2-树形字典）")
    @EnumValues(message = "不能为空", clazz = DictionaryTypeEnums.class)
    private Integer dictionaryType;


}