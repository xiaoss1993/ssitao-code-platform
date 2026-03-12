package com.ssitao.code.modular.meta.infrastructure.converter;

import com.ssitao.code.modular.meta.api.dto.MetaColumnDTO;
import com.ssitao.code.modular.meta.application.command.MetaColumnCreateCommand;
import com.ssitao.code.modular.meta.dal.dataobject.MetaColumnDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 元数据字段转换器
 *
 * @author ssitao-code
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MetaColumnConverter {

    MetaColumnConverter INSTANCE = Mappers.getMapper(MetaColumnConverter.class);

    /**
     * DO转DTO
     */
    MetaColumnDTO toDTO(MetaColumnDO metaColumnDO);

    /**
     * DO列表转DTO列表
     */
    List<MetaColumnDTO> toDTOList(List<MetaColumnDO> metaColumnDOS);

    /**
     * 创建命令转DO
     */
    MetaColumnDO toDO(MetaColumnCreateCommand command);
}
