package com.ssitao.code.modular.iam.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.UnauthorizedException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.modular.iam.dal.dataobject.*;
import com.ssitao.code.modular.iam.dal.mapper.*;
import com.ssitao.code.modular.iam.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;
    private final RolePermissionMapper rolePermissionMapper;
    private final PermissionMapper permissionMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String login(String username, String password) {
        // 查询用户
        UserDO user = userMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("username", username)
                        .eq("deleted", 0)
        );

        if (user == null) {
            throw new UnauthorizedException("用户名或密码错误");
        }

        // 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("用户名或密码错误");
        }

        // 检查状态
        if (user.getStatus() == 0) {
            throw new UnauthorizedException("账号已被禁用");
        }

        // 加载用户角色和权限
        Set<String> roles = loadUserRoles(user.getId());
        Set<String> permissions = loadUserPermissions(user.getId());

        // 创建 LoginUser
        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setUsername(user.getUsername());
        loginUser.setNickname(user.getNickname());
        loginUser.setAvatar(user.getAvatar());
        loginUser.setEmail(user.getEmail());
        loginUser.setMobile(user.getMobile());
        loginUser.setStatus(user.getStatus());
        loginUser.setTenantId(user.getTenantId());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setSuperAdmin(user.getSuperAdmin() == 1);
        loginUser.setRoles(roles);
        loginUser.setPermissions(permissions);
        loginUser.setLoginTime(LocalDateTime.now());

        // 登录（使用 Sa-Token）
        StpUtil.login(user.getId());

        // 存储 LoginUser 到 Session
        StpUtil.getSession().set("loginUser", loginUser);

        // 返回 Token
        return StpUtil.getTokenValue();
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public LoginUser getLoginUser() {
        if (!StpUtil.isLogin()) {
            throw new UnauthorizedException("未登录");
        }

        Object loginUser = StpUtil.getSession().get("loginUser");
        if (loginUser instanceof LoginUser) {
            return (LoginUser) loginUser;
        }

        throw new UnauthorizedException("登录信息已过期，请重新登录");
    }

    @Override
    public void modifyPassword(String oldPassword, String newPassword) {
        LoginUser loginUser = getLoginUser();
        UserDO user = userMapper.selectOneById(loginUser.getId());

        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 验证旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BusinessException("原密码错误");
        }

        // 更新密码
        user.setPassword(passwordEncoder.encode(newPassword));
        userMapper.update(user);
    }

    @Override
    public void modifyProfile(String nickname, String avatar, String email, String mobile) {
        LoginUser loginUser = getLoginUser();
        UserDO user = userMapper.selectOneById(loginUser.getId());

        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 更新信息
        if (StrUtil.isNotBlank(nickname)) {
            user.setNickname(nickname);
        }
        if (StrUtil.isNotBlank(avatar)) {
            user.setAvatar(avatar);
        }
        if (StrUtil.isNotBlank(email)) {
            user.setEmail(email);
        }
        if (StrUtil.isNotBlank(mobile)) {
            user.setMobile(mobile);
        }

        userMapper.update(user);
    }

    /**
     * 加载用户角色
     */
    private Set<String> loadUserRoles(Long userId) {
        try {
            // 查询用户角色
            List<UserRoleDO> userRoles = userRoleMapper.selectListByQuery(
                    QueryWrapper.create()
                            .eq("user_id", userId)
            );

            if (userRoles.isEmpty()) {
                return Collections.emptySet();
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

            return roles.stream()
                    .map(RoleDO::getCode)
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            log.error("加载用户角色失败: userId={}", userId, e);
            return Collections.emptySet();
        }
    }

    /**
     * 加载用户权限
     */
    private Set<String> loadUserPermissions(Long userId) {
        try {
            // 查询用户角色
            List<UserRoleDO> userRoles = userRoleMapper.selectListByQuery(
                    QueryWrapper.create()
                            .eq("user_id", userId)
            );

            if (userRoles.isEmpty()) {
                return Collections.emptySet();
            }

            List<Long> roleIds = userRoles.stream()
                    .map(UserRoleDO::getRoleId)
                    .collect(Collectors.toList());

            // 查询角色权限
            List<RolePermissionDO> rolePermissions = rolePermissionMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in("role_id", roleIds)
            );

            if (rolePermissions.isEmpty()) {
                return Collections.emptySet();
            }

            List<Long> permissionIds = rolePermissions.stream()
                    .map(RolePermissionDO::getPermissionId)
                    .distinct()
                    .collect(Collectors.toList());

            // 查询权限详情
            List<PermissionDO> permissions = permissionMapper.selectListByQuery(
                    QueryWrapper.create()
                            .in("id", permissionIds)
                            .eq("status", 1)
                            .eq("deleted", 0)
            );

            return permissions.stream()
                    .map(PermissionDO::getCode)
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            log.error("加载用户权限失败: userId={}", userId, e);
            return Collections.emptySet();
        }
    }

    @Override
    public String refreshToken(String refreshToken) {
        // Sa-Token 自带 Token 续期功能
        // 这里简单实现：检查当前登录状态并返回新 Token
        if (!StpUtil.isLogin()) {
            throw new UnauthorizedException("Token 已过期，请重新登录");
        }

        // 续期 Token
        StpUtil.renewTimeout(StpUtil.getTokenTimeout());

        // 返回当前 Token 值
        return StpUtil.getTokenValue();
    }

    @Override
    public void resetPassword(String username, String newPassword, String verifyCode) {
        // TODO: 实现验证码验证逻辑
        // 这里需要验证 verifyCode 是否有效

        // 查询用户
        UserDO user = userMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("username", username)
                        .eq("deleted", 0)
        );

        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 更新密码
        user.setPassword(passwordEncoder.encode(newPassword));
        userMapper.update(user);
    }

}
