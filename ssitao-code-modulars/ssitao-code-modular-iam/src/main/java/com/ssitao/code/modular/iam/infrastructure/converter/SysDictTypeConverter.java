package com.ssitao.code.modular.iam.infrastructure.converter;

import com.ssitao.code.modular.iam.application.dto.SysDictTypeDTO;
import com.ssitao.code.modular.iam.application.command.CreateDictTypeCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDictTypeCommand;
import com.ssitao.code.modular.iam.domain.model.SysDictTypeAggregate;
import com.ssitao.code.common.core.domain.entity.SysDictType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典类型对象转换器
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysDictTypeConverter {

    SysDictTypeConverter INSTANCE = Mappers.getMapper(SysDictTypeConverter.class);

    SysDictTypeDTO toDTO(SysDictTypeAggregate aggregate);
    SysDictTypeDTO toDTO(SysDictType dictType);
    SysDictTypeAggregate fromCommand(CreateDictTypeCommand command);
    void updateAggregate(UpdateDictTypeCommand command, @MappingTarget SysDictTypeAggregate aggregate);
    List<SysDictTypeDTO> toDTOList(List<SysDictTypeAggregate> aggregates);
    List<SysDictTypeDTO> toDTOListFromDictType(List<SysDictType> dictTypes);
}
