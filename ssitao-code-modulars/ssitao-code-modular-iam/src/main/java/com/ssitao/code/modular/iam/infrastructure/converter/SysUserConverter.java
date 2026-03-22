package com.ssitao.code.modular.iam.infrastructure.converter;

import com.ssitao.code.common.core.domain.entity.SysUser;
import com.ssitao.code.modular.iam.api.dto.SysUserDTO;
import com.ssitao.code.modular.iam.application.command.ChangePasswordCommand;
import com.ssitao.code.modular.iam.application.command.CreateUserCommand;
import com.ssitao.code.modular.iam.application.command.UpdateUserCommand;
import com.ssitao.code.modular.iam.domain.model.SysUserAggregate;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户对象转换器
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    /**
     * 聚合根 -> DTO
     */
    SysUserDTO toDTO(SysUserAggregate aggregate);

    /**
     * 实体 -> DTO
     */
    SysUserDTO toDTO(SysUser user);

    /**
     * DTO -> 聚合根
     */
    SysUserAggregate toAggregate(SysUserDTO dto);

    /**
     * 创建命令 -> 聚合根
     */
    SysUserAggregate fromCommand(CreateUserCommand command);

    /**
     * 更新命令 -> 聚合根
     */
    void updateAggregate(UpdateUserCommand command, @MappingTarget SysUserAggregate aggregate);

    /**
     * 聚合根列表 -> DTO列表
     */
    List<SysUserDTO> toDTOList(List<SysUserAggregate> aggregates);

    /**
     * 实体列表 -> DTO列表
     */
    List<SysUserDTO> toDTOListFromUser(List<SysUser> users);

    /**
     * DTO更新聚合根（用于更新操作）
     */
    void updateAggregateFromDTO(SysUserDTO dto, @MappingTarget SysUserAggregate aggregate);
}
