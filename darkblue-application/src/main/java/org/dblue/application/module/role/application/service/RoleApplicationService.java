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

package org.dblue.application.module.role.application.service;

import org.dblue.application.module.role.application.dto.RolePageDto;
import org.dblue.application.module.role.application.dto.RoleUserQueryDto;
import org.dblue.application.module.role.application.vo.RolePageVo;
import org.dblue.application.module.role.application.vo.RoleUserVo;
import org.dblue.application.module.role.application.vo.RoleVo;
import org.dblue.application.module.role.application.vo.SimpleRoleVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 角色应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/18 下午3:23
 */
public interface RoleApplicationService {

    /**
     * 角色删除
     * @param roleId 角色ID
     */
    void delete(String roleId);


    /**
     * 分页查询角色信息
     *
     * @param query 查询条件
     * @return 角色列表
     */
    Page<RolePageVo> findByPage(RolePageDto query);

    /**
     * 获取单个角色信息
     * @param roleId 角色ID
     * @return 角色
     */
    RoleVo getOne(String roleId);


    /**
     * 分页查询角色关联的用户
     *
     * @param queryDto 查询条件
     * @return 用户列表
     */
    Page<RoleUserVo> findRefUsers(RoleUserQueryDto queryDto);

    /**
     * 获取全部角色信息
     *
     * @return 角色信息
     */
    List<SimpleRoleVo> getAllForSelect();

}
