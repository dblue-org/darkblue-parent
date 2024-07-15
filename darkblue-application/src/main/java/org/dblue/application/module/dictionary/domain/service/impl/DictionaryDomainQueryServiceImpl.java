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
import org.dblue.application.module.dictionary.application.dto.DictionaryItemPageDto;
import org.dblue.application.module.dictionary.domain.service.DictionaryDomainQueryService;
import org.dblue.application.module.dictionary.infrastructure.entity.Dictionary;
import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.dblue.application.module.dictionary.infrastructure.repository.DictionaryItemRepository;
import org.dblue.application.module.dictionary.infrastructure.repository.DictionaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典领域查询服务层
 *
 * @author xie jin
 * @since 1.0.0  2024/7/12 下午2:30
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class DictionaryDomainQueryServiceImpl implements DictionaryDomainQueryService {

    private final DictionaryRepository dictionaryRepository;
    private final DictionaryItemRepository dictionaryItemRepository;

    /**
     * 获取全部字典信息
     *
     * @return 字典信息
     */
    @Override
    public List<Dictionary> getAll() {
        return dictionaryRepository.findByIsDeleteFalse();
    }

    @Override
    public List<DictionaryItem> getItemTree(String dictionaryId) {
        return dictionaryItemRepository.findByDictionaryIdAndIsDeleteFalse(dictionaryId);
    }


    /**
     * 字典项分页
     *
     * @param itemPageDto 查询参数
     * @return 字典项
     */
    @Override
    public Page<DictionaryItem> page(DictionaryItemPageDto itemPageDto) {
        return dictionaryItemRepository.page(itemPageDto, itemPageDto.toJpaPage());
    }
}
