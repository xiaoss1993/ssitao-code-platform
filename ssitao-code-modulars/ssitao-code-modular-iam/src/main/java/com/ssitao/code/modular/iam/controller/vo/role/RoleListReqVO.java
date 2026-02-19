package com.ssitao.code.modular.iam.controller.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色列表查询请求 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "角色列表查询请求")
public class RoleListReqVO {

    /**
     * 角色编码（模糊查询）
     */
    @Schema(description = "角色编码（模糊查询）")
    private String code;

    /**
     * 角色名称（模糊查询）
     */
    @Schema(description = "角色名称（模糊查询）")
    private String name;

    /**
     * 状态
     */
    @Schema(description = "状态：1-启用 0-禁用")
    private Integer status;

}
