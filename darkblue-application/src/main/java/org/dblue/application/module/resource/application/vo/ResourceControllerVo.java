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

import java.util.List;

/**
 * 资源控制器组
 *
 * @author xie jin
 * @since 1.0.0  2024/7/4 下午2:58
 */
@Schema(description = "资源控制器组")
@Data
public class ResourceControllerVo {

    /**
     * 控制器名称
     */
    @Schema(description = "控制器名称")
    private String tagName;


    /**
     * 平台 1:-PC;2-APP
     */
    private Integer platform;

    /**
     * 资源信息
     */
    @Schema(description = "资源信息")
    private List<ResourceMappingVo> mappings;

    public void merge(ResourceControllerVo resourceControllerVo) {
        this.mappings.addAll(resourceControllerVo.getMappings());
    }

    public static ResourceControllerVo build(String group, List<ResourceMappingVo> mappings) {
        ResourceControllerVo resourceControllerVo = new ResourceControllerVo();
        String[] split = group.split("#");
        resourceControllerVo.setTagName(split[0]);
        if (split.length > 1) {
            resourceControllerVo.setPlatform(Integer.parseInt(split[1]));
        }
        resourceControllerVo.setMappings(mappings);
        return resourceControllerVo;
    }

    public String group() {
        return this.tagName + "#" + this.platform;
    }
}
