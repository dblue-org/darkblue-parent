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
import lombok.EqualsAndHashCode;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(description = "")
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageTemplateUpdateDto extends BaseMessageTemplateDto {

    /**
     * 消息模板ID
     */
    @Schema(description = "消息模板ID")
    private String messageTemplateId;
}