package com.ssitao.code.modular.iam.system.infrastructure.converter;

import com.ssitao.code.modular.iam.dal.dataobject.IamDictTypeDO;
import com.ssitao.code.modular.iam.system.domain.model.IamDictType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * IAM字典类型转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(componentModel = "spring")
public interface IamDictTypeConverter {

    /**
     * DO转领域模型
     *
     * @param dictTypeDO DO对象
     * @return 领域模型
     */
    @Mappings({
            @Mapping(target = "id", source = "tbCoreDictionaryId"),
            @Mapping(target = "dictTypeCode", source = "dictionaryDdcode"),
            @Mapping(target = "dictTypeName", source = "dictionaryDdname"),
            @Mapping(target = "description", source = "dictionaryDdtype"),
            @Mapping(target = "tenantId", source = "syTenantId"),
            @Mapping(target = "createTime", expression = "java(parseLocalDateTime(dictTypeDO.getSyCreatetime()))"),
            @Mapping(target = "updateTime", expression = "java(parseLocalDateTime(dictTypeDO.getSyModifytime()))"),
            @Mapping(target = "creator", source = "syCreateuserid"),
            @Mapping(target = "updater", source = "syModifyuserid"),
            @Mapping(target = "status", expression = "java(parseStatus(dictTypeDO.getSyStatus()))")
    })
    IamDictType toDomain(IamDictTypeDO dictTypeDO);

    /**
     * 领域模型转DO
     *
     * @param dictType 领域模型
     * @return DO对象
     */
    @Mappings({
            @Mapping(target = "tbCoreDictionaryId", source = "id"),
            @Mapping(target = "dictionaryDdcode", source = "dictTypeCode"),
            @Mapping(target = "dictionaryDdname", source = "dictTypeName"),
            @Mapping(target = "dictionaryDdtype", source = "description"),
            @Mapping(target = "syTenantId", source = "tenantId"),
            @Mapping(target = "syCreatetime", expression = "java(formatLocalDateTime(dictType.getCreateTime()))"),
            @Mapping(target = "syModifytime", expression = "java(formatLocalDateTime(dictType.getUpdateTime()))"),
            @Mapping(target = "syCreateuserid", source = "creator"),
            @Mapping(target = "syModifyuserid", source = "updater"),
            @Mapping(target = "syStatus", expression = "java(formatStatus(dictType.getStatus()))")
    })
    IamDictTypeDO toDO(IamDictType dictType);

    /**
     * DO列表转领域模型列表
     *
     * @param dictTypeDOList DO列表
     * @return 领域模型列表
     */
    List<IamDictType> toDomainList(List<IamDictTypeDO> dictTypeDOList);

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

    /**
     * 解析状态
     */
    default Boolean parseStatus(String status) {
        if (status == null || status.isEmpty()) {
            return true;
        }
        return "1".equals(status) || "true".equalsIgnoreCase(status);
    }

    /**
     * 格式化状态
     */
    default String formatStatus(Boolean status) {
        if (status == null) {
            return "1";
        }
        return status ? "1" : "0";
    }
}
