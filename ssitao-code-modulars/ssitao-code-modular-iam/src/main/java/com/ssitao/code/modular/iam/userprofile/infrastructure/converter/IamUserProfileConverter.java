package com.ssitao.code.modular.iam.userprofile.infrastructure.converter;

import com.ssitao.code.modular.iam.dal.dataobject.IamUserDO;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;
import com.ssitao.code.modular.iam.userprofile.domain.model.IamUserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * IAM用户档案转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(componentModel = "spring")
public interface IamUserProfileConverter {

    /**
     * DO转领域模型
     */
    @Mapping(source = "tbIamUserId", target = "userId")
    IamUserProfile toDomain(IamUserDO userDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "userId", target = "tbIamUserId")
    IamUserDO toDO(IamUserProfile userProfile);

    /**
     * DO转DTO
     */
    @Mapping(source = "tbIamUserId", target = "userId")
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

}
