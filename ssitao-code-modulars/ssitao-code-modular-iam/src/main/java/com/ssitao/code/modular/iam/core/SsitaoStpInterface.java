package com.ssitao.code.modular.iam.core;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.util.StrUtil;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamPermissionDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamAccountRoleDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamRolePermissionDO;
import com.ssitao.code.modular.iam.dal.mapper.IamPermissionMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamRoleMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamAccountRoleMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamRolePermissionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Sa-Token 权限验证接口实现
 *
 * @author ssitao-code
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SsitaoStpInterface implements StpInterface {

    private final IamAccountRoleMapper accountRoleMapper;
    private final IamRoleMapper roleMapper;
    private final IamRolePermissionMapper rolePermissionMapper;
    private final IamPermissionMapper permissionMapper;

    /**
     * 模拟用户权限数据（开发环境使用）
     */
    private static final Map<String, List<String>> MOCK_USER_PERMISSIONS = new HashMap<>();
    private static final Map<String, List<String>> MOCK_USER_ROLES = new HashMap<>();

    static {
        // admin用户权限
        MOCK_USER_PERMISSIONS.put("1", Arrays.asList("*:*:*"));
        MOCK_USER_ROLES.put("1", Arrays.asList("admin", "super-admin"));

        // user用户权限
        MOCK_USER_PERMISSIONS.put("2", Arrays.asList("user:view", "user:edit"));
        MOCK_USER_ROLES.put("2", Arrays.asList("user"));
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        try {
            String accountId = loginId.toString();

            // 首先尝试从模拟数据获取
            if (MOCK_USER_PERMISSIONS.containsKey(accountId)) {
                return MOCK_USER_PERMISSIONS.get(accountId);
            }

            // 查询账号角色
            List<IamAccountRoleDO> accountRoles = accountRoleMapper.selectListByQuery(
                    QueryWrapper.create()
                        .eq("accountrole_account_id", accountId)
                        .eq("sy_status", "1")
            );

            if (accountRoles == null || accountRoles.isEmpty()) {
                return new ArrayList<>();
            }

            List<String> roleIds = new ArrayList<>();
            for (IamAccountRoleDO accountRole : accountRoles) {
                if (accountRole.getAccountroleRoleId() != null) {
                    roleIds.add(accountRole.getAccountroleRoleId());
                }
            }

            // 查询角色权限
            List<IamRolePermissionDO> rolePerms = rolePermissionMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in("tb_iam_role_id", roleIds)
                            .eq("sy_status", "1")
            );

            if (rolePerms == null || rolePerms.isEmpty()) {
                return new ArrayList<>();
            }

            List<String> permIds = new ArrayList<>();
            for (IamRolePermissionDO rolePerm : rolePerms) {
                if (rolePerm.getTbIamPermId() != null) {
                    permIds.add(rolePerm.getTbIamPermId());
                }
            }

            // 去重
            permIds = new ArrayList<>(new HashSet<>(permIds));

            // 查询权限详情
            List<IamPermissionDO> perms = permissionMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in("tb_iam_perm_id", permIds)
                            .eq("sy_status", "1")
            );

            List<String> permissions = new ArrayList<>();
            if (perms != null) {
                for (IamPermissionDO perm : perms) {
                    if (StrUtil.isNotBlank(perm.getPermCode())) {
                        permissions.add(perm.getPermCode());
                    }
                }
            }
            return permissions;

        } catch (Exception e) {
            log.error("获取账号权限列表失败: loginId={}", loginId, e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        try {
            String accountId = loginId.toString();

            // 首先尝试从模拟数据获取
            if (MOCK_USER_ROLES.containsKey(accountId)) {
                return MOCK_USER_ROLES.get(accountId);
            }

            // 查询账号角色
            List<IamAccountRoleDO> accountRoles = accountRoleMapper.selectListByQuery(
                    QueryWrapper.create()
                            .eq("accountrole_account_id", accountId)
                            .eq("sy_status", "1")
            );

            if (accountRoles == null || accountRoles.isEmpty()) {
                return new ArrayList<>();
            }

            List<String> roleIds = new ArrayList<>();
            for (IamAccountRoleDO accountRole : accountRoles) {
                if (accountRole.getAccountroleRoleId() != null) {
                    roleIds.add(accountRole.getAccountroleRoleId());
                }
            }

            // 查询角色详情
            List<IamRoleDO> roles = roleMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in("tb_iam_role_id", roleIds)
                            .eq("sy_status", "1")
            );

            List<String> roleCodes = new ArrayList<>();
            if (roles != null) {
                for (IamRoleDO role : roles) {
                    if (StrUtil.isNotBlank(role.getRoleCode())) {
                        roleCodes.add(role.getRoleCode());
                    }
                }
            }
            return roleCodes;

        } catch (Exception e) {
            log.error("获取账号角色列表失败: loginId={}", loginId, e);
            return new ArrayList<>();
        }
    }
}
