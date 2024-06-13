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
import org.dblue.common.enums.EnumState;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.validation.annotation.EnumValues;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.StreamSupport;

/**
 * 枚举校验
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class EnumValueValidator implements ConstraintValidator<EnumValues, Object> {

    private final Set<Object> enumValues = new HashSet<>();

    @Override
    public void initialize(EnumValues constraintAnnotation) {
        Class<? extends Enum<?>> enumClass = constraintAnnotation.clazz();
        Enum<?>[] enums = enumClass.getEnumConstants();
        for (Enum<?> anEnum : enums) {
            if (anEnum instanceof EnumState enumState) {
                this.enumValues.add(enumState.getValue());
            } else {
                try {
                    Method method = enumClass.getDeclaredMethod(constraintAnnotation.valueMethod());
                    Object value = method.invoke(anEnum);
                    if (value != null) {
                        enumValues.add(value);
                    }
                } catch (NoSuchMethodException e) {
                    throw new ServiceException("无法获取枚举值，获取枚举值的方法配置错误");
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new ServiceException("获取枚举值失败", e);
                }
            }
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }

        if (value.getClass().isArray()) {
            return Arrays.stream((Object[]) value).allMatch(enumValues::contains);
        } else if (value instanceof Iterable<?> it) {
            return StreamSupport.stream(it.spliterator(), false).allMatch(enumValues::contains);
        } else {
            return enumValues.contains(value);
        }
    }
}