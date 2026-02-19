package com.ssitao.code.modular.iam.controller.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 权限列表查询请求 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "权限列表查询请求")
public class PermissionListReqVO {

    /**
     * 权限编码（模糊查询）
     */
    @Schema(description = "权限编码（模糊查询）")
    private String code;

    /**
     * 权限名称（模糊查询）
     */
    @Schema(description = "权限名称（模糊查询）")
    private String name;

    /**
     * 权限类型
     */
    @Schema(description = "权限类型：menu-菜单 button-按钮 api-接口")
    private String type;

    /**
     * 状态
     */
    @Schema(description = "状态：1-启用 0-禁用")
    private Integer status;

    /**
     * 是否可见
     */
    @Schema(description = "是否可见：1-显示 0-隐藏")
    private Integer visible;

}
