package com.ssitao.code.modular.iam.authorization.application.service;

import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;

import java.util.List;

/**
 * IAM账号角色关联服务接口
 * 管理 tb_iam_accountrole 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamAccountRoleService {

    /**
     * 为账号分配角色
     */
    void assignRoles(String accountId, List<String> roleIds, String tenantId);

    /**
     * 撤销账号角色
     */
    void revokeRoles(String accountId, List<String> roleIds, String tenantId);

    /**
     * 撤销账号所有角色
     */
    void revokeAllRoles(String accountId, String tenantId);

    /**
     * 获取账号的角色列表
     */
    List<IamRoleDTO> getAccountRoles(String accountId, String tenantId);

    /**
     * 获取角色下的账号列表
     */
    List<IamAccountDTO> getRoleAccounts(String roleId, String tenantId);

    /**
     * 检查账号是否有指定角色
     */
    boolean checkRole(String accountId, String roleId, String tenantId);

}
