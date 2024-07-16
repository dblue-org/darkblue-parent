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
package org.dblue.application.module.dictionary.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dblue.application.module.dictionary.infrastructure.entity.Dictionary;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 字典信息（Select组件使用）
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "字典信息（Select组件使用）")
@Data
public class DictionaryForSelectVo {
    /**
     * 字典ID
     */
    @Schema(description = "字典ID")
    private String dictionaryId;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    private String dictionaryCode;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictionaryName;

    /**
     * 字典类型（1-普通字典；2-树形字典）
     */
    @Schema(description = "字典类型（1-普通字典；2-树形字典）")
    private Integer dictionaryType;

    /**
     * 字典项（列表或树）
     */
    @Schema(description = "字典项（列表或树）")
    private List<DictionaryItemNodeForSelectVo> items;

    public static DictionaryForSelectVo of(Dictionary dictionary) {
        DictionaryForSelectVo dictionaryForSelectVo = new DictionaryForSelectVo();
        BeanUtils.copyProperties(dictionary, dictionaryForSelectVo);
        return dictionaryForSelectVo;
    }
}