package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * IAM租户数据对象
 * 对应数据库表：tb_iam_tenant
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_tenant")
public class IamTenantDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamTenantId;

    /**
     * 租户编码
     */
    @Column(value = "tenant_code")
    private String tenantCode;

    /**
     * 租户名称
     */
    @Column(value = "tenant_name")
    private String tenantName;

    /**
     * 联系人
     */
    @Column(value = "contact_user")
    private String contactUser;

    /**
     * 联系电话
     */
    @Column(value = "contact_mobile")
    private String contactMobile;

    /**
     * 联系邮箱
     */
    @Column(value = "contact_email")
    private String contactEmail;

    /**
     * 状态：1-正常 0-停用
     */
    @Column(value = "status")
    private Boolean status;

    /**
     * 过期时间
     */
    @Column(value = "expire_time")
    private LocalDateTime expireTime;

    /**
     * 最大用户数，-1表示不限制
     */
    @Column(value = "max_users")
    private Integer maxUsers;

    /**
     * 最大存储空间(MB)，-1表示不限制
     */
    @Column(value = "max_storage")
    private Long maxStorage;

    /**
     * 租户Logo
     */
    @Column(value = "logo")
    private String logo;

    /**
     * 主题
     */
    @Column(value = "theme")
    private String theme;

    /**
     * 时区
     */
    @Column(value = "timezone")
    private String timezone;

    /**
     * 语言
     */
    @Column(value = "language")
    private String language;

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
     * 是否删除：0-否 1-是
     */
    @Column(value = "sy_status")
    private String syStatus;

}
