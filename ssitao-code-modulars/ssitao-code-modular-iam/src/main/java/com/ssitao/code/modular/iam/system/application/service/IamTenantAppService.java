package com.ssitao.code.modular.iam.system.application.service;

import com.ssitao.code.modular.iam.system.application.command.IamTenantCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamTenantUpdateCommand;
import com.ssitao.code.modular.iam.system.api.dto.IamTenantDTO;

import java.util.List;

/**
 * IAM租户应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamTenantAppService {

    /**
     * 创建租户
     *
     * @param command 创建命令
     * @return 租户ID
     */
    Long createTenant(IamTenantCreateCommand command);

    /**
     * 更新租户
     *
     * @param command 更新命令
     */
    void updateTenant(IamTenantUpdateCommand command);

    /**
     * 删除租户
     *
     * @param id 租户ID
     */
    void deleteTenant(Long id);

    /**
     * 根据ID获取租户
     *
     * @param id 租户ID
     * @return 租户DTO
     */
    IamTenantDTO getTenantById(Long id);

    /**
     * 根据租户编码获取租户
     *
     * @param tenantCode 租户编码
     * @return 租户DTO
     */
    IamTenantDTO getTenantByCode(String tenantCode);

    /**
     * 根据域名获取租户
     *
     * @param domain 域名
     * @return 租户DTO
     */
    IamTenantDTO getTenantByDomain(String domain);

    /**
     * 获取租户列表
     *
     * @return 租户列表
     */
    List<IamTenantDTO> listTenants();

    /**
     * 启用租户
     *
     * @param id 租户ID
     */
    void enableTenant(Long id);

    /**
     * 禁用租户
     *
     * @param id 租户ID
     */
    void disableTenant(Long id);

}
