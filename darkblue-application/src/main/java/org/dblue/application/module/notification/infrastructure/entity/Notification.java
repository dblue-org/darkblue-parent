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

package org.dblue.application.module.notification.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

/**
 * 通知消息
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_notification")
public class Notification {
    /**
     * 通知消息ID
     */
    @Id
    @Size(max = 32)
    @Column(name = "notification_id", nullable = false, length = 32)
    private String notificationId;

    /**
     * 消息模板组ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "message_template_group_id", nullable = false, length = 32)
    private String messageTemplateGroupId;

    /**
     * 模板ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "message_template_id", nullable = false, length = 32)
    private String messageTemplateId;

    /**
     * 消息标题
     */
    @Size(max = 512)
    @NotNull
    @Column(name = "message_title", nullable = false, length = 512)
    private String messageTitle;

    /**
     * 主要内容
     */
    @NotNull
    @Lob
    @Column(name = "message_content", nullable = false)
    private String messageContent;

    /**
     * 接收人
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "to_user", nullable = false, length = 32)
    private String toUser;

    /**
     * 消息接收时间
     */
    @NotNull
    @Column(name = "receive_time", nullable = false)
    private Instant receiveTime;

    /**
     * 业务类型标识
     */
    @Size(max = 100)
    @Column(name = "service_mark", length = 100)
    private String serviceMark;

    /**
     * 业务记录编码
     */
    @Size(max = 32)
    @Column(name = "service_code", length = 32)
    private String serviceCode;

    /**
     * 业务ID
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "service_id", nullable = false, length = 32)
    private String serviceId;

    /**
     * 是否已读
     */
    @NotNull
    @ColumnDefault("0")
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    /**
     * 读消息时间
     */
    @Column(name = "read_time")
    private Instant readTime;

    /**
     * 业务数据
     */
    @NotNull
    @Column(name = "service_data", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> serviceData;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 创建人
     */
    @Size(max = 32)
    @Column(name = "create_user", length = 32)
    private String createUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * 更新人
     */
    @Size(max = 32)
    @Column(name = "update_user", length = 32)
    private String updateUser;

}