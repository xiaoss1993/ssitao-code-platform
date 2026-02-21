package com.ssitao.code.modular.iam.userprofile.infrastructure.converter;

import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserDTO;
import com.ssitao.code.modular.iam.userprofile.domain.model.IamUser;
import com.ssitao.code.modular.iam.dal.dataobject.IamUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM用户对象转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamUserConverter {

    /**
     * DO转领域模型
     */
    @Mappings({
        @Mapping(source = "tbIamUserId", target = "id"),
        @Mapping(source = "userName", target = "username"),
        @Mapping(source = "userCode", target = "nickname"),
        @Mapping(source = "userMail", target = "email"),
        @Mapping(source = "userPhone", target = "phone"),
        @Mapping(source = "userAvatar", target = "avatar"),
        @Mapping(source = "userSexCode", target = "gender"),
        @Mapping(source = "userBirth", target = "birthday"),
        @Mapping(source = "userCardnum", target = "idCard"),
        @Mapping(source = "userPostCode", target = "postId"),
        @Mapping(source = "userPostName", target = "postName"),
        @Mapping(source = "userInitedCode", target = "userInitedCode"),
        @Mapping(source = "userRemark", target = "remark"),
        @Mapping(source = "syTenantId", target = "tenantId"),
        @Mapping(source = "syOrgId", target = "deptId"),
        @Mapping(source = "syCreatetime", target = "createTime"),
        @Mapping(source = "syModifytime", target = "updateTime"),
        @Mapping(source = "syCreateuserid", target = "createUser"),
        @Mapping(source = "syModifyuserid", target = "modifyUser"),
        @Mapping(source = "syStatus", target = "status"),
        @Mapping(source = "syOrderindex", target = "orderIndex"),
        @Mapping(target = "deptName", ignore = true),
        @Mapping(target = "realName", ignore = true),
        @Mapping(target = "isAdmin", ignore = true),
        @Mapping(target = "accountStatus", ignore = true),
        @Mapping(target = "lastLoginIp", ignore = true),
        @Mapping(target = "lastLoginTime", ignore = true),
        @Mapping(target = "passwordModifyTime", ignore = true),
        @Mapping(target = "passwordNeedModify", ignore = true),
        @Mapping(target = "audFlag", ignore = true),
        @Mapping(target = "createOrg", ignore = true),
        @Mapping(target = "createOrgName", ignore = true),
        @Mapping(target = "flag", ignore = true),
        @Mapping(target = "modifyOrg", ignore = true),
        @Mapping(target = "modifyOrgName", ignore = true),
        @Mapping(target = "piid", ignore = true),
        @Mapping(target = "pdid", ignore = true),
        @Mapping(target = "createUserName", ignore = true),
        @Mapping(target = "modifyUserName", ignore = true),
        @Mapping(target = "deleted", ignore = true)
    })
    IamUser toDomain(IamUserDO DO);

    /**
     * 领域模型转DO
     */
    @Mappings({
        @Mapping(source = "id", target = "tbIamUserId"),
        @Mapping(source = "username", target = "userName"),
        @Mapping(source = "nickname", target = "userCode"),
        @Mapping(source = "email", target = "userMail"),
        @Mapping(source = "phone", target = "userPhone"),
        @Mapping(source = "avatar", target = "userAvatar"),
        @Mapping(source = "gender", target = "userSexCode"),
        @Mapping(source = "birthday", target = "userBirth"),
        @Mapping(source = "idCard", target = "userCardnum"),
        @Mapping(source = "postId", target = "userPostCode"),
        @Mapping(source = "postName", target = "userPostName"),
        @Mapping(source = "userInitedCode", target = "userInitedCode"),
        @Mapping(source = "remark", target = "userRemark"),
        @Mapping(source = "tenantId", target = "syTenantId"),
        @Mapping(source = "deptId", target = "syOrgId"),
        @Mapping(source = "createTime", target = "syCreatetime"),
        @Mapping(source = "updateTime", target = "syModifytime"),
        @Mapping(source = "createUser", target = "syCreateuserid"),
        @Mapping(source = "modifyUser", target = "syModifyuserid"),
        @Mapping(source = "status", target = "syStatus"),
        @Mapping(source = "orderIndex", target = "syOrderindex")
    })
    IamUserDO toDO(IamUser domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "tbIamUserId", target = "id")
    IamUserDTO toDTO(IamUserDO DO);

    /**
     * 领域模型转DTO
     */
    @Mapping(source = "id", target = "id")
    IamUserDTO toDTO(IamUser domain);

    /**
     * DTO转领域模型
     */
    @Mapping(source = "id", target = "id")
    IamUser toDomain(IamUserDTO dto);

    /**
     * DO列表转领域模型列表
     */
    List<IamUser> toDomainList(List<IamUserDO> DOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamUserDTO> toDTOList(List<IamUser> domainList);

}
