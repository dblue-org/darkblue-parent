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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 修改密码
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "修改密码")
@Data
public class UserPasswordChangeDto {

    /**
     * 当前密码
     */
    @NotEmpty(message = "当前密码不能为空")
    @Schema(description = "当前密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotEmpty(message = "新密码不能为空")
    @Size(min = 6, max = 32, message = "密码长度应在6-32位之间")
    @Schema(description = "新密码")
    private String newPassword;
}