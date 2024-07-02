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

package org.dblue.application.module.user.infrastructure.repository;

import org.dblue.application.module.user.infrastructure.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * @author xie jin
 */
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    /**
     * 检测关系是否存在
     *
     * @param roleId 角色ID
     * @return 是否存在
     */
    boolean existsByRoleId(@NonNull String roleId);


    /**
     * 获取用户角色信息
     * @param roleIds 角色ID集合
     * @return 是否存在
     */
    List<UserRole> findByRoleIdIn(@NonNull Collection<String> roleIds);

    /**
     * 通过用户ID删除
     * @param userId 用户ID
     */
    void deleteByUserId(@NonNull String userId);


}