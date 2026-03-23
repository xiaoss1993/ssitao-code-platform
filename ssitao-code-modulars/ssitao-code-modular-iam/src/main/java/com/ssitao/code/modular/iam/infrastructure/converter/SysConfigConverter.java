package com.ssitao.code.modular.iam.infrastructure.converter;

import com.ssitao.code.modular.iam.application.dto.SysConfigDTO;
import com.ssitao.code.modular.iam.application.command.CreateConfigCommand;
import com.ssitao.code.modular.iam.application.command.UpdateConfigCommand;
import com.ssitao.code.modular.iam.domain.model.SysConfigAggregate;
import com.ssitao.code.modular.iam.domain.SysConfig;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 参数配置对象转换器
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysConfigConverter {

    SysConfigConverter INSTANCE = Mappers.getMapper(SysConfigConverter.class);

    /**
     * 聚合根 -> DTO
     */
    SysConfigDTO toDTO(SysConfigAggregate aggregate);

    /**
     * 实体 -> DTO
     */
    SysConfigDTO toDTO(SysConfig config);

    /**
     * 创建命令 -> 聚合根
     */
    SysConfigAggregate fromCommand(CreateConfigCommand command);

    /**
     * 更新命令 -> 聚合根
     */
    void updateAggregate(UpdateConfigCommand command, @MappingTarget SysConfigAggregate aggregate);

    /**
     * 聚合根列表 -> DTO列表
     */
    List<SysConfigDTO> toDTOList(List<SysConfigAggregate> aggregates);

    /**
     * 实体列表 -> DTO列表
     */
    List<SysConfigDTO> toDTOListFromConfig(List<SysConfig> configs);
}
