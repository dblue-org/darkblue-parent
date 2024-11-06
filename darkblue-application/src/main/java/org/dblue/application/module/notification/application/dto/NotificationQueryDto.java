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

package org.dblue.application.module.notification.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.notification.application.vo.NotificationListVo;
import org.dblue.core.web.param.PageParamImpl;

import java.time.LocalDateTime;

/**
 * 通知消息
 */
@EqualsAndHashCode(callSuper = false)
@Schema(description = "通知消息")
@Data
public class NotificationQueryDto extends PageParamImpl<NotificationListVo> {

    /**
     * 消息模板组ID
     */
    @Schema(description = "消息模板组ID")
    private String messageTemplateGroupId;


    /**
     * 消息标题
     */
    @Schema(description = "消息标题")
    private String messageTitle;

    /**
     * 接收人
     */
    @Schema(description = "接收人")
    private String toUser;

    /**
     * 业务记录编码
     */
    @Schema(description = "业务记录编码")
    private String serviceCode;

    /**
     * 是否已读
     */
    @Schema(description = "是否已读")
    private Boolean isRead = false;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTimeStart;

    /**
     * 创建时间开始
     */
    @Schema(description = "创建时间结束")
    private LocalDateTime createTimeEnd;


}