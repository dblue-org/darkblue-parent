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
package org.dblue.application.module.notification.infrastructure.query.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.commons.db.jpa.AbstractBaseJpaQuery;
import org.dblue.application.module.notification.infrastructure.entity.Notification;
import org.dblue.application.module.notification.infrastructure.entity.QNotification;
import org.dblue.application.module.notification.infrastructure.query.NotificationQuery;
import org.dblue.application.module.notification.infrastructure.repository.NotificationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class NotificationQueryImpl extends AbstractBaseJpaQuery<Notification, String> implements NotificationQuery {


    public NotificationQueryImpl(NotificationRepository executor) {
        super(executor);
    }

    @Override
    public NotificationQuery notificationId(String notificationId) {
        this.queryBuilder.and(QNotification.notification.notificationId.eq(notificationId));
        return this;
    }

    @Override
    public NotificationQuery notificationIdIn(Collection<String> notificationIds) {
        if (CollectionUtils.isNotEmpty(notificationIds)) {
            this.queryBuilder.and(QNotification.notification.notificationId.in(notificationIds));
        }
        return this;
    }

    @Override
    public NotificationQuery messageTemplateGroupId(String messageTemplateGroupId) {
        if (StringUtils.isNotBlank(messageTemplateGroupId)) {
            this.queryBuilder.and(QNotification.notification.messageTemplateGroupId.eq(messageTemplateGroupId));
        }
        return this;
    }

    @Override
    public NotificationQuery toUser(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            this.queryBuilder.and(QNotification.notification.toUser.eq(userId));
        }
        return this;
    }

    @Override
    public NotificationQuery serviceCode(String serviceCode) {
        if (StringUtils.isNotBlank(serviceCode)) {
            this.queryBuilder.and(QNotification.notification.serviceCode.eq(serviceCode));
        }
        return this;
    }

    @Override
    public NotificationQuery messageTitleLike(String messageTitleLike) {
        if (StringUtils.isNotBlank(messageTitleLike)) {
            this.queryBuilder.and(QNotification.notification.messageTitle.contains(messageTitleLike));
        }
        return this;
    }

    @Override
    public NotificationQuery isRead(Boolean isRead) {
        if (isRead != null) {
            this.queryBuilder.and(QNotification.notification.isRead.eq(isRead));
        }
        return this;
    }

    @Override
    public NotificationQuery createTimeRange(LocalDate startTime, LocalDate endTime) {
        if (startTime != null) {
            LocalDateTime startDateTime = LocalDateTime.of(startTime, LocalTime.MAX);
            this.queryBuilder.and(QNotification.notification.createTime.goe(startDateTime));
        }
        if (endTime != null) {
            LocalDateTime endDateTime = LocalDateTime.of(endTime, LocalTime.MAX);
            this.queryBuilder.and(QNotification.notification.createTime.loe(endDateTime));
        }
        return this;
    }
}