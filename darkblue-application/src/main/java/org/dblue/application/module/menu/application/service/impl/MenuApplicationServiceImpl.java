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

package org.dblue.application.module.menu.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.commons.enums.MenuTypeEnum;
import org.dblue.application.commons.enums.PlatformEnum;
import org.dblue.application.module.menu.application.service.MenuApplicationService;
import org.dblue.application.module.menu.application.vo.MenuVo;
import org.dblue.application.module.menu.domain.service.MenuDomainQueryService;
import org.dblue.application.module.menu.domain.service.MenuDomainService;
import org.dblue.application.module.menu.errors.MenuErrors;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.application.module.menu.infrastructure.repository.MenuRepository;
import org.dblue.application.module.permission.domain.PermissionDomainQueryService;
import org.dblue.application.module.role.infrastructure.domain.service.RoleMenuDomainQueryService;
import org.dblue.application.module.role.infrastructure.entiry.RoleMenu;
import org.dblue.common.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/17 上午11:29
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MenuApplicationServiceImpl implements MenuApplicationService {

    private final MenuDomainService menuDomainService;
    private final RoleMenuDomainQueryService roleMenuDomainQueryService;
    private final MenuDomainQueryService menuDomainQueryService;
    private final MenuRepository menuRepository;
    private final PermissionDomainQueryService permissionDomainQueryService;

    /**
     * 菜单删除
     *
     * @param menuId 菜单ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String menuId) {
        Menu menu = menuDomainQueryService.getOne(menuId);
        deleteCheck(menuId, menu);
        List<RoleMenu> roleMenuList = roleMenuDomainQueryService.getRoleMenuByMenuId(menuId);
        if (CollectionUtils.isNotEmpty(roleMenuList)) {
            throw new ServiceException(MenuErrors.MENU_ROLE_EXITS);
        }
        menuDomainService.delete(menuId);
    }

    /**
     * 获取所有PC端菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<MenuVo> findAllPcMenus() {
        List<Menu> menuList = menuDomainQueryService.findAllMenus(PlatformEnum.PC);
        if (CollectionUtils.isEmpty(menuList)) {
            return List.of();
        }
        return asTree(menuList);
    }

    /**
     * 获取所有APP端菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<MenuVo> findAllAppMenus() {
        List<Menu> menuList = menuDomainQueryService.findAllMenus(PlatformEnum.APP);
        if (CollectionUtils.isEmpty(menuList)) {
            return List.of();
        }
        return asTree(menuList);
    }

    private void deleteCheck(String menuId, Menu menu) {
        if (MenuTypeEnum.CATALOGUE.getValue() == menu.getMenuType()) {
            long count = menuRepository.countByParentId(menuId);
            if (count > 0) {
                throw new ServiceException(MenuErrors.NOT_ALLOWED_DELETE_HAS_SUB_MENUS);
            }
        } else {
            long count = permissionDomainQueryService.countByMenuId(menuId);
            if (count > 0) {
                throw new ServiceException(MenuErrors.NOT_ALLOWED_DELETE_HAS_PERMISSIONS);
            }
        }
    }

    private List<MenuVo> asTree(List<Menu> menuList) {

        List<MenuVo> menuVoList = menuList.stream().map(this::build).collect(Collectors.toList());
        List<MenuVo> roots = menuVoList.stream().filter(o -> Objects.equals(o.getLevel(), 1))
                                       .collect(Collectors.toList());
        for (MenuVo root : roots) {
            this.setChildren(root, menuVoList);
        }
        return roots;
    }


    private void setChildren(MenuVo menuVo, List<MenuVo> menuVoList) {
        List<MenuVo> children = new ArrayList<>();
        for (MenuVo item : menuVoList) {
            if (Objects.equals(menuVo.getMenuId(), item.getParentId())) {
                children.add(item);
                if (MenuTypeEnum.CATALOGUE.equalsTo(item.getMenuType())) {
                    this.setChildren(item, menuVoList);
                }
            }
        }
        menuVo.setChildren(children);
    }

    public MenuVo build(Menu menu) {
        MenuVo menuVo = new MenuVo();
        BeanUtils.copyProperties(menu, menuVo);
        return menuVo;
    }
}
