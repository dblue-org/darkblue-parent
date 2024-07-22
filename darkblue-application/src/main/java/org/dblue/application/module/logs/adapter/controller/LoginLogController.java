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
package org.dblue.application.module.logs.adapter.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dblue.application.module.logs.application.dto.LoginLogQueryDto;
import org.dblue.application.module.logs.application.service.LoginLogApplicationService;
import org.dblue.application.module.logs.application.vo.LoginLogPageListVo;
import org.dblue.core.annotation.Platform;
import org.dblue.core.web.result.PageResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Tag(name = "登录日志")
@Platform
@RestController
@RequestMapping("/api/login-log")
public class LoginLogController {

    private final LoginLogApplicationService loginLogApplicationService;

    public LoginLogController(LoginLogApplicationService loginLogApplicationService) {
        this.loginLogApplicationService = loginLogApplicationService;
    }

    /**
     * 登录日志查询
     *
     * @param queryDto 查询参数
     * @return 登录日志
     */
    @Operation(summary = "登录日志分页查询")
    @GetMapping("/findByPage")
    public PageResponseBean<LoginLogPageListVo> findByPage(LoginLogQueryDto queryDto) {
        IPage<LoginLogPageListVo> voPage = loginLogApplicationService.findByPage(queryDto);
        return PageResponseBean.success(voPage);
    }

}