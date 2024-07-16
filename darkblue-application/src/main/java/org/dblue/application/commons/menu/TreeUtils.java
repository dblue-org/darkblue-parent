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

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class TreeUtils {

    private TreeUtils() {
    }

    public static <T, R> List<R> toTree(List<T> dataList, DataMapper<T, R> mapper) {
        List<T> roots = dataList.stream().filter(mapper::isRoot).toList();

        List<R> rootNodes = roots.stream().map(mapper::convert).toList();

        for (R rootNode : rootNodes) {
            setChildren(rootNode, dataList, mapper);
        }
        return rootNodes;
    }

    public static <T, R> void setChildren(R parent, List<T> dataList, DataMapper<T, R> mapper) {
        List<R> children = dataList.stream().filter(rawData -> mapper.isSubNode(parent, rawData))
                .map(mapper::convert).toList();
        if (CollectionUtils.isNotEmpty(children)) {
            mapper.setChildren(parent, children);
            for (R child : children) {
                setChildren(child, dataList, mapper);
            }
        }
    }

    public interface DataMapper<T, R> {

        boolean isRoot(T t);

        R convert(T rawData);

        boolean isSubNode(R parent, T rawData);

        void setChildren(R parent, List<R> nodes);
    }
}