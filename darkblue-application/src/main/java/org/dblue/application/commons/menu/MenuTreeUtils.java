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

import org.dblue.application.module.menu.application.vo.BaseMenuTreeNodeVo;
import org.dblue.application.module.menu.infrastructure.entity.Menu;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class MenuTreeUtils {

    private MenuTreeUtils() {
    }

    public static <R extends BaseMenuTreeNodeVo> List<R> buildTree(List<Menu> allNodes, Function<Menu, R> converter) {

        return TreeUtils.toTree(allNodes, new TreeUtils.DataMapper<>() {
            @Override
            public boolean isRoot(Menu menu) {
                return Objects.isNull(menu.getParentId());
            }

            @Override
            public R convert(Menu rawData) {
                return converter.apply(rawData);
            }

            @Override
            public boolean isSubNode(R parent, Menu rawData) {
                return Objects.equals(parent.getMenuId(), rawData.getParentId());
            }

            @Override
            public void setChildren(R parent, List<R> nodes) {
                parent.setChildren(nodes);
            }
        });
    }

}