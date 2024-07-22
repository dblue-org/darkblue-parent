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

package org.dblue.application.module.resource.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.dblue.common.validation.annotation.EnumValues;
import org.dblue.core.enums.PlatformEnum;

import java.util.List;

/**
 * 资源批量添加
 *
 * @author xie jin
 * @since 1.0.0  2024/7/22 下午1:47
 */
@Data
public class ResourceBatchAddDto {

    /**
     * 资源组ID
     */
    @Size(max = 64)
    @NotBlank(message = "资源组ID不能为空")
    private String resourceGroupId;


    /**
     * 适用平台(1-PC；2-APP)
     */
    @EnumValues(message = "适用平台不正确", clazz = PlatformEnum.class)
    private Integer platform;

    /**
     * 资源信息
     */
    @Valid
    @NotEmpty(message = "资源信息不能为空")
    private List<ResourceDto> mappings;

}
