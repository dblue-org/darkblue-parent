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

package org.dblue.application.module.resource.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资源分页返回
 *
 * @author xie jin
 * @since 1.0.0  2024-07-03 09:56:43
 */
@Schema(description = "资源分页返回")
@Data
public class ResourcePageVo {
    /**
     * 资源ID
     */
    @Schema(description = "资源ID")
    private String resourceId;

    /**
     * 资源名称
     */
    @Schema(description = "资源名称")
    private String resourceName;

    /**
     * 资源信息是否发生变更
     */
    @Schema(description = "资源名称是否已经更新")
    private Boolean isAnythingChanged;

    /**
     * 资源信息
     */
    @Schema(description = "资源信息")
    private ResourceMappingVo resourceMapping;

    /**
     * 资源地址
     */
    @Schema(description = "资源地址")
    private String resourceUrl;

    /**
     * 请求方式
     */
    @Schema(description = "请求方式")
    private String requestMethod;

    /**
     * 控制层类
     */
    @Schema(description = "控制层类")
    private String controller;

    /**
     * 控制层方法
     */
    @Schema(description = "控制层方法")
    private String method;

    /**
     * 是否登录即可访问
     */
    @Schema(description = "是否登录即可访问")
    private Boolean isAuthedAccess;


    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;


    /**
     * 适用平台(1-PC；2-APP)
     */
    @Schema(description = "适用平台(1-PC；2-APP)")
    private Integer platform;

    /**
     * 是否违法
     */
    @Schema(description = "是否违法")
    private Boolean isInvalid;

    /**
     * 权限信息
     */
    @Schema(description = "权限信息")
    private List<ResourcePermissionVo> permissions;
}