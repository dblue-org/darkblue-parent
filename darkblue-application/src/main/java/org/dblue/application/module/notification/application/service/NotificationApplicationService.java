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
package org.dblue.application.module.notification.application.service;

import org.dblue.application.module.notification.application.dto.NotificationQueryDto;
import org.dblue.application.module.notification.application.vo.NotificationListVo;
import org.springframework.data.domain.Page;

/**
 * 通知管理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface NotificationApplicationService {

    /**
     * 分页查询通知消息
     *
     * @param queryDto 查询条件
     * @return 通知消息列表
     */
    Page<NotificationListVo> findByPage(NotificationQueryDto queryDto);

    /**
     * 标记为已读
     *
     * @param notificationId 通知ID
     */
    void markRead(String notificationId);

    /**
     * 删除通知消息
     *
     * @param notificationId 通知消息ID
     */
    void deleteById(String notificationId);
}
