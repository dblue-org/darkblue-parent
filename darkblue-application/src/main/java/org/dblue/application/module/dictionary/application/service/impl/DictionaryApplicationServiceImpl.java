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

package org.dblue.application.module.dictionary.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.commons.menu.DictionaryTreeUtils;
import org.dblue.application.module.dictionary.application.dto.DictionaryItemPageDto;
import org.dblue.application.module.dictionary.application.service.DictionaryApplicationService;
import org.dblue.application.module.dictionary.application.vo.*;
import org.dblue.application.module.dictionary.domain.service.DictionaryDomainQueryService;
import org.dblue.application.module.dictionary.enums.DictionaryType;
import org.dblue.application.module.dictionary.infrastructure.entity.Dictionary;
import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 字典应用层服务
 *
 * @author xie jin
 * @since 1.0.0  2024/7/15 上午10:37
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class DictionaryApplicationServiceImpl implements DictionaryApplicationService {

    private final DictionaryDomainQueryService dictionaryDomainQueryService;

    /**
     * 获取全部字典信息
     *
     * @return 字典信息
     */
    @Override
    public List<DictionaryVo> getAll() {

        List<Dictionary> all = dictionaryDomainQueryService.getAll();
        if (CollectionUtils.isNotEmpty(all)) {
            return all.stream().map(dictionary -> {
                DictionaryVo dictionaryVo = new DictionaryVo();
                BeanUtils.copyProperties(dictionary, dictionaryVo);
                return dictionaryVo;
            }).toList();
        }
        return List.of();
    }

    /**
     * 获取树形字典项
     *
     * @param dictionaryId 字典ID
     * @return 字典
     */
    @Override
    public List<DictionaryItemTreeVo> getItemTree(String dictionaryId) {
        List<DictionaryItem> itemList = dictionaryDomainQueryService.getItems(dictionaryId);
        if (CollectionUtils.isEmpty(itemList)) {
            return List.of();
        }
        return DictionaryTreeUtils.toTree(itemList);
    }

    /**
     * 字典项分页
     *
     * @param itemPageDto 查询参数
     * @return 字典项
     */
    @Override
    public Page<DictionaryItemPageVo> page(DictionaryItemPageDto itemPageDto) {
        Page<DictionaryItem> page = dictionaryDomainQueryService.page(itemPageDto);
        if (page.isEmpty()) {
            return Page.empty();
        }
        return page.map(dictionaryItem -> {
            DictionaryItemPageVo dictionaryItemPageVo = new DictionaryItemPageVo();
            BeanUtils.copyProperties(dictionaryItem, dictionaryItemPageVo);
            return dictionaryItemPageVo;
        });

    }

    @Override
    public DictionaryForSelectVo getDictionaryForSelect(String dictionaryCode) {
        Dictionary dictionary = this.dictionaryDomainQueryService.findByDictionaryCode(dictionaryCode);
        DictionaryForSelectVo selectVo = DictionaryForSelectVo.of(dictionary);

        List<DictionaryItem> items = this.dictionaryDomainQueryService.getItems(dictionary.getDictionaryId());
        if (CollectionUtils.isEmpty(items)) {
            selectVo.setItems(Collections.emptyList());
            return selectVo;
        }

        // 如果是普通字典直接转为VO，如果是属性字典则转为树。统一返回，由前端根据类型自动处理
        if (DictionaryType.GENERAL.equalsTo(dictionary.getDictionaryType())) {
            selectVo.setItems(items.stream().map(DictionaryItemNodeForSelectVo::of).toList());
        } else {
            selectVo.setItems(DictionaryTreeUtils.toSelectTree(items));
        }
        return selectVo;
    }
}
