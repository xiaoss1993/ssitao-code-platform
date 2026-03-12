package com.ssitao.code.modular.iam.authorization.infrastructure.converter;

import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.authorization.domain.model.IamRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM角色对象转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {IamPermissionConverter.class}
)
public interface IamRoleConverter {

    /**
     * DO转领域模型
     */
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "children", ignore = true)
    IamRole toDomain(IamRoleDO roleDO);

    /**
     * 领域模型转DO
     */
    IamRoleDO toDO(IamRole domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "roleId", target = "id")
    @Mapping(source = "roleStatus", target = "statusInt")
    @Mapping(source = "roleSort", target = "sort")
    @Mapping(source = "roleDesc", target = "remark")
    IamRoleDTO toDTO(IamRoleDO roleDO);

    /**
     * 领域模型转DTO
     */
    @Mapping(source = "roleId", target = "id")
    @Mapping(source = "roleStatus", target = "statusInt")
    @Mapping(source = "roleSort", target = "sort")
    @Mapping(source = "roleDesc", target = "remark")
    IamRoleDTO toDTO(IamRole domain);

    /**
     * DTO转领域模型
     */
    @Mapping(source = "id", target = "roleId")
    @Mapping(source = "statusInt", target = "roleStatus")
    @Mapping(source = "sort", target = "roleSort")
    @Mapping(source = "remark", target = "roleDesc")
    IamRole toDomain(IamRoleDTO dto);

    /**
     * DO列表转领域模型列表
     */
    List<IamRole> toDomainList(List<IamRoleDO> roleDOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamRoleDTO> toDTOList(List<IamRole> domainList);

}
