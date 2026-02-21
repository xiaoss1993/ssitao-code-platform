package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM权限数据对象
 * 对应数据库表：tb_iam_perm
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_perm")
public class IamPermissionDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamPermId;

    /**
     * 权限名称
     */
    @Column(value = "perm_name")
    private String permName;

    /**
     * 权限编码
     */
    @Column(value = "perm_code")
    private String permCode;

    /**
     * 权限类型_code
     */
    @Column(value = "perm_type_code")
    private String permTypeCode;

    /**
     * 权限类型_name
     */
    @Column(value = "perm_type_name")
    private String permTypeName;

    /**
     * 是否输出权限_code
     */
    @Column(value = "perm_output_code")
    private String permOutputCode;

    /**
     * 是否输出权限_name
     */
    @Column(value = "perm_output_name")
    private String permOutputName;

    /**
     * 输出模板
     */
    @Column(value = "perm_output_template")
    private String permOutputTemplate;

    /**
     * 备注信息
     */
    @Column(value = "perm_remark")
    private String permRemark;

    /**
     * 所属对象
     */
    @Column(value = "perm_object")
    private String permObject;

    /**
     * 操作类型_code
     */
    @Column(value = "perm_operate_code")
    private String permOperateCode;

    /**
     * 操作类型_name
     */
    @Column(value = "perm_operate_name")
    private String permOperateName;

    /**
     * 目标对象_id
     */
    @Column(value = "perm_target_id")
    private String permTargetId;

    /**
     * 目标对象_code
     */
    @Column(value = "perm_target_code")
    private String permTargetCode;

    /**
     * 目标对象_name
     */
    @Column(value = "perm_target_name")
    private String permTargetName;

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

}
