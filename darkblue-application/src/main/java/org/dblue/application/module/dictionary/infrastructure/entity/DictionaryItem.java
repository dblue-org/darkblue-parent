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
import org.hibernate.annotations.ColumnDefault;

/**
 * 数据字典条目
 *
 * @author xie jin
 * @since 1.0.0  2024-07-12 11:15:19
 */
@Getter
@Setter
@Entity
@Table(name = "tb_sys_dictionary_item")
public class DictionaryItem extends AbstractAuditingEntity {
    /**
     * 字典项目ID
     */
    @Id
    @Size(max = 64)
    @ColumnDefault("'1'")
    @Column(name = "dictionary_item_id", nullable = false, length = 64)
    private String dictionaryItemId;

    /**
     * 字典项识别码
     */
    @Size(max = 64)
    @Column(name = "dictionary_item_code", length = 64)
    private String dictionaryItemCode;

    /**
     * 字典ID
     */
    @Size(max = 64)
    @Column(name = "dictionary_id", length = 64)
    private String dictionaryId;

    /**
     * 编码
     */
    @Column(name = "code")
    private Integer code;

    /**
     * 名称
     */
    @Size(max = 500)
    @Column(name = "name", length = 500)
    private String name;

    /**
     * 扩展信息
     */
    @Size(max = 500)
    @Column(name = "extension", length = 500)
    private String extension;

    /**
     * 上级ID
     */
    @Size(max = 64)
    @Column(name = "parent_id", length = 64)
    private String parentId;

    /**
     * 顺序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 级别
     */
    @Column(name = "item_level")
    private Integer itemLevel;

    /**
     * 是否删除
     */
    @ColumnDefault("0")
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 是否启用
     */
    @ColumnDefault("1")
    @Column(name = "is_enabled")
    private Boolean isEnabled;

}