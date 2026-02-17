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
 * 菜单信息 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_menu")
public class TbCoreMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreMenuId;

    /**
     * 一级模块
     */
    private String menuModulecode;

    /**
     * 图标
     */
    private String menuIcon;

    /**
     * 是否快速启动菜单
     */
    private String menuQuickstart;

    /**
     * 是否桌面图标
     */
    private String menuIsdesktop;

    /**
     * 树形路径
     */
    private String menuTreepath;

    /**
     * 是否启用
     */
    private String menuEnabled;

    /**
     * 节点信息
     */
    private String menuNodeinfo;

    /**
     * 节点信息类型
     */
    private String menuNodeinfotype;

    /**
     * 节点名称
     */
    private String menuMenuname;

    /**
     * 节点编码
     */
    private String menuCode;

    /**
     * 功能类型
     */
    private String menuFunctype;

    /**
     * 功能说明
     */
    private String menuHelp;

    /**
     * 大按钮菜单
     */
    private String menuBigbutton;

    /**
     * 英文标题
     */
    private String menuMenusubname;

    /**
     * 数字提醒
     */
    private String menuBadge;

    /**
     * 数字来源
     */
    private String menuBadgetype;

    /**
     * 数字样式
     */
    private String menuBadgecls;

    /**
     * 来源内容
     */
    private String menuBadgeinfo;

    /**
     * 加和菜单
     */
    private String menuBadgemenuids;

    /**
     * 加和菜单text
     */
    private String menuBadgemenutexts;

    /**
     * 数字背景
     */
    private String menuBadgebgcolor;

    /**
     * 数字颜色
     */
    private String menuBadgecolor;

    /**
     * 更新条件
     */
    private String menuBadgeload;

    /**
     * 菜单导航
     */
    private String menuHidelocation;

    /**
     * 流程模块
     */
    private String menuWfmodule;

    /**
     * 流程菜单别名
     */
    private String menuWfname;

    /**
     * 背景颜色
     */
    private String menuBgcolor;

    /**
     * 激活刷新
     */
    private String menuRefresh;

    /**
     * 服务名称
     */
    private String menuService;

    /**
     * 功能展示方式
     */
    private String menuLayout;

    /**
     * 关联顶部菜单id
     */
    private String menuLinkTopmenuId;

    /**
     * 关联顶部菜单name
     */
    private String menuLinkTopmenuName;

    /**
     * 图标颜色
     */
    private String menuIconcolor;

    /**
     * 加载第一条数据
     */
    private String menuLoadFirstData;

    /**
     * 审核标记
     */
    private String syAudflag;

    /**
     * 登记者所在部门编码
     */
    private String syCreateorg;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人编码
     */
    private String syCreateuser;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 是否启用本条数据
     */
    private String syFlag;

    /**
     * 修改人部门编码
     */
    private String syModifyorg;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改时间
     */
    private String syModifytime;

    /**
     * 修改人编码
     */
    private String syModifyuser;

    /**
     * 修改人
     */
    private String syModifyusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 流程实例id
     */
    private String syPiid;

    /**
     * 流程定义id
     */
    private String syPdid;

    /**
     * 拼音简写
     */
    private String syPyjz;

    /**
     * 拼音全称
     */
    private String syPyqc;

    /**
     * 表单上传虚字段
     */
    private String syFormuploadfiles;

    /**
     * 父节点id
     */
    private String syParent;

    /**
     * 节点类型
     */
    private String syNodetype;

    /**
     * 层次
     */
    private Integer syLayer;

    /**
     * 树形路径
     */
    private String syPath;

    /**
     * 父节点路径
     */
    private String syParentpath;

    /**
     * 树形排序字段
     */
    private String syTreeorderindex;

    /**
     * je系统
     */
    private String syJesys;

    /**
     * je核心
     */
    private String syJecore;

    /**
     * 产品name
     */
    private String syProductName;

    /**
     * 产品code
     */
    private String syProductCode;

    /**
     * 产品id
     */
    private String syProductId;

    /**
     * 系统
     */
    private String sySystem;

}
