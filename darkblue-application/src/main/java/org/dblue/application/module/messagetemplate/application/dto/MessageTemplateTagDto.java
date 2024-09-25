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
package org.dblue.application.module.messagetemplate.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dblue.application.module.messagetemplate.infrastructure.entity.MessageTemplateTag;

/**
 * 标签配置
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "标签配置")
@Data
public class MessageTemplateTagDto {

    /**
     * 标签名称
     */
    @Schema(description = "标签名称")
    private String tagName;

    /**
     * 显示条件
     */
    @Schema(description = "显示条件")
    private String showConditional;

    public MessageTemplateTag asEntity() {
        MessageTemplateTag messageTemplateTag = new MessageTemplateTag();
        messageTemplateTag.setTagName(this.getTagName());
        messageTemplateTag.setShowConditional(this.getShowConditional());
        return messageTemplateTag;
    }
}