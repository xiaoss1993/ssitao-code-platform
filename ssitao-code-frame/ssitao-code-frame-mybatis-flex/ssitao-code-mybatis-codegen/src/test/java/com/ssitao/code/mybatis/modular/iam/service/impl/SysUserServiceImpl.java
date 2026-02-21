package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SysUser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SysUserMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>  implements SysUserService{

}
