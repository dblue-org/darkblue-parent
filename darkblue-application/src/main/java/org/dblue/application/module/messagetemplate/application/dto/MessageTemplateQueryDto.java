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

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateListVo;
import org.dblue.core.web.param.PageParamImpl;
import org.springdoc.core.annotations.ParameterObject;

/**
 * 消息模板基础DTO
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@ParameterObject
@Data
public class MessageTemplateQueryDto extends PageParamImpl<MessageTemplateListVo> {

    /**
     * 消息模板编码
     */
    @Parameter(description = "消息模板编码")
    private String messageTemplateCode;

    /**
     * 消息模板名称
     */
    @Parameter(description = "消息模板名称")
    private String messageTemplateName;

    /**
     * 消息模板组ID
     */
    @Parameter(description = "消息模板组ID")
    private String messageTemplateGroupId;

    /**
     * 消息模板类型（1-通知；2-待办）
     */
    @Parameter(description = "消息模板类型（1-通知；2-待办）")
    private Integer messageTemplateType;
}