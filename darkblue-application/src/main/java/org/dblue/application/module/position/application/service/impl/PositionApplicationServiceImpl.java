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

package org.dblue.application.module.position.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.position.application.dto.PositionPageDto;
import org.dblue.application.module.position.application.service.PositionApplicationService;
import org.dblue.application.module.position.application.vo.PositionPageVo;
import org.dblue.application.module.position.application.vo.PositionVo;
import org.dblue.application.module.position.application.vo.SimplePositionVo;
import org.dblue.application.module.position.domain.service.PositionDomainService;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.application.module.user.domain.service.UserDomainQueryService;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 职位应用层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/9 上午10:39
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PositionApplicationServiceImpl implements PositionApplicationService {

    private final PositionDomainService positionDomainService;
    private final UserDomainQueryService userDomainQueryService;

    /**
     * 职位单个查询
     *
     * @param positionId 职位ID
     * @return 职位信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PositionVo getDetails(String positionId) {
        Position position = positionDomainService.getOne(positionId);
        if (Objects.isNull(position)) {
            return PositionVo.of();
        }
        PositionVo positionVo = new PositionVo();
        BeanUtils.copyProperties(position, positionVo);
        List<User> userList = userDomainQueryService.findByPositionIdIn(Set.of(positionId));
        if (CollectionUtils.isNotEmpty(userList)) {
            positionVo.setUserNums(userList.size());
        }
        return positionVo;
    }

    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 职位信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<PositionPageVo> page(PositionPageDto pageDto) {
        Page<Position> page = positionDomainService.page(pageDto);
        if (page.isEmpty()) {
            return Page.empty();
        }
        List<User> userList = userDomainQueryService.findByPositionIdIn(page.map(Position::getPositionId)

                                                                            .toSet());
        Map<String, Long> positionIdMap = HashMap.newHashMap(16);
        if (CollectionUtils.isNotEmpty(userList)) {
            positionIdMap = userList.stream()
                                    .collect(Collectors.groupingBy(User::getPositionId, Collectors.counting()));

        }

        Map<String, Long> finalPositionIdMap = positionIdMap;
        return page.map(position -> {
            PositionPageVo positionPageVo = new PositionPageVo();
            BeanUtils.copyProperties(position, positionPageVo);
            positionPageVo.setUserNums(finalPositionIdMap.getOrDefault(position.getPositionId(), 0L).intValue());
            return positionPageVo;
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<SimplePositionVo> findAll(String keyword) {
        List<Position> positions = this.positionDomainService.findAll(keyword);
        return positions.stream().map(SimplePositionVo::of).toList();
    }
}
