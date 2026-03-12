package com.ssitao.code.modular.meta.infrastructure.converter;

import com.ssitao.code.modular.meta.dal.dataobject.MetaDictionaryDO;
import com.ssitao.code.modular.meta.domain.model.MetaDictionary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典类型转换器
 *
 * @author ssitao-code
 */
@Mapper
public interface MetaDictionaryConverter {

    MetaDictionaryConverter INSTANCE = Mappers.getMapper(MetaDictionaryConverter.class);

    /**
     * DO转领域模型
     */
    MetaDictionary toDomain(MetaDictionaryDO dictDO);

    /**
     * 领域模型转DO
     */
    MetaDictionaryDO toDO(MetaDictionary dictionary);

    /**
     * DO列表转领域模型列表
     */
    List<MetaDictionary> toDomainList(List<MetaDictionaryDO> list);

    /**
     * 领域模型列表转DO列表
     */
    List<MetaDictionaryDO> toDOList(List<MetaDictionary> list);
}
