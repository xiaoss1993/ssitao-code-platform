package com.ssitao.code.modular.iam.controller.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 创建权限请求 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "创建权限请求")
public class PermissionCreateReqVO {

    /**
     * 权限编码
     */
    @Schema(description = "权限编码", required = true)
    @NotBlank(message = "权限编码不能为空")
    @Size(max = 100, message = "权限编码长度不能超过100")
    private String code;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称", required = true)
    @NotBlank(message = "权限名称不能为空")
    @Size(max = 50, message = "权限名称长度不能超过50")
    private String name;

    /**
     * 权限类型
     */
    @Schema(description = "权限类型：menu-菜单 button-按钮 api-接口", required = true)
    @NotBlank(message = "权限类型不能为空")
    private String type;

    /**
     * 父权限ID
     */
    @Schema(description = "父权限ID，0表示根节点")
    private Long parentId;

    /**
     * 路由路径
     */
    @Schema(description = "路由路径")
    @Size(max = 255, message = "路由路径长度不能超过255")
    private String path;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    @Size(max = 255, message = "组件路径长度不能超过255")
    private String component;

    /**
     * 图标
     */
    @Schema(description = "图标")
    @Size(max = 50, message = "图标长度不能超过50")
    private String icon;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 是否可见
     */
    @Schema(description = "是否可见：1-显示 0-隐藏")
    private Integer visible;

    /**
     * 状态
     */
    @Schema(description = "状态：1-启用 0-禁用")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过500")
    private String remark;

}
