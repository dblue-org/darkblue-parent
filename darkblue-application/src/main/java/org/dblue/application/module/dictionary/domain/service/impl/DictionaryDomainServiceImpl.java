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

package org.dblue.application.module.dictionary.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dblue.application.commons.bus.EventBus;
import org.dblue.application.module.dictionary.application.dto.*;
import org.dblue.application.module.dictionary.domain.service.DictionaryDomainService;
import org.dblue.application.module.dictionary.domain.service.event.DictionaryDeleteEvent;
import org.dblue.application.module.dictionary.domain.service.event.DictionaryItemAddEvent;
import org.dblue.application.module.dictionary.error.DictionaryErrors;
import org.dblue.application.module.dictionary.error.DictionaryItemErrors;
import org.dblue.application.module.dictionary.infrastructure.entity.Dictionary;
import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.dblue.application.module.dictionary.infrastructure.repository.DictionaryItemRepository;
import org.dblue.application.module.dictionary.infrastructure.repository.DictionaryRepository;
import org.dblue.common.exception.ServiceException;
import org.dblue.common.id.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 字典领域服务层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/12 下午2:30
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class DictionaryDomainServiceImpl implements DictionaryDomainService {

    private final DictionaryRepository dictionaryRepository;
    private final DictionaryItemRepository dictionaryItemRepository;
    private final EventBus eventBus;

    /**
     * 字典添加
     *
     * @param addDto 字典信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(DictionaryAddDto addDto) {
        Optional<Dictionary> optional = dictionaryRepository.findByDictionaryCodeAndIsDeleteFalse(addDto.getDictionaryCode());
        if (optional.isPresent()) {
            throw new ServiceException(DictionaryErrors.DICTIONARY_EXITS);
        }
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(addDto, dictionary);
        dictionary.setDictionaryId(Snowflake.stringId());
        dictionary.setIsDelete(Boolean.FALSE);
        dictionaryRepository.save(dictionary);
    }

    /**
     * 字典更新
     *
     * @param updateDto 字典信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(DictionaryUpdateDto updateDto) {
        Optional<Dictionary> optional = dictionaryRepository.findByDictionaryIdAndIsDeleteFalse(updateDto.getDictionaryId());
        if (optional.isEmpty()) {
            throw new ServiceException(DictionaryErrors.DICTIONARY_IS_NOT_FOUND);
        }
        Optional<Dictionary> optionalDictionary = dictionaryRepository.findByDictionaryCodeAndDictionaryIdNotAndIsDeleteFalse(updateDto.getDictionaryCode(), updateDto.getDictionaryId());
        if (optionalDictionary.isPresent()) {
            throw new ServiceException(DictionaryErrors.DICTIONARY_EXITS);
        }

        BeanUtils.copyProperties(updateDto, optional.get());
        dictionaryRepository.save(optional.get());
    }

    /**
     * 字典删除
     *
     * @param dictionaryId 字典ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String dictionaryId) {
        Optional<Dictionary> optional = dictionaryRepository.findById(dictionaryId);
        if (optional.isEmpty()) {
            throw new ServiceException(DictionaryErrors.DICTIONARY_IS_NOT_FOUND);
        }
        boolean exists = dictionaryItemRepository.existsByDictionaryIdAndIsDeleteFalse(dictionaryId);
        if (Boolean.TRUE.equals(exists)) {
            throw new ServiceException(DictionaryErrors.DICTIONARY_CONTAIN_DICTIONARY_ITEM);
        }
        optional.get().setIsDelete(Boolean.TRUE);
        dictionaryRepository.save(optional.get());

        this.eventBus.fireEventAfterCommit(new DictionaryDeleteEvent(this, optional.get()));
    }

    /**
     * 字典添加
     *
     * @param addDto 字典信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addItem(DictionaryItemAddDto addDto) {

        Optional<Dictionary> dictionaryOptional = dictionaryRepository.findByDictionaryIdAndIsDeleteFalse(addDto.getDictionaryId());
        if (dictionaryOptional.isEmpty()) {
            throw new ServiceException(DictionaryErrors.DICTIONARY_IS_NOT_FOUND);
        }

        Optional<DictionaryItem> optional = dictionaryItemRepository.findByDictionaryIdAndCodeAndIsDeleteFalse(addDto.getDictionaryId(), addDto.getCode());
        if (optional.isPresent()) {
            throw new ServiceException(DictionaryItemErrors.DICTIONARY_ITEM_EXITS);
        }

        DictionaryItem dictionaryItem = new DictionaryItem();
        BeanUtils.copyProperties(addDto, dictionaryItem);
        dictionaryItem.setDictionaryItemId(Snowflake.stringId());
        dictionaryItem.setIsDelete(Boolean.FALSE);
        dictionaryItem.setIsEnabled(Boolean.TRUE);

        if (StringUtils.isBlank(addDto.getParentId())) {
            dictionaryItem.addItemLevel();
        } else {
            List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findByParentIdAndIsDeleteFalseOrderByOrderNumDesc(dictionaryItem.getParentId());
            if (CollectionUtils.isNotEmpty(dictionaryItemList)) {
                dictionaryItem.addItemLevel(dictionaryItemList.getFirst().getItemLevel());
            }
        }
        List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findByDictionaryIdAndIsDeleteFalseOrderByItemLevelDesc(addDto.getDictionaryId());
        if (CollectionUtils.isNotEmpty(dictionaryItemList)) {
            dictionaryItem.addOrderNum(dictionaryItemList.getFirst().getOrderNum());
        } else {
            dictionaryItem.addOrderNum();
        }
        dictionaryItemRepository.save(dictionaryItem);

        this.eventBus.fireEventAfterCommit(new DictionaryItemAddEvent(this, dictionaryOptional.get(), dictionaryItem));

    }

    /**
     * 字典更新
     *
     * @param updateDto 字典信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateItem(DictionaryItemUpdateDto updateDto) {
        Optional<DictionaryItem> optional = dictionaryItemRepository.findById(updateDto.getDictionaryItemId());
        if (optional.isEmpty()) {
            throw new ServiceException(DictionaryItemErrors.DICTIONARY_ITEM_IS_NOT_FOUND);
        }

        Optional<Dictionary> dictionaryOptional = dictionaryRepository.findByDictionaryIdAndIsDeleteFalse(optional.get().getDictionaryId());
        if (dictionaryOptional.isEmpty()) {
            throw new ServiceException(DictionaryErrors.DICTIONARY_IS_NOT_FOUND);
        }

        Optional<DictionaryItem> optionalDictionaryItem = dictionaryItemRepository.findByDictionaryIdAndCodeAndDictionaryItemIdNotAndIsDeleteFalse(optional
                .get().getDictionaryId(), updateDto.getCode(), updateDto.getDictionaryItemId());
        if (optionalDictionaryItem.isPresent()) {
            throw new ServiceException(DictionaryItemErrors.DICTIONARY_ITEM_EXITS);
        }
        BeanUtils.copyProperties(updateDto, optional.get());
        dictionaryItemRepository.save(optional.get());

        this.eventBus.fireEventAfterCommit(new DictionaryItemAddEvent(this, dictionaryOptional.get(), optional.get()));
    }

    /**
     * 字典项删除
     *
     * @param dictionaryItemId 字典项ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteItem(String dictionaryItemId) {
        Optional<DictionaryItem> optional = dictionaryItemRepository.findById(dictionaryItemId);
        if (optional.isEmpty()) {
            throw new ServiceException(DictionaryItemErrors.DICTIONARY_ITEM_IS_NOT_FOUND);
        }
        Optional<Dictionary> dictionaryOptional = dictionaryRepository.findByDictionaryIdAndIsDeleteFalse(optional.get().getDictionaryId());
        if (dictionaryOptional.isEmpty()) {
            throw new ServiceException(DictionaryErrors.DICTIONARY_IS_NOT_FOUND);
        }
        optional.get().setIsDelete(Boolean.TRUE);
        dictionaryItemRepository.save(optional.get());
        this.eventBus.fireEventAfterCommit(new DictionaryItemAddEvent(this, dictionaryOptional.get(), optional.get()));
    }

    /**
     * 字典项启用禁用
     *
     * @param enableDto 启用禁用信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enableItem(DictionaryItemEnableDto enableDto) {
        Optional<DictionaryItem> optional = dictionaryItemRepository.findById(enableDto.getDictionaryItemId());
        if (optional.isEmpty()) {
            throw new ServiceException(DictionaryItemErrors.DICTIONARY_ITEM_IS_NOT_FOUND);
        }
        optional.get().setIsEnabled(enableDto.getEnable());
        dictionaryItemRepository.save(optional.get());
    }
}
