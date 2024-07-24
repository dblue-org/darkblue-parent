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

package org.dblue.application.module.resource.application.service.impl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dblue.application.module.resource.application.service.SpringMvcMappingService;
import org.dblue.application.module.resource.application.vo.ResourceControllerVo;
import org.dblue.application.module.resource.application.vo.ResourceMappingVo;
import org.dblue.application.module.resource.errors.ResourceErrors;
import org.dblue.common.exception.ServiceException;
import org.dblue.core.annotation.Platform;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * spring 注解服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/18 上午10:16
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SpringMvcMappingServiceImpl implements SpringMvcMappingService, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private List<ResourceControllerVo> resourceList = null;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取资源信息
     *
     * @param platform 平台
     * @return 资源信息
     */
    @Override
    public List<ResourceControllerVo> getResourceController(Integer platform) {
        if (this.resourceList != null) {
            return resourceList.stream()
                    .filter(resourceControllerVo -> this.platformCompare(platform, resourceControllerVo))
                    .toList();
        }
        List<ResourceControllerVo> unGroupResourceControllerVoList = new ArrayList<>();
        Map<String, Object> controllerBeanMap = applicationContext.getBeansWithAnnotation(RestController.class);
        for (Map.Entry<String, Object> entry : controllerBeanMap.entrySet()) {

            Class<?> controllerClass = this.getControllerClass(entry.getValue());
            Tag tag = controllerClass.getAnnotation(Tag.class);
            if (tag == null) {
                continue;
            }

            Platform platformAnnotation = controllerClass.getAnnotation(Platform.class);
            if (platformAnnotation == null) {
                log.error("资源必有平台类型:{}", controllerClass.getName());
                throw new ServiceException(ResourceErrors.RESOURCE_MUST_PLATFORM);
            }

            RequestMapping requestMappingClass = controllerClass.getAnnotation(RequestMapping.class);
            String baseUrl = "";
            if (requestMappingClass != null && requestMappingClass.value().length > 0) {
                baseUrl = requestMappingClass.value()[0];
            }

            ResourceControllerVo resourceControllerVo = buildController(tag, controllerClass, baseUrl);
            resourceControllerVo.setPlatform(platformAnnotation.value().getValue());
            unGroupResourceControllerVoList.add(resourceControllerVo);

        }

        // 对控制层接口进行分组及合并
        Map<String, ResourceControllerVo> resourceControllerVoMap = new HashMap<>();
        for (ResourceControllerVo resourceControllerVo : unGroupResourceControllerVoList) {
            if (resourceControllerVoMap.containsKey(resourceControllerVo.group())) {
                ResourceControllerVo existResourceControllerVo = resourceControllerVoMap.get(resourceControllerVo.group());
                existResourceControllerVo.merge(resourceControllerVo);
            } else {
                resourceControllerVoMap.put(resourceControllerVo.group(), resourceControllerVo);
            }
        }

        // 缓存资源信息
        this.resourceList = new ArrayList<>(resourceControllerVoMap.values());

        return this.resourceList.stream()
                .filter(resourceControllerVo -> this.platformCompare(platform, resourceControllerVo))
                .toList();
    }

    /**
     * 如果控制层类中有SpringSecurity的方法权限控制相关的注解，会导致控制层类实际被GCLib代理，从而导致无法获取到类上注解及类中方法的情况， 所以需要获取到原始的类
     */
    private Class<?> getControllerClass(Object controller) {
        boolean isProxy = AopUtils.isCglibProxy(controller);
        Class<?> controllerClass = controller.getClass();
        if (isProxy) {
            controllerClass = AopUtils.getTargetClass(controller);
        }
        return controllerClass;
    }

    private ResourceControllerVo buildController(Tag tag, Class<?> controllerClass, String baseUrl) {
        ResourceControllerVo resourceControllerVo = new ResourceControllerVo();
        resourceControllerVo.setTagName(tag.name());
        List<ResourceMappingVo> resourceMappingVoList = buildMapping(controllerClass, baseUrl);
        resourceControllerVo.setMappings(resourceMappingVoList);
        return resourceControllerVo;
    }

    private List<ResourceMappingVo> buildMapping(Class<?> controllerClass, String baseUrl) {

        List<ResourceMappingVo> resourceMappingVoList = new ArrayList<>();
        for (Method declaredMethod : controllerClass.getDeclaredMethods()) {
            if (declaredMethod.getModifiers() != Modifier.PUBLIC) {
                continue;
            }
            ResourceMappingVo resourceMappingVo = doBuildRequestMapping(declaredMethod, baseUrl);
            if (resourceMappingVo != null) {
                resourceMappingVoList.add(resourceMappingVo);
            }
        }
        return resourceMappingVoList;
    }

    private ResourceMappingVo doBuildRequestMapping(Method declaredMethod, String baseUrl) {

        MergedAnnotation<RequestMapping> mergedAnnotation = MergedAnnotations
                .from(declaredMethod, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(RequestMapping.class);
        if (mergedAnnotation.isPresent()) {
            ResourceMappingVo resourceMappingVo = new ResourceMappingVo();
            resourceMappingVo.setRequestMethod(mergedAnnotation.synthesize().method()[0].name());
            String annotationPathValue = mergedAnnotation.getStringArray("path")[0];
            resourceMappingVo.setResourceUrl(baseUrl + annotationPathValue);
            resourceMappingVo.setMethod(declaredMethod.getName());
            resourceMappingVo.setController(declaredMethod.getDeclaringClass().getName());

            Operation operation = this.getAnnotation(declaredMethod, Operation.class);
            if (operation == null) {
                log.warn("方法 {} 上没有添加Operation注解，资源地址：{}", declaredMethod.getName(), baseUrl);
                throw new ServiceException("请在方法上添加Operation注解");
            }
            resourceMappingVo.setResourceName(operation.summary());
            return resourceMappingVo;
        }
        return null;
    }

    private boolean platformCompare(Integer platform, ResourceControllerVo resourceControllerVo) {
        if (Objects.isNull(platform)) {
            return true;
        }
        return platform.equals(resourceControllerVo.getPlatform());
    }

    @SuppressWarnings("java:S5857")
    private String replaceAll(String url) {
        return url.replaceAll("\\{.*?}", "*");
    }


    private <T extends Annotation> T getAnnotation(AnnotatedElement element, Class<T> annotationClass) {
        T annotation = AnnotationUtils.findAnnotation(element, annotationClass);
        if (annotation != null) {
            return annotation;
        }

        MergedAnnotation<T> mergedAnnotation =
                MergedAnnotations.from(element, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(annotationClass);
        if (mergedAnnotation.isPresent()) {
            return mergedAnnotation.synthesize();
        }

        return null;
    }


}
