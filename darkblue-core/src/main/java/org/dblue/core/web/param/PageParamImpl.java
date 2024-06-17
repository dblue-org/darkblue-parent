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
package org.dblue.core.web.param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dblue.common.validation.annotation.PositiveNumber;
import org.dblue.core.web.PageParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 分页参数
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
public class PageParamImpl<T> implements PageParam {

    @PositiveNumber(message = "页码数只能是正数")
    @Parameter(description = "页码数")
    @Schema(title = "页码数", description = "页码数")
    private Long page = 1L;

    @PositiveNumber(message = "每页行数只能是正数")
    @Parameter(description = "每页行数")
    @Schema(title = "每页行数", description = "每页行数")
    private Long pageSize = 15L;

    /**
     * 分页数据
     *
     * @return 分页数据
     */
    public IPage<T> toPage() {
        return new Page<>(getPage(), getPageSize());
    }

    public Pageable toJpaPage(){return PageRequest.of(getPage().intValue(),getPageSize().intValue());
    }
}