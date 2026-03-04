package com.ssitao.code.modular.iam.audit.infrastructure.converter;

import com.ssitao.code.modular.iam.audit.api.dto.IamOperateLogDTO;
import com.ssitao.code.modular.iam.audit.dal.dataobject.IamOperateLogDO;
import com.ssitao.code.modular.iam.audit.domain.model.IamOperateLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM操作日志转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamOperateLogConverter {

    /**
     * DO转领域模型
     */
    @Mapping(source = "logId", target = "id")
    @Mapping(source = "moduleName", target = "operateModule")
    @Mapping(source = "businessType", target = "operateDesc")
    @Mapping(source = "executeDuration", target = "executeTime", qualifiedByName = "intToLong")
    @Mapping(source = "operatorName", target = "operatorAccount")
    @Mapping(source = "operateTime", target = "createTime")
    @Mapping(target = "operatorName", ignore = true)
    @Mapping(target = "browserType", ignore = true)
    @Mapping(target = "osType", ignore = true)
    IamOperateLog toDomain(IamOperateLogDO operateLogDO);

    /**
     * 领域模型转DO
     */
    @Mapping(source = "id", target = "logId")
    @Mapping(source = "operateModule", target = "moduleName")
    @Mapping(source = "operateDesc", target = "businessType")
    @Mapping(source = "executeTime", target = "executeDuration", qualifiedByName = "longToInt")
    @Mapping(source = "operatorAccount", target = "operatorName")
    @Mapping(source = "createTime", target = "operateTime")
    @Mapping(target = "businessId", ignore = true)
    @Mapping(target = "methodName", ignore = true)
    @Mapping(target = "operatorDept", ignore = true)
    IamOperateLogDO toDO(IamOperateLog operateLog);

    /**
     * 领域模型转DTO
     */
    IamOperateLogDTO toDTO(IamOperateLog operateLog);

    /**
     * DO转DTO
     */
    @Mapping(source = "logId", target = "id")
    @Mapping(source = "moduleName", target = "operateModule")
    @Mapping(source = "businessType", target = "operateDesc")
    @Mapping(source = "operatorName", target = "operatorAccount")
    @Mapping(source = "operateTime", target = "createTime")
    IamOperateLogDTO toDTO(IamOperateLogDO operateLogDO);

    /**
     * DO列表转领域模型列表
     */
    List<IamOperateLog> toDomainList(List<IamOperateLogDO> operateLogDOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamOperateLogDTO> toDTOList(List<IamOperateLog> operateLogList);

    @Named("intToLong")
    default Long intToLong(Integer value) {
        return value != null ? value.longValue() : null;
    }

    @Named("longToInt")
    default Integer longToInt(Long value) {
        return value != null ? value.intValue() : null;
    }
}
