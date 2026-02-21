package com.ssitao.code.modular.iam.authorization.application.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.application.service.IamAccountRoleService;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamRoleConverter;
import com.ssitao.code.modular.iam.dal.dataobject.IamAccountDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamAccountRoleDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.dal.mapper.IamAccountMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamAccountRoleMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamRoleMapper;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamAccountConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM账号角色关联服务实现
 * 管理 tb_iam_accountrole 表
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

    private static final String STATUS_ACTIVE = "1";
    private static final String YES_CODE = "1";
    private static final String NO_CODE = "0";

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

        // 获取账号信息用于设置部门相关字段
        String deptId = account.getSyOrgId();
        String deptName = account.getSyOrgName();

        // 查询角色信息并验证
        QueryWrapper roleQueryWrapper = QueryWrapper.create()
                .where(IamRoleDO::getSyTenantId).eq(tenantId)
                .and(IamRoleDO::getSyStatus).eq(STATUS_ACTIVE);
        List<IamRoleDO> roleDOList = roleMapper.selectListByQuery(roleQueryWrapper);
        List<String> validRoleIds = roleDOList.stream()
                .map(IamRoleDO::getTbIamRoleId)
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
                .where(IamAccountRoleDO::getAccountroleAccountId).eq(accountId)
                .and(IamAccountRoleDO::getSyTenantId).eq(tenantId)
                .and(IamAccountRoleDO::getSyStatus).eq(STATUS_ACTIVE);
        List<IamAccountRoleDO> existRelations = accountRoleMapper.selectListByQuery(existQueryWrapper);
        List<String> existRoleIds = existRelations.stream()
                .map(IamAccountRoleDO::getAccountroleRoleId)
                .collect(Collectors.toList());

        // 只插入新的关联关系
        List<IamAccountRoleDO> newRelations = new ArrayList<>();
        for (String roleId : validAssignRoleIds) {
            if (!existRoleIds.contains(roleId)) {
                IamAccountRoleDO relation = new IamAccountRoleDO();
                relation.setTbIamAccountroleId(UUID.randomUUID().toString().replace("-", ""));
                relation.setAccountroleRoleId(roleId);
                relation.setAccountroleAccountId(accountId);
                relation.setAccountroleDeptId(deptId);
                relation.setAccountroleDeptName(deptName);
                relation.setAccountroleMainCode(NO_CODE);
                relation.setSyTenantId(tenantId);
                relation.setSyStatus(STATUS_ACTIVE);
                relation.setSyCreatetime(String.valueOf(new Date().getTime()));
                relation.setSyOrderindex("0");
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
                .where(IamAccountRoleDO::getAccountroleAccountId).eq(accountId)
                .and(IamAccountRoleDO::getAccountroleRoleId).in(roleIds)
                .and(IamAccountRoleDO::getSyTenantId).eq(tenantId);

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
                .where(IamAccountRoleDO::getAccountroleAccountId).eq(accountId)
                .and(IamAccountRoleDO::getSyTenantId).eq(tenantId);

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
                .where(IamAccountRoleDO::getAccountroleAccountId).eq(accountId)
                .and(IamAccountRoleDO::getSyTenantId).eq(tenantId)
                .and(IamAccountRoleDO::getSyStatus).eq(STATUS_ACTIVE);

        List<IamAccountRoleDO> accountRoleList = accountRoleMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(accountRoleList)) {
            return new ArrayList<>();
        }

        List<String> roleIds = accountRoleList.stream()
                .map(IamAccountRoleDO::getAccountroleRoleId)
                .collect(Collectors.toList());

        QueryWrapper roleQueryWrapper = QueryWrapper.create()
                .where(IamRoleDO::getTbIamRoleId).in(roleIds)
                .and(IamRoleDO::getSyTenantId).eq(tenantId)
                .and(IamRoleDO::getSyStatus).eq(STATUS_ACTIVE);

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
                .where(IamAccountRoleDO::getAccountroleRoleId).eq(roleId)
                .and(IamAccountRoleDO::getSyTenantId).eq(tenantId)
                .and(IamAccountRoleDO::getSyStatus).eq(STATUS_ACTIVE);

        List<IamAccountRoleDO> accountRoleList = accountRoleMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(accountRoleList)) {
            return new ArrayList<>();
        }

        List<String> accountIds = accountRoleList.stream()
                .map(IamAccountRoleDO::getAccountroleAccountId)
                .collect(Collectors.toList());

        QueryWrapper accountQueryWrapper = QueryWrapper.create()
                .where(IamAccountDO::getTbIamAccountId).in(accountIds)
                .and(IamAccountDO::getSyTenantId).eq(tenantId)
                .and(IamAccountDO::getSyStatus).eq(STATUS_ACTIVE);

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
                .where(IamAccountRoleDO::getAccountroleAccountId).eq(accountId)
                .and(IamAccountRoleDO::getAccountroleRoleId).eq(roleId)
                .and(IamAccountRoleDO::getSyTenantId).eq(tenantId)
                .and(IamAccountRoleDO::getSyStatus).eq(STATUS_ACTIVE);

        return accountRoleMapper.selectOneByQuery(queryWrapper) != null;
    }
}
