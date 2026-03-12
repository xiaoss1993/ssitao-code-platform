package com.ssitao.code.modular.iam.authorization.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * IAM权限更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamPermissionUpdateCommand {

    /**
     * 权限ID
     */
    @NotNull(message = "权限ID不能为空")
    private Long id;

    /**
     * 权限名称
     */
    @Size(max = 128, message = "权限名称长度不能超过128")
    private String permName;

    /**
     * 路由路径
     */
    @Size(max = 256, message = "路由路径长度不能超过256")
    private String path;

    /**
     * 组件路径
     */
    @Size(max = 256, message = "组件路径长度不能超过256")
    private String component;

    /**
     * 权限标识
     */
    @Size(max = 256, message = "权限标识长度不能超过256")
    private String perms;

    /**
     * 图标
     */
    @Size(max = 128, message = "图标长度不能超过128")
    private String icon;

    /**
     * 是否外链
     */
    private Boolean isFrame;

    /**
     * 是否缓存
     */
    private Boolean isCache;

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 重定向地址
     */
    @Size(max = 256, message = "重定向地址长度不能超过256")
    private String redirect;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 备注
     */
    @Size(max = 512, message = "备注长度不能超过512")
    private String remark;

    /**
     * 租户ID
     */
    private String tenantId;

}
