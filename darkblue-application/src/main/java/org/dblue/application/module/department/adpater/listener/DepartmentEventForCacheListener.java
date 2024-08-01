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
package org.dblue.application.module.department.adpater.listener;

import org.dblue.application.module.department.domain.cache.DepartmentCacheService;
import org.dblue.application.module.department.domain.event.DepartmentAddEvent;
import org.dblue.application.module.department.domain.event.DepartmentUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 部门事件监听
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Component
public class DepartmentEventForCacheListener {

    private final DepartmentCacheService departmentCacheService;

    public DepartmentEventForCacheListener(DepartmentCacheService departmentCacheService) {
        this.departmentCacheService = departmentCacheService;
    }

    @EventListener(DepartmentAddEvent.class)
    public void onAdd(DepartmentAddEvent addEvent) {
        this.departmentCacheService.save(addEvent.getDepartment());
    }

    @EventListener(DepartmentUpdateEvent.class)
    public void onUpdate(DepartmentUpdateEvent updateEvent) {
        this.departmentCacheService.save(updateEvent.getDepartment());
    }

    // 暂不处理删除事件，避免数据错误。部门逻辑删除的目的是为了在某些使用已删除的部门的数据中，部门名称依然能够正确显示

}