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
import lombok.EqualsAndHashCode;

/**
 * 数据字典条目
 *
 * @author xie jin
 * @since 1.0.0  2024-07-12 11:15:19
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "数据字典条目")
@Data
public class DictionaryItemAddDto extends DictionaryItemDto {


    /**
     * 字典ID
     */
    @Schema(description = "字典ID")
    @Size(max = 64)
    @NotBlank(message = "字典ID不能为空")
    private String dictionaryId;


    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    @Size(max = 64)
    private String parentId;


}