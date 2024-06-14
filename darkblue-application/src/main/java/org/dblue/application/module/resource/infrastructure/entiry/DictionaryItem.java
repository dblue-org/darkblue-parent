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

package org.dblue.application.module.resource.infrastructure.entiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_sys_dictionary_item")
public class DictionaryItem {
    @Id
    @Size(max = 64)
    @ColumnDefault("1")
    @Column(name = "dictionary_item_id", nullable = false, length = 64)
    private String dictionaryItemId;

    @Size(max = 64)
    @Column(name = "dictionary_item_code", length = 64)
    private String dictionaryItemCode;

    @Size(max = 64)
    @Column(name = "dictionary_id", length = 64)
    private String dictionaryId;

    @Column(name = "code")
    private Integer code;

    @Size(max = 500)
    @Column(name = "name", length = 500)
    private String name;

    @Size(max = 500)
    @Column(name = "extension", length = 500)
    private String extension;

    @Size(max = 64)
    @Column(name = "parent_id", length = 64)
    private String parentId;

    @Column(name = "order_num")
    private Integer orderNum;

    @Column(name = "item_level")
    private Integer itemLevel;

    @ColumnDefault("0")
    @Column(name = "is_delete")
    private Boolean isDelete;

    @ColumnDefault("1")
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "create_time")
    private Instant createTime;

    @Size(max = 64)
    @Column(name = "create_user", length = 64)
    private String createUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Size(max = 100)
    @Column(name = "update_user", length = 100)
    private String updateUser;

}