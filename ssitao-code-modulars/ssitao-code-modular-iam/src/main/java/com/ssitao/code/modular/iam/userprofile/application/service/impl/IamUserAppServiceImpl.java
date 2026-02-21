package com.ssitao.code.modular.iam.userprofile.application.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamAccountRoleDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.dal.mapper.IamAccountRoleMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamRoleMapper;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserCreateCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserQueryCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserUpdateCommand;
import com.ssitao.code.modular.iam.userprofile.application.service.IamUserAppService;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserDTO;
import com.ssitao.code.modular.iam.userprofile.domain.model.IamUser;
import com.ssitao.code.modular.iam.userprofile.domain.repository.IamUserRepository;
import com.ssitao.code.modular.iam.userprofile.infrastructure.converter.IamUserConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM用户应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamUserAppServiceImpl implements IamUserAppService {

    @Resource
    private IamUserRepository userRepository;

    @Resource
    private IamUserConverter userConverter;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private IamAccountRoleMapper accountRoleMapper;

    @Resource
    private IamRoleMapper roleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(IamUserCreateCommand command) {
        // 检查用户名是否已存在
        if (userRepository.existsByUsername(command.getUsername(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("用户名已存在: " + command.getUsername());
        }

        // 检查邮箱是否已存在
        if (command.getEmail() != null && userRepository.existsByEmail(command.getEmail(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("邮箱已被使用: " + command.getEmail());
        }

        // 检查手机号是否已存在
        if (command.getPhone() != null && userRepository.existsByPhone(command.getPhone(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("手机号已被使用: " + command.getPhone());
        }

        // 创建用户聚合根
        IamUser user = IamUser.create(
                command.getUsername(),
                command.getNickname() != null ? command.getNickname() : command.getUsername(),
                command.getTenantId()
        );

        // 设置ID
        user.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置其他属性
        user.setRealName(command.getRealName());
        user.setEmail(command.getEmail());
        user.setPhone(command.getPhone());
        user.setAvatar(command.getAvatar());
        user.setGender(command.getGender());
        user.setBirthday(command.getBirthday());
        user.setIdCard(command.getIdCard());

        if (command.getDeptId() != null) {
            user.setDeptId(command.getDeptId().toString());
        }
        if (command.getPostId() != null) {
            user.setPostId(command.getPostId().toString());
        }

        user.setRemark(command.getRemark());

        String id = userRepository.save(user);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchCreateUsers(List<IamUserCreateCommand> commands) {
        return commands.stream()
                .map(this::createUser)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(IamUserUpdateCommand command) {
        IamUser user = userRepository.findById(command.getId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + command.getId()));

        // 检查邮箱是否已被其他用户使用
        if (command.getEmail() != null && userRepository.existsByEmail(command.getEmail(), command.getTenantId(), command.getId().toString())) {
            throw new IllegalArgumentException("邮箱已被其他用户使用: " + command.getEmail());
        }

        // 检查手机号是否已被其他用户使用
        if (command.getPhone() != null && userRepository.existsByPhone(command.getPhone(), command.getTenantId(), command.getId().toString())) {
            throw new IllegalArgumentException("手机号已被其他用户使用: " + command.getPhone());
        }

        // 更新属性
        if (command.getNickname() != null) {
            user.setNickname(command.getNickname());
        }
        user.setRealName(command.getRealName());
        user.setEmail(command.getEmail());
        user.setPhone(command.getPhone());
        user.setAvatar(command.getAvatar());
        user.setGender(command.getGender());
        user.setBirthday(command.getBirthday());
        user.setIdCard(command.getIdCard());

        if (command.getDeptId() != null) {
            user.setDeptId(command.getDeptId().toString());
        }
        if (command.getPostId() != null) {
            user.setPostId(command.getPostId().toString());
        }

        user.setRemark(command.getRemark());
        user.setUpdateTime(LocalDateTime.now());

        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id, String tenantId) {
        userRepository.deleteById(id.toString(), tenantId);
    }

    @Override
    public IamUserDTO getUserById(Long id, String tenantId) {
        IamUser user = userRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
        return userConverter.toDTO(user);
    }

    @Override
    public IamUserDTO getUserByUsername(String username, String tenantId) {
        IamUser user = userRepository.findByUsername(username, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + username));
        return userConverter.toDTO(user);
    }

    @Override
    public List<IamUserDTO> listUsers(IamUserQueryCommand command) {
        List<IamUser> users = userRepository.findPage(
                command.getTenantId(),
                command.getKeyword(),
                command.getDeptId() != null ? command.getDeptId().toString() : null,
                0,
                1000
        );
        return userConverter.toDTOList(users);
    }

    @Override
    public List<IamUserDTO> pageUsers(IamUserQueryCommand command, int page, int size) {
        List<IamUser> users = userRepository.findPage(
                command.getTenantId(),
                command.getKeyword(),
                command.getDeptId() != null ? command.getDeptId().toString() : null,
                page,
                size
        );
        return userConverter.toDTOList(users);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableUser(Long id, String tenantId) {
        IamUser user = userRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
        user.enable();
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableUser(Long id, String tenantId) {
        IamUser user = userRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
        user.disable();
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void lockUser(Long id, String tenantId) {
        IamUser user = userRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
        user.lock();
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unlockUser(Long id, String tenantId) {
        IamUser user = userRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
        user.unlock();
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(Long id, String newPassword, String tenantId) {
        IamUser user = userRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
        // 密码重置逻辑通常在账号层面处理，这里更新用户状态
        user.passwordModified();
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignDepartment(Long id, Long deptId, String deptName, String tenantId) {
        IamUser user = userRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
        user.updateDepartment(deptId.toString(), deptName);
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignPost(Long id, Long postId, String postName, String tenantId) {
        IamUser user = userRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
        user.updatePost(postId.toString(), postName);
        userRepository.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRoles(Long id, List<Long> roleIds, String tenantId) {
        IamUser user = userRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));

        // 删除用户原有的角色关联
        QueryWrapper deleteQuery = QueryWrapper.create()
                .eq("accountrole_account_id", id.toString());
        if (tenantId != null && !tenantId.isEmpty()) {
            deleteQuery.eq("sy_tenant_id", tenantId);
        }
        accountRoleMapper.deleteByQuery(deleteQuery);

        // 分配新的角色
        if (roleIds != null && !roleIds.isEmpty()) {
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            for (Long roleId : roleIds) {
                // 查询角色信息
                IamRoleDO roleDO = roleMapper.selectOneById(roleId.toString());
                if (roleDO == null) {
                    continue;
                }

                IamAccountRoleDO accountRole = new IamAccountRoleDO();
                accountRole.setTbIamAccountroleId(UUID.randomUUID().toString().replace("-", ""));
                accountRole.setAccountroleAccountId(id.toString());
                accountRole.setAccountroleRoleId(roleId.toString());
                accountRole.setAccountroleRoleName(roleDO.getRoleCode());
                accountRole.setSyCreatetime(now);
                accountRole.setSyStatus("1");
                accountRole.setSyTenantId(tenantId);
                accountRole.setSyOrderindex("0");
                accountRole.setAccountroleMainCode("1"); // 默认为主角色

                accountRoleMapper.insert(accountRole);
            }
        }
    }
}
