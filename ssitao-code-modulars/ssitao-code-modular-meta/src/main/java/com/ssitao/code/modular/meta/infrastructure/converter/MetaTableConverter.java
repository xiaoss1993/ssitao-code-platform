package com.ssitao.code.modular.meta.infrastructure.converter;

import com.ssitao.code.modular.meta.api.dto.MetaTableDTO;
import com.ssitao.code.modular.meta.application.command.MetaTableCreateCommand;
import com.ssitao.code.modular.meta.dal.dataobject.MetaTableDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 元数据表配置转换器
 *
 * @author ssitao-code
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MetaTableConverter {

    MetaTableConverter INSTANCE = Mappers.getMapper(MetaTableConverter.class);

    MetaTableDTO toDTO(MetaTableDO metaTableDO);

    List<MetaTableDTO> toDTOList(List<MetaTableDO> metaTableDOS);

    MetaTableDO toDO(MetaTableCreateCommand command);
}
