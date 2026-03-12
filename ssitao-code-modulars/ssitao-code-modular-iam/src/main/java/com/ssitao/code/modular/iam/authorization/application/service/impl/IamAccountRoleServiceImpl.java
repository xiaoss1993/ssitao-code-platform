package com.ssitao.code.modular.iam.authorization.application.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.application.service.IamAccountRoleService;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamAccountRoleDO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamAccountRoleMapper;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamRoleMapper;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamRoleConverter;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.dal.dataobject.IamAccountDO;
import com.ssitao.code.modular.iam.identity.dal.mapper.IamAccountMapper;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamAccountConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM账号角色关联服务实现
 * 管理 iam_account_role 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamAccountRoleServiceImpl implements IamAccountRoleService {

    @Resource
    private IamAccountRoleMapper accountRoleMapper;

    @Resource
    private IamRoleMapper roleMapper;

    @Resource
    private IamAccountMapper accountMapper;

    @Resource
    private IamRoleConverter roleConverter;

    @Resource
    private IamAccountConverter accountConverter;

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer IS_VALID = 1;
    private static final Integer NOT_DELETED = 0;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRoles(String accountId, List<String> roleIds, String tenantId) {
        if (accountId == null || accountId.isEmpty()) {
            throw new IllegalArgumentException("账号ID不能为空");
        }
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new IllegalArgumentException("角色ID列表不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        // 验证账号是否存在
        IamAccountDO account = accountMapper.selectOneById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("账号不存在: " + accountId);
        }

        // 查询角色信息并验证
        QueryWrapper roleQueryWrapper = QueryWrapper.create()
                .eq("tenant_id", tenantId)
                .eq("role_status", STATUS_ACTIVE)
                .eq("is_deleted", NOT_DELETED);
        List<IamRoleDO> roleDOList = roleMapper.selectListByQuery(roleQueryWrapper);
        List<String> validRoleIds = roleDOList.stream()
                .map(IamRoleDO::getRoleId)
                .collect(Collectors.toList());

        // 过滤掉无效的角色ID
        List<String> validAssignRoleIds = roleIds.stream()
                .filter(validRoleIds::contains)
                .collect(Collectors.toList());

        if (validAssignRoleIds.isEmpty()) {
            throw new IllegalArgumentException("没有有效的角色ID");
        }

        // 获取现有角色关联
        QueryWrapper existQueryWrapper = QueryWrapper.create()
                .eq("account_id", accountId)
                .eq("tenant_id", tenantId)
                .eq("is_valid", IS_VALID)
                .eq("is_deleted", NOT_DELETED);
        List<IamAccountRoleDO> existRelations = accountRoleMapper.selectListByQuery(existQueryWrapper);
        List<String> existRoleIds = existRelations.stream()
                .map(IamAccountRoleDO::getRoleId)
                .collect(Collectors.toList());

        // 只插入新的关联关系
        List<IamAccountRoleDO> newRelations = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (String roleId : validAssignRoleIds) {
            if (!existRoleIds.contains(roleId)) {
                IamAccountRoleDO relation = new IamAccountRoleDO();
                relation.setId(UUID.randomUUID().toString().replace("-", ""));
                relation.setRoleId(roleId);
                relation.setAccountId(accountId);
                relation.setTenantId(tenantId);
                relation.setIsValid(IS_VALID);
                relation.setCreateTime(now);
                relation.setIsDeleted(NOT_DELETED);
                newRelations.add(relation);
            }
        }

        if (!newRelations.isEmpty()) {
            accountRoleMapper.insertBatch(newRelations);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void revokeRoles(String accountId, List<String> roleIds, String tenantId) {
        if (accountId == null || accountId.isEmpty()) {
            throw new IllegalArgumentException("账号ID不能为空");
        }
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new IllegalArgumentException("角色ID列表不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        // 构建删除条件
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("account_id", accountId)
                .in("role_id", roleIds)
                .eq("tenant_id", tenantId);

        accountRoleMapper.deleteByQuery(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void revokeAllRoles(String accountId, String tenantId) {
        if (accountId == null || accountId.isEmpty()) {
            throw new IllegalArgumentException("账号ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("account_id", accountId)
                .eq("tenant_id", tenantId);

        accountRoleMapper.deleteByQuery(queryWrapper);
    }

    @Override
    public List<IamRoleDTO> getAccountRoles(String accountId, String tenantId) {
        if (accountId == null || accountId.isEmpty()) {
            throw new IllegalArgumentException("账号ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("account_id", accountId)
                .eq("tenant_id", tenantId)
                .eq("is_valid", IS_VALID)
                .eq("is_deleted", NOT_DELETED);

        List<IamAccountRoleDO> accountRoleList = accountRoleMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(accountRoleList)) {
            return new ArrayList<>();
        }

        List<String> roleIds = accountRoleList.stream()
                .map(IamAccountRoleDO::getRoleId)
                .collect(Collectors.toList());

        QueryWrapper roleQueryWrapper = QueryWrapper.create()
                .in("role_id", roleIds)
                .eq("tenant_id", tenantId)
                .eq("role_status", STATUS_ACTIVE)
                .eq("is_deleted", NOT_DELETED);

        List<IamRoleDO> roleDOList = roleMapper.selectListByQuery(roleQueryWrapper);

        return roleConverter.toDTOList(roleConverter.toDomainList(roleDOList));
    }

    @Override
    public List<IamAccountDTO> getRoleAccounts(String roleId, String tenantId) {
        if (roleId == null || roleId.isEmpty()) {
            throw new IllegalArgumentException("角色ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("role_id", roleId)
                .eq("tenant_id", tenantId)
                .eq("is_valid", IS_VALID)
                .eq("is_deleted", NOT_DELETED);

        List<IamAccountRoleDO> accountRoleList = accountRoleMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(accountRoleList)) {
            return new ArrayList<>();
        }

        List<String> accountIds = accountRoleList.stream()
                .map(IamAccountRoleDO::getAccountId)
                .collect(Collectors.toList());

        QueryWrapper accountQueryWrapper = QueryWrapper.create()
                .in("account_id", accountIds)
                .eq("tenant_id", tenantId)
                .eq("account_status", STATUS_ACTIVE)
                .eq("is_deleted", NOT_DELETED);

        List<IamAccountDO> accountDOList = accountMapper.selectListByQuery(accountQueryWrapper);

        return accountConverter.toDTOList(accountConverter.toDomainList(accountDOList));
    }

    @Override
    public boolean checkRole(String accountId, String roleId, String tenantId) {
        if (accountId == null || accountId.isEmpty()) {
            return false;
        }
        if (roleId == null || roleId.isEmpty()) {
            return false;
        }
        if (tenantId == null || tenantId.isEmpty()) {
            return false;
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("account_id", accountId)
                .eq("role_id", roleId)
                .eq("tenant_id", tenantId)
                .eq("is_valid", IS_VALID)
                .eq("is_deleted", NOT_DELETED);

        return accountRoleMapper.selectOneByQuery(queryWrapper) != null;
    }
}
