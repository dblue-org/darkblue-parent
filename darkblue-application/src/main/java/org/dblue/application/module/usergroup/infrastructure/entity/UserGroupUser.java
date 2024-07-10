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

package org.dblue.application.module.usergroup.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractCreateAuditingEntity;

/**
 * 用户组用户
 *
 * @author xie jin
 * @since 1.0.0  2024-07-10 14:56:11
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_user_group_user")
public class UserGroupUser extends AbstractCreateAuditingEntity {
    /**
     * 用户组用户ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "user_group_user_id", nullable = false, length = 64)
    private String userGroupUserId;

    /**
     * 用户组ID
     */
    @Size(max = 64)
    @Column(name = "user_group_id", length = 64)
    private String userGroupId;

    /**
     * 用户ID
     */
    @Size(max = 64)
    @Column(name = "user_id", length = 64)
    private String userId;

}