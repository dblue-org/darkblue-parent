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
package org.dblue.core.mybatis.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Model 工具类
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public class ModelUtils {

    private ModelUtils() {
    }

    public static void setCreateUpdateInfoByInterface(Object entity, String userId) {
        if (entity instanceof CreateUpdateInfo) {
            setCreateUpdateInfo((CreateUpdateInfo) entity, userId);
        } else if (entity instanceof CreateInfo) {
            setCreateInfo((CreateInfo) entity, userId);
        }
    }

    public static void setCreateUpdateInfo(CreateUpdateInfo createUpdateInfo, String userId) {
        setCreateInfo(createUpdateInfo, userId);
        setUpdateInfo(createUpdateInfo, userId);
    }

    public static void setCreateInfo(CreateInfo createInfo, String userId) {
        if (createInfo.getCreateUser() == null) {
            createInfo.setCreateUser(userId);
        }
        createInfo.setCreateTime(LocalDateTime.now());
    }

    public static void setUpdateInfo(UpdateInfo updateInfo, String userId) {
        if (updateInfo.getUpdateUser() == null) {
            updateInfo.setUpdateUser(userId);
        }
        updateInfo.setUpdateTime(LocalDateTime.now());
    }

    public static void setUpdateInfoByInterface(Object entity, String userId) {
        if (entity instanceof UpdateInfo) {
            setUpdateInfo((UpdateInfo) entity, userId);
        }
    }

    public static void trySetCreateUpdateInfo(Object entity, String userId) {
        trySetCreateInfo(entity, userId);
        trySetUpdateInfo(entity, userId);
    }

    public static void trySetCreateInfo(Object entity, String userId) {
        if (entity instanceof CreateInfo) {
            setCreateInfo((CreateInfo) entity, userId);
        } else {
            trySetCreateInfoByFieldName(entity, userId);
        }
    }

    public static void trySetUpdateInfo(Object entity, String userId) {
        if (entity instanceof UpdateInfo) {
            setUpdateInfo((UpdateInfo) entity, userId);
        } else {
            trySetUpdateInfoByFieldName(entity, userId);
        }
    }

    private static void trySetCreateInfoByFieldName(Object entity, String userId) {
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(entity.getClass());
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if ("createTime".equals(propertyDescriptor.getName())) {
                setCurrentTime(entity, propertyDescriptor);
            }
            if ("createUser".equals(propertyDescriptor.getName())) {
                setUserId(entity, propertyDescriptor, userId);
            }
        }
    }

    private static void trySetUpdateInfoByFieldName(Object entity, String userId) {
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(entity.getClass());
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if ("updateTime".equals(propertyDescriptor.getName())) {
                setCurrentTime(entity, propertyDescriptor);
            }
            if ("updateUser".equals(propertyDescriptor.getName())) {
                setUserId(entity, propertyDescriptor, userId);
            }
        }
    }

    private static void setCurrentTime(Object entity, PropertyDescriptor propertyDescriptor) {
        Method method = propertyDescriptor.getWriteMethod();
        try {
            if (propertyDescriptor.getPropertyType().equals(LocalDateTime.class)) {
                method.invoke(entity, LocalDateTime.now());
            } else if (propertyDescriptor.getPropertyType().equals(Date.class)) {
                method.invoke(entity, new Date());
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            log.warn(ex.getMessage(), ex);
        }
    }

    private static void setUserId(Object entity, PropertyDescriptor propertyDescriptor, String userId) {
        Method readMethod = propertyDescriptor.getWriteMethod();
        Method writeMethod = propertyDescriptor.getWriteMethod();
        try {
            Object oldValue = readMethod.invoke(entity);
            if (oldValue != null) {
                return;
            }
            writeMethod.invoke(entity, userId);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            log.warn(ex.getMessage(), ex);
        }
    }
}