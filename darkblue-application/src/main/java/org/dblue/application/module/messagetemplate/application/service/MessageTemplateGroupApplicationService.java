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
package org.dblue.application.module.messagetemplate.application.service;

import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateGroupAddDto;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateGroupUpdateDto;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateGroupVo;

import java.util.List;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface MessageTemplateGroupApplicationService {

    /**
     * 添加消息模板分组
     *
     * @param groupAddDto 消息模板分组信息
     */
    void add(MessageTemplateGroupAddDto groupAddDto);


    /**
     * 更新消息模板分组
     *
     * @param updateDto 消息模板分组信息
     */
    void update(MessageTemplateGroupUpdateDto updateDto);

    /**
     * 查询所有模板组
     *
     * @return 模板分组列表
     */
    List<MessageTemplateGroupVo> findAll();

    /**
     * 删除模板分组
     *
     * @param messageTemplateGroupId 模板分组ID
     */
    void delete(String messageTemplateGroupId);
}
