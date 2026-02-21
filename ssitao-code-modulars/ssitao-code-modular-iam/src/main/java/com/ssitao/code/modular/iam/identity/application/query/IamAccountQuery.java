package com.ssitao.code.modular.iam.identity.application.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * IAM账号查询条件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "IAM账号查询条件")
public class IamAccountQuery {

    @Schema(description = "账号名称(模糊查询)")
    private String accountName;

    @Schema(description = "账号编码(精确查询)")
    private String accountCode;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "状态：1-正常 0-禁用")
    private Boolean status;

    @Schema(description = "是否锁定：1-是 0-否")
    private Boolean locked;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页大小", example = "20")
    private Integer pageSize = 20;

}
