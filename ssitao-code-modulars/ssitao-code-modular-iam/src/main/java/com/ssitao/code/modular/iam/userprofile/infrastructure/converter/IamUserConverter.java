package com.ssitao.code.modular.iam.userprofile.infrastructure.converter;

import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserDTO;
import com.ssitao.code.modular.iam.userprofile.dal.dataobject.IamUserDO;
import com.ssitao.code.modular.iam.userprofile.domain.model.IamUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
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
    @Mapping(source = "userId", target = "id")
    @Mapping(source = "userName", target = "username")
    @Mapping(source = "userCode", target = "nickname")
    @Mapping(source = "userMail", target = "email")
    @Mapping(source = "userPhone", target = "phone")
    @Mapping(source = "userPhoto", target = "avatar")
    @Mapping(source = "userSex", target = "gender")
    @Mapping(source = "userBirthday", target = "birthday")
    @Mapping(source = "userIdCard", target = "idCard")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "createOrgId", target = "deptId")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "modifyTime", target = "updateTime")
    @Mapping(source = "createUserId", target = "createUser")
    @Mapping(source = "modifyUserId", target = "modifyUser")
    @Mapping(source = "userStatus", target = "status")
    @Mapping(target = "deptName", ignore = true)
    @Mapping(target = "realName", ignore = true)
    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "postName", ignore = true)
    @Mapping(target = "userInitedCode", ignore = true)
    @Mapping(target = "remark", ignore = true)
    @Mapping(target = "orderIndex", ignore = true)
    @Mapping(target = "isAdmin", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "lastLoginIp", ignore = true)
    @Mapping(target = "lastLoginTime", ignore = true)
    @Mapping(target = "passwordModifyTime", ignore = true)
    @Mapping(target = "passwordNeedModify", ignore = true)
    @Mapping(target = "audFlag", ignore = true)
    @Mapping(target = "createOrg", ignore = true)
    @Mapping(target = "createOrgName", ignore = true)
    @Mapping(target = "flag", ignore = true)
    @Mapping(target = "modifyOrg", ignore = true)
    @Mapping(target = "modifyOrgName", ignore = true)
    @Mapping(target = "piid", ignore = true)
    @Mapping(target = "pdid", ignore = true)
    @Mapping(target = "createUserName", ignore = true)
    @Mapping(target = "modifyUserName", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    IamUser toDomain(IamUserDO userDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "userId")
    @Mapping(source = "username", target = "userName")
    @Mapping(source = "nickname", target = "userCode")
    @Mapping(source = "email", target = "userMail")
    @Mapping(source = "phone", target = "userPhone")
    @Mapping(source = "avatar", target = "userPhoto")
    @Mapping(source = "gender", target = "userSex")
    @Mapping(source = "birthday", target = "userBirthday")
    @Mapping(source = "idCard", target = "userIdCard")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "deptId", target = "createOrgId")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "modifyTime")
    @Mapping(source = "createUser", target = "createUserId")
    @Mapping(source = "modifyUser", target = "modifyUserId")
    @Mapping(source = "status", target = "userStatus")
    @Mapping(target = "userNativePlace", ignore = true)
    @Mapping(target = "userNation", ignore = true)
    @Mapping(target = "userMaritalStatus", ignore = true)
    @Mapping(target = "userPoliticalStatus", ignore = true)
    @Mapping(target = "userWorkNumber", ignore = true)
    @Mapping(target = "userEntryDate", ignore = true)
    @Mapping(target = "userProbationEndDate", ignore = true)
    @Mapping(target = "userEmploymentType", ignore = true)
    @Mapping(target = "userEducation", ignore = true)
    @Mapping(target = "userAddress", ignore = true)
    @Mapping(target = "isDeleted", constant = "0")
    @Mapping(target = "version", constant = "0")
    IamUserDO toDO(IamUser domain);

    /**
     * DO转DTO
     */
    @Mapping(source = "userId", target = "id")
    IamUserDTO toDTO(IamUserDO userDO);

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
    List<IamUser> toDomainList(List<IamUserDO> userDOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamUserDTO> toDTOList(List<IamUser> domainList);

}
