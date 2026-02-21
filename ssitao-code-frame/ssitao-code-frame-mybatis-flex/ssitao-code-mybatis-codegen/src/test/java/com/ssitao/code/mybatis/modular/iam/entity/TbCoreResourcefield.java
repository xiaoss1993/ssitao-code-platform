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
 * 单字段 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_resourcefield")
public class TbCoreResourcefield extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreResourcefieldId;

    /**
     * 编码
     */
    private String resourcefieldCode;

    /**
     * 名称
     */
    private String resourcefieldName;

    /**
     * 宽度
     */
    private String resourcefieldWidth;

    /**
     * 比例
     */
    private String resourcefieldFlex;

    /**
     * 类型
     */
    private String resourcefieldXtype;

    /**
     * 是否为空
     */
    private String resourcefieldAllowblank;

    /**
     * 是否隐藏
     */
    private String resourcefieldHidden;

    /**
     * 是否导入
     */
    private String resourcefieldIfimpl;

    /**
     * 是否可用
     */
    private String resourcefieldDisabled;

    /**
     * 是否只读
     */
    private String resourcefieldReadonly;

    /**
     * 是否主键
     */
    private String resourcefieldIspk;

    /**
     * 配置信息
     */
    private String resourcefieldConfiginfo;

    /**
     * 校验信息
     */
    private String resourcefieldVtype;

    /**
     * 描述
     */
    private String resourcefieldDescription;

    /**
     * 分组框
     */
    private String resourcefieldGroupname;

    /**
     * 所占列数
     */
    private String resourcefieldColspan;

    /**
     * 字段样式表
     */
    private String resourcefieldFieldcls;

    /**
     * 列数(分组框用)
     */
    private String resourcefieldCols;

    /**
     * 所占行数
     */
    private String resourcefieldRowspan;

    /**
     * 最小值
     */
    private String resourcefieldMinvalue;

    /**
     * 最大值
     */
    private String resourcefieldMaxvalue;

    /**
     * 步长
     */
    private String resourcefieldStep;

    /**
     * 默认值
     */
    private String resourcefieldValue;

    /**
     * 可选可编辑
     */
    private String resourcefieldEditable;

    /**
     * 绑定信息
     */
    private String resourcefieldBinding;

    /**
     * 正则表达式
     */
    private String resourcefieldRegex;

    /**
     * 正则提示信息
     */
    private String resourcefieldRegextext;

    /**
     * 系统模式
     */
    private String resourcefieldSysmode;

    /**
     * 产品扩展功能id
     */
    private String resourcefieldNewfuncid;

    /**
     * 辅助配置信息
     */
    private String resourcefieldOtherconfig;

    /**
     * 监听事件
     */
    private String resourcefieldJslistener;

    /**
     * 显隐控制表达式
     */
    private String resourcefieldInterpreter;

    /**
     * 输入提示
     */
    private String resourcefieldEmptytext;

    /**
     * 功能外键
     */
    private String resourcefieldFuncinfoId;

    /**
     * 背景色
     */
    private String resourcefieldBgcolor;

    /**
     * wheresql
     */
    private String resourcefieldWheresql;

    /**
     * 高度
     */
    private String resourcefieldHeight;

    /**
     * 历史留痕
     */
    private String resourcefieldHistory;

    /**
     * 验证配置
     */
    private String resourcefieldVtypeconfig;

    /**
     * 必填表达式
     */
    private String resourcefieldAllowblankexp;

    /**
     * 只读表达式
     */
    private String resourcefieldReadonlyexp;

    /**
     * 可选表达式
     */
    private String resourcefieldEnableexp;

    /**
     * 可选表达式提示语
     */
    private String resourcefieldEnabletip;

    /**
     * 定位
     */
    private String resourcefieldLocate;

    /**
     * 字段后缀
     */
    private String resourcefieldUnittpl;

    /**
     * 显隐控制表达式方法
     */
    private String resourcefieldInterpreterfn;

    /**
     * 必填控制表达式方法
     */
    private String resourcefieldAllowblankexpfn;

    /**
     * 树形字典项可选表达式
     */
    private String resourcefieldEnableexpfn;

    /**
     * 只读控制表达式方法
     */
    private String resourcefieldReadonlyexpfn;

    /**
     * 绑定表达式方法
     */
    private String resourcefieldBindingfn;

    /**
     * 默认值函数
     */
    private String resourcefieldValuefn;

    /**
     * 标签颜色
     */
    private String resourcefieldLabelcolor;

    /**
     * 字段颜色
     */
    private String resourcefieldFieldcolor;

    /**
     * 禁用
     */
    private String resourcefieldRemoved;

    /**
     * 英文名
     */
    private String resourcefieldNameEn;

    /**
     * 高级后缀
     */
    private String resourcefieldUnitlisttpl;

    /**
     * 依附字段
     */
    private String resourcefieldRowfield;

    /**
     * 字段懒加载
     */
    private String resourcefieldFieldlazy;

    /**
     * 自定义验证方法
     */
    private String resourcefieldVtypefn;

    /**
     * 标签位置
     */
    private String resourcefieldLabelalign;

    /**
     * 隐藏标签
     */
    private String resourcefieldHidelabel;

    /**
     * 字段长度
     */
    private String resourcefieldMaxlength;

    /**
     * 帮助提醒
     */
    private String resourcefieldHelp;

    /**
     * 保持收起
     */
    private String resourcefieldKeepup;

    /**
     * 数据字典带值配置
     */
    private String dicBandValueConfig;

    /**
     * 数字提醒
     */
    private String resourcefieldNumberTip;

    /**
     * 级联数字提醒
     */
    private String resourcefieldJlNumberTip;

    /**
     * 树形展开层数
     */
    private String resourcefieldTreeOpenHeight;

    /**
     * 根节点id
     */
    private String resourcefieldRootId;

    /**
     * 是否多根树
     */
    private String resourcefieldMultipleRoot;

    /**
     * 帮助提示宽
     */
    private String resourcefieldHelpTipW;

    /**
     * 帮助提示高
     */
    private String resourcefieldHelpTipH;

    /**
     * 允许上传类型
     */
    private String resourcefieldCanUpload;

    /**
     * 禁止上传类型
     */
    private String resourcefieldNoUpload;

    /**
     * 上传文件大小
     */
    private String resourcefieldFileSize;

    /**
     * 按钮位置
     */
    private String resourcefieldButPosition;

    /**
     * 可删除
     */
    private String resourcefieldAllowDel;

    /**
     * 可批量删除
     */
    private String resourcefieldBatchDel;

    /**
     * 可添加
     */
    private String resourcefieldAllowAdd;

    /**
     * 可批量添加
     */
    private String resourcefieldBatchAdd;

    /**
     * 批量添加类型
     */
    private String resourcefieldBatchAddType;

    /**
     * 批量添加配置
     */
    private String resourcefieldBatchAddConfig;

    /**
     * 文件上传数量
     */
    private String resourcefieldUploadCount;

    /**
     * 数字颜色
     */
    private String resourcefieldNumberColor;

    /**
     * 文件操作权限
     */
    private String resourcefieldFileAuthority;

    /**
     * 基础色
     */
    private String resourcefieldBaseColor;

    /**
     * 标题色
     */
    private String resourcefieldTitleColor;

    /**
     * 标题背景色
     */
    private String resourcefieldTitlebColor;

    /**
     * 边框色
     */
    private String resourcefieldBorderColor;

    /**
     * 上边框色
     */
    private String resourcefieldAboveColor;

    /**
     * 无样式
     */
    private String resourcefieldNoStyle;

    /**
     * 可穿透
     */
    private String resourcefieldCanThrough;

    /**
     * 显示主功能
     */
    private String resourcefieldShowMainFunc;

    /**
     * 显示子功能
     */
    private String resourcefieldShowSubFunc;

    /**
     * 展示字段
     */
    private String resourcefieldShowField;

    /**
     * 查询字段
     */
    private String resourcefieldQueryField;

    /**
     * 弹出面板宽
     */
    private String resourcefieldPopupWidth;

    /**
     * 弹出面板高
     */
    private String resourcefieldPopupHeight;

    /**
     * 部门过滤
     */
    private String resourcefieldDepartmentFilter;

    /**
     * 岗位过滤
     */
    private String resourcefieldJobsFilter;

    /**
     * 角色过滤
     */
    private String resourcefieldRoleFilter;

    /**
     * 工作组过滤
     */
    private String resourcefieldWorkGroupFilter;

    /**
     * 回选过滤字段
     */
    private String resourcefieldAgainFilterField;

    /**
     * 快查条件
     */
    private String resourcefieldFastQueryCondition;

    /**
     * 组内标题宽
     */
    private String resourcefieldTitleWidth;

    /**
     * 表单方案外键
     */
    private String resourcefieldPlanId;

    /**
     * 必填
     */
    private String resourcefieldRequire;

    /**
     * 必填表达式
     */
    private String resourcefieldRequireExp;

    /**
     * 条件说明
     */
    private String resourcefieldConditionDesc;

    /**
     * 快查取值字段
     */
    private String resourcefieldFastQueryValue;

    /**
     * 必填控制表达式方法
     */
    private String resourcefieldRequireExpfn;

    /**
     * saas产品
     */
    private String resourcefieldSaasPid;

    /**
     * 使用范围
     */
    private String resourcefieldUseScope;

    /**
     * 字典自定义变量
     */
    private String resourcefieldDdCustomerVariables;

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
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

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
