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
 * 列字段 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_resourcecolumn")
public class TbCoreResourcecolumn extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreResourcecolumnId;

    /**
     * 字典编码
     */
    private String resourcecolumnDdcode;

    /**
     * 是否在字典中取
     */
    private String resourcecolumnIsdd;

    /**
     * 高亮策略中的color
     */
    private String resourcecolumnColor1;

    /**
     * 字典策略中的color
     */
    private String resourcecolumnColor2;

    /**
     * 字典策略中的color
     */
    private String resourcecolumnFontcolor;

    /**
     * 合并单元格
     */
    private String resourcecolumnMerge;

    /**
     * 关键字查询组建名称
     */
    private String resourcecolumnKeyword;

    /**
     * 是否拥转换器conversion
     */
    private String resourcecolumnConversion;

    /**
     * 是否拥转换器highlighting
     */
    private String resourcecolumnHighlighting;

    /**
     * 是否超链接
     */
    private String resourcecolumnHyperlink;

    /**
     * 链接方法
     */
    private String resourcecolumnLinkmethod;

    /**
     * 是否编辑
     */
    private String resourcecolumnAllowedit;

    /**
     * 编辑时全选
     */
    private String resourcecolumnSelectonfocus;

    /**
     * 最小值
     */
    private String resourcecolumnMinvalue;

    /**
     * 最大值
     */
    private String resourcecolumnMaxvalue;

    /**
     * 步长
     */
    private String resourcecolumnStep;

    /**
     * 编码
     */
    private String resourcecolumnCode;

    /**
     * 名称
     */
    private String resourcecolumnName;

    /**
     * 宽度
     */
    private String resourcecolumnWidth;

    /**
     * 比例
     */
    private String resourcecolumnFlex;

    /**
     * 类型
     */
    private String resourcecolumnXtype;

    /**
     * 查询类型
     */
    private String resourcecolumnQuerytype;

    /**
     * 快速查询时查询类型
     */
    private String resourcecolumnQuickquerytype;

    /**
     * 是否快速查询
     */
    private String resourcecolumnQuickquery;

    /**
     * 是否为空
     */
    private String resourcecolumnAllowblank;

    /**
     * 是否隐藏
     */
    private String resourcecolumnHidden;

    /**
     * 是否导入
     */
    private String resourcecolumnIfimpl;

    /**
     * 是否主键
     */
    private String resourcecolumnIspk;

    /**
     * 查询信息
     */
    private String resourcecolumnQueryinfo;

    /**
     * 配置信息
     */
    private String resourcecolumnConfiginfo;

    /**
     * 策略
     */
    private String resourcecolumnTactics;

    /**
     * 校验信息
     */
    private String resourcecolumnVtype;

    /**
     * 描述
     */
    private String resourcecolumnDescription;

    /**
     * 默认值
     */
    private String resourcecolumnValue;

    /**
     * 锁定列
     */
    private String resourcecolumnLocked;

    /**
     * 多表头
     */
    private String resourcecolumnMorecolumn;

    /**
     * 多表头名称
     */
    private String resourcecolumnMorecolumnname;

    /**
     * 统计类型
     */
    private String resourcecolumnStatisticstype;

    /**
     * 统计描述
     */
    private String resourcecolumnStatisticsmsg;

    /**
     * 列表排序字段
     */
    private String resourcecolumnFieldorderindex;

    /**
     * 查询排序
     */
    private String resourcecolumnQueryindex;

    /**
     * 多行显示
     */
    private String resourcecolumnMultirows;

    /**
     * 时间格式
     */
    private String resourcecolumnDateformat;

    /**
     * 系统模式
     */
    private String resourcecolumnSysmode;

    /**
     * 产品扩展功能id
     */
    private String resourcecolumnNewfuncid;

    /**
     * 编码
     */
    private String resourcecolumnFkcode;

    /**
     * 启用图标
     */
    private String resourcecolumnEnableicon;

    /**
     * 列居中
     */
    private String resourcecolumnAlign;

    /**
     * 列提示
     */
    private String resourcecolumnColumntip;

    /**
     * 功能外键
     */
    private String resourcecolumnFuncinfoId;

    /**
     * 监听事件
     */
    private String resourcecolumnJslistener;

    /**
     * 是否索引
     */
    private String resourcecolumnIndex;

    /**
     * 是否排序
     */
    private String resourcecolumnOrder;

    /**
     * 启用批量更新
     */
    private String resourcecolumnEnableupdate;

    /**
     * 选中样式
     */
    private String resourcecolumnCheckedcls;

    /**
     * 补选中样式
     */
    private String resourcecolumnUncheckedcls;

    /**
     * 右边框色
     */
    private String resourcecolumnRbordercolor;

    /**
     * 隐藏编辑提示
     */
    private String resourcecolumnHidetitlecls;

    /**
     * 总统计描述
     */
    private String resourcecolumnStatistallmsg;

    /**
     * 分组
     */
    private String resourcecolumnGroup;

    /**
     * 统计数字格式
     */
    private String resourcecolumnSummaryformat;

    /**
     * 列查询类型
     */
    private String resourcecolumnColumnquerytype;

    /**
     * 分步加载
     */
    private String resourcecolumnLazyload;

    /**
     * 颜色
     */
    private String resourcecolumnTitlecolor;

    /**
     * 英文名
     */
    private String resourcecolumnNameEn;

    /**
     * 英文宽度
     */
    private String resourcecolumnWidthEn;

    /**
     * 辅助配置项
     */
    private String resourcecolumnOtherconfig;

    /**
     * 个性化
     */
    private String resourcecolumnIdit;

    /**
     * 表单列
     */
    private String resourcecolumnFormcolumn;

    /**
     * 自动填充
     */
    private String resourcecolumnAutoFill;

    /**
     * 锁定前列
     */
    private String resourcecolumnLockForefront;

    /**
     * 锁定后列
     */
    private String resourcecolumnLockAfter;

    /**
     * 快查带值
     */
    private String resourcecolumnCheckbandvalue;

    /**
     * 开启更多按钮
     */
    private String resourcecolumnOpenmorebut;

    /**
     * 更多按钮配置
     */
    private String resourcecolumnMorebutconfig;

    /**
     * 启用超级链接
     */
    private String resourcecolumnEnablhyperlinks;

    /**
     * 标题对齐方式
     */
    private String resourcecolumnAlignTitle;

    /**
     * 统计配置信息
     */
    private String resourcecolumnStatisticsconfig;

    /**
     * 列表快速查询
     */
    private String resourcecolumnQuickqueryvalue;

    /**
     * 列表方案外键
     */
    private String resourcecolumnPlanId;

    /**
     * 字段增量类型
     */
    private String resourcecolumnBulkingType;

    /**
     * 复选框样式
     */
    private String resourcecolumnCheckboxStyle;

    /**
     * saas产品
     */
    private String resourcecolumnSaasPid;

    /**
     * 统计策略
     */
    private String resourcecolumnStatisticalstrategy;

    /**
     * 登记者所在部门id
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
     * 所属公司id
     */
    private String syCompanyId;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 审核标记
     */
    private String syAudflag;

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

}
