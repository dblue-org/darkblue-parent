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


    /**
     * 适用平台(1-PC；2-APP)从菜单代入
     */
    @Parameter(description = "适用平台(1-PC；2-APP)从菜单代入")
    private Integer platform;
}
