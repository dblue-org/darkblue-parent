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

package org.dblue.application.module.position.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.module.position.application.dto.PositionAddDto;
import org.dblue.application.module.position.application.dto.PositionEnableDto;
import org.dblue.application.module.position.application.dto.PositionPageDto;
import org.dblue.application.module.position.application.dto.PositionUpdateDto;
import org.dblue.application.module.position.domain.service.PositionDomainService;
import org.dblue.application.module.position.errors.PositionErrors;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.application.module.position.infrastructure.query.PositionQuery;
import org.dblue.application.module.position.infrastructure.repository.PositionRepository;
import org.dblue.common.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 职位领域层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/9 上午9:54
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PositionDomainServiceImpl implements PositionDomainService {

    private final PositionRepository positionRepository;

    /**
     * 职位添加
     *
     * @param addDto 职位信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(PositionAddDto addDto) {
        Optional<Position> optionalPosition = positionRepository.findByPositionCodeAndPositionNameAndIsDelFalse(addDto.getPositionCode(), addDto.getPositionName());
        if (optionalPosition.isPresent()) {
            throw new ServiceException(PositionErrors.POSITION_EXITS);
        }
        Position position = new Position();
        BeanUtils.copyProperties(addDto, position);
        position.init();
        positionRepository.save(position);
    }

    /**
     * 职位更新
     *
     * @param updateDto 职位信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(PositionUpdateDto updateDto) {

        Optional<Position> optional = positionRepository.findById(updateDto.getPositionId());
        if (optional.isEmpty()) {
            throw new ServiceException(PositionErrors.POSITION_IS_NOT_FOUND);
        }
        Optional<Position> optionalPosition = positionRepository.findByPositionCodeAndPositionNameAndPositionIdNotAndIsDelFalse(updateDto.getPositionCode(), updateDto.getPositionName(), updateDto.getPositionId());
        if (optionalPosition.isPresent()) {
            throw new ServiceException(PositionErrors.POSITION_EXITS);
        }
        BeanUtils.copyProperties(updateDto, optional.get());
        positionRepository.save(optional.get());
    }

    /**
     * 职位删除
     *
     * @param positionId 职位ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String positionId) {
        Optional<Position> optional = positionRepository.findById(positionId);
        if (optional.isEmpty()) {
            throw new ServiceException(PositionErrors.POSITION_IS_NOT_FOUND);
        }
        optional.get().delete();
        positionRepository.save(optional.get());
    }

    /**
     * 启用禁用
     *
     * @param enableDto 启用禁用信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void toggleState(PositionEnableDto enableDto) {
        Optional<Position> optional = positionRepository.findById(enableDto.getPositionId());
        if (optional.isEmpty()) {
            throw new ServiceException(PositionErrors.POSITION_IS_NOT_FOUND);
        }
        if (Boolean.TRUE.equals(enableDto.getEnable())) {
            optional.get().enable();
        } else {
            optional.get().disable();
        }
        positionRepository.save(optional.get());
    }

    /**
     * 职位单个查询
     *
     * @param positionId 职位ID
     * @return 职位信息
     */
    @Override
    public Position getOne(String positionId) {
        if (StringUtils.isBlank(positionId)) {
            return null;
        }
        Optional<Position> optional = this.positionRepository.createQuery().positionId(positionId).single();
        return optional.orElse(null);
    }

    /**
     * 分页
     *
     * @param pageDto 查询信息
     * @return 职位信息
     */
    @Override
    public Page<Position> page(PositionPageDto pageDto) {
        return positionRepository.page(pageDto, pageDto.toJpaPage());
    }

    @Override
    public List<Position> findAll(String keyword) {
        PositionQuery positionQuery = this.positionRepository.createQuery()
                .positionCodeLike(keyword)
                .positionNameLike(keyword);
        return positionQuery.list();
    }

    @Override
    public PositionQuery createQuery() {
        return this.positionRepository.createQuery();
    }
}
