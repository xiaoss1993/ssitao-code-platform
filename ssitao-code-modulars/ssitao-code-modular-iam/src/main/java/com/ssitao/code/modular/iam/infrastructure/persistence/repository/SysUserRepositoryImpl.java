package com.ssitao.code.modular.iam.infrastructure.persistence.repository;

import com.ssitao.code.frame.aggregate.repository.AbstractAggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysUserAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysUserRepository;
import com.ssitao.code.modular.iam.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户仓储实现
 */
@Repository
public class SysUserRepositoryImpl extends AbstractAggregateRepository<SysUserAggregate, Long> implements SysUserRepository {

    @Autowired
    private SysUserMapper userMapper;

    public SysUserRepositoryImpl() {
        super(SysUserAggregate.class);
    }

    @Override
    protected Collection<SysUserAggregate> doSave(Collection<SysUserAggregate> aggregates) {
        List<SysUserAggregate> saved = new ArrayList<>();
        for (SysUserAggregate aggregate : aggregates) {
            if (aggregate.isNew()) {
                userMapper.insertUser(toEntity(aggregate));
            } else {
                userMapper.updateUser(toEntity(aggregate));
            }
            saved.add(aggregate);
        }
        return saved;
    }

    @Override
    protected void doRemove(Collection<SysUserAggregate> aggregates) {
        for (SysUserAggregate aggregate : aggregates) {
            userMapper.deleteUserById(aggregate.getUserId());
        }
    }

    @Override
    protected SysUserAggregate doFindOne(Long id) {
        com.ssitao.code.common.core.domain.entity.SysUser user = userMapper.selectUserById(id);
        if (user == null) {
            return null;
        }
        return toAggregate(user);
    }

    @Override
    protected boolean doExists(Long id) {
        return userMapper.selectUserById(id) != null;
    }

    @Override
    protected List<Long> doFindAllIds() {
        List<com.ssitao.code.common.core.domain.entity.SysUser> users = userMapper.selectUserList(new com.ssitao.code.common.core.domain.entity.SysUser());
        List<Long> ids = new ArrayList<>();
        if (users != null) {
            for (com.ssitao.code.common.core.domain.entity.SysUser user : users) {
                ids.add(user.getUserId());
            }
        }
        return ids;
    }

    @Override
    protected List<SysUserAggregate> doFindAll(Collection<Long> ids) {
        List<SysUserAggregate> result = new ArrayList<>();
        for (Long id : ids) {
            SysUserAggregate aggregate = doFindOne(id);
            if (aggregate != null) {
                result.add(aggregate);
            }
        }
        return result;
    }

    @Override
    protected long doCount() {
        return userMapper.selectUserList(new com.ssitao.code.common.core.domain.entity.SysUser()).size();
    }

    @Override
    public SysUserAggregate findByLoginName(String loginName) {
        com.ssitao.code.common.core.domain.entity.SysUser user = userMapper.selectUserByLoginName(loginName);
        if (user == null) {
            return null;
        }
        return toAggregate(user);
    }

    @Override
    public SysUserAggregate findByPhonenumber(String phonenumber) {
        com.ssitao.code.common.core.domain.entity.SysUser user = userMapper.selectUserByPhoneNumber(phonenumber);
        if (user == null) {
            return null;
        }
        return toAggregate(user);
    }

    @Override
    public SysUserAggregate findByEmail(String email) {
        com.ssitao.code.common.core.domain.entity.SysUser user = userMapper.selectUserByEmail(email);
        if (user == null) {
            return null;
        }
        return toAggregate(user);
    }

    @Override
    public boolean existsByLoginName(String loginName) {
        return userMapper.checkLoginNameUnique(loginName) != null;
    }

    @Override
    public boolean existsByPhonenumber(String phonenumber) {
        return userMapper.checkPhoneUnique(phonenumber) != null;
    }

    /**
     * 将聚合根转换为实体
     */
    private com.ssitao.code.common.core.domain.entity.SysUser toEntity(SysUserAggregate aggregate) {
        com.ssitao.code.common.core.domain.entity.SysUser user = new com.ssitao.code.common.core.domain.entity.SysUser();
        user.setUserId(aggregate.getUserId());
        user.setDeptId(aggregate.getDeptId());
        user.setLoginName(aggregate.getLoginName());
        user.setUserName(aggregate.getUserName());
        user.setEmail(aggregate.getEmail());
        user.setPhonenumber(aggregate.getPhonenumber());
        user.setSex(aggregate.getSex());
        user.setAvatar(aggregate.getAvatar());
        user.setPassword(aggregate.getPassword());
        user.setSalt(aggregate.getSalt());
        user.setStatus(aggregate.getStatus());
        user.setDelFlag(aggregate.getDelFlag());
        user.setLoginIp(aggregate.getLoginIp());
        user.setLoginDate(aggregate.getLoginDate());
        user.setPwdUpdateDate(aggregate.getPwdUpdateDate());
        user.setCreateBy(aggregate.getCreateBy());
        user.setCreateTime(aggregate.getCreateTime());
        user.setUpdateBy(aggregate.getUpdateBy());
        user.setUpdateTime(aggregate.getUpdateTime());
        user.setRemark(aggregate.getRemark());
        user.setRoleIds(aggregate.getRoleIds());
        user.setPostIds(aggregate.getPostIds());
        return user;
    }

    /**
     * 将实体转换为聚合根
     */
    private SysUserAggregate toAggregate(com.ssitao.code.common.core.domain.entity.SysUser user) {
        SysUserAggregate aggregate = new SysUserAggregate();
        aggregate.setUserId(user.getUserId());
        aggregate.setDeptId(user.getDeptId());
        aggregate.setLoginName(user.getLoginName());
        aggregate.setUserName(user.getUserName());
        aggregate.setEmail(user.getEmail());
        aggregate.setPhonenumber(user.getPhonenumber());
        aggregate.setSex(user.getSex());
        aggregate.setAvatar(user.getAvatar());
        aggregate.setPassword(user.getPassword());
        aggregate.setSalt(user.getSalt());
        aggregate.setStatus(user.getStatus());
        aggregate.setDelFlag(user.getDelFlag());
        aggregate.setLoginIp(user.getLoginIp());
        aggregate.setLoginDate(user.getLoginDate());
        aggregate.setPwdUpdateDate(user.getPwdUpdateDate());
        aggregate.setCreateBy(user.getCreateBy());
        aggregate.setCreateTime(user.getCreateTime());
        aggregate.setUpdateBy(user.getUpdateBy());
        aggregate.setUpdateTime(user.getUpdateTime());
        aggregate.setRemark(user.getRemark());
        aggregate.setRoleIds(user.getRoleIds());
        aggregate.setPostIds(user.getPostIds());
        if (user.getDept() != null) {
            aggregate.setDeptName(user.getDept().getDeptName());
        }
        return aggregate;
    }
}
