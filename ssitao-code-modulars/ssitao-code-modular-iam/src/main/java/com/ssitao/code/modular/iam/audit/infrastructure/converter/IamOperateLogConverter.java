package com.ssitao.code.modular.iam.audit.infrastructure.converter;

import com.ssitao.code.modular.iam.audit.api.dto.IamOperateLogDTO;
import com.ssitao.code.modular.iam.audit.domain.model.IamOperateLog;
import com.ssitao.code.modular.iam.dal.dataobject.IamOperateLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

/**
 * IAM操作日志转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(componentModel = "spring")
public interface IamOperateLogConverter {

    /**
     * DO转领域模型
     *
     * @param operateLogDO DO对象
     * @return 领域模型
     */
    @Mappings({
            @Mapping(target = "id", source = "tbIamOperatelogId"),
            @Mapping(target = "operateModule", source = "operatelogModule"),
            @Mapping(target = "operateDesc", source = "operatelogDesc"),
            @Mapping(target = "operateType", source = "operatelogType"),
            @Mapping(target = "operateStatus", source = "operatelogStatus", qualifiedByName = "parseStatusForDomain"),
            @Mapping(target = "createTime", source = "syCreatetime", qualifiedByName = "parseLocalDateTime"),
            @Mapping(target = "tenantId", source = "syTenantId"),
            @Mapping(target = "operatorId", source = "operatorId"),
            @Mapping(target = "operatorAccount", source = "operatorAccount"),
            @Mapping(target = "operateIp", source = "operateIp"),
            @Mapping(target = "operateLocation", source = "operateLocation")
    })
    IamOperateLog toDomain(IamOperateLogDO operateLogDO);

    /**
     * 领域模型转DO
     *
     * @param operateLog 领域模型
     * @return DO对象
     */
    @Mappings({
            @Mapping(target = "tbIamOperatelogId", source = "id"),
            @Mapping(target = "operatelogModule", source = "operateModule"),
            @Mapping(target = "operatelogDesc", source = "operateDesc"),
            @Mapping(target = "operatelogType", source = "operateType"),
            @Mapping(target = "operatelogStatus", source = "operateStatus", qualifiedByName = "formatStatusForDO"),
            @Mapping(target = "syCreatetime", source = "createTime", qualifiedByName = "formatLocalDateTime"),
            @Mapping(target = "syTenantId", source = "tenantId"),
            @Mapping(target = "operatorId", source = "operatorId"),
            @Mapping(target = "operatorAccount", source = "operatorAccount"),
            @Mapping(target = "operateIp", source = "operateIp"),
            @Mapping(target = "operateLocation", source = "operateLocation")
    })
    IamOperateLogDO toDO(IamOperateLog operateLog);

    /**
     * 领域模型转DTO
     *
     * @param operateLog 领域模型
     * @return DTO对象
     */
    IamOperateLogDTO toDTO(IamOperateLog operateLog);

    /**
     * DO转DTO
     *
     * @param operateLogDO DO对象
     * @return DTO对象
     */
    @Mappings({
            @Mapping(target = "id", source = "tbIamOperatelogId"),
            @Mapping(target = "operateModule", source = "operatelogModule"),
            @Mapping(target = "operateDesc", source = "operatelogDesc"),
            @Mapping(target = "operateType", source = "operatelogType"),
            @Mapping(target = "operateStatus", source = "operatelogStatus", qualifiedByName = "parseStatusToText"),
            @Mapping(target = "createTime", source = "syCreatetime", qualifiedByName = "parseLocalDateTime"),
            @Mapping(target = "tenantId", source = "syTenantId"),
            @Mapping(target = "operatorId", source = "operatorId"),
            @Mapping(target = "operatorAccount", source = "operatorAccount"),
            @Mapping(target = "operateIp", source = "operateIp"),
            @Mapping(target = "operateLocation", source = "operateLocation")
    })
    IamOperateLogDTO toDTO(IamOperateLogDO operateLogDO);

    /**
     * DO列表转领域模型列表
     *
     * @param operateLogDOList DO列表
     * @return 领域模型列表
     */
    List<IamOperateLog> toDomainList(List<IamOperateLogDO> operateLogDOList);

    /**
     * 领域模型列表转DTO列表
     *
     * @param operateLogList 领域模型列表
     * @return DTO列表
     */
    List<IamOperateLogDTO> toDTOList(List<IamOperateLog> operateLogList);

    /**
     * 解析状态为领域模型状态
     */
    @Named("parseStatusForDomain")
    default String parseStatusForDomain(String status) {
        return "1".equals(status) ? "SUCCESS" : "FAIL";
    }

    /**
     * 格式化状态为DO状态
     */
    @Named("formatStatusForDO")
    default String formatStatusForDO(String status) {
        return "SUCCESS".equals(status) ? "1" : "0";
    }

    /**
     * 解析状态为文本
     */
    @Named("parseStatusToText")
    default String parseStatusToText(String status) {
        return "1".equals(status) ? "SUCCESS" : "FAIL";
    }

    /**
     * 解析LocalDateTime
     */
    @Named("parseLocalDateTime")
    default java.time.LocalDateTime parseLocalDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }
        try {
            return java.time.LocalDateTime.parse(dateTimeStr.replace(" ", "T"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 格式化LocalDateTime
     */
    @Named("formatLocalDateTime")
    default String formatLocalDateTime(java.time.LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toString().replace("T", " ");
    }
}
