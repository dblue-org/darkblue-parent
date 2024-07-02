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

package org.dblue.application.module.menu.domain.service;

import org.dblue.application.module.menu.application.dto.MenuAddDto;
import org.dblue.application.module.menu.application.dto.MenuEnableDto;
import org.dblue.application.module.menu.application.dto.MenuUpdateDto;

/**
 * 菜单领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/14 下午3:01
 */
public interface MenuDomainService {


    /**
     * 菜单添加
     *
     * @param menuAddDto 菜单信息
     */
    void add(MenuAddDto menuAddDto);


    /**
     * 菜单更新
     *
     * @param menuUpdateDto 菜单信息
     */
    void update(MenuUpdateDto menuUpdateDto);


    /**
     * 菜单删除
     * @param menuId 菜单ID
     */
    void delete(String menuId);

    /**
     * 菜单启用禁用
     * @param menuEnableDto 菜单信息
     */
    void enable(MenuEnableDto menuEnableDto);


}
