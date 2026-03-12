package com.ssitao.code.modular.meta.infrastructure.converter;

import com.ssitao.code.modular.meta.api.dto.MetaListDTO;
import com.ssitao.code.modular.meta.api.dto.MetaListColumnDTO;
import com.ssitao.code.modular.meta.api.dto.MetaListButtonDTO;
import com.ssitao.code.modular.meta.api.dto.MetaListQueryDTO;
import com.ssitao.code.modular.meta.application.command.MetaListCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaListColumnCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaListButtonCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaListQueryCreateCommand;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListColumnDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListButtonDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListQueryDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 元数据列表配置转换器
 *
 * @author ssitao-code
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MetaListConverter {

    MetaListConverter INSTANCE = Mappers.getMapper(MetaListConverter.class);

    // ==================== MetaList 转换 ====================

    /**
     * DO转DTO
     */
    MetaListDTO toDTO(MetaListDO metaListDO);

    /**
     * DO列表转DTO列表
     */
    List<MetaListDTO> toDTOList(List<MetaListDO> metaListDOS);

    /**
     * 创建命令转DO
     */
    MetaListDO toDO(MetaListCreateCommand command);

    // ==================== MetaListColumn 转换 ====================

    /**
     * ColumnDO转ColumnDTO
     */
    MetaListColumnDTO toColumnDTO(MetaListColumnDO metaListColumnDO);

    /**
     * ColumnDO列表转ColumnDTO列表
     */
    List<MetaListColumnDTO> toColumnDTOList(List<MetaListColumnDO> metaListColumnDOS);

    /**
     * Column创建命令转ColumnDO
     */
    MetaListColumnDO toColumnDO(MetaListColumnCreateCommand command);

    // ==================== MetaListButton 转换 ====================

    /**
     * ButtonDO转ButtonDTO
     */
    MetaListButtonDTO toButtonDTO(MetaListButtonDO metaListButtonDO);

    /**
     * ButtonDO列表转ButtonDTO列表
     */
    List<MetaListButtonDTO> toButtonDTOList(List<MetaListButtonDO> metaListButtonDOS);

    /**
     * Button创建命令转ButtonDO
     */
    MetaListButtonDO toButtonDO(MetaListButtonCreateCommand command);

    // ==================== MetaListQuery 转换 ====================

    /**
     * QueryDO转QueryDTO
     */
    MetaListQueryDTO toQueryDTO(MetaListQueryDO metaListQueryDO);

    /**
     * QueryDO列表转QueryDTO列表
     */
    List<MetaListQueryDTO> toQueryDTOList(List<MetaListQueryDO> metaListQueryDOS);

    /**
     * Query创建命令转QueryDO
     */
    MetaListQueryDO toQueryDO(MetaListQueryCreateCommand command);
}
