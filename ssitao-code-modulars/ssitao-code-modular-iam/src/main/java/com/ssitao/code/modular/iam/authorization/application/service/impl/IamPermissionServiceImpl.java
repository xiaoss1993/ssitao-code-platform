package com.ssitao.code.modular.iam.authorization.application.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.authorization.application.service.IamPermissionService;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamAccountRoleDO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermissionDO;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamAccountRoleMapper;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamPermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * IAM权限验证服务实现
 * 提供菜单权限、按钮权限、API权限的验证功能
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamPermissionServiceImpl implements IamPermissionService {

    @Resource
    private IamPermissionMapper permissionMapper;

    @Resource
    private IamAccountRoleMapper accountRoleMapper;

    /**
     * 权限类型-菜单
     */
    private static final String PERM_TYPE_MENU = "MENU";

    /**
     * 权限类型-按钮
     */
    private static final String PERM_TYPE_BUTTON = "BUTTON";

    /**
     * 权限类型-API
     */
    private static final String PERM_TYPE_API = "API";

    /**
     * 状态-启用
     */
    private static final Integer STATUS_ENABLED = 1;

    /**
     * 未删除
     */
    private static final Integer NOT_DELETED = 0;

    @Override
    public boolean hasPermission(String permission) {
        if (StrUtil.isBlank(permission)) {
            return false;
        }
        // 超级管理员拥有所有权限
        if (SecurityUtil.isSuperAdmin()) {
            return true;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null) {
            return false;
        }
        Set<String> permissions = loginUser.getPermissions();
        if (CollectionUtils.isEmpty(permissions)) {
            return false;
        }
        return permissions.contains(permission);
    }

    @Override
    public boolean hasAnyPermission(String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return false;
        }
        // 超级管理员拥有所有权限
        if (SecurityUtil.isSuperAdmin()) {
            return true;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null) {
            return false;
        }
        Set<String> userPermissions = loginUser.getPermissions();
        if (CollectionUtils.isEmpty(userPermissions)) {
            return false;
        }
        for (String permission : permissions) {
            if (userPermissions.contains(permission)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAllPermissions(String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return false;
        }
        // 超级管理员拥有所有权限
        if (SecurityUtil.isSuperAdmin()) {
            return true;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null) {
            return false;
        }
        Set<String> userPermissions = loginUser.getPermissions();
        if (CollectionUtils.isEmpty(userPermissions)) {
            return false;
        }
        for (String permission : permissions) {
            if (!userPermissions.contains(permission)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getUserPermissions() {
        // 超级管理员拥有所有权限
        if (SecurityUtil.isSuperAdmin()) {
            return getAllPermissions();
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null) {
            return new ArrayList<>();
        }
        Set<String> permissions = loginUser.getPermissions();
        return permissions == null ? new ArrayList<>() : new ArrayList<>(permissions);
    }

    @Override
    public List<String> getUserPermissions(String userId) {
        if (StrUtil.isBlank(userId)) {
            return new ArrayList<>();
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        // 如果是查询当前用户，直接从上下文获取
        if (loginUser != null && userId.equals(loginUser.getId())) {
            return getUserPermissions();
        }
        // 根据用户ID查询权限
        return getPermissionsByUserId(userId);
    }

    @Override
    public boolean hasMenuPermission(String path) {
        if (StrUtil.isBlank(path)) {
            return false;
        }
        // 超级管理员拥有所有权限
        if (SecurityUtil.isSuperAdmin()) {
            return true;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null) {
            return false;
        }
        String tenantId = loginUser.getTenantId();
        if (StrUtil.isBlank(tenantId)) {
            return false;
        }
        // 查询菜单权限
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("permission_type", PERM_TYPE_MENU)
                .eq("permission_resource", path)
                .eq("tenant_id", tenantId)
                .eq("permission_status", STATUS_ENABLED)
                .eq("is_deleted", NOT_DELETED);
        List<IamPermissionDO> menuPermissions = permissionMapper.selectListByQuery(queryWrapper);
        if (CollectionUtils.isEmpty(menuPermissions)) {
            return false;
        }
        // 获取用户权限编码
        Set<String> userPermissions = loginUser.getPermissions();
        if (CollectionUtils.isEmpty(userPermissions)) {
            return false;
        }
        // 检查用户是否拥有该菜单权限
        List<String> permCodes = menuPermissions.stream()
                .map(IamPermissionDO::getPermissionCode)
                .collect(Collectors.toList());
        for (String permCode : permCodes) {
            if (userPermissions.contains(permCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasButtonPermission(String buttonCode) {
        if (StrUtil.isBlank(buttonCode)) {
            return false;
        }
        // 超级管理员拥有所有权限
        if (SecurityUtil.isSuperAdmin()) {
            return true;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null) {
            return false;
        }
        String tenantId = loginUser.getTenantId();
        if (StrUtil.isBlank(tenantId)) {
            return false;
        }
        // 查询按钮权限
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("permission_type", PERM_TYPE_BUTTON)
                .eq("permission_action", buttonCode)
                .eq("tenant_id", tenantId)
                .eq("permission_status", STATUS_ENABLED)
                .eq("is_deleted", NOT_DELETED);
        List<IamPermissionDO> buttonPermissions = permissionMapper.selectListByQuery(queryWrapper);
        if (CollectionUtils.isEmpty(buttonPermissions)) {
            return false;
        }
        // 获取用户权限编码
        Set<String> userPermissions = loginUser.getPermissions();
        if (CollectionUtils.isEmpty(userPermissions)) {
            return false;
        }
        // 检查用户是否拥有该按钮权限
        List<String> permCodes = buttonPermissions.stream()
                .map(IamPermissionDO::getPermissionCode)
                .collect(Collectors.toList());
        for (String permCode : permCodes) {
            if (userPermissions.contains(permCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasApiPermission(String apiPath, String method) {
        if (StrUtil.isBlank(apiPath) || StrUtil.isBlank(method)) {
            return false;
        }
        // 超级管理员拥有所有权限
        if (SecurityUtil.isSuperAdmin()) {
            return true;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null) {
            return false;
        }
        String tenantId = loginUser.getTenantId();
        if (StrUtil.isBlank(tenantId)) {
            return false;
        }
        // 查询API权限
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("permission_type", PERM_TYPE_API)
                .eq("permission_resource", apiPath)
                .eq("permission_action", method.toUpperCase())
                .eq("tenant_id", tenantId)
                .eq("permission_status", STATUS_ENABLED)
                .eq("is_deleted", NOT_DELETED);
        List<IamPermissionDO> apiPermissions = permissionMapper.selectListByQuery(queryWrapper);
        if (CollectionUtils.isEmpty(apiPermissions)) {
            return false;
        }
        // 获取用户权限编码
        Set<String> userPermissions = loginUser.getPermissions();
        if (CollectionUtils.isEmpty(userPermissions)) {
            return false;
        }
        // 检查用户是否拥有该API权限
        List<String> permCodes = apiPermissions.stream()
                .map(IamPermissionDO::getPermissionCode)
                .collect(Collectors.toList());
        for (String permCode : permCodes) {
            if (userPermissions.contains(permCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取所有权限（超级管理员使用）
     *
     * @return 所有权限编码列表
     */
    private List<String> getAllPermissions() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("permission_status", STATUS_ENABLED)
                .eq("is_deleted", NOT_DELETED);
        List<IamPermissionDO> allPermissions = permissionMapper.selectListByQuery(queryWrapper);
        if (CollectionUtils.isEmpty(allPermissions)) {
            return new ArrayList<>();
        }
        return allPermissions.stream()
                .map(IamPermissionDO::getPermissionCode)
                .collect(Collectors.toList());
    }

    /**
     * 根据用户ID获取权限列表
     *
     * @param userId 用户ID
     * @return 权限编码列表
     */
    private List<String> getPermissionsByUserId(String userId) {
        // 查询用户角色
        QueryWrapper roleQueryWrapper = QueryWrapper.create()
                .eq("account_id", userId)
                .eq("is_valid", STATUS_ENABLED)
                .eq("is_deleted", NOT_DELETED);
        List<IamAccountRoleDO> accountRoles = accountRoleMapper.selectListByQuery(roleQueryWrapper);
        if (CollectionUtils.isEmpty(accountRoles)) {
            return new ArrayList<>();
        }
        List<String> roleIds = accountRoles.stream()
                .map(IamAccountRoleDO::getRoleId)
                .collect(Collectors.toList());
        // 查询角色对应的权限
        QueryWrapper permQueryWrapper = QueryWrapper.create()
                .in("role_id", roleIds)
                .eq("is_valid", STATUS_ENABLED)
                .eq("is_deleted", NOT_DELETED);
        // 需要通过角色权限关联表查询，这里简化处理，直接返回空列表
        // 实际应该查询 iam_role_permission 表
        return new ArrayList<>();
    }

}
