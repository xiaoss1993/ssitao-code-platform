package com.ssitao.code.modular.iam.menu.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单领域模型
 * 对应表：iam_menu
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class IamMenu {

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型: DIRECTORY-目录, MENU-菜单, BUTTON-按钮
     */
    private String menuType;

    /**
     * 父菜单ID
     */
    private String menuParentId;

    /**
     * 路由路径
     */
    private String menuPath;

    /**
     * 组件路径
     */
    private String menuComponent;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 排序号
     */
    private Integer menuSort;

    /**
     * 是否可见: 0-隐藏, 1-显示
     */
    private Integer menuIsVisible;

    /**
     * 是否缓存: 0-否, 1-是
     */
    private Integer menuIsCached;

    /**
     * 是否固定: 0-否, 1-是
     */
    private Integer menuIsAffix;

    /**
     * 权限标识
     */
    private String menuPermission;

    /**
     * 重定向路径
     */
    private String menuRedirect;

    /**
     * 菜单描述
     */
    private String menuDesc;

    /**
     * 菜单状态: 0-禁用, 1-启用
     */
    private Integer menuStatus;

    /**
     * 是否内置: 0-否, 1-是
     */
    private Integer menuIsBuiltin;

    /**
     * 树形路径
     */
    private String menuTreePath;

    /**
     * 树形层级
     */
    private Integer menuTreeLevel;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 修改人ID
     */
    private String modifyUserId;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer isDeleted;

    /**
     * 版本号
     */
    private Integer version;

    // ==================== 前端兼容字段 ====================

    /**
     * 激活菜单
     */
    private String active;

    /**
     * 颜色
     */
    private String color;

    /**
     * 是否全屏
     */
    private Boolean fullpage;

    /**
     * 标签
     */
    private String tag;

    /**
     * 隐藏面包屑
     */
    private Boolean hiddenBreadcrumb;

    /**
     * 子菜单列表
     */
    private List<IamMenu> children;

    /**
     * 创建菜单
     *
     * @param menuCode      菜单编码
     * @param menuName      菜单名称
     * @param menuType      菜单类型
     * @param menuParentId  父菜单ID
     * @param tenantId      租户ID
     * @return 菜单实体
     */
    public static IamMenu create(String menuCode, String menuName, String menuType,
                                 String menuParentId, String tenantId) {
        IamMenu menu = new IamMenu();
        menu.setMenuCode(menuCode);
        menu.setMenuName(menuName);
        menu.setMenuType(menuType);
        menu.setMenuParentId(menuParentId);
        menu.setTenantId(tenantId);
        menu.setMenuSort(0);
        menu.setMenuIsVisible(1);
        menu.setMenuIsCached(0);
        menu.setMenuIsAffix(0);
        menu.setMenuStatus(1);
        menu.setMenuIsBuiltin(0);
        menu.setMenuTreeLevel(1);
        menu.setCreateTime(LocalDateTime.now());
        menu.setModifyTime(LocalDateTime.now());
        menu.setIsDeleted(0);
        menu.setVersion(0);
        menu.setChildren(new ArrayList<>());
        return menu;
    }

    /**
     * 判断是否为目录
     *
     * @return true-目录，false-非目录
     */
    public boolean isDirectory() {
        return "DIRECTORY".equals(this.menuType);
    }

    /**
     * 判断是否为菜单
     *
     * @return true-菜单，false-非菜单
     */
    public boolean isMenu() {
        return "MENU".equals(this.menuType);
    }

    /**
     * 判断是否为按钮
     *
     * @return true-按钮，false-非按钮
     */
    public boolean isButton() {
        return "BUTTON".equals(this.menuType);
    }

    /**
     * 判断是否可见
     *
     * @return true-可见，false-隐藏
     */
    public boolean isVisible() {
        return this.menuIsVisible != null && this.menuIsVisible == 1;
    }

    /**
     * 判断是否启用
     *
     * @return true-启用，false-禁用
     */
    public boolean isEnabled() {
        return this.menuStatus != null && this.menuStatus == 1;
    }

    /**
     * 判断是否为内置菜单
     *
     * @return true-内置，false-非内置
     */
    public boolean isBuiltin() {
        return this.menuIsBuiltin != null && this.menuIsBuiltin == 1;
    }

    /**
     * 启用菜单
     */
    public void enable() {
        this.menuStatus = 1;
        this.modifyTime = LocalDateTime.now();
    }

    /**
     * 禁用菜单
     */
    public void disable() {
        this.menuStatus = 0;
        this.modifyTime = LocalDateTime.now();
    }

    /**
     * 显示菜单
     */
    public void show() {
        this.menuIsVisible = 1;
        this.modifyTime = LocalDateTime.now();
    }

    /**
     * 隐藏菜单
     */
    public void hide() {
        this.menuIsVisible = 0;
        this.modifyTime = LocalDateTime.now();
    }

    /**
     * 添加子菜单
     *
     * @param child 子菜单
     */
    public void addChild(IamMenu child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
        child.setMenuParentId(this.menuId);
        child.setMenuTreeLevel(this.menuTreeLevel + 1);
    }

    /**
     * 判断是否为根菜单
     *
     * @return true-根菜单，false-非根菜单
     */
    public boolean isRoot() {
        return this.menuParentId == null || this.menuParentId.isEmpty();
    }

    /**
     * 判断是否有子菜单
     *
     * @return true-有子菜单，false-无子菜单
     */
    public boolean hasChildren() {
        return this.children != null && !this.children.isEmpty();
    }

    // ==================== 别名方法（兼容Repository） ====================

    /**
     * 获取重定向路径（别名）
     */
    public String getRedirect() {
        return this.menuRedirect;
    }

    /**
     * 设置重定向路径（别名）
     */
    public void setRedirect(String redirect) {
        this.menuRedirect = redirect;
    }

    /**
     * 获取图标（别名）
     */
    public String getIcon() {
        return this.menuIcon;
    }

    /**
     * 设置图标（别名）
     */
    public void setIcon(String icon) {
        this.menuIcon = icon;
    }

    /**
     * 获取是否可见（别名）
     */
    public Integer getVisible() {
        return this.menuIsVisible;
    }

    /**
     * 设置是否可见（别名）
     */
    public void setVisible(Integer visible) {
        this.menuIsVisible = visible;
    }

    /**
     * 获取组件路径（别名）
     */
    public String getComponent() {
        return this.menuComponent;
    }

    /**
     * 设置组件路径（别名）
     */
    public void setComponent(String component) {
        this.menuComponent = component;
    }

    /**
     * 获取权限标识（别名）
     */
    public String getPermission() {
        return this.menuPermission;
    }

    /**
     * 设置权限标识（别名）
     */
    public void setPermission(String permission) {
        this.menuPermission = permission;
    }

    /**
     * 获取排序（别名）
     */
    public Integer getSortOrder() {
        return this.menuSort;
    }

    /**
     * 设置排序（别名）
     */
    public void setSortOrder(Integer sortOrder) {
        this.menuSort = sortOrder;
    }

    /**
     * 获取状态（别名）
     */
    public Integer getStatus() {
        return this.menuStatus;
    }

    /**
     * 设置状态（别名）
     */
    public void setStatus(Integer status) {
        this.menuStatus = status;
    }

    /**
     * 获取路径（别名）
     */
    public String getPath() {
        return this.menuPath;
    }

    /**
     * 设置路径（别名）
     */
    public void setPath(String path) {
        this.menuPath = path;
    }

    /**
     * 获取ID（别名）
     */
    public String getId() {
        return this.menuId;
    }

    /**
     * 设置ID（别名）
     */
    public void setId(String id) {
        this.menuId = id;
    }

    /**
     * 获取父ID（别名）
     */
    public String getParentId() {
        return this.menuParentId;
    }

    /**
     * 设置父ID（别名）
     */
    public void setParentId(String parentId) {
        this.menuParentId = parentId;
    }

}

