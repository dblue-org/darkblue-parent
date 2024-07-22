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

package org.dblue.application.module.menu.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.common.validation.annotation.EnumValues;
import org.dblue.core.enums.PlatformEnum;

/**
 * 菜单添加
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单")
@Data
public class MenuAddDto extends MenuDto{


    /**
     * 菜单适用平台(1-PC；2-APP)
     */
    @Schema(description = "菜单适用平台(1-PC；2-APP)")
    @EnumValues(message = "适用平台不正确", clazz = PlatformEnum.class)
    private Integer platform;


}