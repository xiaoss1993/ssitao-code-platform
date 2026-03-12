package com.ssitao.code.modular.iam.organization.infrastructure.converter;

import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamDepartmentDO;
import com.ssitao.code.modular.iam.organization.domain.model.IamDepartment;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
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
    @Mapping(source = "deptId", target = "id")
    @Mapping(source = "deptParentId", target = "parentId")
    @Mapping(source = "deptLeaderId", target = "leaderId")
    @Mapping(source = "deptPhone", target = "phone")
    @Mapping(source = "deptStatus", target = "status", qualifiedByName = "intToBoolean")
    @Mapping(source = "deptDesc", target = "remark")
    @Mapping(source = "deptSort", target = "sortOrder")
    @Mapping(source = "deptTreePath", target = "path")
    @Mapping(source = "deptTreeLevel", target = "layer")
    @Mapping(source = "createUserId", target = "createUser")
    @Mapping(source = "modifyUserId", target = "modifyUser")
    @Mapping(source = "modifyTime", target = "updateTime")
    @Mapping(source = "isDeleted", target = "deleted", qualifiedByName = "intToBoolean")
    @Mapping(target = "nodeType", ignore = true)
    @Mapping(target = "nodeInfo", ignore = true)
    @Mapping(target = "nodeInfoType", ignore = true)
    @Mapping(target = "audFlag", ignore = true)
    @Mapping(target = "createOrg", ignore = true)
    @Mapping(target = "createOrgName", ignore = true)
    @Mapping(target = "flag", ignore = true)
    @Mapping(target = "createUserName", ignore = true)
    @Mapping(target = "modifyUserName", ignore = true)
    @Mapping(target = "leaderName", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "piid", ignore = true)
    @Mapping(target = "pdid", ignore = true)
    @Mapping(target = "children", ignore = true)
    IamDepartment toDomain(IamDepartmentDO departmentDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "deptId")
    @Mapping(source = "parentId", target = "deptParentId")
    @Mapping(source = "leaderId", target = "deptLeaderId")
    @Mapping(source = "phone", target = "deptPhone")
    @Mapping(source = "status", target = "deptStatus", qualifiedByName = "booleanToInt")
    @Mapping(source = "remark", target = "deptDesc")
    @Mapping(source = "sortOrder", target = "deptSort")
    @Mapping(source = "path", target = "deptTreePath")
    @Mapping(source = "layer", target = "deptTreeLevel")
    @Mapping(source = "createUser", target = "createUserId")
    @Mapping(source = "modifyUser", target = "modifyUserId")
    @Mapping(source = "updateTime", target = "modifyTime")
    @Mapping(target = "deptType", ignore = true)
    @Mapping(target = "deptCompanyId", ignore = true)
    @Mapping(target = "deptAddress", ignore = true)
    @Mapping(target = "isDeleted", constant = "0")
    @Mapping(target = "version", constant = "0")
    IamDepartmentDO toDO(IamDepartment domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "deptId", target = "id")
    @Mapping(source = "deptParentId", target = "parentId")
    @Mapping(source = "deptName", target = "deptName")
    IamDepartmentDTO toDTO(IamDepartmentDO departmentDO);

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
    List<IamDepartment> toDomainList(List<IamDepartmentDO> departmentDOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamDepartmentDTO> toDTOList(List<IamDepartment> domainList);

    /**
     * 设置DTO的label字段（前端树形控件需要）
     */
    @AfterMapping
    default void setLabel(IamDepartment domain, @MappingTarget IamDepartmentDTO dto) {
        if (dto != null && domain != null && domain.getDeptName() != null) {
            dto.setLabel(domain.getDeptName());
        }
    }

    @Named("intToBoolean")
    default Boolean intToBoolean(Integer value) {
        return value != null && value == 1;
    }

    @Named("booleanToInt")
    default Integer booleanToInt(Boolean value) {
        return value != null && value ? 1 : 0;
    }

}
