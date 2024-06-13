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
package org.dblue.core.spring.exception;

import lombok.extern.slf4j.Slf4j;
import org.dblue.common.error.CommonError;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 默认异常返回数据处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public class CustomErrorAttributes extends DefaultErrorAttributes {


    public CustomErrorAttributes() {
        log.info("自定义 [ErrorAttributes] 已初始化。");
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("success", false);
        Integer status = (Integer) errorAttributes.get("status");
        if (status == HttpStatus.NOT_FOUND.value()) {
            handle404(errorAttributes);
        } else {
            defaultHandle(errorAttributes);
        }
        return errorAttributes;
    }

    protected void handle404(Map<String, Object> errorAttributes) {
        errorAttributes.put("errorCode", "404");
        errorAttributes.put("message", "请求的资源不存在");
    }

    protected void defaultHandle(Map<String, Object> errorAttributes) {
        errorAttributes.put("errorCode", CommonError.INTERNAL_SERVER_ERROR.getErrorCode());
        errorAttributes.put("message", CommonError.INTERNAL_SERVER_ERROR.getErrorMessage());
    }
}