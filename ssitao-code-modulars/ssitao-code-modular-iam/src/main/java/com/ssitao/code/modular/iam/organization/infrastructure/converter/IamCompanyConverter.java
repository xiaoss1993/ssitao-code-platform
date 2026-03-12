package com.ssitao.code.modular.iam.organization.infrastructure.converter;

import com.ssitao.code.modular.iam.organization.api.dto.IamCompanyDTO;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamCompanyDO;
import com.ssitao.code.modular.iam.organization.domain.model.IamCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM公司转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamCompanyConverter {

    /**
     * DO转领域模型
     */
    @Mapping(source = "companyId", target = "id")
    @Mapping(source = "companyType", target = "companyTypeCode")
    @Mapping(source = "companyMail", target = "companyEmail")
    @Mapping(source = "companyLegalRep", target = "companyLegalPerson")
    @Mapping(source = "companyRegistrationNo", target = "companyCreditCode")
    @Mapping(source = "companyStatus", target = "status", qualifiedByName = "intToString")
    @Mapping(source = "companyDesc", target = "companyRemark")
    @Mapping(source = "createUserId", target = "creator")
    @Mapping(source = "modifyUserId", target = "updater")
    @Mapping(source = "modifyTime", target = "updateTime")
    @Mapping(target = "companyTypeName", ignore = true)
    @Mapping(target = "companyEstablishDate", ignore = true)
    @Mapping(target = "companyRegisteredCapital", ignore = true)
    @Mapping(target = "companyIndustryCode", ignore = true)
    @Mapping(target = "companyIndustryName", ignore = true)
    @Mapping(target = "companyScaleCode", ignore = true)
    @Mapping(target = "companyScaleName", ignore = true)
    @Mapping(target = "departments", ignore = true)
    IamCompany toDomain(IamCompanyDO companyDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "companyId")
    @Mapping(source = "companyTypeCode", target = "companyType")
    @Mapping(source = "companyEmail", target = "companyMail")
    @Mapping(source = "companyLegalPerson", target = "companyLegalRep")
    @Mapping(source = "companyCreditCode", target = "companyRegistrationNo")
    @Mapping(source = "status", target = "companyStatus", qualifiedByName = "stringToInt")
    @Mapping(source = "companyRemark", target = "companyDesc")
    @Mapping(source = "creator", target = "createUserId")
    @Mapping(source = "updater", target = "modifyUserId")
    @Mapping(source = "updateTime", target = "modifyTime")
    @Mapping(target = "companyLevel", ignore = true)
    @Mapping(target = "companyParentId", ignore = true)
    @Mapping(target = "companyTaxNo", ignore = true)
    @Mapping(target = "companySort", ignore = true)
    @Mapping(target = "companyTreePath", ignore = true)
    @Mapping(target = "companyTreeLevel", ignore = true)
    @Mapping(target = "isDeleted", constant = "0")
    @Mapping(target = "version", constant = "0")
    IamCompanyDO toDO(IamCompany company);

    /**
     * 领域模型转DTO
     */
    IamCompanyDTO toDTO(IamCompany company);

    /**
     * DO转DTO
     */
    @Mapping(source = "companyId", target = "id")
    IamCompanyDTO toDTO(IamCompanyDO companyDO);

    /**
     * DO列表转领域模型列表
     */
    List<IamCompany> toDomainList(List<IamCompanyDO> companyDOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamCompanyDTO> toDTOList(List<IamCompany> companyList);

    @Named("intToString")
    default String intToString(Integer value) {
        return value != null && value == 1 ? "1" : "0";
    }

    @Named("stringToInt")
    default Integer stringToInt(String value) {
        return "1".equals(value) ? 1 : 0;
    }
}
