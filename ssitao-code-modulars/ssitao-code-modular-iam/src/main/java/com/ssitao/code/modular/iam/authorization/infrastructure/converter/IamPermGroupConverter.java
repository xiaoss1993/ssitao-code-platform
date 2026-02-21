package com.ssitao.code.modular.iam.authorization.infrastructure.converter;

import com.ssitao.code.modular.iam.authorization.domain.model.IamPermGroup;
import com.ssitao.code.modular.iam.dal.dataobject.IamPermGroupDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * IAM权限组转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(componentModel = "spring")
public interface IamPermGroupConverter {

    /**
     * DO转领域模型
     *
     * @param permGroupDO DO对象
     * @return 领域模型
     */
    @Mappings({
            @Mapping(target = "id", source = "tbIamPermgroupId"),
            @Mapping(target = "groupCode", source = "permgroupCode"),
            @Mapping(target = "groupName", source = "permgroupName"),
            @Mapping(target = "status", expression = "java(parseStatus(permGroupDO.getSyStatus()))"),
            @Mapping(target = "sortOrder", source = "syOrderindex"),
            @Mapping(target = "createTime", expression = "java(parseLocalDateTime(permGroupDO.getSyCreatetime()))"),
            @Mapping(target = "creator", source = "syCreateusername")
    })
    IamPermGroup toDomain(IamPermGroupDO permGroupDO);

    /**
     * 领域模型转DO
     *
     * @param permGroup 领域模型
     * @return DO对象
     */
    @Mappings({
            @Mapping(target = "tbIamPermgroupId", source = "id"),
            @Mapping(target = "permgroupCode", source = "groupCode"),
            @Mapping(target = "permgroupName", source = "groupName"),
            @Mapping(target = "syStatus", expression = "java(formatStatus(permGroup.getStatus()))"),
            @Mapping(target = "syOrderindex", source = "sortOrder"),
            @Mapping(target = "syCreatetime", expression = "java(formatLocalDateTime(permGroup.getCreateTime()))"),
            @Mapping(target = "syCreateusername", source = "creator")
    })
    IamPermGroupDO toDO(IamPermGroup permGroup);

    /**
     * DO列表转领域模型列表
     *
     * @param permGroupDOList DO列表
     * @return 领域模型列表
     */
    List<IamPermGroup> toDomainList(List<IamPermGroupDO> permGroupDOList);

    /**
     * 解析状态
     */
    default Boolean parseStatus(String status) {
        return "1".equals(status);
    }

    /**
     * 格式化状态
     */
    default String formatStatus(Boolean status) {
        return Boolean.TRUE.equals(status) ? "1" : "0";
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
