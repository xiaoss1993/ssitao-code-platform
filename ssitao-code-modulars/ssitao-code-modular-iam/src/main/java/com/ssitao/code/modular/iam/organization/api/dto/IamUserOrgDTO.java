package com.ssitao.code.modular.iam.organization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM用户组织DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IAM用户组织DTO")
public class IamUserOrgDTO {

    @Schema(description = "用户组织ID")
    private String id;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "集团ID")
    private String groupId;

    @Schema(description = "集团名称")
    private String groupName;

    @Schema(description = "公司ID")
    private String companyId;

    @Schema(description = "公司名称")
    private String companyName;

    @Schema(description = "部门ID")
    private String deptId;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "岗位ID")
    private String postId;

    @Schema(description = "岗位名称")
    private String postName;

    @Schema(description = "是否主组织(true是/false否)")
    private Boolean isMain;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建人")
    private String creator;
}
