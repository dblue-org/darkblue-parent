/*
 * Copyright (c) 2023-2025. the original authors and DBLUE.ORG
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

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomUtils {
    private static final SecureRandom SECURE_RANDOM;

    static {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("DRBG");
        } catch (NoSuchAlgorithmException e) {
            secureRandom = new SecureRandom();
        }
        SECURE_RANDOM = secureRandom;
    }

    private RandomUtils() {
    }

    public static Long nextLong() {
        return Math.abs(SECURE_RANDOM.nextLong());
    }
}
