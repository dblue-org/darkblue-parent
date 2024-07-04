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

package org.dblue.application.module.user.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.user.application.vo.UserPageVo;
import org.dblue.core.web.param.PageParamImpl;
import org.springdoc.core.annotations.ParameterObject;

/**
 * 用户分页查询
 *
 * @author xie jin
 * @since 1.0.0  2024/7/2 上午9:25
 */
@ParameterObject
@Schema(description = "用户分页查询")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageDto extends PageParamImpl<UserPageVo> {

    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    @NotBlank(message = "部门ID不能为空")
    private String deptId;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;


    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phoneNumber;

}
