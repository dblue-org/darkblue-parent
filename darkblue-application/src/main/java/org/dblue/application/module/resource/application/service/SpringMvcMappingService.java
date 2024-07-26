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

package org.dblue.application.module.resource.application.service;

import org.dblue.application.module.resource.application.vo.ResourceControllerVo;
import org.dblue.application.module.resource.application.vo.ResourceMappingVo;

import java.util.List;

/**
 * spring 注解服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/18 上午10:15
 */
public interface SpringMvcMappingService {

    /**
     * 获取资源信息
     * @param platform 平台
     *
     * @return 资源信息
     */
    List<ResourceControllerVo> getResourceController(Integer platform);

    /**
     * 根据资源地址获取资源信息
     *
     * @param method      请求方法
     * @param resourceUrl 资源地址
     * @return 资源信息
     */
    ResourceMappingVo getMapping(String method, String resourceUrl);
}
