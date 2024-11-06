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

package org.dblue.application.module.notification.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * 通知消息
 */
@Schema(description = "通知消息")
@Data
public class NotificationListVo {
    /**
     * 通知消息ID
     */
    @Schema(description = "通知消息ID")
    private String notificationId;

    /**
     * 消息模板组ID
     */
    @Schema(description = "消息模板组ID")
    private String messageTemplateGroupId;

    /**
     * 消息模板组名称
     */
    @Schema(description = "消息模板组名称")
    private String messageTemplateGroupName;

    /**
     * 消息标题
     */
    @Schema(description = "消息标题")
    private String messageTitle;

    /**
     * 主要内容
     */
    @Schema(description = "主要内容")
    private String messageContent;

    /**
     * 接收人
     */
    @Schema(description = "接收人")
    private String toUser;

    /**
     * 接收人姓名
     */
    @Schema(description = "接收人姓名")
    private String toUserName;

    /**
     * 消息接收时间
     */
    @Schema(description = "消息接收时间")
    private Instant receiveTime;

    /**
     * 业务类型标识
     */
    @Schema(description = "业务类型标识")
    private String serviceMark;

    /**
     * 业务记录编码
     */
    @Schema(description = "业务记录编码")
    private String serviceCode;

    /**
     * 业务ID
     */
    @Schema(description = "业务ID")
    private String serviceId;

    /**
     * 是否已读
     */
    @Schema(description = "是否已读")
    private Boolean isRead = false;

    /**
     * 读消息时间
     */
    @Schema(description = "读消息时间")
    private LocalDateTime readTime;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;


}