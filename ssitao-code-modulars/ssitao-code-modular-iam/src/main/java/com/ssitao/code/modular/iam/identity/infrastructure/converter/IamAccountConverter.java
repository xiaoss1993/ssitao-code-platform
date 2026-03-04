package com.ssitao.code.modular.iam.identity.infrastructure.converter;

import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.dal.dataobject.IamAccountDO;
import com.ssitao.code.modular.iam.identity.domain.model.IamAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

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

    /**
     * DO转领域模型
     */
    @Mapping(source = "accountId", target = "id")
    @Mapping(source = "accountCode", target = "accountCode")
    @Mapping(source = "accountName", target = "accountName")
    @Mapping(source = "accountPassword", target = "password")
    @Mapping(source = "accountPhone", target = "phone")
    @Mapping(source = "accountMail", target = "email")
    @Mapping(source = "accountAvatar", target = "avatar")
    @Mapping(source = "accountStatus", target = "status", qualifiedByName = "integerToBoolean")
    @Mapping(source = "accountLastLoginTime", target = "lastLoginTime")
    @Mapping(source = "accountLastLoginIp", target = "lastLoginIp")
    @Mapping(source = "accountInitPassword", target = "passwordInited", qualifiedByName = "integerToBoolean")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "createOrgId", target = "orgId")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "createUserId", target = "creator")
    @Mapping(source = "modifyTime", target = "updateTime")
    @Mapping(source = "modifyUserId", target = "updater")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "salt", ignore = true)
    @Mapping(target = "openId", ignore = true)
    @Mapping(target = "openType", ignore = true)
    @Mapping(target = "expireTime", ignore = true)
    @Mapping(target = "lockExpireTime", ignore = true)
    @Mapping(target = "permanent", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "lastPasswordChangeTime", ignore = true)
    @Mapping(target = "remark", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "loginLogs", ignore = true)
    IamAccount toDomain(IamAccountDO accountDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "accountId")
    @Mapping(source = "accountCode", target = "accountCode")
    @Mapping(source = "accountName", target = "accountName")
    @Mapping(source = "password", target = "accountPassword")
    @Mapping(source = "phone", target = "accountPhone")
    @Mapping(source = "email", target = "accountMail")
    @Mapping(source = "avatar", target = "accountAvatar")
    @Mapping(source = "status", target = "accountStatus", qualifiedByName = "booleanToInteger")
    @Mapping(source = "lastLoginTime", target = "accountLastLoginTime")
    @Mapping(source = "lastLoginIp", target = "accountLastLoginIp")
    @Mapping(source = "passwordInited", target = "accountInitPassword", qualifiedByName = "booleanToInteger")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "orgId", target = "createOrgId")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "creator", target = "createUserId")
    @Mapping(source = "updateTime", target = "modifyTime")
    @Mapping(source = "updater", target = "modifyUserId")
    @Mapping(target = "accountType", constant = "SYSTEM")
    @Mapping(target = "accountSource", constant = "LOCAL")
    @Mapping(target = "accountIsAdmin", constant = "0")
    @Mapping(target = "accountInitPasswordResetTime", ignore = true)
    @Mapping(target = "createOrgName", ignore = true)
    @Mapping(target = "createUserName", ignore = true)
    @Mapping(target = "modifyOrgId", ignore = true)
    @Mapping(target = "modifyOrgName", ignore = true)
    @Mapping(target = "modifyUserName", ignore = true)
    @Mapping(target = "isDeleted", constant = "0")
    @Mapping(target = "version", constant = "0")
    IamAccountDO toDO(IamAccount domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "accountId", target = "id")
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
