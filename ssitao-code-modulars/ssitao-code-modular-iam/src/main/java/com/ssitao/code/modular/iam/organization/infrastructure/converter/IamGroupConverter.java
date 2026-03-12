package com.ssitao.code.modular.iam.organization.infrastructure.converter;

import com.ssitao.code.modular.iam.organization.api.dto.IamGroupDTO;
import com.ssitao.code.modular.iam.organization.domain.model.IamGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * IAM集团对象转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper
public interface IamGroupConverter {

    IamGroupConverter INSTANCE = Mappers.getMapper(IamGroupConverter.class);

    /**
     * 集团DO转DTO
     *
     * @param group 集团
     * @return DTO
     */
    IamGroupDTO toDTO(IamGroup group);

    /**
     * 集团DTO转DO
     *
     * @param dto DTO
     * @return 集团
     */
    IamGroup toDomain(IamGroupDTO dto);

    /**
     * 集团列表转DTO列表
     *
     * @param groups 集团列表
     * @return DTO列表
     */
    List<IamGroupDTO> toDTOList(List<IamGroup> groups);
}
