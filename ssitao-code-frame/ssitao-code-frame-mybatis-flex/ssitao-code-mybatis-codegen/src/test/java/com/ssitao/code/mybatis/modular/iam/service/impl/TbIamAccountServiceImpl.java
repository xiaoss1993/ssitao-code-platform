package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamAccount;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamAccountMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamAccountService;
import org.springframework.stereotype.Service;

/**
 * 账号管理 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class TbIamAccountServiceImpl extends ServiceImpl<TbIamAccountMapper, TbIamAccount>  implements TbIamAccountService{

}
