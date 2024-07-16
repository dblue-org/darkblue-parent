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
import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 字典项
 *
 * @author xie jin
 * @since 1.0.0  2024/7/12 下午2:53
 */
@Schema(description = "字典项")
@Data
public class DictionaryItemNodeForSelectVo {

    /**
     * 字典项目ID
     */
    @Schema(description = "字典项目ID")
    private String dictionaryItemId;

    /**
     * 编码
     */
    @Schema(description = "编码")
    private Integer code;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extension;

    /**
     * 级别
     */
    @Schema(description = "级别")
    private Integer itemLevel;

    /**
     * 子节点
     */
    @Schema(description = "子节点")
    private List<DictionaryItemNodeForSelectVo> children;

    public static DictionaryItemNodeForSelectVo of(DictionaryItem dictionaryItem) {
        DictionaryItemNodeForSelectVo selectVo = new DictionaryItemNodeForSelectVo();
        BeanUtils.copyProperties(dictionaryItem, selectVo);
        return selectVo;
    }
}
