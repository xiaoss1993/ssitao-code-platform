package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamRole;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamRoleMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamRoleServiceImpl extends ServiceImpl<TbIamRoleMapper, TbIamRole>  implements TbIamRoleService{

}
