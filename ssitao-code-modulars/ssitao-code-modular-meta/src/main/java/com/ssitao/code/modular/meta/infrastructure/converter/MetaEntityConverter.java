package com.ssitao.code.modular.meta.infrastructure.converter;

import com.ssitao.code.modular.meta.api.dto.MetaEntityDTO;
import com.ssitao.code.modular.meta.application.command.MetaEntityCreateCommand;
import com.ssitao.code.modular.meta.dal.dataobject.MetaEntityDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 元数据实体配置转换器
 *
 * @author ssitao-code
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MetaEntityConverter {

    MetaEntityConverter INSTANCE = Mappers.getMapper(MetaEntityConverter.class);

    MetaEntityDTO toDTO(MetaEntityDO metaEntityDO);

    List<MetaEntityDTO> toDTOList(List<MetaEntityDO> metaEntityDOS);

    MetaEntityDO toDO(MetaEntityCreateCommand command);
}
