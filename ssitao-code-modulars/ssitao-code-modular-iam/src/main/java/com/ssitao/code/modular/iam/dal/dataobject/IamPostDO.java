package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM岗位数据对象
 * 对应数据库表：tb_iam_post
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_post")
public class IamPostDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamPostId;

    /**
     * 岗位编码
     */
    @Column(value = "post_code")
    private String postCode;

    /**
     * 岗位名称
     */
    @Column(value = "post_name")
    private String postName;

    /**
     * 岗位级别
     */
    @Column(value = "post_level")
    private Integer postLevel;

    /**
     * 岗位类型
     */
    @Column(value = "post_type")
    private String postType;

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
     * 租户ID
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

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

}
