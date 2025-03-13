package org.dblue.application.module.role.application.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dblue.application.module.role.application.vo.RolePageVo;
import org.dblue.core.web.param.PageParamImpl;
import org.springdoc.core.annotations.ParameterObject;

/**
 * 角色查询
 *
 * @author xie jin
 * @since 1.0.0  2024-06-18 15:47:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ParameterObject
public class RolePageDto extends PageParamImpl<RolePageVo> {

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
