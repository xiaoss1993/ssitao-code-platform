package com.ssitao.code.modular.iam.domain.model;

import com.ssitao.code.frame.aggregate.entity.AbstractAggregateRoot;
import lombok.Getter;

import java.util.Date;

/**
 * 菜单聚合根
 */
@Getter
public class SysMenuAggregate extends AbstractAggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    private long version = 1;
    private Long menuId;
    private String menuName;
    private String parentName;
    private Long parentId;
    private String orderNum;
    private String url;
    private String target;
    private String menuType;
    private String visible;
    private String isRefresh;
    private String perms;
    private String icon;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;

    public SysMenuAggregate() {
    }

    public SysMenuAggregate(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public Long getId() {
        return menuId;
    }

    @Override
    public void setId(Long id) {
        this.menuId = id;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public Date getLastUpdateTime() {
        return updateTime;
    }

    public void createMenu(String menuName, Long parentId, String orderNum, String url,
                          String target, String menuType, String visible, String isRefresh,
                          String perms, String icon, String createBy) {
        this.menuName = menuName;
        this.parentId = parentId;
        this.orderNum = orderNum;
        this.url = url;
        this.target = target;
        this.menuType = menuType;
        this.visible = visible != null ? visible : "0";
        this.isRefresh = isRefresh != null ? isRefresh : "0";
        this.perms = perms;
        this.icon = icon;
        this.createBy = createBy;
        this.createTime = new Date();
    }

    public void updateMenu(String menuName, Long parentId, String orderNum, String url,
                          String target, String menuType, String visible, String isRefresh,
                          String perms, String icon, String updateBy) {
        this.menuName = menuName;
        this.parentId = parentId;
        this.orderNum = orderNum;
        this.url = url;
        this.target = target;
        this.menuType = menuType;
        this.visible = visible;
        this.isRefresh = isRefresh;
        this.perms = perms;
        this.icon = icon;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    public void changeStatus(String visible, String updateBy) {
        this.visible = visible;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    // Setters
    public void setMenuId(Long menuId) { this.menuId = menuId; }
    public void setMenuName(String menuName) { this.menuName = menuName; }
    public void setParentName(String parentName) { this.parentName = parentName; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
    public void setOrderNum(String orderNum) { this.orderNum = orderNum; }
    public void setUrl(String url) { this.url = url; }
    public void setTarget(String target) { this.target = target; }
    public void setMenuType(String menuType) { this.menuType = menuType; }
    public void setVisible(String visible) { this.visible = visible; }
    public void setIsRefresh(String isRefresh) { this.isRefresh = isRefresh; }
    public void setPerms(String perms) { this.perms = perms; }
    public void setIcon(String icon) { this.icon = icon; }
    public void setCreateBy(String createBy) { this.createBy = createBy; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    public void setRemark(String remark) { this.remark = remark; }
    public String getCreateBy() { return createBy; }
    public String getUpdateBy() { return updateBy; }
    public String getRemark() { return remark; }
}
