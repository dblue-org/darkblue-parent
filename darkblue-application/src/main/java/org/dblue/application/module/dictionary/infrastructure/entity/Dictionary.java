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

package org.dblue.application.module.dictionary.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dblue.application.jpa.AbstractAuditingEntity;

/**
 * 数据字典
 *
 * @author xie jin
 * @since 1.0.0  2024-07-12 11:15:06
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_dictionary")
public class Dictionary extends AbstractAuditingEntity {
    /**
     * 字典ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "dictionary_id", nullable = false, length = 64)
    private String dictionaryId;

    /**
     * 字典编码
     */
    @Size(max = 64)
    @Column(name = "dictionary_code", length = 64)
    private String dictionaryCode;

    /**
     * 字典名称
     */
    @Size(max = 64)
    @Column(name = "dictionary_name", length = 64)
    private String dictionaryName;

    /**
     * 字典类型（1-普通字典；2-树形字典）
     */
    @Column(name = "dictionary_type")
    private Integer dictionaryType;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

}