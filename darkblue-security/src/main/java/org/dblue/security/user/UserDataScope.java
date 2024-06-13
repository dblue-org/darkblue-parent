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

package org.dblue.security.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * 数据权限
 *
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 13:16]
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDataScope {

    /**
     * 部门ID列表
     */
    private Set<String> deptIds = new HashSet<>();

    /**
     * 管理范围
     */
    private Set<String> manageDeptIds = new HashSet<>();

    public void add(String id) {
        deptIds.add(id);
    }

    public void addManageDeptId(String id) {
        manageDeptIds.add(id);
    }

}
