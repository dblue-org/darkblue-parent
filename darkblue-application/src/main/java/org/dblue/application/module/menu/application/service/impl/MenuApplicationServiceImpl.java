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
import org.dblue.application.module.menu.application.vo.MenuTreeVo;
import org.dblue.application.module.menu.domain.service.MenuDomainQueryService;
import org.dblue.application.module.menu.domain.service.MenuDomainService;
import org.dblue.application.module.menu.errors.MenuErrors;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.application.module.menu.infrastructure.repository.MenuRepository;
import org.dblue.application.module.permission.domain.PermissionDomainQueryService;
import org.dblue.application.module.role.domain.service.RoleMenuDomainQueryService;
import org.dblue.application.module.role.infrastructure.entiry.RoleMenu;
import org.dblue.common.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<MenuTreeVo> findAllPcMenus() {
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
    public List<MenuTreeVo> findAllAppMenus() {
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

    private List<MenuTreeVo> asTree(List<Menu> menuList) {

        List<MenuTreeVo> menuTreeVoList = menuList.stream().map(this::build).toList();
        List<MenuTreeVo> roots = menuTreeVoList.stream().filter(o -> Objects.equals(o.getLevel(), 1))
                                               .toList();
        for (MenuTreeVo root : roots) {
            this.setChildren(root, menuTreeVoList);
        }
        return roots;
    }


    private void setChildren(MenuTreeVo menuTreeVo, List<MenuTreeVo> menuTreeVoList) {
        List<MenuTreeVo> children = new ArrayList<>();
        for (MenuTreeVo item : menuTreeVoList) {
            if (Objects.equals(menuTreeVo.getMenuId(), item.getParentId())) {
                children.add(item);
                if (MenuTypeEnum.CATALOGUE.equalsTo(item.getMenuType())) {
                    this.setChildren(item, menuTreeVoList);
                }
            }
        }
        menuTreeVo.setChildren(children);
    }

    public MenuTreeVo build(Menu menu) {
        MenuTreeVo menuTreeVo = new MenuTreeVo();
        BeanUtils.copyProperties(menu, menuTreeVo);
        return menuTreeVo;
    }
}
