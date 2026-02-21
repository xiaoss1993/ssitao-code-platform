package com.ssitao.code.modular.iam.authorization.infrastructure.converter;

import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.domain.model.IamRole;
import com.ssitao.code.modular.iam.dal.dataobject.IamRoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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
    @Mappings({
        @Mapping(source = "tbIamRoleId", target = "id"),
        @Mapping(source = "roleCode", target = "roleCode"),
        @Mapping(source = "roleName", target = "roleName"),
        @Mapping(source = "roleTypeCode", target = "roleType"),
        @Mapping(source = "roleRemark", target = "remark"),
        @Mapping(source = "roleIconcls", target = "iconCls"),
        @Mapping(source = "rolePermgroupId", target = "permGroupId"),
        @Mapping(source = "syParent", target = "parentId"),
        @Mapping(source = "syLayer", target = "layer"),
        @Mapping(source = "syPath", target = "path"),
        @Mapping(source = "syNodetype", target = "nodeType"),
        @Mapping(source = "syDisabled", target = "status"),
        @Mapping(source = "syOrderindex", target = "sortOrder"),
        @Mapping(source = "syTenantId", target = "tenantId"),
        @Mapping(source = "syCreatetime", target = "createTime"),
        @Mapping(source = "syCreateuser", target = "creator"),
        @Mapping(target = "updateTime", ignore = true),
        @Mapping(target = "updater", ignore = true),
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "dataScope", ignore = true),
        @Mapping(target = "permissions", ignore = true),
        @Mapping(target = "children", ignore = true)
    })
    IamRole toDomain(IamRoleDO DO);

    /**
     * 领域模型转DO
     */
    @Mappings({
        @Mapping(source = "id", target = "tbIamRoleId"),
        @Mapping(source = "roleCode", target = "roleCode"),
        @Mapping(source = "roleName", target = "roleName"),
        @Mapping(source = "roleType", target = "roleTypeCode"),
        @Mapping(source = "remark", target = "roleRemark"),
        @Mapping(source = "iconCls", target = "roleIconcls"),
        @Mapping(source = "permGroupId", target = "rolePermgroupId"),
        @Mapping(source = "parentId", target = "syParent"),
        @Mapping(source = "layer", target = "syLayer"),
        @Mapping(source = "path", target = "syPath"),
        @Mapping(source = "nodeType", target = "syNodetype"),
        @Mapping(source = "status", target = "syDisabled"),
        @Mapping(source = "sortOrder", target = "syOrderindex"),
        @Mapping(source = "tenantId", target = "syTenantId"),
        @Mapping(source = "createTime", target = "syCreatetime"),
        @Mapping(source = "creator", target = "syCreateuser")
    })
    IamRoleDO toDO(IamRole domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "tbIamRoleId", target = "id")
    IamRoleDTO toDTO(IamRoleDO DO);

    /**
     * 领域模型转DTO
     */
    @Mapping(source = "id", target = "id")
    IamRoleDTO toDTO(IamRole domain);

    /**
     * DTO转领域模型
     */
    @Mapping(source = "id", target = "id")
    IamRole toDomain(IamRoleDTO dto);

    /**
     * DO列表转领域模型列表
     */
    List<IamRole> toDomainList(List<IamRoleDO> DOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamRoleDTO> toDTOList(List<IamRole> domainList);

}
