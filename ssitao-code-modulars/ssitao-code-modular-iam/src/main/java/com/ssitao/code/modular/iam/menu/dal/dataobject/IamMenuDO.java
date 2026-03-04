package com.ssitao.code.modular.iam.menu.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 菜单数据对象
 * 对应表：iam_menu
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_menu")
public class IamMenuDO {

    /**
     * 菜单ID
     */
    @Id(keyType = KeyType.Auto)
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

}
