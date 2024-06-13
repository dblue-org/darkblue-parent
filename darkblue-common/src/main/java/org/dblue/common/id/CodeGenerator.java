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
package org.dblue.common.id;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 生成一个 n 位编码，编码为纯数字。
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2024/6/13 11:10]
 */
public class CodeGenerator {

    private static final Random RANDOM = new SecureRandom();

    private CodeGenerator() {
    }

    /**
     * 生成一个带前缀的编码，编码为纯数字。编码长度不包含前缀
     *
     * @param prefix 前缀
     * @param length 编码长度
     * @return 编码
     */
    public static String get(String prefix, int length) {
        return prefix + get(length);
    }

    public static String get(int length) {
        StringBuilder sb = new StringBuilder();
        IntStream intStream = RANDOM.ints(length, 0, 9);
        intStream.forEach(sb::append);
        intStream.close();
        return sb.toString();
    }
}