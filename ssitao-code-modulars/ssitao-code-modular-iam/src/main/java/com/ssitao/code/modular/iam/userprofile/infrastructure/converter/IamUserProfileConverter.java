package com.ssitao.code.modular.iam.userprofile.infrastructure.converter;

import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;
import com.ssitao.code.modular.iam.userprofile.dal.dataobject.IamUserDO;
import com.ssitao.code.modular.iam.userprofile.domain.model.IamUserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * IAM用户档案转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamUserProfileConverter {

    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter DATE_ONLY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * DO转领域模型
     */
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "userCode", target = "userCode")
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "realName", target = "realName")
    @Mapping(source = "userSex", target = "userSexCode")
    @Mapping(source = "userPhone", target = "userPhone")
    @Mapping(source = "userMail", target = "userMail")
    @Mapping(source = "userBirthday", target = "userBirth", qualifiedByName = "localDateToString")
    @Mapping(source = "userIdCard", target = "userCardnum")
    @Mapping(source = "userPhoto", target = "userAvatar")
    @Mapping(source = "userAddress", target = "userAddress")
    @Mapping(source = "userNativePlace", target = "userNativePlace")
    @Mapping(source = "userNation", target = "userNationCode")
    @Mapping(source = "userPoliticalStatus", target = "userPoliticalCode")
    @Mapping(source = "userWorkNumber", target = "userJobnum")
    @Mapping(source = "userEntryDate", target = "userEntryDate", qualifiedByName = "localDateToString")
    @Mapping(source = "userEducation", target = "userEducationCode")
    @Mapping(source = "userStatus", target = "userEmployeeStatus")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "deptId", target = "deptId")
    @Mapping(source = "tenantId", target = "syTenantId")
    @Mapping(source = "createOrgId", target = "syOrgId")
    @Mapping(source = "createTime", target = "syCreatetime", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "modifyTime", target = "syModifytime", qualifiedByName = "localDateTimeToString")
    @Mapping(target = "userSexName", ignore = true)
    @Mapping(target = "userAge", ignore = true)
    @Mapping(target = "userLeaveDate", ignore = true)
    @Mapping(target = "userIdtypeCode", ignore = true)
    @Mapping(target = "userIdtypeName", ignore = true)
    @Mapping(target = "userEducationName", ignore = true)
    @Mapping(target = "userPostCode", ignore = true)
    @Mapping(target = "userPostName", ignore = true)
    @Mapping(target = "userMonitordeptId", ignore = true)
    @Mapping(target = "userMonitordeptName", ignore = true)
    @Mapping(target = "userRoleId", ignore = true)
    @Mapping(target = "userRoleName", ignore = true)
    @Mapping(target = "userRemark", ignore = true)
    @Mapping(target = "userStation", ignore = true)
    @Mapping(target = "userNowaddress", ignore = true)
    @Mapping(target = "userContactorName", ignore = true)
    @Mapping(target = "userContactorPhone", ignore = true)
    @Mapping(target = "userTelephone", ignore = true)
    @Mapping(target = "userNationName", ignore = true)
    @Mapping(target = "userNationalityCode", ignore = true)
    @Mapping(target = "userNationalityName", ignore = true)
    @Mapping(target = "syStatus", ignore = true)
    @Mapping(target = "syOrderindex", ignore = true)
    @Mapping(target = "syTenantName", ignore = true)
    IamUserProfile toDomain(IamUserDO userDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "userCode", target = "userCode")
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "userSexCode", target = "userSex")
    @Mapping(source = "userPhone", target = "userPhone")
    @Mapping(source = "userMail", target = "userMail")
    @Mapping(source = "userBirth", target = "userBirthday", qualifiedByName = "stringToLocalDate")
    @Mapping(source = "userCardnum", target = "userIdCard")
    @Mapping(source = "userAvatar", target = "userPhoto")
    @Mapping(source = "userAddress", target = "userAddress")
    @Mapping(source = "userNativePlace", target = "userNativePlace")
    @Mapping(source = "userNationCode", target = "userNation")
    @Mapping(source = "userPoliticalCode", target = "userPoliticalStatus")
    @Mapping(source = "userJobnum", target = "userWorkNumber")
    @Mapping(source = "userEntryDate", target = "userEntryDate", qualifiedByName = "stringToLocalDate")
    @Mapping(source = "userEducationCode", target = "userEducation")
    @Mapping(source = "userEmployeeStatus", target = "userStatus")
    @Mapping(source = "syTenantId", target = "tenantId")
    @Mapping(source = "syOrgId", target = "createOrgId")
    @Mapping(source = "syCreatetime", target = "createTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(source = "syModifytime", target = "modifyTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(target = "userProbationEndDate", ignore = true)
    @Mapping(target = "userEmploymentType", ignore = true)
    @Mapping(target = "userMaritalStatus", ignore = true)
    @Mapping(target = "createUserId", ignore = true)
    @Mapping(target = "modifyUserId", ignore = true)
    @Mapping(target = "isDeleted", constant = "0")
    @Mapping(target = "version", constant = "0")
    IamUserDO toDO(IamUserProfile userProfile);

    /**
     * DO转DTO
     */
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "userId", target = "id")
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "userName", target = "user_name")
    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "realName", target = "real_name")
    @Mapping(source = "userPhone", target = "phone")
    @Mapping(source = "userMail", target = "email")
    @Mapping(source = "userSex", target = "gender")
    @Mapping(source = "deptId", target = "dept_id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createTime", target = "create_time", qualifiedByName = "localDateTimeToString")
    IamUserProfileDTO toDTO(IamUserDO userDO);

    /**
     * 领域模型转DTO
     */
    IamUserProfileDTO toDTO(IamUserProfile userProfile);

    /**
     * DO列表转领域模型列表
     */
    List<IamUserProfile> toDomainList(List<IamUserDO> userDOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamUserProfileDTO> toDTOList(List<IamUserProfile> userProfileList);

    /**
     * LocalDate转String
     */
    @Named("localDateToString")
    default String localDateToString(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DATE_ONLY_FORMATTER);
    }

    /**
     * String转LocalDate
     */
    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(dateStr, DATE_ONLY_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * LocalDateTime转String
     */
    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DATE_FORMATTER);
    }

    /**
     * String转LocalDateTime
     */
    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }
}
