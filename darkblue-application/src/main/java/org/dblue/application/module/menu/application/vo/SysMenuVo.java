/*
 * Copyright (c) 2021-2022. 通冠机械租赁股份有限公司 Inc. All Right Reserved.
 *
 * 公司拥有本软件版权，任何个人不得用于其他商业用户，公司保有所有权利
 */

package org.dblue.application.module.menu.application.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单
 *
 * @author xie jin
 * @since 1.0.0  2024-06-17 15:17:15
 */
@Schema(description = "菜单")
@Data
public class SysMenuVo {

    /**
     * 菜单ID
     */
    @Schema(title = "菜单ID", description = "菜单ID", required = true)
    private String menuId;

    /**
     * 上级菜单ID
     */
    @Schema(title = "上级菜单ID", description = "上级菜单ID", required = true)
    private String parentId;

    /**
     * 菜单适用平台(1-PC；2-APP)
     */
    @Schema(title = "菜单适用平台(1-PC；2-APP)", description = "菜单适用平台(1-PC；2-APP)", required = true)
    private Integer platform;

    /**
     * 菜单类型(1-目录;2-菜单)
     */
    @Schema(title = "菜单类型(1-目录;2-菜单)", description = "菜单类型(1-目录;2-菜单)", required = true)
    private Integer menuType;

    /**
     * URL名称
     */
    @Schema(title = "URL名称", description = "URL名称", required = true)
    private String urlName;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称", description = "菜单名称", required = true)
    private String menuName;

    /**
     * 菜单url
     */
    @Schema(title = "菜单url", description = "菜单url", required = true)
    private String menuUrl;

    /**
     * 菜单层级
     */
    @Schema(title = "菜单层级", description = "菜单层级", required = true)
    private Integer level;

    /**
     * 显示顺序
     */
    @Schema(title = "显示顺序", description = "显示顺序", required = true)
    private Integer sortNum;

    /**
     * 菜单图标
     */
    @Schema(title = "菜单图标", description = "菜单图标", required = true)
    private String menuIcon;

    /**
     * 备注
     */
    @Schema(title = "备注", description = "备注", required = true)
    private String remark;

    /**
     * 是否可用
     */
    @Schema(title = "是否可用", description = "是否可用", required = true)
    private Boolean isEnable;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间", description = "创建时间", required = true)
    private LocalDateTime createTime;

    /**
     * 子菜单
     */
    @Schema(title = "子菜单", description = "子菜单", required = true)
    private List<SysMenuVo> children;

    /**
     * 是否可见
     */
    @Schema(description = "是否可见")
    private Boolean isVisible;

}


