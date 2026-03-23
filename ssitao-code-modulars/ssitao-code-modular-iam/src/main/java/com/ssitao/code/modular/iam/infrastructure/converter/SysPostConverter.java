package com.ssitao.code.modular.iam.infrastructure.converter;

import com.ssitao.code.modular.iam.application.dto.SysPostDTO;
import com.ssitao.code.modular.iam.application.command.CreatePostCommand;
import com.ssitao.code.modular.iam.application.command.UpdatePostCommand;
import com.ssitao.code.modular.iam.domain.model.SysPostAggregate;
import com.ssitao.code.modular.iam.domain.SysPost;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 岗位对象转换器
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysPostConverter {

    SysPostConverter INSTANCE = Mappers.getMapper(SysPostConverter.class);

    /**
     * 聚合根 -> DTO
     */
    SysPostDTO toDTO(SysPostAggregate aggregate);

    /**
     * 实体 -> DTO
     */
    SysPostDTO toDTO(SysPost post);

    /**
     * DTO -> 聚合根
     */
    SysPostAggregate toAggregate(SysPostDTO dto);

    /**
     * 创建命令 -> 聚合根
     */
    SysPostAggregate fromCommand(CreatePostCommand command);

    /**
     * 更新命令 -> 聚合根
     */
    void updateAggregate(UpdatePostCommand command, @MappingTarget SysPostAggregate aggregate);

    /**
     * 聚合根列表 -> DTO列表
     */
    List<SysPostDTO> toDTOList(List<SysPostAggregate> aggregates);

    /**
     * 实体列表 -> DTO列表
     */
    List<SysPostDTO> toDTOListFromPost(List<SysPost> posts);
}
