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

import org.dblue.application.module.resource.application.dto.ResourceBatchAddDto;
import org.dblue.application.module.resource.application.dto.ResourcePageDto;
import org.dblue.application.module.resource.application.dto.ResourcePermissionDto;
import org.dblue.application.module.resource.application.vo.ResourceInvalidVo;
import org.dblue.application.module.resource.application.vo.ResourcePageVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 资源应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/3 上午9:39
 */
public interface ResourceApplicationService {

    /**
     * 删除资源
     * @param resourceId 资源ID
     */
    void delete(String resourceId);


    /**
     * 分页查询
     *
     * @param pageDto 查询参数
     * @return 资源
     */
    Page<ResourcePageVo> page(ResourcePageDto pageDto);

    /**
     * 设置权限
     *
     * @param permissionDto 权限信息
     */
    void setPermission(ResourcePermissionDto permissionDto);


    /**
     * 批量添加或者更新
     */
    void batchAddOrUpDate();


    /**
     * 批量添加
     *
     * @param batchAddDto 资源信息
     */
    void batchAdd(ResourceBatchAddDto batchAddDto);

    /**
     * 检测资源合法性
     * @return 非法资源
     */
    List<ResourceInvalidVo> checkResourceValidity();
}
