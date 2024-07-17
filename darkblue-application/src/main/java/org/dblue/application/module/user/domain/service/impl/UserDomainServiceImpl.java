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

package org.dblue.application.module.user.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.commons.bus.EventBus;
import org.dblue.application.module.user.application.dto.UserAddDto;
import org.dblue.application.module.user.application.dto.UserDto;
import org.dblue.application.module.user.application.dto.UserEnableDto;
import org.dblue.application.module.user.application.dto.UserUpdateDto;
import org.dblue.application.module.user.domain.event.UserAddEvent;
import org.dblue.application.module.user.domain.event.UserDeleteEvent;
import org.dblue.application.module.user.domain.event.UserUpdateEvent;
import org.dblue.application.module.user.domain.service.UserDomainService;
import org.dblue.application.module.user.errors.UserErrors;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.dblue.application.module.user.infrastructure.entity.UserRole;
import org.dblue.application.module.user.infrastructure.repository.UserRepository;
import org.dblue.application.module.user.infrastructure.repository.UserRoleRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * 用户领域服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/21 下午4:05
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final EventBus eventBus;

    /**
     * 用户添加
     *
     * @param addDto 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserAddDto addDto) {
        User user = userRepository.findByUsername(addDto.getUsername());
        if (Objects.nonNull(user)) {
            throw new ServiceException(UserErrors.USERNAME_EXISTS);
        }
        User userSave = new User();
        BeanUtils.copyProperties(addDto, userSave);
        userSave.setUserId(Snowflake.stringId());
        userSave.setIsDel(Boolean.FALSE);
        userSave.setIsEnable(Boolean.TRUE);
        userSave.setIsAdmin(Boolean.FALSE);
        userRepository.save(userSave);
        saveUserRole(addDto, userSave);

        this.eventBus.fireEventAfterCommit(new UserAddEvent(this, userSave));
    }

    private void saveUserRole(UserDto addDto, User userSave) {
        if (CollectionUtils.isNotEmpty(addDto.getRoles())) {
            for (String roleId : addDto.getRoles()) {
                UserRole userRole = new UserRole();
                userRole.setUserRoleId(Snowflake.stringId());
                userRole.setUserId(userSave.getUserId());
                userRole.setRoleId(roleId);
                userRoleRepository.save(userRole);
            }
        }
    }

    /**
     * 用户更新
     *
     * @param updateDto 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UserUpdateDto updateDto) {
        Optional<User> optional = userRepository.findByUserIdAndIsDelFalse(updateDto.getUserId());
        if (optional.isEmpty()) {
            throw new ServiceException(UserErrors.USER_NOT_FOUND);
        }
        Optional<User> existsOptional = userRepository.findByUsernameAndUserIdNotAndIsDelIsFalse(updateDto.getUsername(), updateDto.getUserId());
        if (existsOptional.isPresent()) {
            throw new ServiceException(UserErrors.USERNAME_EXISTS);
        }
        BeanUtils.copyProperties(updateDto, optional.get());
        userRepository.save(optional.get());
        userRoleRepository.deleteByUserId(updateDto.getUserId());
        saveUserRole(updateDto, optional.get());

        this.eventBus.fireEventAfterCommit(new UserUpdateEvent(this, optional.get()));

    }

    /**
     * 用户删除
     *
     * @param userId 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isEmpty()) {
            throw new ServiceException(UserErrors.USER_NOT_FOUND);
        }
        optional.get().setIsDel(Boolean.TRUE);
        userRepository.save(optional.get());
        userRoleRepository.deleteByUserId(userId);

        this.eventBus.fireEventAfterCommit(new UserDeleteEvent(this, optional.get()));
    }

    /**
     * 用户启用/禁用
     *
     * @param enableDto 启用/禁用信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enable(UserEnableDto enableDto) {
        Optional<User> optional = userRepository.findByUserIdAndIsDelFalse(enableDto.getUserId());
        if (optional.isEmpty()) {
            throw new ServiceException(UserErrors.USER_NOT_FOUND);
        }
        optional.get().setIsEnable(enableDto.getEnable());
        userRepository.save(optional.get());

        this.eventBus.fireEventAfterCommit(new UserUpdateEvent(this, optional.get()));
    }


}
