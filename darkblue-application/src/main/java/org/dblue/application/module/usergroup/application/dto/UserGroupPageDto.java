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

package org.dblue.application.module.usergroup.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.usergroup.application.vo.UserGroupPageVo;
import org.dblue.core.web.param.PageParamImpl;

/**
 * 用户组分页查询
 *
 * @author xie jin
 * @since 1.0.0  2024/7/10 下午3:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserGroupPageDto extends PageParamImpl<UserGroupPageVo> {

    /**
     * 用户组名称
     */
    @Schema(description = "用户组名称")
    private String userGroupName;
}
