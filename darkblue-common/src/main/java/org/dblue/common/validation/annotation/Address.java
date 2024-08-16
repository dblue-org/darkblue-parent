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
package org.dblue.common.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.dblue.common.validation.validator.AddressValidator;

import java.lang.annotation.*;

/**
 * 地址校验注解：限制数字、字母、汉字
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AddressValidator.class)
public @interface Address {

    String message() default "{org.dblue.common.validation.Address.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
