package com.ssitao.code.modular.iam.system.infrastructure.converter;

import com.ssitao.code.modular.iam.system.dal.dataobject.IamTenantDO;
import com.ssitao.code.modular.iam.system.domain.model.IamTenant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM租户转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamTenantConverter {

    /**
     * DO转领域模型
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "tenantCode", target = "tenantCode")
    @Mapping(source = "tenantName", target = "tenantName")
    @Mapping(source = "tenantType", target = "tenantType")
    @Mapping(source = "contact", target = "contact")
    @Mapping(source = "contactPhone", target = "contactPhone")
    @Mapping(source = "contactEmail", target = "contactEmail")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "tenantStatus", target = "tenantStatus")
    @Mapping(source = "expireTime", target = "expireTime")
    @Mapping(source = "userLimit", target = "userLimit")
    @Mapping(source = "currentUserCount", target = "currentUserCount")
    @Mapping(source = "storageLimit", target = "storageLimit")
    @Mapping(source = "usedStorage", target = "usedStorage")
    @Mapping(source = "logoUrl", target = "logoUrl")
    @Mapping(source = "domain", target = "domain")
    @Mapping(source = "remark", target = "remark")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "updater", target = "updater")
    @Mapping(source = "deleted", target = "deleted", qualifiedByName = "intToBoolean")
    IamTenant toDomain(IamTenantDO tenantDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "tenantCode", target = "tenantCode")
    @Mapping(source = "tenantName", target = "tenantName")
    @Mapping(source = "tenantType", target = "tenantType")
    @Mapping(source = "contact", target = "contact")
    @Mapping(source = "contactPhone", target = "contactPhone")
    @Mapping(source = "contactEmail", target = "contactEmail")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "tenantStatus", target = "tenantStatus")
    @Mapping(source = "expireTime", target = "expireTime")
    @Mapping(source = "userLimit", target = "userLimit")
    @Mapping(source = "currentUserCount", target = "currentUserCount")
    @Mapping(source = "storageLimit", target = "storageLimit")
    @Mapping(source = "usedStorage", target = "usedStorage")
    @Mapping(source = "logoUrl", target = "logoUrl")
    @Mapping(source = "domain", target = "domain")
    @Mapping(source = "remark", target = "remark")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "updater", target = "updater")
    @Mapping(source = "deleted", target = "deleted", qualifiedByName = "booleanToInt")
    IamTenantDO toDO(IamTenant tenant);

    /**
     * DO列表转领域模型列表
     */
    List<IamTenant> toDomainList(List<IamTenantDO> tenantDOList);

    @Named("intToBoolean")
    default Boolean intToBoolean(Integer value) {
        return value != null && value == 1;
    }

    @Named("booleanToInt")
    default Integer booleanToInt(Boolean value) {
        return value != null && value ? 1 : 0;
    }
}
