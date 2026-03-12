package com.ssitao.code.modular.iam.organization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * IAM公司聚合根
 * Organization领域的核心聚合根之一
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamCompany {

    /**
     * 公司ID
     */
    private String id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 公司简称
     */
    private String companyShortName;

    /**
     * 公司类型
     */
    private String companyTypeCode;

    /**
     * 公司类型名称
     */
    private String companyTypeName;

    /**
     * 统一社会信用代码
     */
    private String companyCreditCode;

    /**
     * 法人代表
     */
    private String companyLegalPerson;

    /**
     * 注册地址
     */
    private String companyAddress;

    /**
     * 联系电话
     */
    private String companyPhone;

    /**
     * 电子邮箱
     */
    private String companyEmail;

    /**
     * 公司网址
     */
    private String companyWebsite;

    /**
     * 成立日期
     */
    private String companyEstablishDate;

    /**
     * 注册资本
     */
    private String companyRegisteredCapital;

    /**
     * 备注
     */
    private String companyRemark;

    /**
     * 公司logo
     */
    private String companyLogo;

    /**
     * 所属行业
     */
    private String companyIndustryCode;

    /**
     * 所属行业名称
     */
    private String companyIndustryName;

    /**
     * 公司规模
     */
    private String companyScaleCode;

    /**
     * 公司规模名称
     */
    private String companyScaleName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 状态
     */
    private String status;

    /**
     * 下属部门列表
     */
    private List<IamDepartment> departments;

    /**
     * 创建公司
     *
     * @param companyCode 公司编码
     * @param companyName 公司名称
     * @param creator     创建人
     * @return 公司聚合根
     */
    public static IamCompany create(String companyCode, String companyName, String creator) {
        IamCompany company = new IamCompany();
        company.setCompanyCode(companyCode);
        company.setCompanyName(companyName);
        company.setStatus("1"); // 默认启用
        company.setCreateTime(LocalDateTime.now());
        company.setCreator(creator);
        company.setDepartments(new ArrayList<>());
        return company;
    }

    /**
     * 添加部门
     *
     * @param department 部门
     */
    public void addDepartment(IamDepartment department) {
        if (this.departments == null) {
            this.departments = new ArrayList<>();
        }
        this.departments.add(department);
    }

    /**
     * 移除部门
     *
     * @param departmentId 部门ID
     */
    public void removeDepartment(String departmentId) {
        if (this.departments != null) {
            this.departments.removeIf(d -> d.getId().equals(departmentId));
        }
    }

    /**
     * 禁用公司
     */
    public void disable() {
        this.status = "0";
    }

    /**
     * 启用公司
     */
    public void enable() {
        this.status = "1";
    }

    /**
     * 判断公司是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        return "1".equals(this.status);
    }
}
