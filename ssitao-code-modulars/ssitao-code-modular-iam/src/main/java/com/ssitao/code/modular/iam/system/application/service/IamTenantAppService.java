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
    String create(IamTenantCreateCommand command);

    /**
     * 更新租户
     *
     * @param command 更新命令
     */
    void update(IamTenantUpdateCommand command);

    /**
     * 删除租户
     *
     * @param id 租户ID
     */
    void delete(String id);

    /**
     * 根据ID获取租户
     *
     * @param id 租户ID
     * @return 租户DTO
     */
    IamTenantDTO getById(String id);

    /**
     * 根据租户编码获取租户
     *
     * @param tenantCode 租户编码
     * @return 租户DTO
     */
    IamTenantDTO getByTenantCode(String tenantCode);

    /**
     * 根据域名获取租户
     *
     * @param domain 域名
     * @return 租户DTO
     */
    IamTenantDTO getByDomain(String domain);

    /**
     * 获取租户列表
     *
     * @return 租户列表
     */
    List<IamTenantDTO> listAll();

    /**
     * 启用租户
     *
     * @param id 租户ID
     */
    void enable(String id);

    /**
     * 禁用租户
     *
     * @param id 租户ID
     */
    void disable(String id);

}
