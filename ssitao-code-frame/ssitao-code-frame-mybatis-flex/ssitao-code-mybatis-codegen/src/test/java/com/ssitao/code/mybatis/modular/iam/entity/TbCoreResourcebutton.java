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
 * 功能按钮 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_resourcebutton")
public class TbCoreResourcebutton extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreResourcebuttonId;

    /**
     * 编码
     */
    private String resourcebuttonCode;

    /**
     * 名称
     */
    private String resourcebuttonName;

    /**
     * 不只读
     */
    private String resourcebuttonNoreadonly;

    /**
     * 是否隐藏
     */
    private String resourcebuttonHidden;

    /**
     * 是否可用
     */
    private String resourcebuttonDisabled;

    /**
     * 样式
     */
    private String resourcebuttonXtype;

    /**
     * 触发事件
     */
    private String resourcebuttonFireevent;

    /**
     * 绑定表单验证
     */
    private String resourcebuttonFormbind;

    /**
     * 流程结束可用
     */
    private String resourcebuttonWfendedenable;

    /**
     * 配置信息
     */
    private String resourcebuttonConfiginfo;

    /**
     * 系统模式
     */
    private String resourcebuttonSysmode;

    /**
     * 产品扩展功能id
     */
    private String resourcebuttonNewfuncid;

    /**
     * 监听事件
     */
    private String resourcebuttonJslistener;

    /**
     * 显隐控制表达式
     */
    private String resourcebuttonInterpreter;

    /**
     * 功能外键
     */
    private String resourcebuttonFuncinfoId;

    /**
     * 类型
     */
    private String resourcebuttonType;

    /**
     * 提示信息
     */
    private String resourcebuttonMsg;

    /**
     * 英文名
     */
    private String resourcebuttonNameEn;

    /**
     * 样式
     */
    private String resourcebuttonCls;

    /**
     * 依附按钮
     */
    private String resourcebuttonYfan;

    /**
     * 按钮位置
     */
    private String resourcebuttonPosition;

    /**
     * 图标
     */
    private String resourcebuttonIcon;

    /**
     * 图标颜色
     */
    private String resourcebuttonIconcolor;

    /**
     * saas产品
     */
    private String resourcebuttonSaasPid;

    /**
     * 使用范围
     */
    private String resourcebuttonUseScope;

    /**
     * 背景颜色
     */
    private String resourcebuttonBgcolor;

    /**
     * 字体颜色
     */
    private String resourcebuttonFontcolor;

    /**
     * 边框颜色
     */
    private String resourcebuttonBordercolor;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 修改部门id
     */
    private String syModifyorgid;

    /**
     * 修改人id
     */
    private String syModifyuserid;

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
