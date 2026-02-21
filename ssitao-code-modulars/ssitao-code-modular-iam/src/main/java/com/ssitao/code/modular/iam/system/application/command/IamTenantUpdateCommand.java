package com.ssitao.code.modular.iam.system.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * IAM租户更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamTenantUpdateCommand {

    /**
     * 租户ID
     */
    @NotNull(message = "租户ID不能为空")
    private Long id;

    /**
     * 租户名称
     */
    @Size(max = 128, message = "租户名称长度不能超过128")
    private String tenantName;

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
