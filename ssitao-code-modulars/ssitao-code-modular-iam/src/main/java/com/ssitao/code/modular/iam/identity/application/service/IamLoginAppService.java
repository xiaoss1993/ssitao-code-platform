package com.ssitao.code.modular.iam.identity.application.service;

import com.ssitao.code.modular.iam.identity.api.dto.IamLoginLogDTO;
import com.ssitao.code.modular.iam.identity.api.dto.IamLoginResultDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamLoginCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamLogoutCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamRefreshTokenCommand;
import com.ssitao.code.modular.iam.identity.application.query.IamLoginLogQuery;

import java.util.List;

/**
 * IAM登录应用服务接口
 * 负责登录、登出、令牌管理
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamLoginAppService {

    // ==================== 登录/登出 ====================

    /**
     * 登录
     */
    IamLoginResultDTO login(IamLoginCommand command);

    /**
     * 登出
     */
    void logout(String token, IamLogoutCommand command);

    /**
     * 刷新令牌
     */
    IamLoginResultDTO refreshToken(IamRefreshTokenCommand command);

    /**
     * 验证令牌
     */
    Boolean verifyToken(String token);

    // ==================== 当前用户信息 ====================

    /**
     * 获取当前登录用户信息
     */
    Object getCurrentUser(String token);

    /**
     * 获取当前用户权限列表
     */
    Object getCurrentUserPermissions(String token);

    /**
     * 获取当前用户角色列表
     */
    Object getCurrentUserRoles(String token);

    // ==================== 登录日志 ====================

    /**
     * 查询登录日志
     */
    List<IamLoginLogDTO> queryLoginLogs(IamLoginLogQuery query);

    /**
     * 获取登录日志详情
     */
    IamLoginLogDTO getLoginLog(String id);

}
