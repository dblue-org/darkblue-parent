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
package org.dblue.application.module.notification.application.service.impl;

import org.dblue.application.module.notification.application.dto.NotificationQueryDto;
import org.dblue.application.module.notification.application.service.NotificationApplicationService;
import org.dblue.application.module.notification.application.vo.NotificationListVo;
import org.dblue.application.module.notification.domain.NotificationDomainService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Service
public class NotificationApplicationServiceImpl implements NotificationApplicationService {

    private final NotificationDomainService notificationDomainService;

    public NotificationApplicationServiceImpl(NotificationDomainService notificationDomainService) {
        this.notificationDomainService = notificationDomainService;
    }

    @Override
    public Page<NotificationListVo> findByPage(NotificationQueryDto queryDto) {
        return null;
    }

    @Override
    public void markRead(String notificationId) {
        this.notificationDomainService.markRead(notificationId);
    }

    @Override
    public void deleteById(String notificationId) {

    }
}