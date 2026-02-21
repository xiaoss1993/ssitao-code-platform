package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 系统设置方案 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_setplan")
public class TbCoreSetplan extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreSetplanId;

    /**
     * 方案名称
     */
    private String setplanPlanname;

    /**
     * saas产品
     */
    private String setplanSaasPid;

    /**
     * 是否个性化
     */
    private String setplanPrivateEnable;

    /**
     * 关联顶部菜单id
     */
    private String setplanAssociateTopMenuId;

    /**
     * 关联顶部菜单名称
     */
    private String setplanAssociateTopMenuName;

    /**
     * 是否关联所有顶部菜单
     */
    private String setplanAssociateAllTopMenu;

    /**
     * 是否默认方案
     */
    private String setplanIsDefult;

    /**
     * 是否可用
     */
    private String setplanYesornoused;

    /**
     * 路径方案
     */
    private String tbSysPathscheme;

    /**
     * 可使用产品
     */
    private String tbSysAvailableproducts;

    /**
     * 登录页方式
     */
    private String tbSysLoginmethod;

    /**
     * 登录页高亮元素颜色
     */
    private String tbSysLogincolor;

    /**
     * 系统标题图（logo）
     */
    private String tbSysTitlelogo;

    /**
     * 网页背景图（background）
     */
    private String tbSysBackgroundlogo;

    /**
     * 左侧信息图（img）
     */
    private String tbSysLeftimg;

    /**
     * 系统名称（sysname）
     */
    private String tbSysSysname;

    /**
     * 系统访问地址（url）
     */
    private String tbSysSysurl;

    /**
     * 浏览器显示图标（icon）
     */
    private String tbSysIcon;

    /**
     * 浏览页页面标题（title）
     */
    private String tbSysWebtitle;

    /**
     * 系统顶部标题（logo）
     */
    private String tbSysWeblogo;

    /**
     * 系统默认色调（hue）
     */
    private String tbSysDefaultHue;

    /**
     * 可使用产品name
     */
    private String tbSysAvailableproductsName;

    /**
     * 路径方案名称
     */
    private String tbSysPathschemename;

    /**
     * 顶部菜单位置
     */
    private String tbSysTopmenu;

    /**
     * 系统灰色模式
     */
    private String tbSysGrayModel;

    /**
     * 系统默认菜单色调
     */
    private String tbSysDefaultMenucolor;

    /**
     * 备案号
     */
    private String tbSysFilingNumber;

    /**
     * 登记部门主键
     */
    private String syCreateorgid;

    /**
     * 登记部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人主键
     */
    private String syCreateuserid;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 租户名称（旧）
     */
    private String syZhmc;

    /**
     * 租户id（旧）
     */
    private String syZhid;

    /**
     * 租户id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 租户code
     */
    private String syTenantCode;

}
