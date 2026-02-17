package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamLoginlog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamLoginlogMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamLoginlogService;
import org.springframework.stereotype.Service;

/**
 * 新登录日志 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class TbIamLoginlogServiceImpl extends ServiceImpl<TbIamLoginlogMapper, TbIamLoginlog>  implements TbIamLoginlogService{

}
