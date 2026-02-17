package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreSlowsqllog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreSlowsqllogMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreSlowsqllogService;
import org.springframework.stereotype.Service;

/**
 * 慢sql日志 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class TbCoreSlowsqllogServiceImpl extends ServiceImpl<TbCoreSlowsqllogMapper, TbCoreSlowsqllog>  implements TbCoreSlowsqllogService{

}
