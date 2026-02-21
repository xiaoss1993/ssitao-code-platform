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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        try {
            String accountId = loginId.toString();

            // 查询账号角色
            List<IamAccountRoleDO> accountRoles = accountRoleMapper.selectListByQuery(
                    QueryWrapper.create()
                        .eq(IamAccountRoleDO::getAccountroleAccountId, accountId)
                        .eq(IamAccountRoleDO::getSyStatus, "1")
            );

            if (accountRoles.isEmpty()) {
                return new ArrayList<>();
            }

            List<String> roleIds = accountRoles.stream()
                    .map(IamAccountRoleDO::getAccountroleRoleId)
                    .collect(Collectors.toList());

            // 查询角色权限
            List<IamRolePermissionDO> rolePerms = rolePermissionMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in(IamRolePermissionDO::getTbIamRoleId, roleIds)
                            .eq(IamRolePermissionDO::getSyStatus, "1")
            );

            if (rolePerms.isEmpty()) {
                return new ArrayList<>();
            }

            List<String> permIds = rolePerms.stream()
                    .map(IamRolePermissionDO::getTbIamPermId)
                    .distinct()
                    .collect(Collectors.toList());

            // 查询权限详情
            List<IamPermissionDO> perms = permissionMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in(IamPermissionDO::getTbIamPermId, permIds)
                            .eq(IamPermissionDO::getSyStatus, "1")
            );

            return perms.stream()
                    .map(IamPermissionDO::getPermCode)
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("获取账号权限列表失败: loginId={}", loginId, e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        try {
            String accountId = loginId.toString();

            // 查询账号角色
            List<IamAccountRoleDO> accountRoles = accountRoleMapper.selectListByQuery(
                    QueryWrapper.create()
                            .eq(IamAccountRoleDO::getAccountroleAccountId, accountId)
                            .eq(IamAccountRoleDO::getSyStatus, "1")
            );

            if (accountRoles.isEmpty()) {
                return new ArrayList<>();
            }

            List<String> roleIds = accountRoles.stream()
                    .map(IamAccountRoleDO::getAccountroleRoleId)
                    .collect(Collectors.toList());

            // 查询角色详情
            List<IamRoleDO> roles = roleMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in(IamRoleDO::getTbIamRoleId, roleIds)
                            .eq(IamRoleDO::getSyStatus, "1")
            );

            return roles.stream()
                    .map(IamRoleDO::getRoleCode)
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("获取账号角色列表失败: loginId={}", loginId, e);
            return new ArrayList<>();
        }
    }
}
