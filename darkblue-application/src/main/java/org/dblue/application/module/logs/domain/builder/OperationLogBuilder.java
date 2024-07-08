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
package org.dblue.application.module.logs.domain.builder;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.dblue.application.module.logs.infrastructure.entity.OperationLog;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class OperationLogBuilder {

    private String operationName;
    private String serviceClass;
    private String serviceMethod;
    private String methodParams;
    private String result;
    private Boolean isError;
    private Integer timeConsuming;
    private String errorDetails;

    private OperationLogBuilder() {
    }

    public static OperationLogBuilder newInstance() {
        return new OperationLogBuilder();
    }

    public OperationLogBuilder operationName(String val) {
        operationName = val;
        return this;
    }

    public OperationLogBuilder serviceClass(String val) {
        serviceClass = val;
        return this;
    }

    public OperationLogBuilder serviceMethod(String val) {
        serviceMethod = val;
        return this;
    }

    public OperationLogBuilder methodParams(String val) {
        methodParams = val;
        return this;
    }

    public OperationLogBuilder result(String val) {
        result = val;
        return this;
    }

    public OperationLogBuilder isError(Boolean val) {
        isError = val;
        return this;
    }

    public OperationLogBuilder timeConsuming(Integer val) {
        timeConsuming = val;
        return this;
    }

    public OperationLogBuilder errorDetails(String val) {
        errorDetails = val;
        return this;
    }

    public OperationLogBuilder errorDetails(Throwable val) {
        errorDetails = ExceptionUtils.getStackTrace(val);
        return this;
    }


    public OperationLog build() {
        OperationLog operationLog = new OperationLog();
        operationLog.setOperationName(this.operationName);
        operationLog.setServiceClass(this.serviceClass);
        operationLog.setServiceMethod(this.serviceMethod);
        operationLog.setMethodParams(this.methodParams);
        operationLog.setResult(this.result);
        operationLog.setIsError(this.isError);
        operationLog.setTimeConsuming(this.timeConsuming);
        operationLog.setErrorDetails(this.errorDetails);
        return operationLog;
    }

}