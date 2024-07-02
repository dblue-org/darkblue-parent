/*
 * Copyright (c) 2021-2022. 通冠机械租赁股份有限公司 Inc. All Right Reserved.
 *
 * 公司拥有本软件版权，任何个人不得用于其他商业用户，公司保有所有权利
 */

package org.dblue.application.module.permission.application.dto;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.permission.application.vo.PermissionPageVo;
import org.dblue.core.web.param.PageParamImpl;

/**
 * 权限查询
 *
 * @author Wang Chengwei
 * @since 1.0.0 2021/10/29 17:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionPageDto extends PageParamImpl<PermissionPageVo> {

    /**
     * 菜单ID
     */
    @Parameter(description = "菜单ID")
    private String menuId;

    /**
     * 权限名称
     */
    @Parameter(description = "权限名称")
    private String permissionName;

    /**
     * 权限标识
     */
    @Parameter(description = "权限标识")
    private String permissionCode;
}
