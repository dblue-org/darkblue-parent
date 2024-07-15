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

package org.dblue.application.module.menu.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 菜单多选框树
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 上午10:48
 */
@Schema(description = "菜单多选框树")
@Data
public class MenuCheckBoxTreeVo {


    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private String menuId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;


    /**
     * 菜单类型(1-目录;2-菜单)
     */
    @Schema(description = "菜单类型(1-目录;2-菜单)")
    private Integer menuType;


    /**
     * 层级
     */
    @Schema(description = "层级")
    private Integer level;


    /**
     * 是否选中
     */
    @Schema(description = "是否选中")
    private Boolean checked = false;


    /**
     * 显示顺序
     */
    private Integer sortNum;


    /**
     * 子节点
     */
    @Schema(description = "子节点")
    private List<MenuCheckBoxTreeVo> children;
}
