package com.ssitao.code.modular.iam.system.infrastructure.converter;

import com.ssitao.code.modular.iam.system.dal.dataobject.IamDictDataDO;
import com.ssitao.code.modular.iam.system.domain.model.IamDictData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM字典数据转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamDictDataConverter {

    /**
     * DO转领域模型
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dictTypeId", target = "dictTypeId")
    @Mapping(source = "dictTypeCode", target = "dictTypeCode")
    @Mapping(source = "dictDataCode", target = "dictDataCode")
    @Mapping(source = "dictDataValue", target = "dictDataValue")
    @Mapping(source = "dictDataLabel", target = "dictDataLabel")
    @Mapping(source = "cssClass", target = "cssClass")
    @Mapping(source = "listClass", target = "listClass")
    @Mapping(source = "isDefault", target = "isDefault", qualifiedByName = "intToBoolean")
    @Mapping(source = "sortOrder", target = "sortOrder")
    @Mapping(source = "status", target = "status", qualifiedByName = "intToBoolean")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "remark", target = "remark")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "updater", target = "updater")
    @Mapping(source = "parentId", target = "parentId")
    @Mapping(source = "layer", target = "layer")
    @Mapping(target = "deleted", ignore = true)
    IamDictData toDomain(IamDictDataDO dictDataDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dictTypeId", target = "dictTypeId")
    @Mapping(source = "dictTypeCode", target = "dictTypeCode")
    @Mapping(source = "dictDataCode", target = "dictDataCode")
    @Mapping(source = "dictDataValue", target = "dictDataValue")
    @Mapping(source = "dictDataLabel", target = "dictDataLabel")
    @Mapping(source = "cssClass", target = "cssClass")
    @Mapping(source = "listClass", target = "listClass")
    @Mapping(source = "isDefault", target = "isDefault", qualifiedByName = "booleanToInt")
    @Mapping(source = "sortOrder", target = "sortOrder")
    @Mapping(source = "status", target = "status", qualifiedByName = "booleanToInt")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "remark", target = "remark")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "updater", target = "updater")
    @Mapping(source = "parentId", target = "parentId")
    @Mapping(source = "layer", target = "layer")
    @Mapping(target = "deleted", constant = "0")
    IamDictDataDO toDO(IamDictData dictData);

    /**
     * DO列表转领域模型列表
     */
    List<IamDictData> toDomainList(List<IamDictDataDO> dictDataDOList);

    @Named("intToBoolean")
    default Boolean intToBoolean(Integer value) {
        return value != null && value == 1;
    }

    @Named("booleanToInt")
    default Integer booleanToInt(Boolean value) {
        return value != null && value ? 1 : 0;
    }
}
