package com.ssitao.code.modular.iam.organization.infrastructure.converter;

import com.ssitao.code.modular.iam.dal.dataobject.IamCompanyDO;
import com.ssitao.code.modular.iam.organization.api.dto.IamCompanyDTO;
import com.ssitao.code.modular.iam.organization.domain.model.IamCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * IAM公司转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(componentModel = "spring")
public interface IamCompanyConverter {

    /**
     * DO转领域模型
     *
     * @param companyDO DO对象
     * @return 领域模型
     */
    @Mappings({
            @Mapping(target = "id", source = "tbIamCompanyId"),
            @Mapping(target = "companyCode", source = "companyCode"),
            @Mapping(target = "companyName", source = "companyName"),
            @Mapping(target = "companyShortName", source = "companySimplename"),
            @Mapping(target = "companyAddress", source = "companyAddress"),
            @Mapping(target = "companyRemark", source = "companyRemark"),
            @Mapping(target = "companyLogo", source = "companyIcon"),
            @Mapping(target = "companyPhone", source = "companyTelephone"),
            @Mapping(target = "companyEstablishDate", source = "companyClrq"),
            @Mapping(target = "tenantId", source = "syTenantId"),
            @Mapping(target = "createTime", expression = "java(parseLocalDateTime(companyDO.getSyCreatetime()))"),
            @Mapping(target = "updateTime", expression = "java(parseLocalDateTime(companyDO.getSyModifytime()))"),
            @Mapping(target = "creator", source = "syCreateusername"),
            @Mapping(target = "updater", source = "syModifyusername"),
            @Mapping(target = "status", source = "syStatus")
    })
    IamCompany toDomain(IamCompanyDO companyDO);

    /**
     * 领域模型转DO
     *
     * @param company 领域模型
     * @return DO对象
     */
    @Mappings({
            @Mapping(target = "tbIamCompanyId", source = "id"),
            @Mapping(target = "companyCode", source = "companyCode"),
            @Mapping(target = "companyName", source = "companyName"),
            @Mapping(target = "companySimplename", source = "companyShortName"),
            @Mapping(target = "companyAddress", source = "companyAddress"),
            @Mapping(target = "companyRemark", source = "companyRemark"),
            @Mapping(target = "companyIcon", source = "companyLogo"),
            @Mapping(target = "companyTelephone", source = "companyPhone"),
            @Mapping(target = "companyClrq", source = "companyEstablishDate"),
            @Mapping(target = "syTenantId", source = "tenantId"),
            @Mapping(target = "syCreatetime", expression = "java(formatLocalDateTime(company.getCreateTime()))"),
            @Mapping(target = "syModifytime", expression = "java(formatLocalDateTime(company.getUpdateTime()))"),
            @Mapping(target = "syCreateusername", source = "creator"),
            @Mapping(target = "syModifyusername", source = "updater"),
            @Mapping(target = "syStatus", source = "status")
    })
    IamCompanyDO toDO(IamCompany company);

    /**
     * 领域模型转DTO
     *
     * @param company 领域模型
     * @return DTO对象
     */
    IamCompanyDTO toDTO(IamCompany company);

    /**
     * DO转DTO
     *
     * @param companyDO DO对象
     * @return DTO对象
     */
    @Mappings({
            @Mapping(target = "id", source = "tbIamCompanyId"),
            @Mapping(target = "companyCode", source = "companyCode"),
            @Mapping(target = "companyName", source = "companyName"),
            @Mapping(target = "companyShortName", source = "companySimplename"),
            @Mapping(target = "companyAddress", source = "companyAddress"),
            @Mapping(target = "companyRemark", source = "companyRemark"),
            @Mapping(target = "companyLogo", source = "companyIcon"),
            @Mapping(target = "companyPhone", source = "companyTelephone"),
            @Mapping(target = "companyEstablishDate", source = "companyClrq"),
            @Mapping(target = "tenantId", source = "syTenantId"),
            @Mapping(target = "createTime", expression = "java(parseLocalDateTime(companyDO.getSyCreatetime()))"),
            @Mapping(target = "updateTime", expression = "java(parseLocalDateTime(companyDO.getSyModifytime()))"),
            @Mapping(target = "creator", source = "syCreateusername"),
            @Mapping(target = "updater", source = "syModifyusername"),
            @Mapping(target = "status", source = "syStatus")
    })
    IamCompanyDTO toDTO(IamCompanyDO companyDO);

    /**
     * DO列表转领域模型列表
     *
     * @param companyDOList DO列表
     * @return 领域模型列表
     */
    List<IamCompany> toDomainList(List<IamCompanyDO> companyDOList);

    /**
     * 领域模型列表转DTO列表
     *
     * @param companyList 领域模型列表
     * @return DTO列表
     */
    List<IamCompanyDTO> toDTOList(List<IamCompany> companyList);

    /**
     * 解析LocalDateTime
     */
    default java.time.LocalDateTime parseLocalDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }
        try {
            return java.time.LocalDateTime.parse(dateTimeStr.replace(" ", "T"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 格式化LocalDateTime
     */
    default String formatLocalDateTime(java.time.LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toString();
    }
}
