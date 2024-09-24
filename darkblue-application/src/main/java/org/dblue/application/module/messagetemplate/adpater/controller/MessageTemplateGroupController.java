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
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateGroupAddDto;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateGroupUpdateDto;
import org.dblue.application.module.messagetemplate.application.service.MessageTemplateGroupApplicationService;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateGroupVo;
import org.dblue.core.web.result.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息模板组
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Tag(name = "消息模板组")
@RequestMapping("/api/message-template-group")
@RestController
public class MessageTemplateGroupController {

    private final MessageTemplateGroupApplicationService messageTemplateGroupApplicationService;


    /**
     * 添加消息模板分组
     *
     * @param groupAddDto 消息模板分组信息
     */
    @Operation(summary = "添加消息模板分组")
    @PostMapping("/add")
    public ResponseBean<Void> add(@RequestBody @Valid MessageTemplateGroupAddDto groupAddDto) {
        this.messageTemplateGroupApplicationService.add(groupAddDto);
        return ResponseBean.success();
    }


    /**
     * 更新消息模板分组
     *
     * @param updateDto 消息模板分组信息
     */
    @Operation(summary = "更新消息模板分组")
    @PutMapping("/update")
    public ResponseBean<Void> update(@RequestBody @Valid MessageTemplateGroupUpdateDto updateDto) {
        this.messageTemplateGroupApplicationService.update(updateDto);
        return ResponseBean.success();
    }

    /**
     * 查询所有模板组
     *
     * @return 模板分组列表
     */
    @Operation(summary = "查询所有模板组")
    @GetMapping("/findAll")
    public ResponseBean<List<MessageTemplateGroupVo>> findAll() {
        List<MessageTemplateGroupVo> voList = this.messageTemplateGroupApplicationService.findAll();
        return ResponseBean.success(voList);
    }

    /**
     * 删除模板分组
     *
     * @param messageTemplateGroupId 模板分组ID
     */
    @Operation(summary = "删除模板分组")
    @DeleteMapping("/delete/{messageTemplateGroupId}")
    public ResponseBean<Void> delete(@PathVariable String messageTemplateGroupId) {
        this.messageTemplateGroupApplicationService.delete(messageTemplateGroupId);
        return ResponseBean.success();
    }
}