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
import org.dblue.common.validation.validator.PositiveNumberValidator;

import java.lang.annotation.*;

/**
 * 正数校验，数值必须大于0，支持下列数据类型
 * <ul>
 *     <li>{@link java.math.BigDecimal}</li>
 *     <li>{@link java.lang.Integer}</li>
 *     <li>{@link java.lang.Double}</li>
 *     <li>{@link java.lang.Float}</li>
 * </ul>
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PositiveNumberValidator.class)
public @interface PositiveNumber {

    String message() default "{org.dblue.common.validation.annotation.PositiveNumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
