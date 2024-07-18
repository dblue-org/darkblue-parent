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
package org.dblue.application.module.user.application.service;

import org.dblue.application.module.user.application.dto.UserPageDto;
import org.dblue.application.module.user.application.vo.UserMenuVo;
import org.dblue.application.module.user.application.vo.UserPageVo;
import org.dblue.application.module.user.application.vo.UserSelectVo;
import org.dblue.application.module.user.application.vo.UserVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 用户应用服务
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface UserApplicationService {

    /**
     * 分页查询
     *
     * @param pageDto 查询参数
     * @return 用户信息
     */
    Page<UserPageVo> page(UserPageDto pageDto);

    /**
     * 单个信息获取
     * @param userId 用户ID
     * @return 单个信息
     */
    UserVo getOne(String userId);

    /**
     * 下拉列表使用
     * @param name 用户名/姓名
     * @return 用户信息
     */
    List<UserSelectVo> selectByNameOrUserName(String name);

    /**
     * 获取用户菜单权限
     *
     * @param platform 平台
     * @return 用户菜单权限
     */
    List<UserMenuVo> getUserMenu(Integer platform);

    /**
     * 用户删除
     *
     * @param userId 用户ID
     */
    void delete(String userId);
}