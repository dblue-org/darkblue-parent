package org.dblue.application.module.role.application.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色列表
 *
 * @author xie jin
 * @since 1.0.0  2024-06-18 15:48:33
 */
@Schema(description = "角色列表")
@Data
public class RolePageVo {

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private String roleId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String roleCode;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 是否可用
     */
    @Schema(description = "是否可用")
    private Boolean isEnable;

    /**
     * 是否内置
     */
    @Schema(description = "是否内置")
    private Boolean isBuiltIn;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 使用人数
     */
    @Schema(description = "使用人数")
    private Integer userNums = 0;

}


