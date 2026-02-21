package com.ssitao.code.modular.iam.organization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM公司DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IAM公司DTO")
public class IamCompanyDTO {

    @Schema(description = "公司ID")
    private String id;

    @Schema(description = "公司名称")
    private String companyName;

    @Schema(description = "公司编码")
    private String companyCode;

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

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "状态")
    private String status;
}
