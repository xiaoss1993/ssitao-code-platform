package com.ssitao.code.frame.satoken.core;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.frame.satoken.api.LoginUser;

/**
 * Sa-Token 安全工具类
 * <p>
 * 提供便捷的认证授权操作方法
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public class SecurityUtil {

    /**
     * 获取当前登录用户ID
     *
     * @return 用户ID
     */
    public static Long getUserId() {
        Object loginId = StpUtil.getLoginIdDefaultNull();
        if (loginId == null) {
            return null;
        }
        return Long.valueOf(loginId.toString());
    }

    /**
     * 获取当前登录用户ID（必须登录，否则抛出异常）
     *
     * @return 用户ID
     */
    public static Long getRequiredUserId() {
        return Long.valueOf(StpUtil.getLoginIdAsString());
    }

    /**
     * 获取当前登录用户
     *
     * @return 登录用户信息
     */
    public static LoginUser getLoginUser() {
        Object loginId = StpUtil.getLoginIdDefaultNull();
        if (loginId == null) {
            return null;
        }
        return (LoginUser) StpUtil.getTokenSessionByToken(StpUtil.getTokenValue()).get("loginUser");
    }

    /**
     * 获取当前登录用户（必须登录，否则抛出异常）
     *
     * @return 登录用户信息
     */
    public static LoginUser getRequiredLoginUser() {
        return (LoginUser) StpUtil.getTokenSession().get("loginUser");
    }

    /**
     * 判断当前用户是否已登录
     *
     * @return 是否已登录
     */
    public static boolean isLogin() {
        return StpUtil.isLogin();
    }

    /**
     * 检查当前用户是否已登录，未登录则抛出异常
     */
    public static void checkLogin() {
        StpUtil.checkLogin();
    }

    /**
     * 获取当前会话的Token值
     *
     * @return Token值
     */
    public static String getTokenValue() {
        return StpUtil.getTokenValue();
    }

    /**
     * 获取当前会话的Token信息
     *
     * @return Token信息
     */
    public static Object getTokenInfo() {
        return StpUtil.getTokenInfo();
    }

    /**
     * 注销当前会话
     */
    public static void logout() {
        StpUtil.logout();
    }

    /**
     * 注销指定用户的所有会话
     *
     * @param userId 用户ID
     */
    public static void logout(Long userId) {
        StpUtil.logout(userId);
    }

    /**
     * 检查当前用户是否拥有指定权限
     *
     * @param permission 权限码
     * @return 是否拥有权限
     */
    public static boolean hasPermission(String permission) {
        return StpUtil.hasPermission(permission);
    }

    /**
     * 检查当前用户是否拥有指定权限，没有则抛出异常
     *
     * @param permission 权限码
     */
    public static void checkPermission(String permission) {
        StpUtil.checkPermission(permission);
    }

    /**
     * 检查当前用户是否拥有指定角色
     *
     * @param role 角色码
     * @return 是否拥有角色
     */
    public static boolean hasRole(String role) {
        return StpUtil.hasRole(role);
    }

    /**
     * 检查当前用户是否拥有指定角色，没有则抛出异常
     *
     * @param role 角色码
     */
    public static void checkRole(String role) {
        StpUtil.checkRole(role);
    }

    /**
     * 判断当前用户是否为超管
     *
     * @return 是否为超管
     */
    public static boolean isSuperAdmin() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null && loginUser.getSuperAdmin();
    }
}
