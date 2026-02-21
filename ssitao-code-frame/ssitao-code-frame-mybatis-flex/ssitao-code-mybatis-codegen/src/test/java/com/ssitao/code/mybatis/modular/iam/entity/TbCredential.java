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
 * 凭据 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_credential")
public class TbCredential extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCredentialId;

    /**
     * 唯一标识
     */
    private String uniqueIdentification;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 描述说明
     */
    private String description;

    /**
     * 账号类型
     */
    private String credentialType;

    /**
     * 镜像拉取密钥
     */
    private String credentialImagePullSecrets;

    /**
     * 认证邮箱
     */
    private String credentialEmail;

    /**
     * 认证地址
     */
    private String credentialAddress;

    /**
     * 密钥初始化状态
     */
    private String credentialInitializationKeyStatus;

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

}
