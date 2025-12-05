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
package org.dblue.application.module.messagetemplate.adpater.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dblue.application.commons.EnumValue;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateAddDto;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateQueryDto;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateUpdateDto;
import org.dblue.application.module.messagetemplate.application.service.MessageTemplateApplicationService;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateDetailsVo;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateListVo;
import org.dblue.application.module.messagetemplate.domain.enums.RouterTypes;
import org.dblue.core.annotation.Platform;
import org.dblue.core.web.result.PageResponseBean;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息模板
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Platform
@RequiredArgsConstructor
@Tag(name = "消息模板")
@RequestMapping("/api/message-template")
@RestController
public class MessageTemplateController {

    private final MessageTemplateApplicationService messageTemplateApplicationService;


    /**
     * 获取所有路由类型
     *
     * @return 路由类型列表
     */
    @Operation(summary = "获取所有路由类型")
    @GetMapping("/getRouterTypes")
    public ResponseBean<List<EnumValue>> getRouterTypes() {
        return ResponseBean.success(RouterTypes.all());
    }


    /**
     * 添加消息模板
     *
     * @param addDto 消息模板数据
     */
    @Operation(summary = "添加消息模板")
    @PostMapping("/add")
    public ResponseBean<Void> add(@RequestBody @Valid MessageTemplateAddDto addDto) {
        this.messageTemplateApplicationService.add(addDto);
        return ResponseBean.success();
    }

    /**
     * 更新消息模板
     *
     * @param updateDto 消息模板数据
     */
    @Operation(summary = "更新消息模板")
    @PutMapping("/update")
    public ResponseBean<Void> update(@RequestBody @Valid MessageTemplateUpdateDto updateDto) {
        this.messageTemplateApplicationService.update(updateDto);
        return ResponseBean.success();
    }

    /**
     * 删除消息模板
     *
     * @param messageTemplateId 消息模板ID
     */
    @Operation(summary = "删除消息模板")
    @DeleteMapping("/delete/{messageTemplateId}")
    public ResponseBean<Void> delete(@PathVariable String messageTemplateId) {
        this.messageTemplateApplicationService.delete(messageTemplateId);
        return ResponseBean.success();
    }

    /**
     * 分页查询消息模板
     *
     * @param queryDto 消息模板查询条件
     * @return 消息模板列表
     */
    @Operation(summary = "分页查询消息模板")
    @GetMapping("/findByPage")
    public PageResponseBean<MessageTemplateListVo> findByPage(MessageTemplateQueryDto queryDto) {
        Page<MessageTemplateListVo> voPage = this.messageTemplateApplicationService.findByPage(queryDto);
        return PageResponseBean.success(voPage);
    }

    /**
     * 获取消息模板详情
     *
     * @param messageTemplateId 消息模板ID
     * @param withVars          是否包含变量信息
     * @return 消息模板详情
     */
    @Operation(summary = "获取消息模板详情")
    @GetMapping("/getDetails/{messageTemplateId}")
    public ResponseBean<MessageTemplateDetailsVo> getDetails(
            @PathVariable String messageTemplateId,
            @RequestParam(required = false, defaultValue = "false") boolean withVars) {
        MessageTemplateDetailsVo vo = this.messageTemplateApplicationService.getDetails(messageTemplateId, withVars);
        return ResponseBean.success(vo);
    }

}