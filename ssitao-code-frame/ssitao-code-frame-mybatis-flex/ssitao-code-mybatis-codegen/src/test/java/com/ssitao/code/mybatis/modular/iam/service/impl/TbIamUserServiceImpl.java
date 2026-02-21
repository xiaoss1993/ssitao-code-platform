package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamUser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamUserMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamUserService;
import org.springframework.stereotype.Service;

/**
 * 部门人员 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamUserServiceImpl extends ServiceImpl<TbIamUserMapper, TbIamUser>  implements TbIamUserService{

}
