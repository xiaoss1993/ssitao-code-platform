package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM权限组权限关联数据对象
 * 对应数据库表：tb_iam_permgroupperm
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_permgroupperm")
public class IamPermGroupPermDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamPermgrouppermId;

    /**
     * 权限id
     */
    @Column(value = "tb_iam_perm_id")
    private String tbIamPermId;

    /**
     * 权限组id
     */
    @Column(value = "tb_iam_permgroup_id")
    private String tbIamPermgroupId;

    /**
     * 授权类型_code
     */
    @Column(value = "permgroupperm_granttype_code")
    private String permgrouppermGranttypeCode;

    /**
     * 授权类型_name
     */
    @Column(value = "permgroupperm_granttype_name")
    private String permgrouppermGranttypeName;

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
     * 租户_id
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

    /**
     * 租户名称
     */
    @Column(value = "sy_tenant_name")
    private String syTenantName;

}
