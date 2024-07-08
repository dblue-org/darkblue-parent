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
package org.dblue.application.module.logs.infrastructure.repository.impl;

import org.dblue.application.module.logs.domain.query.LoginLogQuery;
import org.dblue.application.module.logs.domain.query.LoginLogQueryImpl;
import org.dblue.application.module.logs.infrastructure.mapper.LoginLogMapper;
import org.dblue.application.module.logs.infrastructure.repository.CustomLoginLogRepository;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class CustomLoginLogRepositoryImpl implements CustomLoginLogRepository {

    private final LoginLogMapper loginLogMapper;

    public CustomLoginLogRepositoryImpl(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;
    }

    @Override
    public LoginLogQuery createQuery() {
        return new LoginLogQueryImpl(this.loginLogMapper);
    }
}