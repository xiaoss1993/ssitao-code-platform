package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SUser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SUserMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SUserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SUserServiceImpl extends ServiceImpl<SUserMapper, SUser>  implements SUserService{

}
