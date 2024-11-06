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
package org.dblue.application.module.notification.domain.impl;

import lombok.RequiredArgsConstructor;
import org.dblue.application.module.notification.domain.NotificationDomainService;
import org.dblue.application.module.notification.infrastructure.entity.Notification;
import org.dblue.application.module.notification.infrastructure.query.NotificationQuery;
import org.dblue.application.module.notification.infrastructure.query.impl.NotificationQueryImpl;
import org.dblue.application.module.notification.infrastructure.repository.NotificationRepository;
import org.dblue.common.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class NotificationDomainServiceImpl implements NotificationDomainService {

    private final NotificationRepository notificationRepository;

    @Override
    public void addNotification(Notification notification) {
        this.notificationRepository.save(notification);
    }

    @Override
    public void markRead(String notificationId) {
        Optional<Notification> notificationOptional = this.notificationRepository.findById(notificationId);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            if (Boolean.TRUE.equals(notification.getIsRead())) {
                return;
            }
            notification.setIsRead(true);
            notification.setReadTime(LocalDateTime.now());
            this.notificationRepository.save(notification);
        } else {
            throw new ServiceException("Notification not found");
        }
    }

    @Override
    public NotificationQuery createQuery() {
        return new NotificationQueryImpl(this.notificationRepository);
    }
}