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
package org.dblue.application.module.dictionary.error;

import lombok.Getter;
import org.dblue.common.error.ErrorInfo;

/**
 * 字典项错误
 *
 * @author xie jin
 * @since 1.0.0  2024-07-12 14:10:04
 */
@Getter
public enum DictionaryItemErrors implements ErrorInfo {


    /**
     *
     */
    DICTIONARY_ITEM_EXITS("DICTIONARY_ITEM_0001", "字典项信息已存在"),
    DICTIONARY_ITEM_IS_NOT_FOUND("DICTIONARY_ITEM_0002", "字典项信息不存在"),
    ;
    private final String errorCode;
    private final String errorMessage;

    DictionaryItemErrors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}