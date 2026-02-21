package com.ssitao.code.modular.iam.identity.infrastructure.converter;

import com.ssitao.code.modular.iam.dal.dataobject.IamLoginLogDO;
import com.ssitao.code.modular.iam.identity.domain.model.IamLoginLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * IAM登录日志转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(componentModel = "spring")
public interface IamLoginLogConverter {

    /**
     * DO转领域模型
     *
     * @param loginLogDO DO对象
     * @return 领域模型
     */
    @Mappings({
            @Mapping(target = "id", source = "tbIamLoginlogId"),
            @Mapping(target = "username", source = "loginlogAccountName"),
            @Mapping(target = "accountCode", source = "loginlogAccountCode"),
            @Mapping(target = "loginType", source = "loginlogTypeCode"),
            @Mapping(target = "deviceInfo", source = "loginlogDevice"),
            @Mapping(target = "accountId", source = "syAccountId"),
            @Mapping(target = "loginTime", expression = "java(parseLocalDateTime(loginLogDO.getSyCreatetime()))"),
            @Mapping(target = "tenantId", source = "syTenantId"),
            @Mapping(target = "userId", ignore = true),
            @Mapping(target = "loginIp", ignore = true),
            @Mapping(target = "loginLocation", ignore = true),
            @Mapping(target = "userAgent", ignore = true),
            @Mapping(target = "loginStatus", ignore = true),
            @Mapping(target = "errorMessage", ignore = true)
    })
    IamLoginLog toDomain(IamLoginLogDO loginLogDO);

    /**
     * 领域模型转DO
     *
     * @param loginLog 领域模型
     * @return DO对象
     */
    @Mappings({
            @Mapping(target = "tbIamLoginlogId", source = "id"),
            @Mapping(target = "loginlogAccountName", source = "username"),
            @Mapping(target = "loginlogAccountCode", source = "accountCode"),
            @Mapping(target = "loginlogTypeCode", source = "loginType"),
            @Mapping(target = "loginlogDevice", source = "deviceInfo"),
            @Mapping(target = "syAccountId", source = "accountId"),
            @Mapping(target = "syCreatetime", expression = "java(formatLocalDateTime(loginLog.getLoginTime()))"),
            @Mapping(target = "syTenantId", source = "tenantId"),
            @Mapping(target = "loginlogBzxx", source = "errorMessage")
    })
    IamLoginLogDO toDO(IamLoginLog loginLog);

    /**
     * DO列表转领域模型列表
     *
     * @param loginLogDOList DO列表
     * @return 领域模型列表
     */
    List<IamLoginLog> toDomainList(List<IamLoginLogDO> loginLogDOList);

    /**
     * 领域模型列表转DO列表
     *
     * @param loginLogList 领域模型列表
     * @return DO列表
     */
    List<IamLoginLogDO> toDOList(List<IamLoginLog> loginLogList);

    /**
     * 解析LocalDateTime
     */
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
    default String formatLocalDateTime(java.time.LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toString().replace("T", " ");
    }
}
