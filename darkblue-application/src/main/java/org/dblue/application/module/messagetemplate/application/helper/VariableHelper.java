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
package org.dblue.application.module.messagetemplate.application.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.messagetemplate.application.vo.VarNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class VariableHelper {

    private VariableHelper() {
    }

    public static List<VarNode> merge(List<VarNode> nodes) {
        if (CollectionUtils.isEmpty(nodes)) {
            return List.of();
        }

        Map<String, VarNode> nodeMap = new HashMap<>();
        for (VarNode varNode : nodes) {
            VarNode exist = nodeMap.get(varNode.getName());
            if (exist != null) {
                exist.merge(varNode);
            } else {
                nodeMap.put(varNode.getName(), varNode);
            }
        }
        return new ArrayList<>(nodeMap.values());
    }

}