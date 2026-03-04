package com.ssitao.code.modular.iam.organization.application.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.organization.application.service.IamDepartmentUserService;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamDeptUserDO;
import com.ssitao.code.modular.iam.organization.dal.mapper.IamDeptUserMapper;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamDepartmentConverter;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;
import com.ssitao.code.modular.iam.userprofile.dal.dataobject.IamUserDO;
import com.ssitao.code.modular.iam.userprofile.dal.mapper.IamUserMapper;
import com.ssitao.code.modular.iam.userprofile.infrastructure.converter.IamUserProfileConverter;
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
 * IAM部门用户关联服务实现
 * 管理 iam_dept_user 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamDepartmentUserServiceImpl implements IamDepartmentUserService {

    @Resource
    private IamDeptUserMapper deptUserMapper;

    @Resource
    private IamUserMapper userMapper;

    @Resource
    private IamDepartmentConverter departmentConverter;

    @Resource
    private IamUserProfileConverter userProfileConverter;

    private static final Integer IS_VALID = 1;
    private static final Integer NOT_DELETED = 0;
    private static final Integer IS_PRIMARY_YES = 1;
    private static final Integer IS_PRIMARY_NO = 0;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUsersToDepartment(String departmentId, List<String> userIds, String tenantId) {
        if (departmentId == null || departmentId.isEmpty()) {
            throw new IllegalArgumentException("部门ID不能为空");
        }
        if (CollectionUtils.isEmpty(userIds)) {
            throw new IllegalArgumentException("用户ID列表不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        // 查询用户信息并验证
        QueryWrapper userQueryWrapper = QueryWrapper.create()
                .eq("tenant_id", tenantId)
                .eq("is_deleted", NOT_DELETED);
        List<IamUserDO> userDOList = userMapper.selectListByQuery(userQueryWrapper);
        List<String> validUserIds = userDOList.stream()
                .map(IamUserDO::getUserId)
                .collect(Collectors.toList());

        // 过滤掉无效的用户ID
        List<String> validAddUserIds = userIds.stream()
                .filter(validUserIds::contains)
                .collect(Collectors.toList());

        if (validAddUserIds.isEmpty()) {
            throw new IllegalArgumentException("没有有效的用户ID");
        }

        // 获取现有部门用户关联
        QueryWrapper existQueryWrapper = QueryWrapper.create()
                .eq("dept_id", departmentId)
                .eq("tenant_id", tenantId)
                .eq("is_deleted", NOT_DELETED);
        List<IamDeptUserDO> existRelations = deptUserMapper.selectListByQuery(existQueryWrapper);
        List<String> existUserIds = existRelations.stream()
                .map(IamDeptUserDO::getUserId)
                .collect(Collectors.toList());

        // 只插入新的关联关系
        List<IamDeptUserDO> newRelations = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (String userId : validAddUserIds) {
            if (!existUserIds.contains(userId)) {
                IamDeptUserDO relation = new IamDeptUserDO();
                relation.setId(UUID.randomUUID().toString().replace("-", ""));
                relation.setDeptId(departmentId);
                relation.setUserId(userId);
                relation.setIsPrimary(IS_PRIMARY_NO);
                relation.setTenantId(tenantId);
                relation.setCreateTime(now);
                relation.setIsDeleted(NOT_DELETED);
                newRelations.add(relation);
            }
        }

        if (!newRelations.isEmpty()) {
            deptUserMapper.insertBatch(newRelations);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeUsersFromDepartment(String departmentId, List<String> userIds, String tenantId) {
        if (departmentId == null || departmentId.isEmpty()) {
            throw new IllegalArgumentException("部门ID不能为空");
        }
        if (CollectionUtils.isEmpty(userIds)) {
            throw new IllegalArgumentException("用户ID列表不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        // 构建删除条件
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("dept_id", departmentId)
                .in("user_id", userIds)
                .eq("tenant_id", tenantId);

        deptUserMapper.deleteByQuery(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferUsers(String fromDepartmentId, String toDepartmentId, List<String> userIds, String tenantId) {
        if (fromDepartmentId == null || fromDepartmentId.isEmpty()) {
            throw new IllegalArgumentException("原部门ID不能为空");
        }
        if (toDepartmentId == null || toDepartmentId.isEmpty()) {
            throw new IllegalArgumentException("目标部门ID不能为空");
        }
        if (CollectionUtils.isEmpty(userIds)) {
            throw new IllegalArgumentException("用户ID列表不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        // 先从原部门移除用户
        removeUsersFromDepartment(fromDepartmentId, userIds, tenantId);

        // 再添加到目标部门
        addUsersToDepartment(toDepartmentId, userIds, tenantId);
    }

    @Override
    public List<IamUserProfileDTO> getDepartmentUsers(String departmentId, String tenantId) {
        if (departmentId == null || departmentId.isEmpty()) {
            throw new IllegalArgumentException("部门ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("dept_id", departmentId)
                .eq("tenant_id", tenantId)
                .eq("is_deleted", NOT_DELETED);

        List<IamDeptUserDO> departmentUserList = deptUserMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(departmentUserList)) {
            return new ArrayList<>();
        }

        List<String> userIds = departmentUserList.stream()
                .map(IamDeptUserDO::getUserId)
                .collect(Collectors.toList());

        QueryWrapper userQueryWrapper = QueryWrapper.create()
                .in("user_id", userIds)
                .eq("tenant_id", tenantId)
                .eq("is_deleted", NOT_DELETED);

        List<IamUserDO> userDOList = userMapper.selectListByQuery(userQueryWrapper);

        return userProfileConverter.toDTOList(userProfileConverter.toDomainList(userDOList));
    }

    @Override
    public List<IamDepartmentDTO> getUserDepartments(String userId, String tenantId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("user_id", userId)
                .eq("tenant_id", tenantId)
                .eq("is_deleted", NOT_DELETED);

        List<IamDeptUserDO> departmentUserList = deptUserMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(departmentUserList)) {
            return new ArrayList<>();
        }

        // 返回空列表，需要通过关联查询获取部门信息
        return new ArrayList<>();
    }

    @Override
    public IamDepartmentDTO getUserMainDepartment(String userId, String tenantId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("user_id", userId)
                .eq("tenant_id", tenantId)
                .eq("is_primary", IS_PRIMARY_YES)
                .eq("is_deleted", NOT_DELETED);

        IamDeptUserDO departmentUser = deptUserMapper.selectOneByQuery(queryWrapper);

        if (departmentUser == null) {
            // 如果没有设置主部门，返回用户的第一个部门
            QueryWrapper firstDeptQueryWrapper = QueryWrapper.create()
                    .eq("user_id", userId)
                    .eq("tenant_id", tenantId)
                    .eq("is_deleted", NOT_DELETED)
                    .orderBy("create_time", true)
                    .limit(1);
            departmentUser = deptUserMapper.selectOneByQuery(firstDeptQueryWrapper);
        }

        if (departmentUser == null) {
            return null;
        }

        // 返回空，需要通过关联查询获取部门信息
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setUserMainDepartment(String userId, String departmentId, String tenantId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        if (departmentId == null || departmentId.isEmpty()) {
            throw new IllegalArgumentException("部门ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        // 查询用户是否已经在该部门
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("user_id", userId)
                .eq("dept_id", departmentId)
                .eq("tenant_id", tenantId)
                .eq("is_deleted", NOT_DELETED);

        IamDeptUserDO departmentUser = deptUserMapper.selectOneByQuery(queryWrapper);

        if (departmentUser == null) {
            // 用户不在该部门，先添加到部门
            List<String> userIds = new ArrayList<>();
            userIds.add(userId);
            addUsersToDepartment(departmentId, userIds, tenantId);
            departmentUser = deptUserMapper.selectOneByQuery(queryWrapper);
        }

        if (departmentUser != null) {
            // 取消用户原有的主部门设置
            QueryWrapper clearMainQueryWrapper = QueryWrapper.create()
                    .eq("user_id", userId)
                    .eq("tenant_id", tenantId)
                    .eq("is_primary", IS_PRIMARY_YES);

            IamDeptUserDO updateDO = new IamDeptUserDO();
            updateDO.setIsPrimary(IS_PRIMARY_NO);
            deptUserMapper.updateByQuery(updateDO, true, clearMainQueryWrapper);

            // 设置新的主部门
            departmentUser.setIsPrimary(IS_PRIMARY_YES);
            deptUserMapper.update(departmentUser, true);
        }
    }
}
