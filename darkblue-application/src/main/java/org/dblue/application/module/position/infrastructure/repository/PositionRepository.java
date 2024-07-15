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

package org.dblue.application.module.position.infrastructure.repository;

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.jpa.BaseJpaRepository;
import org.dblue.application.module.position.application.dto.PositionPageDto;
import org.dblue.application.module.position.infrastructure.entity.Position;
import org.dblue.application.module.position.infrastructure.entity.QPosition;
import org.dblue.application.module.position.infrastructure.query.PositionQuery;
import org.dblue.application.module.position.infrastructure.query.impl.PositionQueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * 职位
 *
 * @author xie jin
 * @since 1.0.0  2024-07-09 09:28:20
 */
public interface PositionRepository extends BaseJpaRepository<Position, String> {

    /**
     * 新增查询用
     *
     * @param positionCode 职位编号
     * @param positionName 职位名称
     * @return 职位
     */
    default Optional<Position> findByPositionCodeOrPositionNameAndIsDelFalse(
            String positionCode, String positionName) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QPosition.position.positionCode.eq(positionCode)
                                                   .or(QPosition.position.positionName.eq(positionName)));
        builder.and(QPosition.position.isDel.isFalse());
        return getOne(builder);

    }


    /**
     * 更新排重用
     *
     * @param positionCode 职位编号
     * @param positionName 职位名称
     * @param positionId   职位ID
     * @return 职位
     */
    default Optional<Position> findByPositionCodeOrPositionNameAndPositionIdNotAndIsDelFalse(
            @NonNull String positionCode, @NonNull String positionName, @NonNull String positionId) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QPosition.position.positionCode.eq(positionCode)
                                                   .or(QPosition.position.positionName.eq(positionName)));
        builder.and(QPosition.position.positionId.ne(positionId));
        builder.and(QPosition.position.isDel.isFalse());
        return getOne(builder);
    }

    /**
     * 分页查询
     *
     * @param pageDto  查询参数
     * @param pageable 分页
     * @return 分页
     */
    default Page<Position> page(PositionPageDto pageDto, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(pageDto.getPositionCode())) {
            builder.and(QPosition.position.positionCode.eq(pageDto.getPositionCode()));
        }
        if (StringUtils.isNotBlank(pageDto.getPositionName())) {
            builder.and(QPosition.position.positionName.eq(pageDto.getPositionName()));
        }
        builder.and(QPosition.position.isDel.isFalse());
        QSort qSort = new QSort(QPosition.position.createTime.desc());
        return page(builder, pageable, qSort);
    }

    /**
     * 查询未删除的职位信息
     *
     * @param positionId 职位ID
     * @return 职位
     */
    Optional<Position> findByPositionIdAndIsDelFalse(@NonNull String positionId);


    /**
     * 创建查询器
     *
     * @return 查询器
     */
    default PositionQuery createQuery() {
        return new PositionQueryImpl(this);
    }


}