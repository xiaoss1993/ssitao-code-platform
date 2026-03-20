package com.ssitao.code.modular.iam.domain.repository;

import com.ssitao.code.frame.aggregate.repository.AggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysUserAggregate;

/**
 * 用户仓储接口
 */
public interface SysUserRepository extends AggregateRepository<SysUserAggregate, Long> {

    /**
     * 根据登录名查询用户
     */
    SysUserAggregate findByLoginName(String loginName);

    /**
     * 根据手机号查询用户
     */
    SysUserAggregate findByPhonenumber(String phonenumber);

    /**
     * 根据邮箱查询用户
     */
    SysUserAggregate findByEmail(String email);

    /**
     * 检查登录名是否存在
     */
    boolean existsByLoginName(String loginName);

    /**
     * 检查手机号是否存在
     */
    boolean existsByPhonenumber(String phonenumber);
}
