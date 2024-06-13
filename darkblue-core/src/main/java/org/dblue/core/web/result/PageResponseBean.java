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
package org.dblue.core.web.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.common.error.ErrorInfo;
import org.dblue.core.web.PageParam;

import java.util.List;

/**
 * 分页数据的应答
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Schema(title = "分页应答数据")
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResponseBean<T> extends BaseResponseBean<Object> implements PageParam {

    /**
     * 应答数据
     */
    @Schema(title = "应答数据", description = "应答数据")
    private List<T> data;

    /**
     * 总记录数
     */
    @Schema(title = "总记录数", description = "总记录数")
    private Long total;

    /**
     * 当前页码
     */
    @Schema(title = "当前页码", description = "当前页码")
    private Long page;

    /**
     * 每页记录数
     */
    @Schema(title = "每页记录数", description = "每页记录数")
    private Long pageSize;

    /**
     * 总页数
     */
    @Schema(title = "总页数", description = "总页数")
    private Long totalPage;

    public PageResponseBean() {
    }

    public PageResponseBean(boolean success) {
        super(success);
    }

    public PageResponseBean(String errorCode, String message) {
        super(errorCode, message);
    }

    public PageResponseBean(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public static <T> PageResponseBean<T> success(Long page, Long pageSize, Long total, List<T> data) {
        PageResponseBean<T> responseBean = success();
        responseBean.setPage(page);
        responseBean.setPageSize(pageSize);
        responseBean.setTotal(total);
        responseBean.setData(data);
        responseBean.setTotalPage((total + pageSize - 1) / pageSize);
        return responseBean;
    }

    public static <T> PageResponseBean<T> success() {
        return new PageResponseBean<>(true);
    }

    public static <T> PageResponseBean<T> failure(String errorCode, String message) {
        PageResponseBean<T> responseBean = failure(message);
        responseBean.setErrorCode(errorCode);
        return responseBean;
    }

    public static <T> PageResponseBean<T> failure(String message) {
        PageResponseBean<T> responseBean = failure();
        responseBean.setMessage(message);
        return responseBean;
    }

    public static <T> PageResponseBean<T> failure() {
        return new PageResponseBean<>(false);
    }

    /**
     * 返回成功状态，返回分页信息
     *
     * @param page 分页
     * @param <T>  数据类型
     * @return 响应数据包
     */
    public static <T> PageResponseBean<T> success(IPage<T> page) {
        PageResponseBean<T> responseBean = createPageInfo(page);
        responseBean.setSuccess(true);
        return responseBean;
    }

    /**
     * 创建PageResponseBean
     *
     * @param page 分页数据
     * @param <T>  输出数据类型
     * @return 响应数据
     */
    private static <T> PageResponseBean<T> createPageInfo(IPage<T> page) {
        //List<R>
        PageResponseBean<T> responseBean = new PageResponseBean<>();
        responseBean.setData(page.getRecords());
        responseBean.setPage(page.getCurrent());
        responseBean.setPageSize(page.getSize());
        responseBean.setTotal(page.getTotal());
        responseBean.setTotalPage(page.getPages());
        return responseBean;
    }

    /**
     * 返回成功状态，返回分页信息
     *
     * @param page      分页
     * @param extension 扩展数据
     * @param <T>       数据类型
     * @return 响应数据包
     */
    public static <T> PageResponseBean<T> success(IPage<T> page, Object extension) {
        PageResponseBean<T> responseBean = createPageInfo(page);
        responseBean.setExtension(extension);
        responseBean.setSuccess(true);
        return responseBean;
    }

    public static <T> PageResponseBean<T> failure(ErrorInfo errorInfo) {
        return new PageResponseBean<>(errorInfo);
    }
}