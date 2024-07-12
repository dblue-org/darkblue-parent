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

package org.dblue.application.module.position.domain.service;

import org.dblue.application.module.position.application.dto.PositionAddDto;
import org.dblue.application.module.position.application.dto.PositionEnableDto;
import org.dblue.application.module.position.application.dto.PositionPageDto;
import org.dblue.application.module.position.application.dto.PositionUpdateDto;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.application.module.position.infrastructure.query.PositionQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 职位领域层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/9 上午9:48
 */
public interface PositionDomainService {


    /**
     * 职位添加
     *
     * @param addDto 职位信息
     */
    void add(PositionAddDto addDto);


    /**
     * 职位更新
     *
     * @param updateDto 职位信息
     */
    void update(PositionUpdateDto updateDto);

    /**
     * 职位删除
     *
     * @param positionId 职位ID
     */
    void delete(String positionId);

    /**
     * 启用禁用
     *
     * @param enableDto 启用禁用信息
     */
    void toggleState(PositionEnableDto enableDto);

    /**
     * 职位单个查询
     *
     * @param positionId 职位ID
     * @return 职位信息
     */
    Position getOne(String positionId);

    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 职位信息
     */
    Page<Position> page(PositionPageDto pageDto);

    /**
     * 获取所有职位
     *
     * @param keyword 关键字，模糊匹配职位编码和职位名称
     * @return 职位列表
     */
    List<Position> findAll(String keyword);

    /**
     * 创建查询器
     *
     * @return 查询器
     */
    PositionQuery createQuery();


}
