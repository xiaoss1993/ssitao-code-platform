package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.user.UserCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.user.UserListReqVO;
import com.ssitao.code.modular.iam.controller.vo.user.UserUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.UserDO;
import com.ssitao.code.modular.iam.dal.dataobject.UserRoleDO;
import com.ssitao.code.modular.iam.dal.mapper.UserMapper;
import com.ssitao.code.modular.iam.dal.mapper.UserRoleMapper;
import com.ssitao.code.modular.iam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(UserCreateReqVO createReqVO) {
        // 检查用户名是否已存在
        UserDO existUser = userMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("username", createReqVO.getUsername())
                        .eq("deleted", 0)
        );
        if (existUser != null) {
            throw new BusinessException("用户名已存在: " + createReqVO.getUsername());
        }

        // 创建用户
        UserDO user = new UserDO();
        user.setUsername(createReqVO.getUsername());
        user.setPassword(passwordEncoder.encode(createReqVO.getPassword()));
        user.setNickname(createReqVO.getNickname());
        user.setAvatar(createReqVO.getAvatar());
        user.setEmail(createReqVO.getEmail());
        user.setMobile(createReqVO.getMobile());
        user.setGender(createReqVO.getGender());
        user.setStatus(createReqVO.getStatus() != null ? createReqVO.getStatus() : 1);
        user.setDeptId(createReqVO.getDeptId());
        user.setSuperAdmin(createReqVO.getSuperAdmin() != null ? createReqVO.getSuperAdmin() : 0);
        user.setRemark(createReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            user.setTenantId(loginUser.getTenantId());
            user.setCreator(loginUser.getUsername());
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);

        userMapper.insert(user);
        log.info("创建用户成功: username={}", user.getUsername());
        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserUpdateReqVO updateReqVO) {
        UserDO user = userMapper.selectOneById(updateReqVO.getId());
        if (user == null || user.getDeleted() == 1) {
            throw new NotFoundException("用户不存在");
        }

        // 检查用户名是否被其他用户使用
        if (!user.getUsername().equals(updateReqVO.getUsername())) {
            UserDO existUser = userMapper.selectOneByQuery(
                    QueryWrapper.create()
                            .eq("username", updateReqVO.getUsername())
                            .ne("id", updateReqVO.getId())
                            .eq("deleted", 0)
            );
            if (existUser != null) {
                throw new BusinessException("用户名已存在: " + updateReqVO.getUsername());
            }
        }

        // 更新用户信息
        user.setUsername(updateReqVO.getUsername());
        user.setNickname(updateReqVO.getNickname());
        user.setAvatar(updateReqVO.getAvatar());
        user.setEmail(updateReqVO.getEmail());
        user.setMobile(updateReqVO.getMobile());
        user.setGender(updateReqVO.getGender());
        if (updateReqVO.getStatus() != null) {
            user.setStatus(updateReqVO.getStatus());
        }
        user.setDeptId(updateReqVO.getDeptId());
        user.setRemark(updateReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            user.setUpdater(loginUser.getUsername());
        }
        user.setUpdateTime(LocalDateTime.now());

        userMapper.update(user);
        log.info("更新用户成功: id={}, username={}", user.getId(), user.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        UserDO user = userMapper.selectOneById(id);
        if (user == null || user.getDeleted() == 1) {
            throw new NotFoundException("用户不存在");
        }

        // 检查是否为超管
        if (user.getSuperAdmin() == 1) {
            throw new BusinessException("超级管理员不能删除");
        }

        // 逻辑删除
        user.setDeleted(1);
        user.setUpdateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            user.setUpdater(loginUser.getUsername());
        }

        userMapper.update(user);

        // 删除用户角色关联
        userRoleMapper.deleteByQuery(
                QueryWrapper.create()
                        .eq("user_id", id)
        );

        log.info("删除用户成功: id={}, username={}", user.getId(), user.getUsername());
    }

    @Override
    public UserDO getUser(Long id) {
        UserDO user = userMapper.selectOneById(id);
        if (user == null || user.getDeleted() == 1) {
            throw new NotFoundException("用户不存在");
        }
        return user;
    }

    @Override
    public UserDO getUserByUsername(String username) {
        return userMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("username", username)
                        .eq("deleted", 0)
        );
    }

    @Override
    public List<UserDO> getUserList(UserListReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("deleted", 0);

        if (reqVO.getUsername() != null && !reqVO.getUsername().isEmpty()) {
            queryWrapper.like("username", reqVO.getUsername());
        }
        if (reqVO.getNickname() != null && !reqVO.getNickname().isEmpty()) {
            queryWrapper.like("nickname", reqVO.getNickname());
        }
        if (reqVO.getMobile() != null && !reqVO.getMobile().isEmpty()) {
            queryWrapper.eq("mobile", reqVO.getMobile());
        }
        if (reqVO.getStatus() != null) {
            queryWrapper.eq("status", reqVO.getStatus());
        }
        if (reqVO.getDeptId() != null) {
            queryWrapper.eq("dept_id", reqVO.getDeptId());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("id", true);
        return userMapper.selectListByQuery(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(Long id, Integer status) {
        UserDO user = userMapper.selectOneById(id);
        if (user == null || user.getDeleted() == 1) {
            throw new NotFoundException("用户不存在");
        }

        // 检查是否为超管
        if (user.getSuperAdmin() == 1) {
            throw new BusinessException("超级管理员不能禁用");
        }

        user.setStatus(status);
        user.setUpdateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            user.setUpdater(loginUser.getUsername());
        }

        userMapper.update(user);
        log.info("更新用户状态成功: id={}, status={}", user.getId(), status);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserPassword(Long id, String password) {
        UserDO user = userMapper.selectOneById(id);
        if (user == null || user.getDeleted() == 1) {
            throw new NotFoundException("用户不存在");
        }

        user.setPassword(passwordEncoder.encode(password));
        user.setUpdateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            user.setUpdater(loginUser.getUsername());
        }

        userMapper.update(user);
        log.info("更新用户密码成功: id={}", user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRoles(Long userId, List<Long> roleIds) {
        // 检查用户是否存在
        UserDO user = userMapper.selectOneById(userId);
        if (user == null || user.getDeleted() == 1) {
            throw new NotFoundException("用户不存在");
        }

        // 删除用户原有角色关联
        userRoleMapper.deleteByQuery(
                QueryWrapper.create()
                        .eq("user_id", userId)
        );

        // 批量插入新的角色关联
        if (roleIds != null && !roleIds.isEmpty()) {
            String tenantId = user.getTenantId();
            LocalDateTime now = LocalDateTime.now();

            for (Long roleId : roleIds) {
                UserRoleDO userRole = new UserRoleDO();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRole.setTenantId(tenantId);
                userRole.setCreateTime(now);
                userRoleMapper.insert(userRole);
            }
        }

        log.info("分配用户角色成功: userId={}, roleCount={}", userId,
                roleIds != null ? roleIds.size() : 0);
    }

    @Override
    public List<Long> getUserRoles(Long userId) {
        // 检查用户是否存在
        UserDO user = userMapper.selectOneById(userId);
        if (user == null || user.getDeleted() == 1) {
            throw new NotFoundException("用户不存在");
        }

        // 查询用户角色关联
        List<UserRoleDO> userRoles = userRoleMapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("user_id", userId)
        );

        return userRoles.stream()
                .map(UserRoleDO::getRoleId)
                .collect(Collectors.toList());
    }

}
