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
package org.dblue.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * 脱敏处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public final class DesensitizationUtils {


    /**
     * 字符 {@code *}
     */
    public static final byte SENSITIVE_CHAR = 42;
    /**
     * 手机号码长度
     */
    public static final int PHONE_NUMBER_LENGTH = 11;
    /**
     * 身份证号长度
     */
    public static final int IDENTITY_NO_LENGTH = 18;

    private DesensitizationUtils() {
    }

    /**
     * 手机号码脱敏，对手机号码中的中间4位进行脱敏处理<br>
     * <code>eg: 13678954114 → 136****4114 </code>
     *
     * @param phoneNumber 手机号码
     * @return 脱敏后的手机号码
     */
    public static String phoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)) {
            return phoneNumber;
        }

        if (phoneNumber.length() != PHONE_NUMBER_LENGTH) {
            return phoneNumber;
        }

        return doExecute(phoneNumber, 3, 7);
    }

    /**
     * 执行脱敏处理，将字符串指定位置的子串替换为等长的 {@code *}
     *
     * @param source     原字符串
     * @param startIndex 开始位置
     * @param endIndex   结束位置
     * @return 脱敏后的字符串
     */
    private static String doExecute(String source, int startIndex, int endIndex) {
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
        for (int i = startIndex; i < endIndex; i++) {
            bytes[i] = 42;
        }
        return new String(bytes);
    }

    /**
     * 身份证号脱敏处理<br>
     * <code>eg: 410110199001011111 → 410110********1111 </code>
     *
     * @param identityNo 身份证号码
     * @return 脱敏后的身份证号
     */
    public static String identityNo(String identityNo) {
        if (StringUtils.isBlank(identityNo)) {
            return identityNo;
        }

        if (identityNo.length() != IDENTITY_NO_LENGTH) {
            return identityNo;
        }

        return doExecute(identityNo, 4, 15);
    }

    /**
     * 自动脱敏处理，处理规则如下
     * <ul>
     *     <li>如果是空串或者只有一位，则不做任何处理</li>
     *     <li>如果有两位，则最后一位替换为*</li>
     *     <li>如果大于两位，则对中间部分加密</li>
     * </ul>
     *
     * @param str 要脱敏的字符串
     * @return 脱敏后的字符串
     */
    public static String auto(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        if (str.length() == 1) {
            return str;
        }
        if (str.length() == 2) {
            return doExecute(str, 1, 2);
        }
        int div = str.length() / 3;
        return doExecute(str, div, str.length() - div);
    }
}