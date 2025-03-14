package org.dblue.application.module.permission.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * 权限
 *
 * @author xie jin
 * @since 1.0.0  2024-06-17 16:11:59
 */
@Schema(description = "权限")
@Data
public class PermissionPageVo {

    /**
     * 权限ID
     */
    @Schema(description = "权限ID")
    private String permissionId;

    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private String menuId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;

    /**
     * 适用平台(1-PC；2-APP)从菜单代入
     */
    @Schema(description = "适用平台(1-PC；2-APP)从菜单代入")
    private Integer platform;

    /**
     * 资源数量
     */
    @Schema(description = "资源数量")
    private Long resourceNum;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String permissionName;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String permissionCode;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}


