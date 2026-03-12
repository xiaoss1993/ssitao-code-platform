package com.ssitao.code.modular.iam.organization.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * IAM公司更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IAM公司更新命令")
public class IamCompanyUpdateCommand {

    @NotBlank(message = "公司ID不能为空")
    @Schema(description = "公司ID", required = true)
    private String id;

    @NotBlank(message = "公司编码不能为空")
    @Schema(description = "公司编码", required = true)
    private String companyCode;

    @NotBlank(message = "公司名称不能为空")
    @Schema(description = "公司名称", required = true)
    private String companyName;

    @Schema(description = "公司简称")
    private String companyShortName;

    @Schema(description = "公司类型")
    private String companyTypeCode;

    @Schema(description = "公司类型名称")
    private String companyTypeName;

    @Schema(description = "统一社会信用代码")
    private String companyCreditCode;

    @Schema(description = "法人代表")
    private String companyLegalPerson;

    @Schema(description = "注册地址")
    private String companyAddress;

    @Schema(description = "联系电话")
    private String companyPhone;

    @Schema(description = "电子邮箱")
    private String companyEmail;

    @Schema(description = "公司网址")
    private String companyWebsite;

    @Schema(description = "成立日期")
    private String companyEstablishDate;

    @Schema(description = "注册资本")
    private String companyRegisteredCapital;

    @Schema(description = "备注")
    private String companyRemark;

    @Schema(description = "公司logo")
    private String companyLogo;

    @Schema(description = "所属行业")
    private String companyIndustryCode;

    @Schema(description = "所属行业名称")
    private String companyIndustryName;

    @Schema(description = "公司规模")
    private String companyScaleCode;

    @Schema(description = "公司规模名称")
    private String companyScaleName;

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID", required = true)
    private String tenantId;

    @NotBlank(message = "更新人不能为空")
    @Schema(description = "更新人", required = true)
    private String updater;
}
