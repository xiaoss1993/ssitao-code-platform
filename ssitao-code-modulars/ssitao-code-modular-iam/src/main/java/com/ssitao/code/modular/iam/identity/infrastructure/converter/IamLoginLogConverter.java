package com.ssitao.code.modular.iam.identity.infrastructure.converter;

import com.ssitao.code.modular.iam.identity.dal.dataobject.IamLoginLogDO;
import com.ssitao.code.modular.iam.identity.domain.model.IamLoginLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * IAM登录日志转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamLoginLogConverter {

    /**
     * DO转领域模型
     *
     * @param loginLogDO DO对象
     * @return 领域模型
     */
    @Mapping(target = "id", source = "logId")
    @Mapping(target = "username", source = "accountName")
    @Mapping(target = "accountCode", source = "accountCode")
    @Mapping(target = "loginType", source = "loginType")
    @Mapping(target = "loginIp", source = "loginIp")
    @Mapping(target = "loginLocation", source = "loginLocation")
    @Mapping(target = "userAgent", source = "loginDevice")
    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "loginTime", source = "loginTime")
    @Mapping(target = "tenantId", source = "tenantId")
    @Mapping(target = "loginStatus", source = "loginStatus")
    @Mapping(target = "errorMessage", source = "loginFailReason")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "deviceInfo", ignore = true)
    IamLoginLog toDomain(IamLoginLogDO loginLogDO);

    /**
     * 领域模型转DO
     *
     * @param loginLog 领域模型
     * @return DO对象
     */
    @Mapping(target = "logId", source = "id")
    @Mapping(target = "accountName", source = "username")
    @Mapping(target = "accountCode", source = "accountCode")
    @Mapping(target = "loginType", source = "loginType")
    @Mapping(target = "loginIp", source = "loginIp")
    @Mapping(target = "loginLocation", source = "loginLocation")
    @Mapping(target = "loginDevice", source = "userAgent")
    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "loginTime", source = "loginTime")
    @Mapping(target = "tenantId", source = "tenantId")
    @Mapping(target = "loginStatus", source = "loginStatus")
    @Mapping(target = "loginFailReason", source = "errorMessage")
    @Mapping(target = "loginBrowser", ignore = true)
    @Mapping(target = "loginOs", ignore = true)
    @Mapping(target = "logoutTime", ignore = true)
    @Mapping(target = "onlineDuration", ignore = true)
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
}
