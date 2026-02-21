package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SRole;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SRoleMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SRoleServiceImpl extends ServiceImpl<SRoleMapper, SRole>  implements SRoleService{

}
