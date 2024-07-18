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

package org.dblue.application.module.user.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.menu.application.vo.BaseMenuTreeNodeVo;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.springframework.beans.BeanUtils;

/**
 * 用户拥有菜单权限
 *
 * @author xie jin
 * @since 1.0.0  2024/7/8 上午10:47
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户拥有菜单权限")
@Data
public class UserMenuVo extends BaseMenuTreeNodeVo {


    /**
     * 菜单类型(1-目录;2-菜单)
     */
    @Schema(description = "菜单类型(1-目录;2-菜单)")
    private Integer menuType;


    /**
     * 菜单url
     */
    @Schema(description = "菜单url")
    private String menuUrl;

    /**
     * 菜单层级
     */
    @Schema(description = "菜单层级")
    private Integer level;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sortNum;

    /**
     * 菜单图标
     */

    @Schema(description = "菜单图标")
    private String menuIcon;

    public static UserMenuVo of(Menu menu) {
        UserMenuVo userMenuVo = new UserMenuVo();
        BeanUtils.copyProperties(menu, userMenuVo);
        return userMenuVo;
    }

}
