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

import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateAddDto;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateQueryDto;
import org.dblue.application.module.messagetemplate.application.dto.MessageTemplateUpdateDto;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateDetailsVo;
import org.dblue.application.module.messagetemplate.application.vo.MessageTemplateListVo;
import org.springframework.data.domain.Page;

/**
 * 消息模板业务
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public interface MessageTemplateApplicationService {

    /**
     * 添加消息模板
     *
     * @param addDto 消息模板数据
     */
    void add(MessageTemplateAddDto addDto);

    /**
     * 更新消息模板
     *
     * @param updateDto 消息模板数据
     */
    void update(MessageTemplateUpdateDto updateDto);

    /**
     * 删除消息模板
     *
     * @param messageTemplateId 消息模板ID
     */
    void delete(String messageTemplateId);

    /**
     * 分页查询消息模板
     *
     * @param queryDto 消息模板查询条件
     * @return 消息模板列表
     */
    Page<MessageTemplateListVo> findByPage(MessageTemplateQueryDto queryDto);

    /**
     * 获取消息模板详情
     *
     * @param messageTemplateId 消息模板ID
     * @param withVars          是否包含变量信息
     * @return 消息模板详情
     */
    MessageTemplateDetailsVo getDetails(String messageTemplateId, boolean withVars);
}
