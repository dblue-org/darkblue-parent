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
package org.dblue.core.mybatis;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.dblue.core.mybatis.audit.ModelUtils;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 自动设置审计信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class AutoSetCreateUpdateInfoInnerInterceptor implements InnerInterceptor {

    private final UserContext userContext;
    private String insertListMethodName = ".insertList";

    public AutoSetCreateUpdateInfoInnerInterceptor(UserContext userContext) {
        this.userContext = userContext;
    }

    public void setInsertListMethodName(String insertListMethodName) {
        this.insertListMethodName = insertListMethodName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        if (SqlCommandType.INSERT == ms.getSqlCommandType()) {
            if (!(parameter instanceof Map) && !(parameter instanceof Collection)) {
                doInsert(parameter);
            } else if (parameter instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) parameter;
                Object entities = map.getOrDefault("entities", null);
                if (entities instanceof Collection && ms.getId().endsWith(insertListMethodName)) {
                    doInsertList((List<Object>) entities);
                }
            }
        } else if (SqlCommandType.UPDATE == ms.getSqlCommandType() && parameter instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) parameter;
            doUpdate(map);
        }
    }

    protected void doInsert(Object entity) {
        ModelUtils.setCreateUpdateInfoByInterface(entity, this.getUserId());
    }

    protected void doInsertList(List<Object> entities) {
        for (Object entity : entities) {
            ModelUtils.setCreateUpdateInfoByInterface(entity, this.getUserId());
        }
    }

    protected void doUpdate(Map<String, Object> map) {
        Object entity = map.getOrDefault(Constants.ENTITY, null);
        if (Objects.isNull(entity)) {
            return;
        }
        ModelUtils.setUpdateInfoByInterface(entity, this.getUserId());
    }


    private String getUserId() {
        return userContext != null ? userContext.getUserId() : null;
    }
}