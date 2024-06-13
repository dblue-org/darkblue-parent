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
package org.dblue.common.exception;

/**
 * 操作不支持，暂未实现。用于新旧接口过渡，或预留未来要实现的接口
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2024/6/13 10:56]
 */
public class OperationNotSupportedException extends RuntimeException {

    public OperationNotSupportedException() {
        this("This operation is not supported");
    }

    public OperationNotSupportedException(String message) {
        super(message);
    }
}