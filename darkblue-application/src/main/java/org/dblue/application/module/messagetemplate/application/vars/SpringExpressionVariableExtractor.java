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
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.CompositeStringExpression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelNode;
import org.springframework.expression.spel.ast.PropertyOrFieldReference;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取 SPEL 变量
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class SpringExpressionVariableExtractor {

    private static final ExpressionParser PARSER = new SpelExpressionParser();
    private static final TemplateParserContext PARSER_CONTEXT = new TemplateParserContext("${", "}");

    private SpringExpressionVariableExtractor() {
    }

    public static Set<String> extractVariables(String templateStr) {
        Expression expression = PARSER.parseExpression(templateStr, PARSER_CONTEXT);
        List<VarDef> varSet = new ArrayList<>();
        findOutVarByExpression(expression, varSet);
        return mergeVars(varSet);
    }

    private static void findOutVarByExpression(Expression rootExpression, List<VarDef> varSet) {
        if (rootExpression instanceof SpelExpression spelExpression) {
            SpelNode spelNode = spelExpression.getAST();
            findOutVar(spelNode, varSet);
        } else if (rootExpression instanceof CompositeStringExpression compositeStringExpression) {
            Expression[] expressions = compositeStringExpression.getExpressions();
            for (Expression expression : expressions) {
                findOutVarByExpression(expression, varSet);
            }
        }
    }

    private static void findOutVar(SpelNode node, List<VarDef> varSet) {
        if (node instanceof PropertyOrFieldReference pNode) {
            varSet.add(new VarDef(pNode));
        }
        if (node.getChildCount() > 0) {
            for (int i = 0; i < node.getChildCount(); i++) {
                findOutVar(node.getChild(i), varSet);
            }
        }
    }

    /**
     * 此处用于处理 #{serviceData.xxx.xxx} 这种变量的情况，Spring会解析为3个连续的PropertyOrFieldReference。首尾相接。
     * <p>
     * 处理思路：将变量信息入栈，入栈时获取栈顶元素，如果栈顶元素的结束位置+1 = 当前变量的开始位置，则合并这两个变量。
     * </p>
     */
    private static Set<String> mergeVars(List<VarDef> varDefList) {
        Deque<VarDef> stack = new ArrayDeque<>();
        for (VarDef varDef : varDefList) {
            if (stack.isEmpty()) {
                stack.push(varDef);
            } else {
                VarDef top = stack.peek();
                if (top.end + 1 == varDef.start) {
                    top.merge(varDef);
                } else {
                    stack.push(varDef);
                }
            }
        }
        return stack.stream().map(VarDef::getName).collect(Collectors.toSet());
    }

    @Data
    private static class VarDef {

        private String name;

        private int start;

        private int end;

        public VarDef(PropertyOrFieldReference reference) {
            this.name = reference.getName();
            this.start = reference.getStartPosition();
            this.end = reference.getEndPosition();
        }

        public void merge(VarDef def) {
            this.name = this.name + "." + def.name;
            this.end = def.end;
        }
    }

}