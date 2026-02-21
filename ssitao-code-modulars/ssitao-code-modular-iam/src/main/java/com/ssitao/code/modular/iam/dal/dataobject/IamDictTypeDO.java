package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM字典类型数据对象
 * 对应数据库表：tb_iam_dicttype
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_dicttype")
public class IamDictTypeDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamDicttypeId;

    /**
     * 字典编码
     */
    @Column(value = "dicttype_code")
    private String dicttypeCode;

    /**
     * 字典名称
     */
    @Column(value = "dicttype_name")
    private String dicttypeName;

    /**
     * 字典类型：SYSTEM-系统 BUSINESS-业务
     */
    @Column(value = "dicttype_type")
    private String dicttypeType;

    /**
     * 状态：1-启用 0-禁用
     */
    @Column(value = "status")
    private Boolean status;

    /**
     * 排序
     */
    @Column(value = "sy_orderindex")
    private Integer syOrderindex;

    /**
     * 备注
     */
    @Column(value = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(value = "sy_createtime")
    private String syCreatetime;

    /**
     * 修改时间
     */
    @Column(value = "sy_modifytime")
    private String syModifytime;

    /**
     * 创建人
     */
    @Column(value = "sy_createuserid")
    private String syCreateuserid;

    /**
     * 更新人
     */
    @Column(value = "sy_modifyuserid")
    private String syModifyuserid;

    /**
     * 租户ID
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

}
