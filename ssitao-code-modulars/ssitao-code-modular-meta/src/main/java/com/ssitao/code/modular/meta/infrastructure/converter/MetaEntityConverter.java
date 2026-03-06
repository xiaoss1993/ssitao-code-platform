package com.ssitao.code.modular.meta.infrastructure.converter;

import com.ssitao.code.modular.meta.api.dto.MetaEntityDTO;
import com.ssitao.code.modular.meta.application.command.MetaEntityCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaEntityUpdateCommand;
import com.ssitao.code.modular.meta.dal.dataobject.MetaEntityDO;
import com.ssitao.code.modular.meta.domain.model.MetaEntity;
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

    // Domain 转换方法
    MetaEntity toDomain(MetaEntityDO metaEntityDO);

    List<MetaEntity> toDomainList(List<MetaEntityDO> metaEntityDOS);

    MetaEntityDO toDO(MetaEntity entity);

    // Command 到 Domain
    MetaEntity toDomain(MetaEntityCreateCommand command);

    MetaEntity toDomain(MetaEntityUpdateCommand command);
}
