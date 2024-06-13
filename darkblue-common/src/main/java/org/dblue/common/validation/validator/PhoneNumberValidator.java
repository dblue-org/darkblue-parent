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
package org.dblue.common.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.dblue.common.validation.annotation.PhoneNumber;
import org.dblue.common.validation.annotation.PhoneNumberType;

import java.util.regex.Pattern;

/**
 * 电话号码验证
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String MOBILE_PATTERN = "^(\\+\\d{2}|)1\\d{10}$";
    private static final String TELEPHONE_PATTERN = "^\\d{3}-\\d{7,8}|\\d{4}-\\d{7,8}$|\\d{7,8}";

    private PhoneNumberType type;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        if (PhoneNumberType.MOBILE == type) {
            return Pattern.matches(MOBILE_PATTERN, value);
        } else if (PhoneNumberType.TELEPHONE == type) {
            return Pattern.matches(TELEPHONE_PATTERN, value);
        } else {
            return Pattern.matches(MOBILE_PATTERN, value) || Pattern.matches(TELEPHONE_PATTERN, value);
        }
    }

}