package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 更新菜单命令
 */
@Data
public class UpdateMenuCommand {

    /** 菜单ID */
    @NotNull(message = "菜单ID不能为空")
    private Long menuId;

    /** 菜单名称 */
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    private String menuName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序 */
    @NotBlank(message = "显示顺序不能为空")
    private String orderNum;

    /** 菜单URL */
    @Size(min = 0, max = 200, message = "请求地址不能超过200个字符")
    private String url;

    /** 打开方式 */
    private String target;

    /** 类型（M目录 C菜单 F按钮） */
    @NotBlank(message = "菜单类型不能为空")
    private String menuType;

    /** 菜单状态（0显示 1隐藏） */
    private String visible;

    /** 是否刷新 */
    private String isRefresh;

    /** 权限字符串 */
    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;

    /** 菜单图标 */
    private String icon;

    /** 备注 */
    private String remark;
}
