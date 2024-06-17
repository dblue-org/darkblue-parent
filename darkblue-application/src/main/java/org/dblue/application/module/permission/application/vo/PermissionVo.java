/*
 * Copyright (c) 2021-2022. 通冠机械租赁股份有限公司 Inc. All Right Reserved.
 *
 * 公司拥有本软件版权，任何个人不得用于其他商业用户，公司保有所有权利
 */

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
public class PermissionVo {

    /**
     * 权限ID
     */
    @Schema(title = "权限ID", description = "权限ID", required = true)
    private String permissionId;

    /**
     * 菜单ID
     */
    @Schema(title = "菜单ID", description = "菜单ID", required = true)
    private String menuId;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称", description = "菜单名称", required = true)
    private String menuName;

    /**
     * 适用平台(1-PC；2-APP)从菜单代入
     */
    @Schema(title = "适用平台(1-PC；2-APP)从菜单代入", description = "适用平台(1-PC；2-APP)从菜单代入", required = true)
    private Integer platform;

    /**
     * 权限名称
     */
    @Schema(title = "权限名称", description = "权限名称", required = true)
    private String permissionName;

    /**
     * 权限标识
     */
    @Schema(title = "权限标识", description = "权限标识", required = true)
    private String permissionCode;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间", description = "创建时间", required = true)
    private LocalDateTime createTime;
}


