package com.ssitao.code.modular.iam.core;

import cn.dev33.satoken.stp.StpInterface;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.PermissionDO;
import com.ssitao.code.modular.iam.dal.dataobject.RoleDO;
import com.ssitao.code.modular.iam.dal.dataobject.RolePermissionDO;
import com.ssitao.code.modular.iam.dal.dataobject.UserRoleDO;
import com.ssitao.code.modular.iam.dal.mapper.PermissionMapper;
import com.ssitao.code.modular.iam.dal.mapper.RoleMapper;
import com.ssitao.code.modular.iam.dal.mapper.RolePermissionMapper;
import com.ssitao.code.modular.iam.dal.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sa-Token 权限验证接口实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SsitaoStpInterface implements StpInterface {

    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;
    private final RolePermissionMapper rolePermissionMapper;
    private final PermissionMapper permissionMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        try {
            Long userId = Long.parseLong(loginId.toString());

            // 查询用户角色
            List<UserRoleDO> userRoles = userRoleMapper.selectListByQuery(
                    QueryWrapper.create()
                            .eq("user_id", userId)
            );

            if (userRoles.isEmpty()) {
                return new ArrayList<>();
            }

            List<Long> roleIds = userRoles.stream()
                    .map(UserRoleDO::getRoleId)
                    .collect(Collectors.toList());

            // 查询角色权限
            List<RolePermissionDO> rolePermissions = rolePermissionMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in("role_id", roleIds.toArray(new Long[0]))
            );

            if (rolePermissions.isEmpty()) {
                return new ArrayList<>();
            }

            List<Long> permissionIds = rolePermissions.stream()
                    .map(RolePermissionDO::getPermissionId)
                    .distinct()
                    .collect(Collectors.toList());

            // 查询权限详情
            List<PermissionDO> permissions = permissionMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in("id", permissionIds.toArray(new Long[0]))
                            .eq("status", 1)
                            .eq("deleted", 0)
            );

            return permissions.stream()
                    .map(PermissionDO::getCode)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("获取用户权限列表失败: loginId={}", loginId, e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        try {
            Long userId = Long.parseLong(loginId.toString());

            // 查询用户角色
            List<UserRoleDO> userRoles = userRoleMapper.selectListByQuery(
                    QueryWrapper.create()
                            .eq("user_id", userId)
            );

            if (userRoles.isEmpty()) {
                return new ArrayList<>();
            }

            List<Long> roleIds = userRoles.stream()
                    .map(UserRoleDO::getRoleId)
                    .collect(Collectors.toList());

            // 查询角色详情
            List<RoleDO> roles = roleMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in("id", roleIds.toArray(new Long[0]))
                            .eq("status", 1)
                            .eq("deleted", 0)
            );

            return roles.stream()
                    .map(RoleDO::getCode)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("获取用户角色列表失败: loginId={}", loginId, e);
            return new ArrayList<>();
        }
    }
}
