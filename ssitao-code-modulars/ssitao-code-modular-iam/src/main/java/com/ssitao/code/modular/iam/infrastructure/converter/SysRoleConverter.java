package com.ssitao.code.modular.iam.infrastructure.converter;

import com.ssitao.code.common.core.domain.entity.SysRole;
import com.ssitao.code.modular.iam.api.dto.SysRoleDTO;
import com.ssitao.code.modular.iam.application.command.CreateRoleCommand;
import com.ssitao.code.modular.iam.application.command.UpdateRoleCommand;
import com.ssitao.code.modular.iam.domain.model.SysRoleAggregate;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色对象转换器
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleConverter {

    SysRoleConverter INSTANCE = Mappers.getMapper(SysRoleConverter.class);

    /**
     * 聚合根 -> DTO
     */
    SysRoleDTO toDTO(SysRoleAggregate aggregate);

    /**
     * 实体 -> DTO
     */
    SysRoleDTO toDTO(SysRole role);

    /**
     * DTO -> 聚合根
     */
    SysRoleAggregate toAggregate(SysRoleDTO dto);

    /**
     * 创建命令 -> 聚合根
     */
    SysRoleAggregate fromCommand(CreateRoleCommand command);

    /**
     * 更新命令 -> 聚合根
     */
    void updateAggregate(UpdateRoleCommand command, @MappingTarget SysRoleAggregate aggregate);

    /**
     * 聚合根列表 -> DTO列表
     */
    List<SysRoleDTO> toDTOList(List<SysRoleAggregate> aggregates);

    /**
     * 实体列表 -> DTO列表
     */
    List<SysRoleDTO> toDTOListFromRole(List<SysRole> roles);
}
