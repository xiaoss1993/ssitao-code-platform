package com.ssitao.code.modular.iam.organization.infrastructure.converter;

import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.organization.domain.model.IamDepartment;
import com.ssitao.code.modular.iam.dal.dataobject.IamDepartmentDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM部门对象转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamDepartmentConverter {

    /**
     * DO转领域模型
     */
    @Mapping(source = "tbIamDepartmentId", target = "id")
    @Mapping(source = "syParent", target = "parentId")
    @Mapping(source = "departmentMajorId", target = "leaderId")
    IamDepartment toDomain(IamDepartmentDO DO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "tbIamDepartmentId")
    @Mapping(source = "parentId", target = "syParent")
    @Mapping(source = "leaderId", target = "departmentMajorId")
    IamDepartmentDO toDO(IamDepartment domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "tbIamDepartmentId", target = "id")
    IamDepartmentDTO toDTO(IamDepartmentDO DO);

    /**
     * 领域模型转DTO
     */
    @Mapping(source = "id", target = "id")
    IamDepartmentDTO toDTO(IamDepartment domain);

    /**
     * DTO转领域模型
     */
    @Mapping(source = "id", target = "id")
    IamDepartment toDomain(IamDepartmentDTO dto);

    /**
     * DO列表转领域模型列表
     */
    List<IamDepartment> toDomainList(List<IamDepartmentDO> DOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamDepartmentDTO> toDTOList(List<IamDepartment> domainList);

}
