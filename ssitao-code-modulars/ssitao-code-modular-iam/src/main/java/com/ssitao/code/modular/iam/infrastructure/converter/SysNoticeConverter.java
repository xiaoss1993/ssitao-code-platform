package com.ssitao.code.modular.iam.infrastructure.converter;

import com.ssitao.code.modular.iam.application.dto.SysNoticeDTO;
import com.ssitao.code.modular.iam.application.command.CreateNoticeCommand;
import com.ssitao.code.modular.iam.application.command.UpdateNoticeCommand;
import com.ssitao.code.modular.iam.domain.model.SysNoticeAggregate;
import com.ssitao.code.modular.iam.domain.SysNotice;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 通知公告对象转换器
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysNoticeConverter {

    SysNoticeConverter INSTANCE = Mappers.getMapper(SysNoticeConverter.class);

    /**
     * 聚合根 -> DTO
     */
    SysNoticeDTO toDTO(SysNoticeAggregate aggregate);

    /**
     * 实体 -> DTO
     */
    SysNoticeDTO toDTO(SysNotice notice);

    /**
     * 创建命令 -> 聚合根
     */
    SysNoticeAggregate fromCommand(CreateNoticeCommand command);

    /**
     * 更新命令 -> 聚合根
     */
    void updateAggregate(UpdateNoticeCommand command, @MappingTarget SysNoticeAggregate aggregate);

    /**
     * 聚合根列表 -> DTO列表
     */
    List<SysNoticeDTO> toDTOList(List<SysNoticeAggregate> aggregates);

    /**
     * 实体列表 -> DTO列表
     */
    List<SysNoticeDTO> toDTOListFromNotice(List<SysNotice> notices);
}
