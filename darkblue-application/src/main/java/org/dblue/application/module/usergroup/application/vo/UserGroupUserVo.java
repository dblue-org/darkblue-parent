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

package org.dblue.application.module.usergroup.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.user.application.vo.BaseUserVo;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.springframework.beans.BeanUtils;

/**
 * 用户组用户
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 下午3:11
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户组用户")
@Data
public class UserGroupUserVo extends BaseUserVo {


    /**
     * 用户组用户ID
     */
    @Schema(description = "用户组用户ID")
    private String userGroupUserId;

    /**
     * 是否可用
     */
    @Schema(description = "是否可用")
    private Boolean isEnable;

    public static UserGroupUserVo of(User user) {
        UserGroupUserVo userGroupUserVo = new UserGroupUserVo();
        BeanUtils.copyProperties(user, userGroupUserVo);
        return userGroupUserVo;
    }
}
