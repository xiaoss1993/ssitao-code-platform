package com.ssitao.code.modular.iam.identity.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM账号DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IAM账号DTO")
public class IamAccountDTO {

    @Schema(description = "账号ID")
    private String id;

    @Schema(description = "账号编码")
    private String accountCode;

    @Schema(description = "账号名称")
    private String accountName;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "头像URL")
    private String avatar;

    @Schema(description = "状态：1-正常 0-禁用")
    private Boolean status;

    @Schema(description = "是否锁定")
    private Boolean locked;

    @Schema(description = "锁定过期时间")
    private LocalDateTime lockExpireTime;

    @Schema(description = "账号过期时间")
    private LocalDateTime expireTime;

    @Schema(description = "密码是否已初始化")
    private Boolean passwordInited;

    @Schema(description = "是否永久账号")
    private Boolean permanent;

    @Schema(description = "最后登录IP")
    private String lastLoginIp;

    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @Schema(description = "关联用户ID")
    private String userId;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "组织ID")
    private String orgId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "是否可用")
    private Boolean available;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
