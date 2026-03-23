package com.ssitao.code.modular.iam.application.service;

import com.ssitao.code.common.core.domain.entity.SysUser;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.common.utils.StringUtils;
import com.ssitao.code.common.utils.uuid.IdUtils;
import com.ssitao.code.modular.iam.application.dto.SysUserDTO;
import com.ssitao.code.modular.iam.application.command.ChangePasswordCommand;
import com.ssitao.code.modular.iam.application.command.CreateUserCommand;
import com.ssitao.code.modular.iam.application.command.DeleteUserCommand;
import com.ssitao.code.modular.iam.application.command.UpdateUserCommand;
import com.ssitao.code.modular.iam.domain.model.SysUserAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysUserRepository;
import com.ssitao.code.modular.iam.infrastructure.converter.SysUserConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户应用服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserApplicationService {

    private final SysUserRepository userRepository;
    private final SysUserConverter userConverter;
    private final ISysUserService userService;

    /**
     * 查询用户列表
     */
    public List<SysUserDTO> listUsers(SysUserDTO query) {
        SysUser user = new SysUser();
        if (query != null) {
            if (StringUtils.isNotEmpty(query.getLoginName())) {
                user.setLoginName(query.getLoginName());
            }
            if (StringUtils.isNotEmpty(query.getUserName())) {
                user.setUserName(query.getUserName());
            }
            if (StringUtils.isNotEmpty(query.getPhonenumber())) {
                user.setPhonenumber(query.getPhonenumber());
            }
            if (StringUtils.isNotEmpty(query.getStatus())) {
                user.setStatus(query.getStatus());
            }
        }
        List<SysUser> list = userService.selectUserList(user);
        return userConverter.toDTOListFromUser(list);
    }

    /**
     * 根据ID查询用户
     */
    public SysUserDTO getUserById(Long userId) {
        SysUserAggregate aggregate = userRepository.findOne(userId);
        if (aggregate == null) {
            throw new ServiceException("用户不存在");
        }
        return userConverter.toDTO(aggregate);
    }

    /**
     * 根据登录名查询用户
     */
    public SysUserDTO getUserByLoginName(String loginName) {
        SysUserAggregate aggregate = userRepository.findByLoginName(loginName);
        if (aggregate == null) {
            return null;
        }
        return userConverter.toDTO(aggregate);
    }

    /**
     * 创建用户
     */
    @Transactional
    public Long createUser(CreateUserCommand command) {
        // 检查登录名是否已存在
        if (userRepository.existsByLoginName(command.getLoginName())) {
            throw new ServiceException("登录账号已存在");
        }

        // 检查手机号是否已存在
        if (StringUtils.isNotEmpty(command.getPhonenumber())
                && userRepository.existsByPhonenumber(command.getPhonenumber())) {
            throw new ServiceException("手机号码已存在");
        }

        // 生成用户ID
        Long userId = System.nanoTime();

        // 创建用户聚合根
        SysUserAggregate aggregate = new SysUserAggregate();
        aggregate.createUser(
                userId,
                command.getLoginName(),
                command.getUserName(),
                command.getEmail(),
                command.getPhonenumber(),
                command.getDeptId(),
                "0",
                ShiroUtils.getLoginName()
        );

        // 设置初始密码
        if (StringUtils.isNotEmpty(command.getPassword())) {
            String salt = IdUtils.fastUUID().toString();
            aggregate.changePassword(command.getPassword(), salt, ShiroUtils.getLoginName());
        }

        // 保存
        userRepository.save(aggregate);

        log.info("创建用户: {}", userId);
        return userId;
    }

    /**
     * 更新用户
     */
    @Transactional
    public void updateUser(UpdateUserCommand command) {
        SysUserAggregate aggregate = userRepository.findOne(command.getUserId());
        if (aggregate == null) {
            throw new ServiceException("用户不存在");
        }

        // 更新用户信息
        aggregate.updateProfile(
                command.getUserName(),
                command.getEmail(),
                command.getPhonenumber(),
                command.getSex(),
                ShiroUtils.getLoginName()
        );

        // 更新状态
        if (StringUtils.isNotEmpty(command.getStatus())) {
            aggregate.changeStatus(command.getStatus(), ShiroUtils.getLoginName());
        }

        // 分配角色
        if (command.getRoleIds() != null) {
            aggregate.assignRoles(command.getRoleIds(), ShiroUtils.getLoginName());
        }

        // 保存
        userRepository.save(aggregate);

        log.info("更新用户: {}", command.getUserId());
    }

    /**
     * 删除用户
     */
    @Transactional
    public void deleteUsers(DeleteUserCommand command) {
        for (Long userId : command.getUserIds()) {
            SysUserAggregate aggregate = userRepository.findOne(userId);
            if (aggregate != null) {
                if (aggregate.isAdmin()) {
                    throw new ServiceException("不允许删除管理员用户");
                }
                aggregate.markDeleted(ShiroUtils.getLoginName());
                userRepository.save(aggregate);
                log.info("删除用户: {}", userId);
            }
        }
    }

    /**
     * 修改密码
     */
    @Transactional
    public void changePassword(ChangePasswordCommand command) {
        SysUserAggregate aggregate = userRepository.findOne(command.getUserId());
        if (aggregate == null) {
            throw new ServiceException("用户不存在");
        }

        // 验证旧密码
        // String encryptedOldPassword = encryptPassword(command.getOldPassword(), aggregate.getSalt());
        // if (!encryptedOldPassword.equals(aggregate.getPassword())) {
        //     throw new ServiceException("旧密码不正确");
        // }

        // 生成新盐值并加密
        String newSalt = IdUtils.fastUUID().toString();
        aggregate.changePassword(command.getNewPassword(), newSalt, ShiroUtils.getLoginName());

        // 保存
        userRepository.save(aggregate);

        log.info("修改用户密码: {}", command.getUserId());
    }

    /**
     * 重置密码
     */
    @Transactional
    public void resetPassword(Long userId, String newPassword) {
        SysUserAggregate aggregate = userRepository.findOne(userId);
        if (aggregate == null) {
            throw new ServiceException("用户不存在");
        }

        String salt = IdUtils.fastUUID().toString();
        aggregate.changePassword(newPassword, salt, ShiroUtils.getLoginName());

        userRepository.save(aggregate);

        log.info("重置用户密码: {}", userId);
    }

    /**
     * 修改用户状态
     */
    @Transactional
    public void changeStatus(Long userId, String status) {
        SysUserAggregate aggregate = userRepository.findOne(userId);
        if (aggregate == null) {
            throw new ServiceException("用户不存在");
        }

        if (aggregate.isAdmin() && "1".equals(status)) {
            throw new ServiceException("不允许停用管理员用户");
        }

        aggregate.changeStatus(status, ShiroUtils.getLoginName());
        userRepository.save(aggregate);

        log.info("修改用户状态: {} -> {}", userId, status);
    }

    /**
     * 更新登录信息
     */
    public void updateLoginInfo(Long userId, String loginIp) {
        SysUserAggregate aggregate = userRepository.findOne(userId);
        if (aggregate != null) {
            aggregate.updateLoginInfo(loginIp);
            userRepository.save(aggregate);
        }
    }
}
