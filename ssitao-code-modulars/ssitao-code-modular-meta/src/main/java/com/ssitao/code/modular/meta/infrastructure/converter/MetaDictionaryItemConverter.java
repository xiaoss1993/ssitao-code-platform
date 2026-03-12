package com.ssitao.code.modular.meta.infrastructure.converter;

import com.ssitao.code.modular.meta.dal.dataobject.MetaDictionaryItemDO;
import com.ssitao.code.modular.meta.domain.model.MetaDictionaryItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典数据转换器
 *
 * @author ssitao-code
 */
@Mapper
public interface MetaDictionaryItemConverter {

    MetaDictionaryItemConverter INSTANCE = Mappers.getMapper(MetaDictionaryItemConverter.class);

    /**
     * DO转领域模型
     */
    MetaDictionaryItem toDomain(MetaDictionaryItemDO itemDO);

    /**
     * 领域模型转DO
     */
    MetaDictionaryItemDO toDO(MetaDictionaryItem item);

    /**
     * DO列表转领域模型列表
     */
    List<MetaDictionaryItem> toDomainList(List<MetaDictionaryItemDO> list);

    /**
     * 领域模型列表转DO列表
     */
    List<MetaDictionaryItemDO> toDOList(List<MetaDictionaryItem> list);
}
