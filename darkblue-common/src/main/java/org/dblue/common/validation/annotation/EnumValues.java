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
import org.dblue.common.enums.EmptyState;
import org.dblue.common.validation.validator.EnumValueValidator;

import java.lang.annotation.*;

/**
 * 枚举校验，字段的值必须是枚举中的Value
 *
 * @author Wang Chengwei
 * @see org.dblue.common.enums.EnumState
 * @see org.dblue.common.enums.EnumType
 * @since 1.0.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValueValidator.class)
public @interface EnumValues {

    String message() default "{org.dblue.common.validation.annotation.EnumValues.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String valueMethod() default "getValue";

    /**
     * 可选值枚举类
     *
     * @return 可选值枚举类
     */
    Class<? extends Enum<?>> clazz() default EmptyState.class;
}
