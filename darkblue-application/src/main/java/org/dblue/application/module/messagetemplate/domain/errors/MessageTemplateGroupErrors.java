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
package org.dblue.application.module.messagetemplate.domain.errors;

import lombok.Getter;
import org.dblue.common.error.ErrorInfo;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Getter
public enum MessageTemplateGroupErrors implements ErrorInfo {

    NAME_EXIST("MSG_TPL_GROUP_001", "消息模板分组名称已存在"),
    NOT_EXIST("MSG_TPL_GROUP_002", "消息模板分组不存在"),

    ;
    private final String errorCode;
    private final String errorMessage;

    MessageTemplateGroupErrors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}