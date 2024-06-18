/*
 * Copyright (c) 2021-2022. 通冠机械租赁股份有限公司 Inc. All Right Reserved.
 *
 * 公司拥有本软件版权，任何个人不得用于其他商业用户，公司保有所有权利
 */

package org.dblue.application.module.role.application.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.role.application.vo.RoleVo;
import org.dblue.core.web.param.PageParamImpl;

/**
 * 角色查询
 *
 * @author xie jin
 * @since 1.0.0  2024-06-18 15:47:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQueryDto extends PageParamImpl<RoleVo> {

    /**
     * 角色名称
     */
    @Parameter(description = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @Parameter(description = "角色编码")
    private String roleCode;
}
