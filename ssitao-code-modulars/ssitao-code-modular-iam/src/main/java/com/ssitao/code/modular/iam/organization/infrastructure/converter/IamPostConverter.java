package com.ssitao.code.modular.iam.organization.infrastructure.converter;

import com.ssitao.code.modular.iam.organization.api.dto.IamPostDTO;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamPostDO;
import com.ssitao.code.modular.iam.organization.domain.model.IamPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM岗位转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamPostConverter {

    /**
     * DO转领域模型
     */
    @Mapping(source = "postId", target = "id")
    @Mapping(source = "postType", target = "postCategory")
    @Mapping(source = "postStatus", target = "status", qualifiedByName = "intToBoolean")
    @Mapping(source = "postDesc", target = "remark")
    @Mapping(source = "postSort", target = "sortOrder")
    @Mapping(source = "createUserId", target = "creator")
    @Mapping(source = "modifyUserId", target = "updater")
    @Mapping(source = "modifyTime", target = "updateTime")
    @Mapping(source = "isDeleted", target = "deleted", qualifiedByName = "intToBoolean")
    @Mapping(target = "deptId", ignore = true)
    @Mapping(target = "deptName", ignore = true)
    IamPost toDomain(IamPostDO postDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "postId")
    @Mapping(source = "postCategory", target = "postType")
    @Mapping(source = "status", target = "postStatus", qualifiedByName = "booleanToInt")
    @Mapping(source = "remark", target = "postDesc")
    @Mapping(source = "sortOrder", target = "postSort")
    @Mapping(source = "creator", target = "createUserId")
    @Mapping(source = "updater", target = "modifyUserId")
    @Mapping(source = "updateTime", target = "modifyTime")
    @Mapping(target = "isDeleted", constant = "0")
    @Mapping(target = "version", constant = "0")
    IamPostDO toDO(IamPost post);

    /**
     * DO列表转领域模型列表
     */
    List<IamPost> toDomainList(List<IamPostDO> postDOList);

    /**
     * 领域模型列表转DO列表
     */
    List<IamPostDO> toDOList(List<IamPost> postList);

    /**
     * 领域模型转DTO
     */
    IamPostDTO toDTO(IamPost post);

    @Named("intToBoolean")
    default Boolean intToBoolean(Integer value) {
        return value != null && value == 1;
    }

    @Named("booleanToInt")
    default Integer booleanToInt(Boolean value) {
        return value != null && value ? 1 : 0;
    }
}
