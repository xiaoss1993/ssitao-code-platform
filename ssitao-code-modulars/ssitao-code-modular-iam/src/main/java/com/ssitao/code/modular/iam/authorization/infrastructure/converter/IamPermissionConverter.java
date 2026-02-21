package com.ssitao.code.modular.iam.authorization.infrastructure.converter;

import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;
import com.ssitao.code.modular.iam.authorization.domain.model.IamPermission;
import com.ssitao.code.modular.iam.dal.dataobject.IamPermissionDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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
    @Mappings({
        @Mapping(source = "tbIamPermId", target = "id"),
        @Mapping(source = "permCode", target = "permCode"),
        @Mapping(source = "permName", target = "permName"),
        @Mapping(source = "permTypeCode", target = "permType"),
        @Mapping(source = "permTargetId", target = "parentId"),
        @Mapping(source = "permTargetId", target = "targetId"),
        @Mapping(source = "permTargetCode", target = "targetType"),
        @Mapping(source = "permOperateCode", target = "operateCode"),
        @Mapping(source = "permOutputTemplate", target = "outputTemplate"),
        @Mapping(source = "permRemark", target = "remark"),
        @Mapping(source = "syTenantId", target = "tenantId"),
        @Mapping(source = "syCreatetime", target = "createTime"),
        @Mapping(source = "syCreateuserid", target = "creator"),
        @Mapping(source = "syOrderindex", target = "sortOrder"),
        @Mapping(target = "path", ignore = true),
        @Mapping(target = "component", ignore = true),
        @Mapping(target = "perms", ignore = true),
        @Mapping(target = "icon", ignore = true),
        @Mapping(target = "isFrame", ignore = true),
        @Mapping(target = "isCache", ignore = true),
        @Mapping(target = "visible", ignore = true),
        @Mapping(target = "redirect", ignore = true),
        @Mapping(target = "layer", ignore = true),
        @Mapping(target = "status", ignore = true),
        @Mapping(target = "updateTime", ignore = true),
        @Mapping(target = "updater", ignore = true),
        @Mapping(target = "deleted", ignore = true)
    })
    IamPermission toDomain(IamPermissionDO DO);

    /**
     * 领域模型转DO
     */
    @Mappings({
        @Mapping(source = "id", target = "tbIamPermId"),
        @Mapping(source = "permCode", target = "permCode"),
        @Mapping(source = "permName", target = "permName"),
        @Mapping(source = "permType", target = "permTypeCode"),
        @Mapping(source = "targetId", target = "permTargetId"),
        @Mapping(source = "targetType", target = "permTargetCode"),
        @Mapping(source = "operateCode", target = "permOperateCode"),
        @Mapping(source = "outputTemplate", target = "permOutputTemplate"),
        @Mapping(source = "remark", target = "permRemark"),
        @Mapping(source = "tenantId", target = "syTenantId"),
        @Mapping(source = "createTime", target = "syCreatetime"),
        @Mapping(source = "creator", target = "syCreateuserid"),
        @Mapping(source = "sortOrder", target = "syOrderindex")
    })
    IamPermissionDO toDO(IamPermission domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "tbIamPermId", target = "id")
    IamPermissionDTO toDTO(IamPermissionDO DO);

    /**
     * 领域模型转DTO
     */
    @Mapping(source = "id", target = "id")
    IamPermissionDTO toDTO(IamPermission domain);

    /**
     * DTO转领域模型
     */
    @Mapping(source = "id", target = "id")
    IamPermission toDomain(IamPermissionDTO dto);

    /**
     * DO列表转领域模型列表
     */
    List<IamPermission> toDomainList(List<IamPermissionDO> DOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamPermissionDTO> toDTOList(List<IamPermission> domainList);

}
