package com.ssitao.code.modular.iam.identity.infrastructure.converter;

import com.ssitao.code.modular.iam.dal.dataobject.IamAccountDO;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.domain.model.IamAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * IAM账号对象转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamAccountConverter {

    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * DO转领域模型
     */
    @Mappings({
        @Mapping(source = "tbIamAccountId", target = "id"),
        @Mapping(source = "userAssociationId", target = "userId"),
        @Mapping(source = "accountCode", target = "accountCode"),
        @Mapping(source = "accountName", target = "accountName"),
        @Mapping(source = "accountPassword", target = "password"),
        @Mapping(source = "accountSalt", target = "salt"),
        @Mapping(source = "accountOpenid", target = "openId"),
        @Mapping(source = "accountPhone", target = "phone"),
        @Mapping(source = "accountMail", target = "email"),
        @Mapping(source = "accountAvatar", target = "avatar"),
        @Mapping(source = "accountExpireTime", target = "expireTime"),
        @Mapping(source = "accountLockExpire", target = "lockExpireTime"),
        @Mapping(source = "accountInited", target = "passwordInited"),
        @Mapping(source = "accountPermanentCode", target = "permanent"),
        @Mapping(source = "accountLockedStatus", target = "locked"),
        @Mapping(source = "userStatus", target = "status"),
        @Mapping(source = "accountRemark", target = "remark"),
        @Mapping(source = "syTenantId", target = "tenantId"),
        @Mapping(source = "syOrgId", target = "orgId"),
        @Mapping(target = "createTime", expression = "java(parseLocalDateTime(accountDO.getSyCreatetime()))"),
        @Mapping(target = "updateTime", ignore = true),
        @Mapping(source = "syCreateuserid", target = "creator"),
        @Mapping(target = "updater", ignore = true)
    })
    IamAccount toDomain(IamAccountDO accountDO);

    /**
     * 领域模型转DO
     */
    @Mappings({
        @Mapping(source = "id", target = "tbIamAccountId"),
        @Mapping(source = "userId", target = "userAssociationId"),
        @Mapping(source = "accountCode", target = "accountCode"),
        @Mapping(source = "accountName", target = "accountName"),
        @Mapping(source = "password", target = "accountPassword"),
        @Mapping(source = "salt", target = "accountSalt"),
        @Mapping(source = "openId", target = "accountOpenid"),
        @Mapping(source = "phone", target = "accountPhone"),
        @Mapping(source = "email", target = "accountMail"),
        @Mapping(source = "avatar", target = "accountAvatar"),
        @Mapping(target = "accountExpireTime", expression = "java(formatLocalDateTime(domain.getExpireTime()))"),
        @Mapping(target = "accountLockExpire", expression = "java(formatLocalDateTime(domain.getLockExpireTime()))"),
        @Mapping(target = "accountInited", expression = "java(booleanToString(domain.getPasswordInited()))"),
        @Mapping(target = "accountPermanentCode", expression = "java(booleanToString(domain.getPermanent()))"),
        @Mapping(target = "accountLockedStatus", expression = "java(booleanToString(domain.getLocked()))"),
        @Mapping(target = "userStatus", expression = "java(booleanToString(domain.getStatus()))"),
        @Mapping(source = "remark", target = "accountRemark"),
        @Mapping(source = "tenantId", target = "syTenantId"),
        @Mapping(source = "orgId", target = "syOrgId"),
        @Mapping(target = "syCreatetime", expression = "java(formatLocalDateTime(domain.getCreateTime()))"),
        @Mapping(source = "creator", target = "syCreateuserid")
    })
    IamAccountDO toDO(IamAccount domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "tbIamAccountId", target = "id")
    IamAccountDTO toDTO(IamAccountDO accountDO);

    /**
     * 领域模型转DTO
     */
    @Mapping(source = "id", target = "id")
    IamAccountDTO toDTOFromDomain(IamAccount domain);

    /**
     * DTO转领域模型
     */
    @Mapping(source = "id", target = "id")
    IamAccount toDomainFromDTO(IamAccountDTO dto);

    /**
     * DO列表转领域模型列表
     */
    List<IamAccount> toDomainList(List<IamAccountDO> accountDOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamAccountDTO> toDTOList(List<IamAccount> domainList);

    /**
     * 解析LocalDateTime
     */
    default LocalDateTime parseLocalDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 格式化LocalDateTime
     */
    default String formatLocalDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DATE_FORMATTER);
    }

    /**
     * Boolean转String("1"/"0")
     */
    default String booleanToString(Boolean value) {
        return Boolean.TRUE.equals(value) ? "1" : "0";
    }

    /**
     * String转Boolean("1"/"0")
     */
    default Boolean stringToBoolean(String value) {
        return "1".equals(value);
    }
}
