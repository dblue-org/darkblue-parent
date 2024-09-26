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
package org.dblue.application.module.messagetemplate.application.vo;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.messagetemplate.application.vars.VarNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
public class VarTreeNodeVo {

    private String key;

    private String title;

    private Boolean isLeaf;

    private List<VarTreeNodeVo> children;

    public static VarTreeNodeVo of(VarNode varNode) {
        VarTreeNodeVo varTreeNodeVo = new VarTreeNodeVo();
        varTreeNodeVo.setKey(varNode.getName());
        varTreeNodeVo.setTitle(varNode.getName());
        varTreeNodeVo.setIsLeaf(CollectionUtils.isEmpty(varNode.getChildren()));

        if (CollectionUtils.isNotEmpty(varNode.getChildren())) {
            for (VarNode child : varNode.getChildren()) {
                VarTreeNodeVo childNode = VarTreeNodeVo.of(child);
                varTreeNodeVo.addChild(childNode);
            }
        }
        return varTreeNodeVo;
    }

    public void addChild(VarTreeNodeVo treeNodeVo) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(treeNodeVo);
    }
}