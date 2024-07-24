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

package org.dblue.core.annotation;

import org.dblue.core.enums.PlatformEnum;

import java.lang.annotation.*;

/**
 * 平台
 *
 * @author xie jin
 * @since 1.0.0  2024/7/22 下午2:53
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Platform {

    /**
     * 平台信息 默认PC
     * @return 平台编码
     */
    PlatformEnum value() default PlatformEnum.PC;
}
