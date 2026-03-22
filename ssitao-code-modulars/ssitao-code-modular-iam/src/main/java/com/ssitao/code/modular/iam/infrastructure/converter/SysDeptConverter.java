package com.ssitao.code.modular.iam.infrastructure.converter;

import com.ssitao.code.common.core.domain.entity.SysDept;
import com.ssitao.code.modular.iam.api.dto.SysDeptDTO;
import com.ssitao.code.modular.iam.application.command.CreateDeptCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDeptCommand;
import com.ssitao.code.modular.iam.domain.model.SysDeptAggregate;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 部门对象转换器
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysDeptConverter {

    SysDeptConverter INSTANCE = Mappers.getMapper(SysDeptConverter.class);

    /**
     * 聚合根 -> DTO
     */
    SysDeptDTO toDTO(SysDeptAggregate aggregate);

    /**
     * 实体 -> DTO
     */
    SysDeptDTO toDTO(SysDept dept);

    /**
     * DTO -> 聚合根
     */
    SysDeptAggregate toAggregate(SysDeptDTO dto);

    /**
     * 创建命令 -> 聚合根
     */
    SysDeptAggregate fromCommand(CreateDeptCommand command);

    /**
     * 更新命令 -> 聚合根
     */
    void updateAggregate(UpdateDeptCommand command, @MappingTarget SysDeptAggregate aggregate);

    /**
     * 聚合根列表 -> DTO列表
     */
    List<SysDeptDTO> toDTOList(List<SysDeptAggregate> aggregates);

    /**
     * 实体列表 -> DTO列表
     */
    List<SysDeptDTO> toDTOListFromDept(List<SysDept> depts);
}
