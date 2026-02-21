package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM用户权限数据对象
 * 对应数据库表：tb_iam_userperm
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_userperm")
public class IamUserPermissionDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamUserpermId;

    /**
     * 用户ID
     */
    @Column(value = "userperm_user_id")
    private String userpermUserId;

    /**
     * 权限ID
     */
    @Column(value = "userperm_perm_id")
    private String userpermPermId;

    /**
     * 权限类型_code
     */
    @Column(value = "userperm_type_code")
    private String userpermTypeCode;

    /**
     * 权限类型_name
     */
    @Column(value = "userperm_type_name")
    private String userpermTypeName;

    /**
     * 授权类型_code（直接授权/角色继承等）
     */
    @Column(value = "userperm_grant_type_code")
    private String userpermGrantTypeCode;

    /**
     * 授权类型_name
     */
    @Column(value = "userperm_grant_type_name")
    private String userpermGrantTypeName;

    /**
     * 生效时间
     */
    @Column(value = "userperm_start_time")
    private String userpermStartTime;

    /**
     * 失效时间
     */
    @Column(value = "userperm_end_time")
    private String userpermEndTime;

    /**
     * 授权人ID
     */
    @Column(value = "userperm_granter_id")
    private String userpermGranterId;

    /**
     * 授权人姓名
     */
    @Column(value = "userperm_granter_name")
    private String userpermGranterName;

    /**
     * 备注
     */
    @Column(value = "userperm_remark")
    private String userpermRemark;

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
