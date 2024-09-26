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

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.runtime.RuntimeInstance;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.ASTNegateNode;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.runtime.visitor.BaseVisitor;

import java.io.StringReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
public class VelocityVariableExtractor {

    private static final RuntimeInstance RI = new RuntimeInstance();

    private VelocityVariableExtractor() {
    }

    public static Set<String> extractVariables(String templateContent) {
        try {
            Template template = new Template();
            StringReader reader = new StringReader(templateContent);
            SimpleNode node = RI.parse(reader, template);
            VariableExtractingVisitor visitor = new VariableExtractingVisitor();
            node.jjtAccept(visitor, null);
            return visitor.getVariables();
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    @Getter
    private static class VariableExtractingVisitor extends BaseVisitor {
        private final Set<String> variables = new HashSet<>();

        @Override
        public Object visit(ASTReference node, Object data) {
            String varStr = tripIdentifier(node.literal());
            if (!isFunction(varStr)) {
                variables.add(varStr);
            }
            return super.visit(node, data);
        }

        private static String tripIdentifier(String varStr) {
            if (varStr.startsWith("$!{")) {
                return varStr.replace("$!{", "").replace("}", "");
            } else if (varStr.startsWith("${")) {
                return varStr.replace("${", "").replace("}", "");
            } else if (varStr.startsWith("$!")) {
                return varStr.replace("$!", "");
            } else {
                return varStr.replace("$", "");
            }
        }

        private static boolean isFunction(String varStr) {
            return varStr.contains("(") && varStr.contains(")");
        }

        @Override
        public Object visit(ASTNegateNode node, Object data) {
            return null;
        }
    }

}