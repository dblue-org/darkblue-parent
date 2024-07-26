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
package org.dblue.application.module.user.domain.service;

import org.dblue.application.module.user.application.dto.UserAddDto;
import org.dblue.application.module.user.application.dto.UserEnableDto;
import org.dblue.application.module.user.application.dto.UserUpdateDto;
import org.dblue.application.module.user.infrastructure.entity.User;

/**
 * 用户领域服务
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface UserDomainService {

    /**
     * 用户添加
     *
     * @param addDto 用户信息
     */
    void add(UserAddDto addDto);

    /**
     * 用户更新
     *
     * @param updateDto 用户信息
     */
    void update(UserUpdateDto updateDto);

    /**
     * 用户删除
     *
     * @param userId 用户ID
     */
    void delete(String userId);

    /**
     * 用户启用/禁用
     *
     * @param enableDto 启用/禁用信息
     */
    void enable(UserEnableDto enableDto);

    /**
     * 修改密码
     *
     * @param user   用户
     * @param password 密码
     */
    void changePassword(User user, String password);
}
