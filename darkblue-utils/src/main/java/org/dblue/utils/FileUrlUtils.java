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

/**
 * 静态文件路径处理
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class FileUrlUtils {

    public static final String SPLITTER = "/";
    public static final String HTTP = "http:";
    public static final String HTTPS = "https:";

    private FileUrlUtils() {
    }


    public static String join(final String suffix, final String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        if (StringUtils.isBlank(suffix)) {
            return path;
        }
        String lowerCasePath = path.toLowerCase();
        if (lowerCasePath.startsWith(HTTP) || lowerCasePath.startsWith(HTTPS)) {
            return path;
        }

        StringBuilder sb = new StringBuilder(suffix);
        if (!suffix.endsWith(SPLITTER)) {
            sb.append(SPLITTER);
        }
        if (path.startsWith(SPLITTER)) {
            sb.append(path.substring(1));
        } else {
            sb.append(path);
        }
        return sb.toString();
    }

    public static String tripOssPath(String path) {
        String lowerCasePath = path.toLowerCase();
        if (lowerCasePath.startsWith(HTTP) || lowerCasePath.startsWith(HTTPS)) {
            return path.replaceFirst("(http|https)://[a-zA-Z0-9-.]+/", "");
        }
        return path;
    }
}