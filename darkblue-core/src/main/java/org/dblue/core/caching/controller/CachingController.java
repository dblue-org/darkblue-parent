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
package org.dblue.core.caching.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.dblue.core.caching.CachingInitService;
import org.dblue.core.caching.vo.CachingVo;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 缓存处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
@Tag(name = "缓存处理")
@RequestMapping("/api/ops/caching")
@RestController
public class CachingController {

    private final List<CachingInitService> cachingServiceList;

    public CachingController(@Autowired(required = false) List<CachingInitService> cachingInitServiceList) {
        this.cachingServiceList = cachingInitServiceList;
    }

    @Operation(summary = "获取缓存列表")
    @GetMapping("/getCacheList")
    public ResponseBean<CachingVo[]> getCacheList() {
        CachingVo[] cachingVos = new CachingVo[cachingServiceList.size()];
        for (int i = 0; i < cachingServiceList.size(); i++) {
            cachingVos[i] = CachingVo.toCachingVo(cachingServiceList.get(i));
        }
        return ResponseBean.success(cachingVos);
    }

    @Operation(summary = "刷新缓存")
    @GetMapping("/refreshCache")
    public ResponseBean<Void> refreshCache(String cacheCode) {
        for (CachingInitService cachingInitService : cachingServiceList) {
            if (cachingInitService.getCacheCode().equals(cacheCode)) {
                log.info("刷新缓存：{}-{}", cachingInitService.getCacheCode(), cachingInitService.getCacheName());
                cachingInitService.refresh();
            }
        }
        return ResponseBean.success();
    }
}