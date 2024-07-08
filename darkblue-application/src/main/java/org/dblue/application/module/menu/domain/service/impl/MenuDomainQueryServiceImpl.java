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

package org.dblue.application.module.menu.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.commons.enums.PlatformEnum;
import org.dblue.application.module.menu.domain.service.MenuDomainQueryService;
import org.dblue.application.module.menu.errors.MenuErrors;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.application.module.menu.infrastructure.repository.MenuRepository;
import org.dblue.common.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 菜单查询领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/17 下午2:18
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MenuDomainQueryServiceImpl implements MenuDomainQueryService {

    private final MenuRepository menuRepository;

    /**
     * 获取一条菜单数据
     *
     * @param menuId 菜单Id
     * @return 菜单
     */
    @Override
    public Menu getOne(String menuId) {

        Optional<Menu> optional = menuRepository.findById(menuId);
        if (optional.isEmpty()) {
            throw new ServiceException(MenuErrors.MENU_IS_NOT_FOUND);
        }
        return optional.get();
    }

    /**
     * 获取所有菜单
     *
     * @param platformEnum 平台
     * @return 菜单列表
     */
    @Override
    public List<Menu> findAllMenus(PlatformEnum platformEnum) {
        return menuRepository.findByPlatformOrderBySortNum(platformEnum.getValue());
    }

    /**
     * 根据角色ID获取菜单信息
     *
     * @param roleIdSet 角色ID
     * @return 菜单信息
     */
    @Override
    public List<Menu> getMenuByRoleId(Set<String> roleIdSet) {
        if (CollectionUtils.isEmpty(roleIdSet)) {
            return List.of();
        }
        return menuRepository.getMenuByRoleId(roleIdSet);
    }
}
