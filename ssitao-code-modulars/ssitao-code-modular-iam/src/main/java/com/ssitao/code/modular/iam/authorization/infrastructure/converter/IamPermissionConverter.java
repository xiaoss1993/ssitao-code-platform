package com.ssitao.code.modular.iam.authorization.infrastructure.converter;

import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermissionDO;
import com.ssitao.code.modular.iam.authorization.domain.model.IamPermission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM权限对象转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamPermissionConverter {

    /**
     * DO转领域模型
     */
    @Mapping(source = "permissionId", target = "id")
    @Mapping(source = "permissionCode", target = "permCode")
    @Mapping(source = "permissionName", target = "permName")
    @Mapping(source = "permissionType", target = "permType")
    @Mapping(source = "permissionResource", target = "targetId")
    @Mapping(source = "permissionAction", target = "operateCode")
    @Mapping(source = "permissionDesc", target = "remark")
    @Mapping(source = "permissionStatus", target = "status", qualifiedByName = "integerToBoolean")
    @Mapping(source = "permissionSort", target = "sortOrder")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "modifyTime", target = "updateTime")
    @Mapping(source = "parentId", target = "parentId")
    @Mapping(target = "path", ignore = true)
    @Mapping(target = "component", ignore = true)
    @Mapping(target = "perms", ignore = true)
    @Mapping(target = "icon", ignore = true)
    @Mapping(target = "isFrame", ignore = true)
    @Mapping(target = "isCache", ignore = true)
    @Mapping(target = "visible", ignore = true)
    @Mapping(target = "redirect", ignore = true)
    @Mapping(target = "layer", ignore = true)
    @Mapping(target = "targetType", ignore = true)
    @Mapping(target = "outputTemplate", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    IamPermission toDomain(IamPermissionDO permissionDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "permissionId")
    @Mapping(source = "permCode", target = "permissionCode")
    @Mapping(source = "permName", target = "permissionName")
    @Mapping(source = "permType", target = "permissionType")
    @Mapping(source = "targetId", target = "permissionResource")
    @Mapping(source = "operateCode", target = "permissionAction")
    @Mapping(source = "remark", target = "permissionDesc")
    @Mapping(source = "status", target = "permissionStatus", qualifiedByName = "booleanToInteger")
    @Mapping(source = "sortOrder", target = "permissionSort")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "modifyTime")
    @Mapping(target = "permissionIsBuiltin", constant = "0")
    @Mapping(target = "isDeleted", constant = "0")
    IamPermissionDO toDO(IamPermission domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "permissionId", target = "id")
    @Mapping(source = "permissionCode", target = "permCode")
    @Mapping(source = "permissionName", target = "permName")
    @Mapping(source = "permissionType", target = "permType")
    @Mapping(source = "permissionDesc", target = "remark")
    @Mapping(source = "permissionStatus", target = "status", qualifiedByName = "integerToBoolean")
    @Mapping(source = "permissionSort", target = "sortOrder")
    @Mapping(source = "parentId", target = "parentId")
    IamPermissionDTO toDTO(IamPermissionDO permissionDO);

    /**
     * 领域模型转DTO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "parentId", target = "parentId")
    IamPermissionDTO toDTO(IamPermission domain);

    /**
     * DTO转领域模型
     */
    @Mapping(source = "id", target = "id")
    IamPermission toDomain(IamPermissionDTO dto);

    /**
     * DO列表转领域模型列表
     */
    List<IamPermission> toDomainList(List<IamPermissionDO> permissionDOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamPermissionDTO> toDTOList(List<IamPermission> domainList);

    /**
     * Boolean转Integer
     */
    @Named("booleanToInteger")
    default Integer booleanToInteger(Boolean value) {
        if (value == null) {
            return 1;
        }
        return value ? 1 : 0;
    }

    /**
     * Integer转Boolean
     */
    @Named("integerToBoolean")
    default Boolean integerToBoolean(Integer value) {
        if (value == null) {
            return true;
        }
        return value == 1;
    }

}
