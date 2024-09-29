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
package org.dblue.application.module.notification.infrastructure.query;

import org.dblue.application.commons.db.jpa.BaseJpaQuery;
import org.dblue.application.module.notification.infrastructure.entity.Notification;

import java.time.LocalDate;
import java.util.Collection;

/**
 * 通知
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface NotificationQuery extends BaseJpaQuery<Notification> {

    NotificationQuery notificationId(String notificationId);

    NotificationQuery notificationIdIn(Collection<String> notificationIds);

    NotificationQuery messageTemplateGroupId(String messageTemplateGroupId);

    NotificationQuery toUser(String userId);

    NotificationQuery serviceCode(String serviceCode);

    NotificationQuery messageTitleLike(String messageTitleLike);

    NotificationQuery isRead(Boolean isRead);

    NotificationQuery createTimeRange(LocalDate startTime, LocalDate endTime);
}
