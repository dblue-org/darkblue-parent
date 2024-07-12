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

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 资源
 *
 * @author xie jin
 * @since 1.0.0  2024-07-02 17:27:58
 */
@Data
public class ResourceDto  {

    /**
     * 资源名称
     */
    @Schema(description = "资源名称")
    @Size(max = 100)
    @NotBlank(message = "资源名称不能为空")
    private String resourceName;

    /**
     * 资源地址
     */
    @Schema(description = "资源地址")
    @Size(max = 256)
    @NotBlank(message = "资源地址不能为空")
    private String resourceUrl;

    /**
     * 是否登录即可访问
     */
    @Schema(description = "是否登录即可访问")
    @NotNull(message = "是否登录即可访问不能为空")
    private Boolean isAuthedAccess;

    /**
     * 请求方式
     */
    @Schema(description = "请求方式")
    @Size(max = 20)
    @NotBlank(message = "请求方式不能为空")
    private String requestMethod;

    /**
     * 控制层类
     */
    @Schema(description = "控制层类")
    @Size(max = 500)
    @NotBlank(message = "控制层类不能为空")
    private String controller;

    /**
     * 控制层方法
     */
    @Schema(description = "控制层方法")
    @Size(max = 500)
    @NotBlank(message = "控制层方法不能为空")
    private String method;

}