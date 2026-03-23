package com.ssitao.code.modular.iam.infrastructure.converter;

import com.ssitao.code.modular.iam.application.dto.SysDictDataDTO;
import com.ssitao.code.modular.iam.application.command.CreateDictDataCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDictDataCommand;
import com.ssitao.code.modular.iam.domain.model.SysDictDataAggregate;
import com.ssitao.code.common.core.domain.entity.SysDictData;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典数据对象转换器
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysDictDataConverter {

    SysDictDataConverter INSTANCE = Mappers.getMapper(SysDictDataConverter.class);

    SysDictDataDTO toDTO(SysDictDataAggregate aggregate);
    SysDictDataDTO toDTO(SysDictData dictData);
    SysDictDataAggregate fromCommand(CreateDictDataCommand command);
    void updateAggregate(UpdateDictDataCommand command, @MappingTarget SysDictDataAggregate aggregate);
    List<SysDictDataDTO> toDTOList(List<SysDictDataAggregate> aggregates);
    List<SysDictDataDTO> toDTOListFromDictData(List<SysDictData> dictDatas);
}
