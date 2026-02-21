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
 * 功能 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_funcinfo")
public class TbCoreFuncinfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreFuncinfoId;

    /**
     * 节点信息
     */
    private String funcinfoNodeinfo;

    /**
     * 节点信息类型
     */
    private String funcinfoNodeinfotype;

    /**
     * 功能名称
     */
    private String funcinfoFuncname;

    /**
     * 功能编码
     */
    private String funcinfoFunccode;

    /**
     * 产品扩展功能id
     */
    private String funcinfoNewfuncid;

    /**
     * 功能图标
     */
    private String funcinfoIcon;

    /**
     * 子系统外键
     */
    private String funcinfoSubsystemId;

    /**
     * 子系统
     */
    private String funcinfoSystemname;

    /**
     * 主键编码
     */
    private String funcinfoIdname;

    /**
     * 扩展实体全限定名
     */
    private String funcinfoExpandentityname;

    /**
     * 扩展js文件
     */
    private String funcinfoExpandjs;

    /**
     * 对应action
     */
    private String funcinfoFuncaction;

    /**
     * 主键名称
     */
    private String funcinfoPkname;

    /**
     * 表名
     */
    private String funcinfoTablename;

    /**
     * 表单标题
     */
    private String funcinfoFormtitle;

    /**
     * 列表过滤条件
     */
    private String funcinfoWheresql;

    /**
     * 行数
     */
    private Integer funcinfoPagesize;

    /**
     * 排序条件
     */
    private String funcinfoOrdersql;

    /**
     * 功能信息是否完善
     */
    private String funcinfoIscomplete;

    /**
     * 一对一卡片
     */
    private String funcinfoOcform;

    /**
     * 新窗口编辑卡片
     */
    private String funcinfoOpenform;

    /**
     * 子卡片横向显示
     */
    private String funcinfoChildshowtype;

    /**
     * 功能字典
     */
    private String funcinfoFuncdic;

    /**
     * 是否表格树
     */
    private String funcinfoIsgridtree;

    /**
     * 功能字典配置信息
     */
    private String funcinfoFuncdicconfig;

    /**
     * 表单项label宽度
     */
    private Integer funcinfoFormlabelwidth;

    /**
     * 表单列数
     */
    private String funcinfoFormcols;

    /**
     * 默认宽度
     */
    private String funcinfoFormdefwidth;

    /**
     * 扩展panel
     */
    private String funcinfoExpandpanels;

    /**
     * 启用图表
     */
    private String funcinfoChartsopen;

    /**
     * 是否挂接工作流
     */
    private String funcinfoUsewf;

    /**
     * 启用附件
     */
    private String funcinfoUsefiles;

    /**
     * 启用数据留痕
     */
    private String funcinfoUsedatalog;

    /**
     * 数据源
     */
    private String funcinfoDatasource;

    /**
     * 报表附件
     */
    private String funcinfoReportfile;

    /**
     * 全息查询宽度
     */
    private Integer funcinfoQuerywidth;

    /**
     * 全息查询是否多选
     */
    private String funcinfoMultiquery;

    /**
     * 系统模式
     */
    private String funcinfoSysmode;

    /**
     * 附件保存路径
     */
    private String funcinfoAttachmentpath;

    /**
     * 权限配置内容
     */
    private String funcinfoPermconfig;

    /**
     * 是否表单分页
     */
    private String funcinfoFormpaging;

    /**
     * 表单分页配置
     */
    private String funcinfoFormpagingconfig;

    /**
     * 分组字段
     */
    private String funcinfoGroupfield;

    /**
     * 是否大按钮
     */
    private String funcinfoBigbutton;

    /**
     * 分组数据模版
     */
    private String funcinfoGroupfieldtpl;

    /**
     * 操作人
     */
    private String funcinfoCheckuser;

    /**
     * 操作人主键
     */
    private String funcinfoCheckuserid;

    /**
     * 操作人code
     */
    private String funcinfoCheckusercode;

    /**
     * 操作状态
     */
    private String funcinfoCheckstatus;

    /**
     * 版本
     */
    private String funcinfoVersion;

    /**
     * 是否启动书签
     */
    private String funcinfoIsbookmark;

    /**
     * 书签配置信息
     */
    private String funcinfoBookmarkconfig;

    /**
     * 表单监听事件
     */
    private String funcinfoFormjslistener;

    /**
     * 列表监听事件
     */
    private String funcinfoGridjslistener;

    /**
     * 一对一表单
     */
    private String funcinfoOnetoform;

    /**
     * 是否使用向导
     */
    private String funcinfoUseguide;

    /**
     * 向导位置
     */
    private String funcinfoGuidelocation;

    /**
     * 图标样式
     */
    private String funcinfoIconcls;

    /**
     * 功能类型
     */
    private String funcinfoFunctype;

    /**
     * 数据录入方式
     */
    private String funcinfoInserttype;

    /**
     * 列表表单
     */
    private String funcinfoListform;

    /**
     * 列表行模版
     */
    private String funcinfoGridrowtip;

    /**
     * 列表操作说明
     */
    private String funcinfoGridtip;

    /**
     * 快速查询操作说明
     */
    private String funcinfoTreetip;

    /**
     * 表单背景色
     */
    private String funcinfoFormbgcolor;

    /**
     * 表单标题类型
     */
    private String funcinfoFormtitletype;

    /**
     * 功能描述
     */
    private String funcinfoFuncremark;

    /**
     * 列表多选
     */
    private String funcinfoMultiselect;

    /**
     * 过滤条件描述
     */
    private String funcinfoWheresqlDes;

    /**
     * 排序字段描述
     */
    private String funcinfoOrdersqlDes;

    /**
     * 查询策略选择模式
     */
    private String funcinfoCxclselmodel;

    /**
     * 可见角色
     */
    private String funcinfoSeerolenames;

    /**
     * 可见角色id
     */
    private String funcinfoSeeroleids;

    /**
     * 可见岗位id
     */
    private String funcinfoSeesentryids;

    /**
     * 可见人员
     */
    private String funcinfoSeeusernames;

    /**
     * 可见人员id
     */
    private String funcinfoSeeuserids;

    /**
     * 可见岗位
     */
    private String funcinfoSeesentrynames;

    /**
     * 字段边框颜色
     */
    private String funcinfoFieldbordercolor;

    /**
     * 标签背景颜色
     */
    private String funcinfoLabelbgcolor;

    /**
     * 表格样式
     */
    private String funcinfoTablestyle;

    /**
     * 表单最小宽度
     */
    private String funcinfoFormminwidth;

    /**
     * 子功能分步加载数据
     */
    private String funcinfoChildrefresh;

    /**
     * 启用表格表单
     */
    private String funcinfoTableform;

    /**
     * 表格表单模版
     */
    private String funcinfoTableformtpl;

    /**
     * 自定义表单高度
     */
    private String funcinfoTableformheight;

    /**
     * 自定义表单宽度
     */
    private String funcinfoTableformwidth;

    /**
     * 子功能多条过滤
     */
    private String funcinfoChildfilter;

    /**
     * 弹出表单宽高
     */
    private String funcinfoWinformwh;

    /**
     * 拖动排序
     */
    private String funcinfoDdorder;

    /**
     * 列表打印配置
     */
    private String funcinfoGridprintinfo;

    /**
     * 自定义表单宽度2
     */
    private String funcinfoTableformwidth2;

    /**
     * 快速查询分步加载
     */
    private String funcinfoTreerefresh;

    /**
     * 列表子功能并排显示
     */
    private String funcinfoGridchildss;

    /**
     * 启用表单打印按钮
     */
    private String funcinfoEnableformprint;

    /**
     * 隐藏列表工具条
     */
    private String funcinfoHidegridtbar;

    /**
     * 功能组态
     */
    private String funcinfoCombine;

    /**
     * 帮助
     */
    private String funcinfoHelp;

    /**
     * 表单懒加载
     */
    private String funcinfoFormlazy;

    /**
     * 是否启用列分步加载
     */
    private String funcinfoColumnlazy;

    /**
     * 高级查询展开
     */
    private String funcinfoGroupformopen;

    /**
     * 隐藏表单工具条
     */
    private String funcinfoHideformtbar;

    /**
     * lodop打印配置
     */
    private String funcinfoLodopconfig;

    /**
     * lodop打印
     */
    private String funcinfoLodopprint;

    /**
     * 表单直接打印
     */
    private String funcinfoTableformprint;

    /**
     * 可见部门
     */
    private String funcinfoSeedeptnames;

    /**
     * 可见部门id
     */
    private String funcinfoSeedeptids;

    /**
     * 功能英文名
     */
    private String funcinfoFuncnameEn;

    /**
     * 表单项label英文宽度
     */
    private Integer funcinfoFormlabelwidthEn;

    /**
     * 列表操作说明
     */
    private String funcinfoGridtipEn;

    /**
     * 快速查询操作说明英文
     */
    private String funcinfoTreetipEn;

    /**
     * 字段懒加载
     */
    private String funcinfoFieldlazy;

    /**
     * 列表无限滚动
     */
    private String funcinfoGridbuffered;

    /**
     * 列表请求超时时间
     */
    private Integer funcinfoGridtimeout;

    /**
     * 列表行模板总是展开
     */
    private String funcinfoGridrowtipshow;

    /**
     * 列表隐藏元素
     */
    private String funcinfoGridhides;

    /**
     * 操作表名
     */
    private String funcinfoCrudtablename;

    /**
     * 版本_dev
     */
    private String funcinfoVersionDev;

    /**
     * 操作人_dev
     */
    private String funcinfoCheckuserDev;

    /**
     * 操作人主键_dev
     */
    private String funcinfoCheckuseridDev;

    /**
     * 操作状态_dev
     */
    private String funcinfoCheckstatusDev;

    /**
     * 是否需对比
     */
    private String funcinfoSfxdb;

    /**
     * 启用流程审批记录
     */
    private String funcinfoUsewflog;

    /**
     * 子功能高度
     */
    private String funcinfoChildheight;

    /**
     * 使用关系
     */
    private String funcinfoUsecjglnames;

    /**
     * 启用树形列表视图
     */
    private String funcinfoUsetreegrid;

    /**
     * 使用关系id
     */
    private String funcinfoUsecjglids;

    /**
     * 简化查询
     */
    private String funcinfoSimplesearch;

    /**
     * 只读流程
     */
    private String funcinfoReadonlywf;

    /**
     * 启用数据标记
     */
    private String funcinfoUsemark;

    /**
     * 权限sql
     */
    private String funcinfoPermsql;

    /**
     * 自定义列表
     */
    private String funcinfoTableview;

    /**
     * 自定义列表列数
     */
    private Integer funcinfoTableviewCols;

    /**
     * 自定义列表tpl
     */
    private String funcinfoTableviewTpl;

    /**
     * 隐藏表格线
     */
    private String funcinfoGridhidelines;

    /**
     * 启用审核功能
     */
    private String funcinfoEnabledsh;

    /**
     * 数据源
     */
    private String funcinfoDbname;

    /**
     * 简洁工具条
     */
    private String funcinfoSimplebar;

    /**
     * 存储过程
     */
    private String funcinfoProcedure;

    /**
     * 分页信息位置
     */
    private String funcinfoPageinfoalign;

    /**
     * 查询参数
     */
    private String funcinfoQueryparam;

    /**
     * sql
     */
    private String funcinfoSql;

    /**
     * 表单宽度
     */
    private String funcinfoFormwidth;

    /**
     * 快速定位位置
     */
    private String funcinfoFormnavigate;

    /**
     * 帮助数据源
     */
    private String funcinfoHelpsource;

    /**
     * 帮助数据源名称
     */
    private String funcinfoHelpsourceText;

    /**
     * 禁用功能查询sql
     */
    private String funcinfoDisablequerysql;

    /**
     * 表单标题
     */
    private String funcinfoFormtitle4func;

    /**
     * 视图配置信息
     */
    private String funcinfoViewconfiginfo;

    /**
     * saas模式
     */
    private String funcinfoUsesaas;

    /**
     * 辅助配置项
     */
    private String funcinfoOtherconfig;

    /**
     * 启用修改标记
     */
    private String funcinfoUseedit;

    /**
     * 数据权限条件脚本
     */
    private String funcinfoPermjs;

    /**
     * 控制人员字段
     */
    private String funcinfoUserfield;

    /**
     * 控制部门字段
     */
    private String funcinfoDeptfield;

    /**
     * 是否启用安全模式
     */
    private String funcinfoSafety;

    /**
     * 最后静态时间
     */
    private String funcinfoZhjtsj;

    /**
     * 开启更多按钮
     */
    private String funcinfoOpenMoreButton;

    /**
     * 更多按钮配置
     */
    private String funcinfoMoreButtonConfig;

    /**
     * 绑定工作流
     */
    private String funcinfoBindingWorkflow;

    /**
     * 高级查询禁用change事件
     */
    private String funcinfoDisableChange;

    /**
     * 留痕依赖功能
     */
    private String funcinfoLeaveMarkFunc;

    /**
     * 过滤sql条件描述
     */
    private String funcinfoFilterDescribe;

    /**
     * 排序sql描述
     */
    private String funcinfoSortDescribe;

    /**
     * 打印表单标题
     */
    private String funcinfoPrintFormTitle;

    /**
     * 弹出表单位置
     */
    private String funcinfoFormPosition;

    /**
     * 分组框快速定位
     */
    private String funcinfoGroupLocation;

    /**
     * 快速查询动态刷新
     */
    private String funcinfoTreedynamicrefresh;

    /**
     * 快速查询标题
     */
    private String funcinfoTreetitle;

    /**
     * 快速查询宽度
     */
    private Integer funcinfoTreewidth;

    /**
     * 批注标题
     */
    private String funcinfoAnnotationTitle;

    /**
     * 微邮标题
     */
    private String funcinfoMicroMailTitle;

    /**
     * 隔行变色
     */
    private String funcinfoInterlacedDiscolour;

    /**
     * 行编辑
     */
    private String funcinfoLineEdit;

    /**
     * 个性化列定义
     */
    private String funcinfoCustomColumn;

    /**
     * 弹出表单显示子功能
     */
    private String funcinfoFormwinchild;

    /**
     * 默认行高
     */
    private String funcinfoColumnWidth;

    /**
     * 弹出表单位置
     */
    private String funcinfoFormPostion;

    /**
     * 弹出字段间距
     */
    private String funcinfoFieldSpacing;

    /**
     * 标签对齐整体设置
     */
    private String funcinfoLabelalignment;

    /**
     * 标签位置整体设置
     */
    private String funcinfoLabelposition;

    /**
     * 手动切换表单展示方案
     */
    private String funcinfoFormShowWay;

    /**
     * 手动切换表单打印方案
     */
    private String funcinfoFormPrintWay;

    /**
     * 开启更多按钮
     */
    private String funcinfoOpenMoreBut;

    /**
     * sql查询参数
     */
    private String funcinfoQueryparamSql;

    /**
     * 表单状态
     */
    private String funcinfoFormState;

    /**
     * 初始化列表加载数据
     */
    private String funcinfoInitLoadData;

    /**
     * 表单字段间距
     */
    private String funcinfoFormfieldSpacing;

    /**
     * 布局配置方式
     */
    private String funcinfoLayoutType;

    /**
     * 栅格布局列数
     */
    private String funcinfoLayoutColumnCount;

    /**
     * 是否加入查询策略
     */
    private String funcinfoAddQueryStrategy;

    /**
     * 多级子功能导航
     */
    private String funcinfoChildfuncNavigation;

    /**
     * 绑定工作流id
     */
    private String funcinfoBindingWorkflowid;

    /**
     * 高级查询禁用change事件
     */
    private String functionDisabledChange;

    /**
     * 字段标题色
     */
    private String funcinfoFormLabelcolor;

    /**
     * 标题宽
     */
    private String funcinfoGroupqueryLabelWidth;

    /**
     * 启用搜索按钮
     */
    private String funcinfoGroupqueryEnabelQuery;

    /**
     * 是否启用检索按钮
     */
    private String funcinfoOpenSearchButton;

    /**
     * 表单样式
     */
    private String funcinfoFormStyle;

    /**
     * saas产品
     */
    private String funcinfoSaasPid;

    /**
     * 统计策略
     */
    private String funcinfoStatisticalstrategy;

    /**
     * 关闭拖拽排序
     */
    private String funcinfoCloseDragSort;

    /**
     * 启用导出按钮
     */
    private String funcinfoGroupqueryStartExport;

    /**
     * 字段标题位置
     */
    private String funcinfoAppformLabelAlign;

    /**
     * 字段标题宽度
     */
    private Integer funcinfoAppformLabelWidth;

    /**
     * 隐藏表单子功能导航
     */
    private String funcinfoAppformDisableCrumb;

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
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 登记人所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 修改人部门id
     */
    private String syModifyorgid;

    /**
     * 修改人id
     */
    private String syModifyuserid;

    /**
     * 修改人编码
     */
    private String syModifyuser;

    /**
     * 所属产品id
     */
    private String syProductId;

    /**
     * 所属产品code
     */
    private String syProductCode;

    /**
     * 所属产品名称
     */
    private String syProductName;

    /**
     * je核心
     */
    private String syJecore;

    /**
     * je系统
     */
    private String syJesys;

    /**
     * 树形排序字段
     */
    private String syTreeorderindex;

    /**
     * 父节点路径
     */
    private String syParentpath;

    /**
     * 树形路径
     */
    private String syPath;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 是否启用本条数据
     */
    private String syFlag;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改时间
     */
    private String syModifytime;

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

}
