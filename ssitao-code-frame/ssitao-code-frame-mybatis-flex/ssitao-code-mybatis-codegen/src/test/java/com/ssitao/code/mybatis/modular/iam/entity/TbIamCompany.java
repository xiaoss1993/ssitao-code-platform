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
 * 公司管理 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_company")
public class TbIamCompany extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamCompanyId;

    /**
     * 备注信息
     */
    private String companyRemark;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 管理员_id
     */
    private String companyManagerId;

    /**
     * 主管_id
     */
    private String companyMajorId;

    /**
     * 主管_name
     */
    private String companyMajorName;

    /**
     * 公司级别_code
     */
    private String companyLevelCode;

    /**
     * 公司级别_name
     */
    private String companyLevelName;

    /**
     * 办公地址
     */
    private String companyAddress;

    /**
     * 管理员名称
     */
    private String companyManagerName;

    /**
     * 图标
     */
    private String companyIcon;

    /**
     * 监管公司_id
     */
    private String companyJgcompanyId;

    /**
     * 简称
     */
    private String companySimplename;

    /**
     * 公司电话
     */
    private String companyTelephone;

    /**
     * 监管公司_name
     */
    private String companyJgcompanyName;

    /**
     * 职能描述
     */
    private String companyFuncDesc;

    /**
     * 经营范围
     */
    private String companyJyfw;

    /**
     * 成立日期
     */
    private String companyClrq;

    /**
     * 所属集团公司_id
     */
    private String syGroupCompanyId;

    /**
     * 所属集团公司_name
     */
    private String syGroupCompanyName;

    /**
     * 修改人部门主键
     */
    private String syModifyorgid;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改人主键
     */
    private String syModifyuserid;

    /**
     * 修改人
     */
    private String syModifyusername;

    /**
     * 修改时间
     */
    private String syModifytime;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户_name
     */
    private String syTenantName;

    /**
     * 父节点id
     */
    private String syParent;

    /**
     * 节点类型
     */
    private String syNodetype;

    /**
     * 父节点路径
     */
    private String syParentpath;

    /**
     * 层次
     */
    private Integer syLayer;

    /**
     * 树形路径
     */
    private String syPath;

    /**
     * 是否启用
     */
    private String syDisabled;

    /**
     * 树形排序字段
     */
    private String syTreeorderindex;

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

}
