package com.ssitao.code.modular.iam.organization.infrastructure.converter;

import com.ssitao.code.modular.iam.dal.dataobject.IamPostDO;
import com.ssitao.code.modular.iam.organization.api.dto.IamPostDTO;
import com.ssitao.code.modular.iam.organization.domain.model.IamPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * IAM岗位转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(componentModel = "spring")
public interface IamPostConverter {

    /**
     * DO转领域模型
     *
     * @param postDO DO对象
     * @return 领域模型
     */
    @Mappings({
            @Mapping(target = "id", source = "tbIamPostId"),
            @Mapping(target = "postLevel", source = "postLevel"),
            @Mapping(target = "postCategory", source = "postType"),
            @Mapping(target = "sortOrder", source = "syOrderindex"),
            @Mapping(target = "tenantId", source = "syTenantId"),
            @Mapping(target = "createTime", expression = "java(parseLocalDateTime(postDO.getSyCreatetime()))"),
            @Mapping(target = "updateTime", expression = "java(parseLocalDateTime(postDO.getSyModifytime()))"),
            @Mapping(target = "creator", source = "syCreateuserid"),
            @Mapping(target = "updater", source = "syModifyuserid"),
            @Mapping(target = "status", source = "status")
    })
    IamPost toDomain(IamPostDO postDO);

    /**
     * 领域模型转DO
     *
     * @param post 领域模型
     * @return DO对象
     */
    @Mappings({
            @Mapping(target = "tbIamPostId", source = "id"),
            @Mapping(target = "postLevel", source = "postLevel"),
            @Mapping(target = "postType", source = "postCategory"),
            @Mapping(target = "syOrderindex", source = "sortOrder"),
            @Mapping(target = "syTenantId", source = "tenantId"),
            @Mapping(target = "syCreatetime", expression = "java(formatLocalDateTime(post.getCreateTime()))"),
            @Mapping(target = "syModifytime", expression = "java(formatLocalDateTime(post.getUpdateTime()))"),
            @Mapping(target = "syCreateuserid", source = "creator"),
            @Mapping(target = "syModifyuserid", source = "updater"),
            @Mapping(target = "status", source = "status")
    })
    IamPostDO toDO(IamPost post);

    /**
     * DO列表转领域模型列表
     *
     * @param postDOList DO列表
     * @return 领域模型列表
     */
    List<IamPost> toDomainList(List<IamPostDO> postDOList);

    /**
     * 领域模型列表转DO列表
     *
     * @param postList 领域模型列表
     * @return DO列表
     */
    List<IamPostDO> toDOList(List<IamPost> postList);

    /**
     * 领域模型转DTO
     *
     * @param post 领域模型
     * @return DTO对象
     */
    IamPostDTO toDTO(IamPost post);

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
