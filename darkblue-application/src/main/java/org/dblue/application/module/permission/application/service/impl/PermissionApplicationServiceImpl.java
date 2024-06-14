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

package org.dblue.application.module.permission.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dblue.application.module.permission.application.service.PermissionApplicationService;
import org.dblue.application.module.permission.domain.service.PermissionDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/6/14 下午5:12
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionApplicationServiceImpl implements PermissionApplicationService {

    private final PermissionDomainService permissionDomainService;
    /**
     * 权限删除
     *
     * @param id 权限id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        permissionDomainService.delete(id);
    }
}
