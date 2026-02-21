package com.ssitao.code.modular.iam.organization.application.service;

import com.ssitao.code.modular.iam.organization.api.dto.IamCompanyDTO;
import com.ssitao.code.modular.iam.organization.application.command.IamCompanyCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamCompanyUpdateCommand;
import com.ssitao.code.modular.iam.organization.domain.model.IamCompany;
import com.ssitao.code.modular.iam.organization.domain.repository.IamCompanyRepository;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * IAM公司应用服务
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamCompanyAppService {

    @Resource
    private IamCompanyRepository companyRepository;

    /**
     * 创建公司
     *
     * @param command 创建命令
     * @return 公司ID
     */
    @Transactional(rollbackFor = Exception.class)
    public String createCompany(IamCompanyCreateCommand command) {
        // 检查编码是否已存在
        if (companyRepository.existsByCompanyCode(command.getCompanyCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("公司编码已存在: " + command.getCompanyCode());
        }

        IamCompany company = IamCompany.create(
                command.getCompanyCode(),
                command.getCompanyName(),
                command.getCreator()
        );

        // 设置其他属性
        company.setCompanyShortName(command.getCompanyShortName());
        company.setCompanyTypeCode(command.getCompanyTypeCode());
        company.setCompanyTypeName(command.getCompanyTypeName());
        company.setCompanyCreditCode(command.getCompanyCreditCode());
        company.setCompanyLegalPerson(command.getCompanyLegalPerson());
        company.setCompanyAddress(command.getCompanyAddress());
        company.setCompanyPhone(command.getCompanyPhone());
        company.setCompanyEmail(command.getCompanyEmail());
        company.setCompanyWebsite(command.getCompanyWebsite());
        company.setCompanyEstablishDate(command.getCompanyEstablishDate());
        company.setCompanyRegisteredCapital(command.getCompanyRegisteredCapital());
        company.setCompanyRemark(command.getCompanyRemark());
        company.setCompanyLogo(command.getCompanyLogo());
        company.setCompanyIndustryCode(command.getCompanyIndustryCode());
        company.setCompanyIndustryName(command.getCompanyIndustryName());
        company.setCompanyScaleCode(command.getCompanyScaleCode());
        company.setCompanyScaleName(command.getCompanyScaleName());
        company.setTenantId(command.getTenantId());

        return companyRepository.save(company);
    }

    /**
     * 更新公司
     *
     * @param command 更新命令
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateCompany(IamCompanyUpdateCommand command) {
        Optional<IamCompany> companyOpt = companyRepository.findById(command.getId());
        if (!companyOpt.isPresent()) {
            throw new IllegalArgumentException("公司不存在: " + command.getId());
        }

        IamCompany company = companyOpt.get();

        // 检查编码是否已被其他公司使用
        if (companyRepository.existsByCompanyCode(command.getCompanyCode(), command.getTenantId(), command.getId())) {
            throw new IllegalArgumentException("公司编码已被其他公司使用: " + command.getCompanyCode());
        }

        // 更新属性
        company.setCompanyCode(command.getCompanyCode());
        company.setCompanyName(command.getCompanyName());
        company.setCompanyShortName(command.getCompanyShortName());
        company.setCompanyTypeCode(command.getCompanyTypeCode());
        company.setCompanyTypeName(command.getCompanyTypeName());
        company.setCompanyCreditCode(command.getCompanyCreditCode());
        company.setCompanyLegalPerson(command.getCompanyLegalPerson());
        company.setCompanyAddress(command.getCompanyAddress());
        company.setCompanyPhone(command.getCompanyPhone());
        company.setCompanyEmail(command.getCompanyEmail());
        company.setCompanyWebsite(command.getCompanyWebsite());
        company.setCompanyEstablishDate(command.getCompanyEstablishDate());
        company.setCompanyRegisteredCapital(command.getCompanyRegisteredCapital());
        company.setCompanyRemark(command.getCompanyRemark());
        company.setCompanyLogo(command.getCompanyLogo());
        company.setCompanyIndustryCode(command.getCompanyIndustryCode());
        company.setCompanyIndustryName(command.getCompanyIndustryName());
        company.setCompanyScaleCode(command.getCompanyScaleCode());
        company.setCompanyScaleName(command.getCompanyScaleName());
        company.setUpdater(command.getUpdater());

        companyRepository.update(company);
    }

    /**
     * 删除公司
     *
     * @param id 公司ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteCompany(String id) {
        companyRepository.delete(id);
    }

    /**
     * 获取公司
     *
     * @param id 公司ID
     * @return 公司DTO
     */
    public IamCompanyDTO getCompany(String id) {
        Optional<IamCompany> companyOpt = companyRepository.findById(id);
        return companyOpt.map(company -> IamCompanyDTO.builder()
                .id(company.getId())
                .companyCode(company.getCompanyCode())
                .companyName(company.getCompanyName())
                .companyShortName(company.getCompanyShortName())
                .companyTypeCode(company.getCompanyTypeCode())
                .companyTypeName(company.getCompanyTypeName())
                .companyCreditCode(company.getCompanyCreditCode())
                .companyLegalPerson(company.getCompanyLegalPerson())
                .companyAddress(company.getCompanyAddress())
                .companyPhone(company.getCompanyPhone())
                .companyEmail(company.getCompanyEmail())
                .companyWebsite(company.getCompanyWebsite())
                .companyEstablishDate(company.getCompanyEstablishDate())
                .companyRegisteredCapital(company.getCompanyRegisteredCapital())
                .companyRemark(company.getCompanyRemark())
                .companyLogo(company.getCompanyLogo())
                .companyIndustryCode(company.getCompanyIndustryCode())
                .companyIndustryName(company.getCompanyIndustryName())
                .companyScaleCode(company.getCompanyScaleCode())
                .companyScaleName(company.getCompanyScaleName())
                .tenantId(company.getTenantId())
                .createTime(company.getCreateTime())
                .updateTime(company.getUpdateTime())
                .creator(company.getCreator())
                .updater(company.getUpdater())
                .status(company.getStatus())
                .build()).orElse(null);
    }

    /**
     * 根据编码获取公司
     *
     * @param companyCode 公司编码
     * @param tenantId    租户ID
     * @return 公司DTO
     */
    public IamCompanyDTO getCompanyByCode(String companyCode, String tenantId) {
        Optional<IamCompany> companyOpt = companyRepository.findByCompanyCode(companyCode, tenantId);
        return companyOpt.map(company -> IamCompanyDTO.builder()
                .id(company.getId())
                .companyCode(company.getCompanyCode())
                .companyName(company.getCompanyName())
                .companyShortName(company.getCompanyShortName())
                .companyTypeCode(company.getCompanyTypeCode())
                .companyTypeName(company.getCompanyTypeName())
                .companyCreditCode(company.getCompanyCreditCode())
                .companyLegalPerson(company.getCompanyLegalPerson())
                .companyAddress(company.getCompanyAddress())
                .companyPhone(company.getCompanyPhone())
                .companyEmail(company.getCompanyEmail())
                .companyWebsite(company.getCompanyWebsite())
                .companyEstablishDate(company.getCompanyEstablishDate())
                .companyRegisteredCapital(company.getCompanyRegisteredCapital())
                .companyRemark(company.getCompanyRemark())
                .companyLogo(company.getCompanyLogo())
                .companyIndustryCode(company.getCompanyIndustryCode())
                .companyIndustryName(company.getCompanyIndustryName())
                .companyScaleCode(company.getCompanyScaleCode())
                .companyScaleName(company.getCompanyScaleName())
                .tenantId(company.getTenantId())
                .createTime(company.getCreateTime())
                .updateTime(company.getUpdateTime())
                .creator(company.getCreator())
                .updater(company.getUpdater())
                .status(company.getStatus())
                .build()).orElse(null);
    }

    /**
     * 获取所有公司
     *
     * @param tenantId 租户ID
     * @return 公司列表
     */
    public List<IamCompanyDTO> listCompanies(String tenantId) {
        List<IamCompany> companies = companyRepository.findAll(tenantId);
        return companies.stream()
                .map(company -> IamCompanyDTO.builder()
                        .id(company.getId())
                        .companyCode(company.getCompanyCode())
                        .companyName(company.getCompanyName())
                        .companyShortName(company.getCompanyShortName())
                        .companyTypeCode(company.getCompanyTypeCode())
                        .companyTypeName(company.getCompanyTypeName())
                        .companyCreditCode(company.getCompanyCreditCode())
                        .companyLegalPerson(company.getCompanyLegalPerson())
                        .companyAddress(company.getCompanyAddress())
                        .companyPhone(company.getCompanyPhone())
                        .companyEmail(company.getCompanyEmail())
                        .companyWebsite(company.getCompanyWebsite())
                        .companyEstablishDate(company.getCompanyEstablishDate())
                        .companyRegisteredCapital(company.getCompanyRegisteredCapital())
                        .companyRemark(company.getCompanyRemark())
                        .companyLogo(company.getCompanyLogo())
                        .companyIndustryCode(company.getCompanyIndustryCode())
                        .companyIndustryName(company.getCompanyIndustryName())
                        .companyScaleCode(company.getCompanyScaleCode())
                        .companyScaleName(company.getCompanyScaleName())
                        .tenantId(company.getTenantId())
                        .createTime(company.getCreateTime())
                        .updateTime(company.getUpdateTime())
                        .creator(company.getCreator())
                        .updater(company.getUpdater())
                        .status(company.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 根据条件查询公司
     *
     * @param companyName 公司名称
     * @param companyType 公司类型
     * @param tenantId    租户ID
     * @return 公司列表
     */
    public List<IamCompanyDTO> searchCompanies(String companyName, String companyType, String tenantId) {
        List<IamCompany> companies = companyRepository.findByConditions(companyName, companyType, tenantId);
        return companies.stream()
                .map(company -> IamCompanyDTO.builder()
                        .id(company.getId())
                        .companyCode(company.getCompanyCode())
                        .companyName(company.getCompanyName())
                        .companyShortName(company.getCompanyShortName())
                        .companyTypeCode(company.getCompanyTypeCode())
                        .companyTypeName(company.getCompanyTypeName())
                        .companyCreditCode(company.getCompanyCreditCode())
                        .companyLegalPerson(company.getCompanyLegalPerson())
                        .companyAddress(company.getCompanyAddress())
                        .companyPhone(company.getCompanyPhone())
                        .companyEmail(company.getCompanyEmail())
                        .companyWebsite(company.getCompanyWebsite())
                        .companyEstablishDate(company.getCompanyEstablishDate())
                        .companyRegisteredCapital(company.getCompanyRegisteredCapital())
                        .companyRemark(company.getCompanyRemark())
                        .companyLogo(company.getCompanyLogo())
                        .companyIndustryCode(company.getCompanyIndustryCode())
                        .companyIndustryName(company.getCompanyIndustryName())
                        .companyScaleCode(company.getCompanyScaleCode())
                        .companyScaleName(company.getCompanyScaleName())
                        .tenantId(company.getTenantId())
                        .createTime(company.getCreateTime())
                        .updateTime(company.getUpdateTime())
                        .creator(company.getCreator())
                        .updater(company.getUpdater())
                        .status(company.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
