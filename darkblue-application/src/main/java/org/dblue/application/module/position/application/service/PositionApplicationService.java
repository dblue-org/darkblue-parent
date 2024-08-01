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

package org.dblue.application.module.position.application.service;

import org.dblue.application.module.position.application.dto.PositionPageDto;
import org.dblue.application.module.position.application.vo.PositionPageVo;
import org.dblue.application.module.position.application.vo.PositionVo;
import org.dblue.application.module.position.application.vo.SimplePositionVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 职位应用层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/9 上午10:37
 */
public interface PositionApplicationService {

    /**
     * 职位单个查询
     *
     * @param positionId 职位ID
     * @return 职位信息
     */
    PositionVo getDetails(String positionId);

    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 职位信息
     */
    Page<PositionPageVo> page(PositionPageDto pageDto);

    /**
     * 查询所有职位信息
     *
     * @param keyword 关键字，名称或编码
     * @return 职位列表
     */
    List<SimplePositionVo> findAll(String keyword);
}
