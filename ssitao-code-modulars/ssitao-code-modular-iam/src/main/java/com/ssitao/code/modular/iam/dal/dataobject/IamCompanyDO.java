package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM公司数据对象
 * 对应数据库表：tb_iam_company
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_company")
public class IamCompanyDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamCompanyId;

    /**
     * 公司名称
     */
    @Column(value = "company_name")
    private String companyName;

    /**
     * 公司编码
     */
    @Column(value = "company_code")
    private String companyCode;

    /**
     * 备注
     */
    @Column(value = "company_remark")
    private String companyRemark;

    /**
     * 管理员_id
     */
    @Column(value = "company_manager_id")
    private String companyManagerId;

    /**
     * 主管_id
     */
    @Column(value = "company_major_id")
    private String companyMajorId;

    /**
     * 主管_name
     */
    @Column(value = "company_major_name")
    private String companyMajorName;

    /**
     * 公司级别_code
     */
    @Column(value = "company_level_code")
    private String companyLevelCode;

    /**
     * 公司级别_name
     */
    @Column(value = "company_level_name")
    private String companyLevelName;

    /**
     * 办公地址
     */
    @Column(value = "company_address")
    private String companyAddress;

    /**
     * 管理员名称
     */
    @Column(value = "company_manager_name")
    private String companyManagerName;

    /**
     * 图标
     */
    @Column(value = "company_icon")
    private String companyIcon;

    /**
     * 监管公司_id
     */
    @Column(value = "company_jgcompany_id")
    private String companyJgcompanyId;

    /**
     * 简称
     */
    @Column(value = "company_simplename")
    private String companySimplename;

    /**
     * 公司电话
     */
    @Column(value = "company_telephone")
    private String companyTelephone;

    /**
     * 监管公司_name
     */
    @Column(value = "company_jgcompany_name")
    private String companyJgcompanyName;

    /**
     * 职能描述
     */
    @Column(value = "company_func_desc")
    private String companyFuncDesc;

    /**
     * 经营范围
     */
    @Column(value = "company_jyfw")
    private String companyJyfw;

    /**
     * 成立日期
     */
    @Column(value = "company_clrq")
    private String companyClrq;

    /**
     * 所属集团公司_id
     */
    @Column(value = "sy_group_company_id")
    private String syGroupCompanyId;

    /**
     * 所属集团公司_name
     */
    @Column(value = "sy_group_company_name")
    private String syGroupCompanyName;

    /**
     * 租户_id
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

    /**
     * 租户_name
     */
    @Column(value = "sy_tenant_name")
    private String syTenantName;

    /**
     * 父节点id
     */
    @Column(value = "sy_parent")
    private String syParent;

    /**
     * 节点类型
     */
    @Column(value = "sy_nodetype")
    private String syNodetype;

    /**
     * 父节点路径
     */
    @Column(value = "sy_parentpath")
    private String syParentpath;

    /**
     * 层次
     */
    @Column(value = "sy_layer")
    private Integer syLayer;

    /**
     * 树形路径
     */
    @Column(value = "sy_path")
    private String syPath;

    /**
     * 是否启用
     */
    @Column(value = "sy_disabled")
    private String syDisabled;

    /**
     * 树形排序字段
     */
    @Column(value = "sy_treeorderindex")
    private String syTreeorderindex;

    /**
     * 登记部门主键
     */
    @Column(value = "sy_createorgid")
    private String syCreateorgid;

    /**
     * 登记部门
     */
    @Column(value = "sy_createorgname")
    private String syCreateorgname;

    /**
     * 登记时间
     */
    @Column(value = "sy_createtime")
    private String syCreatetime;

    /**
     * 登记人主键
     */
    @Column(value = "sy_createuserid")
    private String syCreateuserid;

    /**
     * 登记人
     */
    @Column(value = "sy_createusername")
    private String syCreateusername;

    /**
     * 数据状态
     */
    @Column(value = "sy_status")
    private String syStatus;

    /**
     * 排序字段
     */
    @Column(value = "sy_orderindex")
    private Integer syOrderindex;

    /**
     * 修改人部门主键
     */
    @Column(value = "sy_modifyorgid")
    private String syModifyorgid;

    /**
     * 修改人部门
     */
    @Column(value = "sy_modifyorgname")
    private String syModifyorgname;

    /**
     * 修改人主键
     */
    @Column(value = "sy_modifyuserid")
    private String syModifyuserid;

    /**
     * 修改人
     */
    @Column(value = "sy_modifyusername")
    private String syModifyusername;

    /**
     * 修改时间
     */
    @Column(value = "sy_modifytime")
    private String syModifytime;
}
