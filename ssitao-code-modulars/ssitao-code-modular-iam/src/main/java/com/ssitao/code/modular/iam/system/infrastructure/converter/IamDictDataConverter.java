package com.ssitao.code.modular.iam.system.infrastructure.converter;

import com.ssitao.code.modular.iam.dal.dataobject.IamDictDataDO;
import com.ssitao.code.modular.iam.system.domain.model.IamDictData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * IAM字典数据转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(componentModel = "spring")
public interface IamDictDataConverter {

    /**
     * DO转领域模型
     *
     * @param dictDataDO DO对象
     * @return 领域模型
     */
    @Mappings({
            @Mapping(target = "id", source = "tbCoreDictionaryitemId"),
            @Mapping(target = "dictTypeId", source = "dictionaryitemDictionaryId"),
            @Mapping(target = "dictDataLabel", source = "dictionaryitemItemname"),
            @Mapping(target = "dictDataCode", source = "dictionaryitemItemcode"),
            @Mapping(target = "dictDataValue", source = "dictionaryitemItemcode"),
            @Mapping(target = "cssClass", source = "dictionaryitemBackgroundcolor"),
            @Mapping(target = "listClass", source = "dictionaryitemFontcolor"),
            @Mapping(target = "tenantId", source = "syTenantId"),
            @Mapping(target = "createTime", expression = "java(parseLocalDateTime(dictDataDO.getSyCreatetime()))"),
            @Mapping(target = "updateTime", expression = "java(parseLocalDateTime(dictDataDO.getSyModifytime()))"),
            @Mapping(target = "creator", source = "syCreateuserid"),
            @Mapping(target = "updater", source = "syModifyuserid"),
            @Mapping(target = "status", expression = "java(parseStatus(dictDataDO.getSyStatus()))"),
            @Mapping(target = "parentId", source = "syParent"),
            @Mapping(target = "layer", source = "syLayer")
    })
    IamDictData toDomain(IamDictDataDO dictDataDO);

    /**
     * 领域模型转DO
     *
     * @param dictData 领域模型
     * @return DO对象
     */
    @Mappings({
            @Mapping(target = "tbCoreDictionaryitemId", source = "id"),
            @Mapping(target = "dictionaryitemDictionaryId", source = "dictTypeId"),
            @Mapping(target = "dictionaryitemItemname", source = "dictDataLabel"),
            @Mapping(target = "dictionaryitemItemcode", source = "dictDataCode"),
            @Mapping(target = "dictionaryitemBackgroundcolor", source = "cssClass"),
            @Mapping(target = "dictionaryitemFontcolor", source = "listClass"),
            @Mapping(target = "syTenantId", source = "tenantId"),
            @Mapping(target = "syCreatetime", expression = "java(formatLocalDateTime(dictData.getCreateTime()))"),
            @Mapping(target = "syModifytime", expression = "java(formatLocalDateTime(dictData.getUpdateTime()))"),
            @Mapping(target = "syCreateuserid", source = "creator"),
            @Mapping(target = "syModifyuserid", source = "updater"),
            @Mapping(target = "syStatus", expression = "java(formatStatus(dictData.getStatus()))")
    })
    IamDictDataDO toDO(IamDictData dictData);

    /**
     * DO列表转领域模型列表
     *
     * @param dictDataDOList DO列表
     * @return 领域模型列表
     */
    List<IamDictData> toDomainList(List<IamDictDataDO> dictDataDOList);

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
