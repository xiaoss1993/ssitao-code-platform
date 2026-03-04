package com.ssitao.code.modular.iam.authorization.infrastructure.converter;

import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermGroupDO;
import com.ssitao.code.modular.iam.authorization.domain.model.IamPermGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM权限组转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamPermGroupConverter {

    /**
     * DO转领域模型
     *
     * @param permGroupDO DO对象
     * @return 领域模型
     */
    @Mapping(source = "groupId", target = "id")
    @Mapping(source = "groupCode", target = "groupCode")
    @Mapping(source = "groupName", target = "groupName")
    @Mapping(source = "groupDesc", target = "remark")
    @Mapping(source = "groupStatus", target = "status", qualifiedByName = "integerToBoolean")
    @Mapping(source = "groupSort", target = "sortOrder")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "createUserId", target = "creator")
    @Mapping(target = "permissionIds", ignore = true)
    IamPermGroup toDomain(IamPermGroupDO permGroupDO);

    /**
     * 领域模型转DO
     *
     * @param permGroup 领域模型
     * @return DO对象
     */
    @Mapping(source = "id", target = "groupId")
    @Mapping(source = "groupCode", target = "groupCode")
    @Mapping(source = "groupName", target = "groupName")
    @Mapping(source = "remark", target = "groupDesc")
    @Mapping(source = "status", target = "groupStatus", qualifiedByName = "booleanToInteger")
    @Mapping(source = "sortOrder", target = "groupSort")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "creator", target = "createUserId")
    @Mapping(target = "modifyTime", ignore = true)
    @Mapping(target = "isDeleted", constant = "0")
    IamPermGroupDO toDO(IamPermGroup permGroup);

    /**
     * DO列表转领域模型列表
     *
     * @param permGroupDOList DO列表
     * @return 领域模型列表
     */
    List<IamPermGroup> toDomainList(List<IamPermGroupDO> permGroupDOList);

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
