package com.ssitao.code.modular.iam.system.infrastructure.converter;

import com.ssitao.code.modular.iam.system.dal.dataobject.IamDictTypeDO;
import com.ssitao.code.modular.iam.system.domain.model.IamDictType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM字典类型转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamDictTypeConverter {

    /**
     * DO转领域模型
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dictTypeCode", target = "dictTypeCode")
    @Mapping(source = "dictTypeName", target = "dictTypeName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status", qualifiedByName = "intToBoolean")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "sortOrder", target = "sortOrder")
    @Mapping(source = "remark", target = "remark")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "updater", target = "updater")
    @Mapping(target = "deleted", ignore = true)
    IamDictType toDomain(IamDictTypeDO dictTypeDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dictTypeCode", target = "dictTypeCode")
    @Mapping(source = "dictTypeName", target = "dictTypeName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status", qualifiedByName = "booleanToInt")
    @Mapping(source = "tenantId", target = "tenantId")
    @Mapping(source = "sortOrder", target = "sortOrder")
    @Mapping(source = "remark", target = "remark")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "updater", target = "updater")
    @Mapping(target = "deleted", constant = "0")
    IamDictTypeDO toDO(IamDictType dictType);

    /**
     * DO列表转领域模型列表
     */
    List<IamDictType> toDomainList(List<IamDictTypeDO> dictTypeDOList);

    @Named("intToBoolean")
    default Boolean intToBoolean(Integer value) {
        return value != null && value == 1;
    }

    @Named("booleanToInt")
    default Integer booleanToInt(Boolean value) {
        return value != null && value ? 1 : 0;
    }
}
