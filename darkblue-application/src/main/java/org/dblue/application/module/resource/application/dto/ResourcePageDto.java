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

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.resource.application.vo.ResourcePageVo;
import org.dblue.core.web.param.PageParamImpl;
import org.springdoc.core.annotations.ParameterObject;

/**
 * 资源分页
 *
 * @author xie jin
 * @since 1.0.0  2024/7/5 上午9:50
 */
@Schema(description = "资源分页")
@EqualsAndHashCode(callSuper = true)
@Data
@ParameterObject
public class ResourcePageDto extends PageParamImpl<ResourcePageVo> {

    /**
     * 资源组ID
     */
    @Parameter(description = "资源组ID")
    private String resourceGroupId;

    /**
     * 资源名称
     */
    @Parameter(description = "资源名称")
    private String resourceName;

    /**
     * 资源地址
     */
    @Parameter(description = "资源地址")
    private String resourceUrl;

    /**
     * 控制层类
     */
    @Parameter(description = "控制层类")
    private String controller;

    /**
     * 控制层方法
     */
    @Parameter(description = "控制层方法")
    private String method;

    /**
     * 是否登录即可访问
     */
    @Parameter(description = "是否登录即可访问")
    private Boolean isAuthedAccess;

}
