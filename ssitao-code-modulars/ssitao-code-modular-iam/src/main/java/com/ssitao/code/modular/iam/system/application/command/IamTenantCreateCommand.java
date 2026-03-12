package com.ssitao.code.modular.iam.system.application.command;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * IAM租户创建命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamTenantCreateCommand {

    /**
     * 租户编码
     */
    @NotBlank(message = "租户编码不能为空")
    @Size(max = 64, message = "租户编码长度不能超过64")
    private String tenantCode;

    /**
     * 租户名称
     */
    @NotBlank(message = "租户名称不能为空")
    @Size(max = 128, message = "租户名称长度不能超过128")
    private String tenantName;

    /**
     * 租户类型：TRIAL-试用 STANDARD-标准 ENTERPRISE-企业
     */
    @NotBlank(message = "租户类型不能为空")
    private String tenantType;

    /**
     * 联系人
     */
    @Size(max = 64, message = "联系人长度不能超过64")
    private String contact;

    /**
     * 联系电话
     */
    @Size(max = 32, message = "联系电话长度不能超过32")
    private String contactPhone;

    /**
     * 联系邮箱
     */
    @Email(message = "联系邮箱格式不正确")
    @Size(max = 128, message = "联系邮箱长度不能超过128")
    private String contactEmail;

    /**
     * 公司地址
     */
    @Size(max = 512, message = "公司地址长度不能超过512")
    private String address;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 用户数量限制
     */
    private Integer userLimit;

    /**
     * 存储空间限制(MB)
     */
    private Long storageLimit;

    /**
     * 网站域名
     */
    @Size(max = 256, message = "网站域名长度不能超过256")
    private String domain;

    /**
     * 备注
     */
    @Size(max = 512, message = "备注长度不能超过512")
    private String remark;

}
