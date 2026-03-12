package com.ssitao.code.modular.iam.authorization.application.service;

import java.util.List;

/**
 * IAM权限验证服务接口
 * 提供菜单权限、按钮权限、API权限的验证功能
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamPermissionService {

    /**
     * 验证当前用户是否拥有指定权限
     *
     * @param permission 权限编码
     * @return true-拥有权限，false-无权限
     */
    boolean hasPermission(String permission);

    /**
     * 验证当前用户是否拥有任意一个指定权限
     *
     * @param permissions 权限编码数组
     * @return true-拥有任意一个权限，false-无权限
     */
    boolean hasAnyPermission(String... permissions);

    /**
     * 验证当前用户是否拥有所有指定权限
     *
     * @param permissions 权限编码数组
     * @return true-拥有所有权限，false-缺少权限
     */
    boolean hasAllPermissions(String... permissions);

    /**
     * 获取当前用户所有权限列表
     *
     * @return 权限编码列表
     */
    List<String> getUserPermissions();

    /**
     * 获取指定用户的所有权限列表
     *
     * @param userId 用户ID
     * @return 权限编码列表
     */
    List<String> getUserPermissions(String userId);

    /**
     * 验证当前用户是否有菜单权限
     *
     * @param path 菜单路径
     * @return true-有权限，false-无权限
     */
    boolean hasMenuPermission(String path);

    /**
     * 验证当前用户是否有按钮权限
     *
     * @param buttonCode 按钮编码
     * @return true-有权限，false-无权限
     */
    boolean hasButtonPermission(String buttonCode);

    /**
     * 验证当前用户是否有API权限
     *
     * @param apiPath API路径
     * @param method  请求方法
     * @return true-有权限，false-无权限
     */
    boolean hasApiPermission(String apiPath, String method);

}
