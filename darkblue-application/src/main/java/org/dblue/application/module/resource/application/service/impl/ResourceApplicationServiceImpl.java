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
import org.dblue.application.module.permission.domain.service.PermissionDomainService;
import org.dblue.application.module.resource.application.service.ResourceApplicationService;
import org.dblue.application.module.resource.application.vo.ResourceControllerVo;
import org.dblue.application.module.resource.application.vo.ResourceMappingVo;
import org.dblue.application.module.resource.domain.service.ResourceDomainService;
import org.dblue.application.module.resource.errors.ResourceErrors;
import org.dblue.common.exception.ServiceException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 资源应用服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/3 上午9:40
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ResourceApplicationServiceImpl implements ResourceApplicationService, ApplicationContextAware {

    private final ResourceDomainService resourceDomainService;
    private final PermissionDomainService permissionDomainService;

    private ApplicationContext applicationContext;

    /**
     * 删除资源
     *
     * @param resourceId 资源ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String resourceId) {
        resourceDomainService.delete(resourceId);
        permissionDomainService.deletePermissionResourceByResourceId(resourceId);
    }

    /**
     * 获取资源信息
     *
     * @return 资源信息
     */
    @Override
    public List<ResourceControllerVo> getResourceController() {
        List<ResourceControllerVo> resourceControllerVoList = new ArrayList<>();
        Map<String, Object> objectMap = applicationContext.getBeansWithAnnotation(RestController.class);
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            Class<?> aClass = entry.getValue().getClass();
            Tag tag = aClass.getAnnotation(Tag.class);
            if (tag == null) {
                continue;
            }
            RequestMapping requestMappingClass = aClass.getAnnotation(RequestMapping.class);
            String baseUrl = requestMappingClass.value()[0];
            ResourceControllerVo resourceControllerVo = buildController(tag, aClass, baseUrl);
            resourceControllerVoList.add(resourceControllerVo);

        }
        return resourceControllerVoList;
    }

    private ResourceControllerVo buildController(Tag tag, Class<?> aClass, String baseUrl) {
        ResourceControllerVo resourceControllerVo = new ResourceControllerVo();
        resourceControllerVo.setTagName(tag.name());
        List<ResourceMappingVo> resourceMappingVoList = buildMapping(aClass, baseUrl);
        resourceControllerVo.setMappingVoList(resourceMappingVoList);
        return resourceControllerVo;
    }

    private List<ResourceMappingVo> buildMapping(Class<?> aClass, String baseUrl) {
        List<ResourceMappingVo> resourceMappingVoList = new ArrayList<>();
        for (Method declaredMethod : aClass.getDeclaredMethods()) {
            ResourceMappingVo resourceMappingVo = setRequestMapping(declaredMethod, baseUrl);
            Operation operation = declaredMethod.getAnnotation(Operation.class);
            resourceMappingVo.setResourceName(operation.description());
            resourceMappingVo.setControlLayerClass(aClass.getName());
            resourceMappingVo.setControlLayerMethod(declaredMethod.getName());
            resourceMappingVoList.add(resourceMappingVo);

        }
        return resourceMappingVoList;
    }

    private ResourceMappingVo setRequestMapping(Method declaredMethod, String baseUrl) {
        ResourceMappingVo resourceMappingVo = new ResourceMappingVo();
        PostMapping postMapping = declaredMethod.getDeclaredAnnotation(PostMapping.class);
        if (postMapping != null) {
            resourceMappingVo.setRequestMethod(HttpMethod.POST.name());
            resourceMappingVo.setResourceUrl(baseUrl + postMapping.value()[0]);
            return resourceMappingVo;
        }
        GetMapping getMapping = declaredMethod.getDeclaredAnnotation(GetMapping.class);
        if (getMapping != null) {
            resourceMappingVo.setRequestMethod(HttpMethod.GET.name());
            resourceMappingVo.setResourceUrl(baseUrl + getMapping.value()[0]);
            return resourceMappingVo;
        }
        PutMapping putMapping = declaredMethod.getDeclaredAnnotation(PutMapping.class);
        if (putMapping != null) {
            resourceMappingVo.setRequestMethod(HttpMethod.PUT.name());
            resourceMappingVo.setResourceUrl(baseUrl + putMapping.value()[0]);
            return resourceMappingVo;
        }
        PatchMapping patchMapping = declaredMethod.getDeclaredAnnotation(PatchMapping.class);
        if (patchMapping != null) {
            resourceMappingVo.setRequestMethod(HttpMethod.PATCH.name());
            resourceMappingVo.setResourceUrl(baseUrl + patchMapping.value()[0]);
            return resourceMappingVo;
        }
        DeleteMapping deleteMapping = declaredMethod.getDeclaredAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            resourceMappingVo.setRequestMethod(HttpMethod.DELETE.name());
            resourceMappingVo.setResourceUrl(baseUrl + deleteMapping.value()[0]);
            return resourceMappingVo;

        }
        throw new ServiceException(ResourceErrors.RESOURCE_METHOD_IS_NOT_SUPPORT);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
