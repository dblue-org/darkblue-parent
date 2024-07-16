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

import java.util.List;

/**
 * 字典项分页
 *
 * @author xie jin
 * @since 1.0.0  2024/7/12 下午2:53
 */
@Schema(description = "字典项分页")
@Data
public class DictionaryItemTreeVo {

    /**
     * 字典项目ID
     */
    @Schema(description = "字典项目ID")
    private String dictionaryItemId;

    /**
     * 字典项识别码
     */
    @Schema(description = "字典项识别码")
    private String dictionaryItemCode;

    /**
     * 字典ID
     */
    @Schema(description = "字典ID")
    private String dictionaryId;

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
     * 顺序
     */
    @Schema(description = "顺序")
    private Integer orderNum;

    /**
     * 级别
     */
    @Schema(description = "级别")
    private Integer itemLevel;

    /**
     * 字典项状态
     */
    @Schema(description = "字典项状态")
    private Boolean isEnable;

    /**
     * 子节点
     */
    @Schema(description = "子节点")
    private List<DictionaryItemTreeVo> children;
}
