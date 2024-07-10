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

package org.dblue.application.module.usergroup.application.service;

import org.dblue.application.module.usergroup.application.dto.UserGroupPageDto;
import org.dblue.application.module.usergroup.application.vo.UserGroupPageVo;
import org.dblue.application.module.usergroup.application.vo.UserGroupVo;
import org.springframework.data.domain.Page;

/**
 * 用户组应用层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 下午4:03
 */
public interface UserGroupApplicationService {

    /**
     * 获取单独一个
     *
     * @param userGroupId 用户组ID
     * @return 用户组信息
     */
    UserGroupVo getOne(String userGroupId);

    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 分组
     */
    Page<UserGroupPageVo> page(UserGroupPageDto pageDto);

}
