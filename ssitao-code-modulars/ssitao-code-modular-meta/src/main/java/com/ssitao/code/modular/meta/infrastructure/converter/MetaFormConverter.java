package com.ssitao.code.modular.meta.infrastructure.converter;

import com.ssitao.code.modular.meta.api.dto.MetaFormDTO;
import com.ssitao.code.modular.meta.api.dto.MetaFormFieldDTO;
import com.ssitao.code.modular.meta.application.command.MetaFormCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaFormFieldCreateCommand;
import com.ssitao.code.modular.meta.dal.dataobject.MetaFormDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaFormFieldDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 元数据表单配置转换器
 *
 * @author ssitao-code
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MetaFormConverter {

    MetaFormConverter INSTANCE = Mappers.getMapper(MetaFormConverter.class);

    // ==================== MetaForm 转换 ====================

    /**
     * DO转DTO
     */
    MetaFormDTO toDTO(MetaFormDO metaFormDO);

    /**
     * DO列表转DTO列表
     */
    List<MetaFormDTO> toDTOList(List<MetaFormDO> metaFormDOS);

    /**
     * 创建命令转DO
     */
    MetaFormDO toDO(MetaFormCreateCommand command);

    // ==================== MetaFormField 转换 ====================

    /**
     * FieldDO转FieldDTO
     */
    MetaFormFieldDTO toFieldDTO(MetaFormFieldDO metaFormFieldDO);

    /**
     * FieldDO列表转FieldDTO列表
     */
    List<MetaFormFieldDTO> toFieldDTOList(List<MetaFormFieldDO> metaFormFieldDOS);

    /**
     * Field创建命令转FieldDO
     */
    MetaFormFieldDO toFieldDO(MetaFormFieldCreateCommand command);
}
