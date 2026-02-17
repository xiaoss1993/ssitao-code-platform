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
 * 机构管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_org")
public class TbIamOrg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源表_id
     */
    private String tbCoreResourcetableId;

    /**
     * 主键id
     */
    @Id
    private String tbIamOrgId;

    /**
     * 名称
     */
    private String orgName;

    /**
     * 编码
     */
    private String orgCode;

    /**
     * 资源表_code
     */
    private String orgResourcetableCode;

    /**
     * 资源表_name
     */
    private String orgResourcetableName;

    /**
     * 账号手机字段
     */
    private String orgAccountPhone;

    /**
     * 账号邮箱字段
     */
    private String orgAccountMail;

    /**
     * 账号头像字段
     */
    private String orgAccountAvatar;

    /**
     * 备注信息
     */
    private String orgRemark;

    /**
     * 角色字段_id
     */
    private String orgRolefieldId;

    /**
     * 角色字段_name
     */
    private String orgRolefieldName;

    /**
     * 账户主键字段
     */
    private String orgFieldPk;

    /**
     * 账户名称字段
     */
    private String orgAccountName;

    /**
     * 账户编码字段
     */
    private String orgAccountCode;

    /**
     * 账号性别字段
     */
    private String orgAccountSex;

    /**
     * 账号真实用户状态
     */
    private String orgAccountStatus;

    /**
     * 账号访问状态
     */
    private String orgAccountaccessStatus;

    /**
     * 账号密级编码字段
     */
    private String orgSecretCode;

    /**
     * 账号密级名称字段
     */
    private String orgSecretName;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户_name
     */
    private String syTenantName;

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
