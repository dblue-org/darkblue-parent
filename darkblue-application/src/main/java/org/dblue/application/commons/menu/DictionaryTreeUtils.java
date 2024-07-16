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
package org.dblue.application.commons.menu;

import org.dblue.application.module.dictionary.application.vo.DictionaryItemNodeForSelectVo;
import org.dblue.application.module.dictionary.application.vo.DictionaryItemTreeVo;
import org.dblue.application.module.dictionary.infrastructure.entity.DictionaryItem;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class DictionaryTreeUtils {

    private DictionaryTreeUtils() {
    }


    public static List<DictionaryItemTreeVo> toTree(List<DictionaryItem> items) {
        return TreeUtils.toTree(items, new TreeUtils.DataMapper<>() {
            @Override
            public boolean isRoot(DictionaryItem dictionaryItem) {
                return Objects.isNull(dictionaryItem.getParentId());
            }

            @Override
            public DictionaryItemTreeVo convert(DictionaryItem rawData) {
                DictionaryItemTreeVo treeVo = new DictionaryItemTreeVo();
                BeanUtils.copyProperties(rawData, treeVo);
                return treeVo;
            }

            @Override
            public boolean isSubNode(DictionaryItemTreeVo parent, DictionaryItem rawData) {
                return Objects.equals(parent.getDictionaryItemId(), rawData.getParentId());
            }

            @Override
            public void setChildren(DictionaryItemTreeVo parent, List<DictionaryItemTreeVo> nodes) {
                parent.setChildren(nodes);
            }
        });
    }

    public static List<DictionaryItemNodeForSelectVo> toSelectTree(List<DictionaryItem> items) {
        return TreeUtils.toTree(items, new TreeUtils.DataMapper<>() {
            @Override
            public boolean isRoot(DictionaryItem dictionaryItem) {
                return Objects.isNull(dictionaryItem.getParentId());
            }

            @Override
            public DictionaryItemNodeForSelectVo convert(DictionaryItem rawData) {
                return DictionaryItemNodeForSelectVo.of(rawData);
            }

            @Override
            public boolean isSubNode(DictionaryItemNodeForSelectVo parent, DictionaryItem rawData) {
                return Objects.equals(parent.getDictionaryItemId(), rawData.getParentId());
            }

            @Override
            public void setChildren(DictionaryItemNodeForSelectVo parent, List<DictionaryItemNodeForSelectVo> nodes) {
                parent.setChildren(nodes);
            }
        });
    }
}