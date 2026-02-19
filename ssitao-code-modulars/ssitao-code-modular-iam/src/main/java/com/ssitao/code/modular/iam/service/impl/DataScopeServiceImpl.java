package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.dal.dataobject.DeptDO;
import com.ssitao.code.modular.iam.dal.dataobject.RoleDO;
import com.ssitao.code.modular.iam.dal.dataobject.UserRoleDO;
import com.ssitao.code.modular.iam.dal.mapper.DeptMapper;
import com.ssitao.code.modular.iam.dal.mapper.RoleMapper;
import com.ssitao.code.modular.iam.dal.mapper.UserRoleMapper;
import com.ssitao.code.modular.iam.service.DataScopeService;
import com.ssitao.code.modular.iam.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据权限服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataScopeServiceImpl implements DataScopeService {

    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;
    private final DeptMapper deptMapper;
    private final DeptService deptService;

    @Override
    public void filterDataScope(QueryWrapper queryWrapper, String deptIdColumn, String userIdColumn) {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null) {
            return;
        }

        // 超级管理员拥有全部数据权限
        if (loginUser.getSuperAdmin()) {
            return;
        }

        Long userId = loginUser.getId();
        DataScope dataScope = getUserDataScope(userId);

        if (dataScope == null) {
            return;
        }

        switch (dataScope) {
            case ALL:
                // 全部数据，不添加过滤条件
                break;
            case DEPT_AND_CHILD:
                // 本部门及子部门
                List<Long> deptIds = getDeptAndChildDeptIds(loginUser.getDeptId());
                if (!deptIds.isEmpty()) {
                    queryWrapper.in(deptIdColumn, deptIds.toArray(new Long[0]));
                } else {
                    // 没有部门权限，查询空结果
                    queryWrapper.eq(deptIdColumn, -1L);
                }
                break;
            case DEPT:
                // 本部门
                if (loginUser.getDeptId() != null) {
                    queryWrapper.eq(deptIdColumn, loginUser.getDeptId());
                } else {
                    queryWrapper.eq(deptIdColumn, -1L);
                }
                break;
            case SELF:
                // 仅本人
                queryWrapper.eq(userIdColumn, userId);
                break;
            case CUSTOM:
                // 自定义部门
                List<Long> customDeptIds = getDataScopeDeptIds(userId);
                if (!customDeptIds.isEmpty()) {
                    queryWrapper.in(deptIdColumn, customDeptIds.toArray(new Long[0]));
                } else {
                    queryWrapper.eq(deptIdColumn, -1L);
                }
                break;
        }
    }

    @Override
    public void filterDataScope(QueryWrapper queryWrapper) {
        filterDataScope(queryWrapper, "dept_id", "user_id");
    }

    @Override
    public List<Long> getDataScopeDeptIds(Long userId) {
        // 查询用户的所有角色
        List<UserRoleDO> userRoles = userRoleMapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("user_id", userId)
        );

        if (userRoles.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> roleIds = userRoles.stream()
                .map(UserRoleDO::getRoleId)
                .collect(Collectors.toList());

        // 查询角色详情
        List<RoleDO> roles = roleMapper.selectListByQuery(
                QueryWrapper.create()
                        .in("id", roleIds)
                        .eq("status", 1)
                        .eq("deleted", 0)
        );

        // 找出最小数据权限范围
        int minScope = Integer.MAX_VALUE;
        String deptIdsStr = null;
        for (RoleDO role : roles) {
            if (role.getDataScope() != null && role.getDataScope() < minScope) {
                minScope = role.getDataScope();
                deptIdsStr = role.getDataScopeDeptIds();
            }
        }

        if (StringUtils.hasText(deptIdsStr)) {
            try {
                // 假设存储的是JSON数组格式，如 "[1,2,3]"
                return parseDeptIds(deptIdsStr);
            } catch (Exception e) {
                log.error("解析部门ID列表失败: deptIdsStr={}", deptIdsStr, e);
            }
        }

        return Collections.emptyList();
    }

    @Override
    public DataScope getUserDataScope(Long userId) {
        // 查询用户的所有角色
        List<UserRoleDO> userRoles = userRoleMapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("user_id", userId)
        );

        if (userRoles.isEmpty()) {
            return null;
        }

        List<Long> roleIds = userRoles.stream()
                .map(UserRoleDO::getRoleId)
                .collect(Collectors.toList());

        // 查询角色详情
        List<RoleDO> roles = roleMapper.selectListByQuery(
                QueryWrapper.create()
                        .in("id", roleIds)
                        .eq("status", 1)
                        .eq("deleted", 0)
        );

        // 找出最小数据权限范围
        int minScope = Integer.MAX_VALUE;
        for (RoleDO role : roles) {
            if (role.getDataScope() != null && role.getDataScope() < minScope) {
                minScope = role.getDataScope();
            }
        }

        if (minScope == Integer.MAX_VALUE) {
            return null;
        }

        return DataScope.fromCode(minScope);
    }

    /**
     * 获取部门及其子部门ID列表
     */
    private List<Long> getDeptAndChildDeptIds(Long deptId) {
        List<Long> deptIds = new ArrayList<>();
        deptIds.add(deptId);

        // 递归获取子部门
        List<Long> childDeptIds = getChildDeptIds(deptId);
        deptIds.addAll(childDeptIds);

        return deptIds;
    }

    /**
     * 递归获取子部门ID
     */
    private List<Long> getChildDeptIds(Long parentDeptId) {
        List<Long> deptIds = new ArrayList<>();

        List<DeptDO> childDepts = deptMapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("parent_id", parentDeptId)
                        .eq("status", 1)
                        .eq("deleted", 0)
        );

        for (DeptDO dept : childDepts) {
            deptIds.add(dept.getId());
            deptIds.addAll(getChildDeptIds(dept.getId()));
        }

        return deptIds;
    }

    /**
     * 解析部门ID列表字符串
     */
    private List<Long> parseDeptIds(String deptIdsStr) {
        // 简单实现：假设格式为 "1,2,3" 或 "[1,2,3]"
        String cleaned = deptIdsStr.replaceAll("[\\[\\]\"]", "");
        String[] parts = cleaned.split(",");
        List<Long> deptIds = new ArrayList<>();
        for (String part : parts) {
            if (StringUtils.hasText(part)) {
                try {
                    deptIds.add(Long.parseLong(part.trim()));
                } catch (NumberFormatException e) {
                    log.warn("解析部门ID失败: part={}", part);
                }
            }
        }
        return deptIds;
    }

}
