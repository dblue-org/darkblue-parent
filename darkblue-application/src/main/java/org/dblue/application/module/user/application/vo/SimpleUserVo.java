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

package org.dblue.application.module.user.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户
 *
 * @author xie jin
 */
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户")
@Data
public class SimpleUserVo extends BaseUserVo {

    /**
     * 性别（1-男；2-女）
     */
    @Schema(description = "性别（1-男；2-女）")
    private Integer sex;

    /**
     * 身份证号码
     */
    @Schema(description = "身份证号码")
    private String identityNo;

}