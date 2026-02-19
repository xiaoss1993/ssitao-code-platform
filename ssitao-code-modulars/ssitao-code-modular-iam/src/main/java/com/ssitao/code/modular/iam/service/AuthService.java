package com.ssitao.code.modular.iam.service;

import com.ssitao.code.frame.satoken.api.LoginUser;

/**
 * 认证服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface AuthService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return Token
     */
    String login(String username, String password);

    /**
     * 用户登出
     */
    void logout();

    /**
     * 获取当前登录用户信息
     *
     * @return 登录用户信息
     */
    LoginUser getLoginUser();

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void modifyPassword(String oldPassword, String newPassword);

    /**
     * 修改个人信息
     *
     * @param nickname 昵称
     * @param avatar   头像
     * @param email    邮箱
     * @param mobile   手机号
     */
    void modifyProfile(String nickname, String avatar, String email, String mobile);

    /**
     * 刷新Token
     *
     * @param refreshToken 刷新令牌
     * @return 新的访问令牌
     */
    String refreshToken(String refreshToken);

    /**
     * 重置密码
     *
     * @param username    用户名
     * @param newPassword 新密码
     * @param verifyCode  验证码
     */
    void resetPassword(String username, String newPassword, String verifyCode);

}
