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
package org.dblue.common.assertion;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dblue.common.error.ErrorInfo;
import org.dblue.common.exception.ServiceException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 业务断言，对对象或字段的值进行判断，如果不满足条件，抛出异常
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public class ServiceAssert {
    private ServiceAssert() {
    }


    /**
     * 判断对象是否为空
     *
     * @param obj     要检查的目标对象
     * @param message 错误信息
     */
    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new ServiceException(message);
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param obj       要检查的目标对象
     * @param errorInfo 错误信息
     */
    public static void notNull(Object obj, ErrorInfo errorInfo) {
        if (obj == null) {
            throw new ServiceException(errorInfo);
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param obj     要检查的目标对象
     * @param clz     要抛出的异常类
     * @param message 错误信息
     */
    public static void notNull(Object obj, Class<? extends ServiceException> clz, String message) {
        if (obj == null) {
            throwException(clz, message);
        }
    }

    /**
     * 抛出异常
     *
     * @param clz     要抛出的异常类
     * @param message 错误信息
     */
    public static void throwException(Class<? extends ServiceException> clz, String message) {
        try {
            Constructor<? extends ServiceException> constructor = clz.getConstructor(String.class);
            throw constructor.newInstance(message);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.warn(e.getMessage(), e);
            throw new ServiceException(message);
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param obj       要检查的目标对象
     * @param clz       要抛出的异常类
     * @param errorInfo 错误信息
     */
    public static void notNull(Object obj, Class<? extends ServiceException> clz, ErrorInfo errorInfo) {
        if (obj == null) {
            throwException(clz, errorInfo);
        }
    }

    /**
     * 抛出异常
     *
     * @param clz       要抛出的异常类
     * @param errorInfo 错误信息
     */
    public static void throwException(Class<? extends ServiceException> clz, ErrorInfo errorInfo) {
        try {
            Constructor<? extends ServiceException> constructor = clz.getConstructor(ErrorInfo.class);
            throw constructor.newInstance(errorInfo);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.warn(e.getMessage(), e);
            throw new ServiceException(errorInfo);
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param obj     要检查的目标对象
     * @param message 错误信息
     */
    public static void notEmpty(String obj, String message) {
        if (StringUtils.isEmpty(obj)) {
            throw new ServiceException(message);
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param obj       要检查的目标对象
     * @param errorInfo 错误信息
     */
    public static void notEmpty(String obj, ErrorInfo errorInfo) {
        if (StringUtils.isEmpty(obj)) {
            throw new ServiceException(errorInfo);
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param obj     要检查的目标对象
     * @param clz     要抛出的异常类
     * @param message 错误信息
     */
    public static void notEmpty(String obj, Class<? extends ServiceException> clz, String message) {
        if (StringUtils.isEmpty(obj)) {
            throwException(clz, message);
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param obj       要检查的目标对象
     * @param clz       要抛出的异常类
     * @param errorInfo 错误信息
     */
    public static void notEmpty(String obj, Class<? extends ServiceException> clz, ErrorInfo errorInfo) {
        if (StringUtils.isEmpty(obj)) {
            throwException(clz, errorInfo);
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param obj       要检查的目标对象
     * @param clz       要抛出的异常类
     * @param errorInfo 错误信息
     */
    public static void notEmpty(Object obj, Class<? extends ServiceException> clz, ErrorInfo errorInfo) {
        if (Objects.isNull(obj)) {
            throwException(clz, errorInfo);
        }
    }

    /**
     * 判断Map是否为空
     *
     * @param map     要检查的目标对象
     * @param message 错误信息
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw new ServiceException(message);
        }
    }

    /**
     * 判断Map是否为空
     *
     * @param map       要检查的目标对象
     * @param errorInfo 错误信息
     */
    public static void notEmpty(Map<?, ?> map, ErrorInfo errorInfo) {
        if (map == null || map.isEmpty()) {
            throw new ServiceException(errorInfo);
        }
    }

    /**
     * 判断Map是否为空
     *
     * @param map     要检查的目标对象
     * @param clz     要抛出的异常类
     * @param message 错误信息
     */
    public static void notEmpty(Map<?, ?> map, Class<? extends ServiceException> clz, String message) {
        if (map == null || map.isEmpty()) {
            throwException(clz, message);
        }
    }

    /**
     * 判断Map是否为空
     *
     * @param map       要检查的目标对象
     * @param clz       要抛出的异常类
     * @param errorInfo 错误信息
     */
    public static void notEmpty(Map<?, ?> map, Class<? extends ServiceException> clz, ErrorInfo errorInfo) {
        if (map == null || map.isEmpty()) {
            throwException(clz, errorInfo);
        }
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 要检查的集合对象
     * @param message    错误信息
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new ServiceException(message);
        }
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 要检查的集合对象
     * @param errorInfo  错误信息
     */
    public static void notEmpty(Collection<?> collection, ErrorInfo errorInfo) {
        if (collection == null || collection.isEmpty()) {
            throw new ServiceException(errorInfo);
        }
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 要检查的集合对象
     * @param clz        要抛出的异常类
     * @param message    错误信息
     */
    public static void notEmpty(Collection<?> collection, Class<? extends ServiceException> clz, String message) {
        if (collection == null || collection.isEmpty()) {
            throwException(clz, message);
        }
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 要检查的集合对象
     * @param clz        要抛出的异常类
     * @param errorInfo  错误信息
     */
    public static void notEmpty(Collection<?> collection, Class<? extends ServiceException> clz, ErrorInfo errorInfo) {
        if (collection == null || collection.isEmpty()) {
            throwException(clz, errorInfo);
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param obj     要检查的目标对象
     * @param message 错误信息
     */
    public static void notBlank(String obj, String message) {
        if (StringUtils.isBlank(obj)) {
            throw new ServiceException(message);
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param obj       要检查的目标对象
     * @param errorInfo 错误信息
     */
    public static void notBlank(String obj, ErrorInfo errorInfo) {
        if (StringUtils.isBlank(obj)) {
            throw new ServiceException(errorInfo);
        }
    }
}