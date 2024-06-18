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

import java.util.Map;
import java.util.Set;

/**
 * 用户角色领域查询服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/18 下午3:27
 */
public interface UserRoleDomainService {

    /**
     * 根据角色ID查询用户角色数据是否存在
     * @param roleId  角色ID
     * @return 是否存在
     */
    boolean existsUserRoleByRoleId(String roleId);

    /**
     * 批量获取使用角色的用户数量
     * @param roleIdSet 角色ID集合
     * @return key:roleId  value:nums
     */
    Map<String,Long> getUserRoleNum(Set<String> roleIdSet);
}
