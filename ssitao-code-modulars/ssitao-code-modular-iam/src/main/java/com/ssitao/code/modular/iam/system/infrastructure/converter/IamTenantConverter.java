package com.ssitao.code.modular.iam.system.infrastructure.converter;

import com.ssitao.code.modular.iam.dal.dataobject.IamTenantDO;
import com.ssitao.code.modular.iam.system.domain.model.IamTenant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * IAM租户转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(componentModel = "spring")
public interface IamTenantConverter {

    /**
     * DO转领域模型
     *
     * @param tenantDO DO对象
     * @return 领域模型
     */
    @Mappings({
            @Mapping(target = "id", source = "tbIamTenantId"),
            @Mapping(target = "tenantCode", source = "tenantCode"),
            @Mapping(target = "tenantName", source = "tenantName"),
            @Mapping(target = "contact", source = "contactUser"),
            @Mapping(target = "contactPhone", source = "contactMobile"),
            @Mapping(target = "contactEmail", source = "contactEmail"),
            @Mapping(target = "tenantStatus", expression = "java(parseTenantStatus(tenantDO.getStatus(), tenantDO.getSyStatus()))"),
            @Mapping(target = "expireTime", source = "expireTime"),
            @Mapping(target = "userLimit", source = "maxUsers"),
            @Mapping(target = "storageLimit", source = "maxStorage"),
            @Mapping(target = "logoUrl", source = "logo"),
            @Mapping(target = "createTime", expression = "java(parseLocalDateTime(tenantDO.getSyCreatetime()))"),
            @Mapping(target = "updateTime", expression = "java(parseLocalDateTime(tenantDO.getSyModifytime()))"),
            @Mapping(target = "creator", source = "syCreateuserid"),
            @Mapping(target = "updater", source = "syModifyuserid"),
            @Mapping(target = "deleted", expression = "java(\"1\".equals(tenantDO.getSyStatus()))")
    })
    IamTenant toDomain(IamTenantDO tenantDO);

    /**
     * 领域模型转DO
     *
     * @param tenant 领域模型
     * @return DO对象
     */
    @Mappings({
            @Mapping(target = "tbIamTenantId", source = "id"),
            @Mapping(target = "tenantCode", source = "tenantCode"),
            @Mapping(target = "tenantName", source = "tenantName"),
            @Mapping(target = "contactUser", source = "contact"),
            @Mapping(target = "contactMobile", source = "contactPhone"),
            @Mapping(target = "contactEmail", source = "contactEmail"),
            @Mapping(target = "status", expression = "java(parseStatusFromTenantStatus(tenant.getTenantStatus()))"),
            @Mapping(target = "maxUsers", source = "userLimit"),
            @Mapping(target = "maxStorage", source = "storageLimit"),
            @Mapping(target = "logo", source = "logoUrl"),
            @Mapping(target = "syCreatetime", expression = "java(formatLocalDateTime(tenant.getCreateTime()))"),
            @Mapping(target = "syModifytime", expression = "java(formatLocalDateTime(tenant.getUpdateTime()))"),
            @Mapping(target = "syCreateuserid", source = "creator"),
            @Mapping(target = "syModifyuserid", source = "updater"),
            @Mapping(target = "syStatus", expression = "java(Boolean.TRUE.equals(tenant.getDeleted()) ? \"1\" : \"0\")")
    })
    IamTenantDO toDO(IamTenant tenant);

    /**
     * DO列表转领域模型列表
     *
     * @param tenantDOList DO列表
     * @return 领域模型列表
     */
    List<IamTenant> toDomainList(List<IamTenantDO> tenantDOList);

    /**
     * 解析租户状态
     */
    default String parseTenantStatus(Boolean status, String syStatus) {
        if ("1".equals(syStatus)) {
            return "DELETED";
        }
        return Boolean.TRUE.equals(status) ? "NORMAL" : "DISABLED";
    }

    /**
     * 从租户状态解析status
     */
    default Boolean parseStatusFromTenantStatus(String tenantStatus) {
        return "NORMAL".equals(tenantStatus);
    }

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
        return dateTime.toString().replace("T", " ");
    }
}
