package com.ssitao.code.modular.iam.organization.application.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamDepartmentDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamDepartmentUserDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamUserDO;
import com.ssitao.code.modular.iam.dal.mapper.IamDepartmentMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamDepartmentUserMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamUserMapper;
import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.organization.application.service.IamDepartmentUserService;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamDepartmentConverter;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;
import com.ssitao.code.modular.iam.userprofile.infrastructure.converter.IamUserProfileConverter;
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
 * IAM部门用户关联服务实现
 * 管理 tb_iam_deptuser 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamDepartmentUserServiceImpl implements IamDepartmentUserService {

    @Resource
    private IamDepartmentUserMapper departmentUserMapper;

    @Resource
    private IamDepartmentMapper departmentMapper;

    @Resource
    private IamUserMapper userMapper;

    @Resource
    private IamDepartmentConverter departmentConverter;

    @Resource
    private IamUserProfileConverter userProfileConverter;

    private static final String STATUS_ACTIVE = "1";
    private static final String YES_CODE = "1";
    private static final String NO_CODE = "0";
    private static final String IS_LEADER_YES = "1";
    private static final String IS_LEADER_NO = "0";

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

        // 验证部门是否存在
        IamDepartmentDO department = departmentMapper.selectOneById(departmentId);
        if (department == null) {
            throw new IllegalArgumentException("部门不存在: " + departmentId);
        }

        // 查询用户信息并验证
        QueryWrapper userQueryWrapper = QueryWrapper.create()
                .where(IamUserDO::getSyTenantId).eq(tenantId)
                .and(IamUserDO::getSyStatus).eq(STATUS_ACTIVE);
        List<IamUserDO> userDOList = userMapper.selectListByQuery(userQueryWrapper);
        List<String> validUserIds = userDOList.stream()
                .map(IamUserDO::getTbIamUserId)
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
                .where(IamDepartmentUserDO::getDeptuserDeptId).eq(departmentId)
                .and(IamDepartmentUserDO::getSyTenantId).eq(tenantId)
                .and(IamDepartmentUserDO::getSyStatus).eq(STATUS_ACTIVE);
        List<IamDepartmentUserDO> existRelations = departmentUserMapper.selectListByQuery(existQueryWrapper);
        List<String> existUserIds = existRelations.stream()
                .map(IamDepartmentUserDO::getDeptuserUserId)
                .collect(Collectors.toList());

        // 只插入新的关联关系
        List<IamDepartmentUserDO> newRelations = new ArrayList<>();
        for (String userId : validAddUserIds) {
            if (!existUserIds.contains(userId)) {
                IamDepartmentUserDO relation = new IamDepartmentUserDO();
                relation.setTbIamDeptuserId(UUID.randomUUID().toString().replace("-", ""));
                relation.setDeptuserDeptId(departmentId);
                relation.setDeptuserUserId(userId);
                relation.setDeptuserIsPrimaryCode(NO_CODE);
                relation.setDeptuserIsPrimaryName("否");
                relation.setDeptuserIsLeaderCode(IS_LEADER_NO);
                relation.setDeptuserIsLeaderName("否");
                relation.setDeptuserSortNo(0);
                relation.setSyTenantId(tenantId);
                relation.setSyStatus(STATUS_ACTIVE);
                relation.setSyCreatetime(String.valueOf(new Date().getTime()));
                relation.setSyOrderindex(0);
                newRelations.add(relation);
            }
        }

        if (!newRelations.isEmpty()) {
            departmentUserMapper.insertBatch(newRelations);
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
                .where(IamDepartmentUserDO::getDeptuserDeptId).eq(departmentId)
                .and(IamDepartmentUserDO::getDeptuserUserId).in(userIds)
                .and(IamDepartmentUserDO::getSyTenantId).eq(tenantId);

        departmentUserMapper.deleteByQuery(queryWrapper);
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

        // 验证目标部门是否存在
        IamDepartmentDO toDepartment = departmentMapper.selectOneById(toDepartmentId);
        if (toDepartment == null) {
            throw new IllegalArgumentException("目标部门不存在: " + toDepartmentId);
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
                .where(IamDepartmentUserDO::getDeptuserDeptId).eq(departmentId)
                .and(IamDepartmentUserDO::getSyTenantId).eq(tenantId)
                .and(IamDepartmentUserDO::getSyStatus).eq(STATUS_ACTIVE)
                .orderBy(IamDepartmentUserDO::getDeptuserSortNo, true);

        List<IamDepartmentUserDO> departmentUserList = departmentUserMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(departmentUserList)) {
            return new ArrayList<>();
        }

        List<String> userIds = departmentUserList.stream()
                .map(IamDepartmentUserDO::getDeptuserUserId)
                .collect(Collectors.toList());

        QueryWrapper userQueryWrapper = QueryWrapper.create()
                .where(IamUserDO::getTbIamUserId).in(userIds)
                .and(IamUserDO::getSyTenantId).eq(tenantId)
                .and(IamUserDO::getSyStatus).eq(STATUS_ACTIVE);

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
                .where(IamDepartmentUserDO::getDeptuserUserId).eq(userId)
                .and(IamDepartmentUserDO::getSyTenantId).eq(tenantId)
                .and(IamDepartmentUserDO::getSyStatus).eq(STATUS_ACTIVE);

        List<IamDepartmentUserDO> departmentUserList = departmentUserMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(departmentUserList)) {
            return new ArrayList<>();
        }

        List<String> departmentIds = departmentUserList.stream()
                .map(IamDepartmentUserDO::getDeptuserDeptId)
                .collect(Collectors.toList());

        QueryWrapper departmentQueryWrapper = QueryWrapper.create()
                .where(IamDepartmentDO::getTbIamDepartmentId).in(departmentIds)
                .and(IamDepartmentDO::getSyTenantId).eq(tenantId)
                .and(IamDepartmentDO::getSyStatus).eq(STATUS_ACTIVE);

        List<IamDepartmentDO> departmentDOList = departmentMapper.selectListByQuery(departmentQueryWrapper);

        return departmentConverter.toDTOList(departmentConverter.toDomainList(departmentDOList));
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
                .where(IamDepartmentUserDO::getDeptuserUserId).eq(userId)
                .and(IamDepartmentUserDO::getSyTenantId).eq(tenantId)
                .and(IamDepartmentUserDO::getDeptuserIsPrimaryCode).eq(YES_CODE)
                .and(IamDepartmentUserDO::getSyStatus).eq(STATUS_ACTIVE);

        IamDepartmentUserDO departmentUser = departmentUserMapper.selectOneByQuery(queryWrapper);

        if (departmentUser == null) {
            // 如果没有设置主部门，返回用户的第一个部门
            QueryWrapper firstDeptQueryWrapper = QueryWrapper.create()
                    .where(IamDepartmentUserDO::getDeptuserUserId).eq(userId)
                    .and(IamDepartmentUserDO::getSyTenantId).eq(tenantId)
                    .and(IamDepartmentUserDO::getSyStatus).eq(STATUS_ACTIVE)
                    .orderBy(IamDepartmentUserDO::getSyCreatetime, true)
                    .limit(1);
            departmentUser = departmentUserMapper.selectOneByQuery(firstDeptQueryWrapper);
        }

        if (departmentUser == null) {
            return null;
        }

        IamDepartmentDO department = departmentMapper.selectOneById(departmentUser.getDeptuserDeptId());

        if (department == null) {
            return null;
        }

        return departmentConverter.toDTO(department);
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

        // 验证部门是否存在
        IamDepartmentDO department = departmentMapper.selectOneById(departmentId);
        if (department == null) {
            throw new IllegalArgumentException("部门不存在: " + departmentId);
        }

        // 查询用户是否已经在该部门
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(IamDepartmentUserDO::getDeptuserUserId).eq(userId)
                .and(IamDepartmentUserDO::getDeptuserDeptId).eq(departmentId)
                .and(IamDepartmentUserDO::getSyTenantId).eq(tenantId)
                .and(IamDepartmentUserDO::getSyStatus).eq(STATUS_ACTIVE);

        IamDepartmentUserDO departmentUser = departmentUserMapper.selectOneByQuery(queryWrapper);

        if (departmentUser == null) {
            // 用户不在该部门，先添加到部门
            List<String> userIds = new ArrayList<>();
            userIds.add(userId);
            addUsersToDepartment(departmentId, userIds, tenantId);
            departmentUser = departmentUserMapper.selectOneByQuery(queryWrapper);
        }

        if (departmentUser != null) {
            // 取消用户原有的主部门设置
            QueryWrapper clearMainQueryWrapper = QueryWrapper.create()
                    .where(IamDepartmentUserDO::getDeptuserUserId).eq(userId)
                    .and(IamDepartmentUserDO::getSyTenantId).eq(tenantId)
                    .and(IamDepartmentUserDO::getDeptuserIsPrimaryCode).eq(YES_CODE);

            IamDepartmentUserDO updateDO = new IamDepartmentUserDO();
            updateDO.setDeptuserIsPrimaryCode(NO_CODE);
            updateDO.setDeptuserIsPrimaryName("否");
            departmentUserMapper.updateByQuery(updateDO, true, clearMainQueryWrapper);

            // 设置新的主部门
            departmentUser.setDeptuserIsPrimaryCode(YES_CODE);
            departmentUser.setDeptuserIsPrimaryName("是");
            departmentUser.setSyModifytime(String.valueOf(new Date().getTime()));
            departmentUserMapper.update(departmentUser, true);
        }
    }
}
