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
package org.dblue.application.module.messagetemplate.application.vars;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
public class VarNode {

    private String name;

    private List<VarNode> children;

    public static VarNode of(String name) {
        VarNode varNode = new VarNode();
        varNode.setName(name);
        return varNode;
    }

    public static VarNode create(String var) {
        int index = var.indexOf('.');
        if (index > 0) {
            String parentObjectVar = var.substring(0, index);
            String childVar = var.substring(index + 1);
            VarNode parent = VarNode.of(parentObjectVar);
            parent.addChild(create(childVar));
            return parent;
        } else {
            return VarNode.of(var);
        }
    }

    public void addChild(VarNode child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    public void merge(VarNode sameParentVar) {
        if (CollectionUtils.isEmpty(sameParentVar.getChildren())) {
            return;
        }
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        List<VarNode> newChildren = sameParentVar.getChildren();
        Map<String, VarNode> childrenMap = this.children.stream().collect(Collectors.toMap(VarNode::getName, Function.identity()));
        for (VarNode newChild : newChildren) {
            VarNode childNode = childrenMap.get(newChild.getName());
            if (childNode == null) {
                this.children.add(newChild);
            } else {
                childNode.merge(newChild);
            }
        }
    }

}