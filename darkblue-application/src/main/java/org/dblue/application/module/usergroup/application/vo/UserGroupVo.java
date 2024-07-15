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
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroup;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * 用户组
 *
 * @author xie jin
 * @since 1.0.0  2024-07-10 14:55:43
 */
@Schema(description = "用户组")
@Data
public class UserGroupVo {
    /**
     * 用户组ID
     */
    @Schema(description = "用户组ID")
    private String userGroupId;

    /**
     * 用户组名称
     */
    @Schema(description = "用户组名称")
    private String userGroupName;

    /**
     * 是否可用
     */
    @Schema(description = "是否可用")
    private Boolean isEnable;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;


    public static UserGroupVo of(UserGroup userGroup) {
        UserGroupVo userGroupVo = new UserGroupVo();
        BeanUtils.copyProperties(userGroup, userGroupVo);
        return userGroupVo;
    }

}