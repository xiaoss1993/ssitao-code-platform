package com.ssitao.code.modular.iam.organization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM集团DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IAM集团DTO")
public class IamGroupDTO {

    @Schema(description = "集团ID")
    private String id;

    @Schema(description = "集团编码")
    private String groupCode;

    @Schema(description = "集团名称")
    private String groupName;

    @Schema(description = "负责人")
    private String leader;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "排序")
    private Integer sortOrder;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "更新人")
    private String updater;
}
