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
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户更新
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 11:31:03
 */
@Schema(description = "用户更新")
@Data
public class UserSelfUpdateDto {


    /**
     * 姓名
     */
    @Schema(description = "姓名")
    @Size(max = 64)
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private Integer sex;

    /**
     * 身份证号码
     */
    @Schema(description = "身份证号码")
    private String identityNo;


    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phoneNumber;

}