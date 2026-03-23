package com.ssitao.code.modular.iam.infrastructure.converter;

import com.ssitao.code.modular.iam.application.dto.SysMenuDTO;
import com.ssitao.code.modular.iam.application.command.CreateMenuCommand;
import com.ssitao.code.modular.iam.application.command.UpdateMenuCommand;
import com.ssitao.code.modular.iam.domain.model.SysMenuAggregate;
import com.ssitao.code.common.core.domain.entity.SysMenu;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 菜单对象转换器
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysMenuConverter {

    SysMenuConverter INSTANCE = Mappers.getMapper(SysMenuConverter.class);

    SysMenuDTO toDTO(SysMenuAggregate aggregate);
    SysMenuDTO toDTO(SysMenu menu);
    SysMenuAggregate fromCommand(CreateMenuCommand command);
    void updateAggregate(UpdateMenuCommand command, @MappingTarget SysMenuAggregate aggregate);
    List<SysMenuDTO> toDTOList(List<SysMenuAggregate> aggregates);
    List<SysMenuDTO> toDTOListFromMenu(List<SysMenu> menus);
}
