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
package org.dblue.application.module.messagetemplate.application.vo.sub;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 路由配置信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "路由配置信息")
@Data
public class BaseMessageTemplateRouteVo {

    /**
     * 路由类型(1-PC;2-Android;3-IOS;4-小程序;5-H5)
     */
    @Schema(description = "路由类型(1-PC;2-Android;3-IOS;4-小程序;5-H5)")
    private Integer routerType;

    /**
     * 路由类型名称
     */
    @Schema(description = "路由类型名称")
    private String routerTypeName;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String routerLink;
}