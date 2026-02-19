package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.online.OnlineUserRespVO;

import java.util.List;

/**
 * 在线用户服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface OnlineUserService {

    /**
     * 获取在线用户列表
     *
     * @return 在线用户列表
     */
    List<OnlineUserRespVO> getOnlineUsers();

    /**
     * 强制用户登出
     *
     * @param userId 用户ID
     */
    void forceLogout(Long userId);

    /**
     * 强制当前登录用户登出
     *
     * @param token 令牌
     */
    void forceLogoutByToken(String token);

}
