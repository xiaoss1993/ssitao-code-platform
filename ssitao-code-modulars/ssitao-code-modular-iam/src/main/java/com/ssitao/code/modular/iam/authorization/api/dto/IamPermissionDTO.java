package com.ssitao.code.modular.iam.authorization.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IAM权限DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamPermissionDTO {

    /**
     * 权限ID
     */
    private String id;

    /**
     * 权限编码
     */
    private String permCode;

    /**
     * 权限名称
     */
    private String permName;

    /**
     * 权限类型：MENU-菜单 BUTTON-按钮 API-接口 DATA-数据
     */
    private String permType;

    /**
     * 父权限ID
     */
    private String parentId;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 权限标识(如:user:add)
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否外链：1-是 0-否
     */
    private Boolean isFrame;

    /**
     * 是否缓存：1-是 0-否
     */
    private Boolean isCache;

    /**
     * 是否可见：1-显示 0-隐藏
     */
    private Boolean visible;

    /**
     * 重定向地址
     */
    private String redirect;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 层级
     */
    private Integer layer;

    /**
     * 目标ID
     */
    private String targetId;

    /**
     * 目标类型
     */
    private String targetType;

    /**
     * 操作编码
     */
    private String operateCode;

    /**
     * 输出模板
     */
    private String outputTemplate;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 子权限列表
     */
    private List<IamPermissionDTO> children;

}
