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
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.commons.enums.MenuTypeEnum;
import org.dblue.application.module.menu.application.dto.MenuAddDto;
import org.dblue.application.module.menu.application.dto.MenuDto;
import org.dblue.application.module.menu.application.dto.MenuEnableDto;
import org.dblue.application.module.menu.application.dto.MenuUpdateDto;
import org.dblue.application.module.menu.domain.service.MenuDomainService;
import org.dblue.application.module.menu.errors.MenuErrors;
import org.dblue.application.module.menu.infrastructure.entity.Menu;
import org.dblue.application.module.menu.infrastructure.repository.MenuRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * 菜单领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/14 下午3:02
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MenuDomainServiceImpl implements MenuDomainService {

    private final MenuRepository menuRepository;

    /**
     * 菜单添加
     *
     * @param menuAddDto 菜单信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(MenuAddDto menuAddDto) {
        checkMenuUrl(menuAddDto);
        Optional<Menu> optionalMenu = menuRepository.findByPlatformAndMenuName(menuAddDto.getPlatform(), menuAddDto.getMenuName());
        if (optionalMenu.isPresent()) {
            throw new ServiceException(MenuErrors.MENU_EXITS);
        }

        Menu menu = new Menu();
        menu.setMenuId(Snowflake.stringId());
        BeanUtils.copyProperties(menuAddDto, menu);
        setParentInfo(menu, menuAddDto.getParentId());
        menu.setIsEnable(Boolean.TRUE);
        menuRepository.save(menu);
    }

    /**
     * 菜单更新
     *
     * @param menuUpdateDto 菜单信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(MenuUpdateDto menuUpdateDto) {
        checkMenuUrl(menuUpdateDto);
        Optional<Menu> optional = menuRepository.findById(menuUpdateDto.getMenuId());
        if (optional.isEmpty()) {
            throw new ServiceException(MenuErrors.MENU_IS_NOT_FOUND);
        }
        Optional<Menu> optionalMenu = menuRepository.findByPlatformAndMenuNameAndMenuIdNot(optional.get().getPlatform(), menuUpdateDto.getMenuName(),
                menuUpdateDto.getMenuId());
        if (optionalMenu.isPresent()) {
            throw new ServiceException(MenuErrors.MENU_EXITS);
        }
        BeanUtils.copyProperties(menuUpdateDto, optional.get());
        menuRepository.save(optional.get());
    }

    /**
     * 菜单删除
     *
     * @param menuId 菜单ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String menuId) {
        Optional<Menu> optional = menuRepository.findById(menuId);
        if (optional.isEmpty()) {
            throw new ServiceException(MenuErrors.MENU_IS_NOT_FOUND);
        }
        menuRepository.deleteById(menuId);
    }

    /**
     * 菜单启用禁用
     *
     * @param menuEnableDto 菜单信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enable(MenuEnableDto menuEnableDto) {
        Optional<Menu> optional = menuRepository.findById(menuEnableDto.getMenuId());
        if (optional.isEmpty()) {
            throw new ServiceException(MenuErrors.MENU_IS_NOT_FOUND);
        }
        optional.get().setIsEnable(menuEnableDto.getEnable());
        menuRepository.save(optional.get());
    }

    private void checkMenuUrl(MenuDto menuDto) {
        if (MenuTypeEnum.MENU.equalsTo(menuDto.getMenuType()) && StringUtils.isBlank(menuDto.getMenuUrl())) {
            throw new ServiceException(MenuErrors.MENU_URL_NOT_BLANK);
        }
    }

    private void setParentInfo(Menu menu, String parentId) {
        if (StringUtils.isNotBlank(parentId)) {
            Optional<Menu> optionalMenu = menuRepository.findById(parentId);
            if (optionalMenu.isEmpty()) {
                throw new ServiceException(MenuErrors.PARENT_MENU_NOT_EXIST);
            }
            Menu parentMenu = optionalMenu.get();
            if (MenuTypeEnum.MENU.equalsTo(parentMenu.getMenuType())) {
                throw new ServiceException(MenuErrors.NOT_ALLOW_TO_ADD_SUB_MENU);
            }
            if (!Objects.equals(menu.getPlatform(), parentMenu.getPlatform())) {
                throw new ServiceException(MenuErrors.PLATFORM_MUST_SAME);
            }
            menu.setLevel(parentMenu.getLevel() + 1);
        } else {
            menu.setLevel(1);
            menu.setParentId(null);
        }
    }
}
