package com.ssitao.code.modular.iam.authorization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM权限聚合根
 * Authorization领域的核心聚合根
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamPermission {

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
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建菜单权限
     *
     * @param permCode 权限编码
     * @param permName 权限名称
     * @return 权限聚合根
     */
    public static IamPermission createMenu(String permCode, String permName) {
        IamPermission permission = new IamPermission();
        permission.setPermCode(permCode);
        permission.setPermName(permName);
        permission.setPermType("MENU");
        permission.setStatus(true);
        permission.setVisible(true);
        permission.setCreateTime(LocalDateTime.now());
        return permission;
    }

    /**
     * 创建按钮权限
     *
     * @param permCode 权限编码
     * @param permName 权限名称
     * @return 权限聚合根
     */
    public static IamPermission createButton(String permCode, String permName) {
        IamPermission permission = new IamPermission();
        permission.setPermCode(permCode);
        permission.setPermName(permName);
        permission.setPermType("BUTTON");
        permission.setStatus(true);
        permission.setVisible(true);
        permission.setCreateTime(LocalDateTime.now());
        return permission;
    }

    /**
     * 创建接口权限
     *
     * @param permCode 权限编码
     * @param permName 权限名称
     * @return 权限聚合根
     */
    public static IamPermission createApi(String permCode, String permName) {
        IamPermission permission = new IamPermission();
        permission.setPermCode(permCode);
        permission.setPermName(permName);
        permission.setPermType("API");
        permission.setStatus(true);
        permission.setCreateTime(LocalDateTime.now());
        return permission;
    }

    /**
     * 创建数据权限
     *
     * @param permCode 权限编码
     * @param permName 权限名称
     * @return 权限聚合根
     */
    public static IamPermission createData(String permCode, String permName) {
        IamPermission permission = new IamPermission();
        permission.setPermCode(permCode);
        permission.setPermName(permName);
        permission.setPermType("DATA");
        permission.setStatus(true);
        permission.setCreateTime(LocalDateTime.now());
        return permission;
    }

    /**
     * 判断是否为菜单权限
     *
     * @return true-是，false-否
     */
    public boolean isMenu() {
        return "MENU".equals(this.permType);
    }

    /**
     * 判断是否为按钮权限
     *
     * @return true-是，false-否
     */
    public boolean isButton() {
        return "BUTTON".equals(this.permType);
    }

    /**
     * 判断是否为接口权限
     *
     * @return true-是，false-否
     */
    public boolean isApi() {
        return "API".equals(this.permType);
    }

    /**
     * 判断是否为数据权限
     *
     * @return true-是，false-否
     */
    public boolean isData() {
        return "DATA".equals(this.permType);
    }

    /**
     * 禁用权限
     */
    public void disable() {
        this.status = false;
    }

    /**
     * 启用权限
     */
    public void enable() {
        this.status = true;
    }

    /**
     * 隐藏权限
     */
    public void hide() {
        this.visible = false;
    }

    /**
     * 显示权限
     */
    public void show() {
        this.visible = true;
    }

}
