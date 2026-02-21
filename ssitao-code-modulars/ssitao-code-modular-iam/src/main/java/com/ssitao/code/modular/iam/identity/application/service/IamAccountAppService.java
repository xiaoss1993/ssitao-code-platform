package com.ssitao.code.modular.iam.identity.application.service;

import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountCreateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountUpdateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamPasswordChangeCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamPasswordResetCommand;
import com.ssitao.code.modular.iam.identity.application.query.IamAccountQuery;

import java.util.List;

/**
 * IAM账号应用服务接口
 * 基于 tb_iam_account 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamAccountAppService {

    // ==================== CRUD 操作 ====================

    /**
     * 创建账号
     */
    String createAccount(IamAccountCreateCommand command);

    /**
     * 更新账号
     */
    void updateAccount(IamAccountUpdateCommand command);

    /**
     * 删除账号
     */
    void deleteAccount(String id, String tenantId);

    /**
     * 获取账号详情
     */
    IamAccountDTO getAccount(String id, String tenantId);

    /**
     * 根据账号编码获取账号
     */
    IamAccountDTO getAccountByCode(String accountCode, String tenantId);

    /**
     * 查询账号列表
     */
    List<IamAccountDTO> listAccounts(IamAccountQuery query);

    /**
     * 分页查询账号
     */
    List<IamAccountDTO> pageAccounts(IamAccountQuery query, int page, int size);

    // ==================== 状态管理 ====================

    /**
     * 锁定账号
     */
    void lockAccount(String id, Integer expireDays, String tenantId);

    /**
     * 解锁账号
     */
    void unlockAccount(String id, String tenantId);

    /**
     * 禁用账号
     */
    void disableAccount(String id, String tenantId);

    /**
     * 启用账号
     */
    void enableAccount(String id, String tenantId);

    // ==================== 密码管理 ====================

    /**
     * 修改密码
     */
    void changePassword(IamPasswordChangeCommand command);

    /**
     * 重置密码
     */
    void resetPassword(IamPasswordResetCommand command);

    /**
     * 根据ID重置密码
     */
    void resetPasswordById(String id, String newPassword, String tenantId);

    // ==================== 用户关联 ====================

    /**
     * 绑定用户档案
     */
    void bindUser(String accountId, String userId, String tenantId);

    /**
     * 解绑用户档案
     */
    void unbindUser(String accountId, String tenantId);

    /**
     * 获取绑定的用户档案
     */
    Object getBoundUser(String accountId, String tenantId);

}
